package cl.antol.consola;

import cl.antol.dominio.*;
import cl.antol.persistencia.RepositorioAutor;
import cl.antol.persistencia.RepositorioLibro;
import cl.antol.dominio.ConsumidorAPI;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class InterfazPrincipal {
    private final Scanner sc = new Scanner(System.in);
    private final Gson gson = new Gson();
    private final RepositorioLibro repositorioLibro;
    private final RepositorioAutor repositorioAutor;

    private String opcion;

    @Autowired
    public InterfazPrincipal(RepositorioLibro repositorioLibro, RepositorioAutor repositorioAutor) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor = repositorioAutor;
    }

    public void menu() {
        System.out.println("""
              ************* Menú Principal *******************
                     
                1. Buscar libro por título
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                
                0. Salir
 
                """);
        System.out.print("Ingrese una opción: ");
        opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> buscarLibroPorTitulo();
            case "2" -> listarLibros();
            case "3" -> listarAutores();
            case "4" -> listarAutoresVivos();
            case "5" -> listarLibrosPorIdioma();
            case "0" -> System.out.println("Saliendo...");
            default -> {
                System.out.println("Opción inválida");
                menu();
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String nombreLibro = sc.nextLine();

        DatosAPI datos = gson.fromJson(ConsumidorAPI.obtenerDatos(nombreLibro), DatosAPI.class);
        Optional<DatosInternos> resultado = datos.resultado()
                .stream()
                .findFirst();

        if (resultado.isPresent()) {
            DatosInternos resultadoLibro = resultado.get();
            System.out.println("************** Libro Encontrado **************");
            System.out.printf("""
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Número de descargas: %d
                    """,
                    resultadoLibro.titulo(),
                    resultadoLibro.autor().get(0).nombre(),
                    resultadoLibro.idiomas().toString().substring(1, 3),
                    resultadoLibro.numeroDescargas());

            LibroInfo libro = new LibroInfo(resultadoLibro);
            DatosInternos autorDatos = resultadoLibro.autor().get(0);
            AutorInfo autor = new AutorInfo(autorDatos.nombre(), autorDatos.anioNacimiento(), autorDatos.anioDefuncion());
            Optional<AutorInfo> autorExistente = repositorioAutor.findByNombreCompleto(autor.getNombreCompleto());

            if (autorExistente.isPresent()) libro.setAutor(autorExistente.get());
            else {
                repositorioAutor.save(autor);
                libro.setAutor(autor);
            }

            try {
                repositorioLibro.save(libro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Libro ya existente en la base de datos");
            }
        } else {
            System.out.println("Libro no encontrado");
        }

        menu();
    }

    private void listarLibros() {
        List<LibroInfo> libros = repositorioLibro.getLibros();
        if (!libros.isEmpty()) {
            System.out.println("************** Libros **************");
            libros.forEach(libro -> System.out.printf("""
                            Título: %s
                            Autor: %s
                            Idiomas: %s
                            **********************************
                            """,
                    libro.getTitulo(),
                    libro.getAutor().getNombreCompleto(),
                    libro.getIdiomasDisponibles()));
        } else {
            System.out.println("Sin resultados");
        }
        menu();
    }

    private void listarAutores() {
        List<AutorInfo> autores = repositorioAutor.getAutores();
        if (!autores.isEmpty()) {
            System.out.println("************** Autores **************");
            autores.forEach(autor -> System.out.printf("""
                            Nombre: %s
                            Año de nacimiento: %d
                            Año de fallecimiento: %d
                            Libros: %s
                            ***********************************
                            """,
                    autor.getNombreCompleto(),
                    autor.getNacimiento(),
                    autor.getFallecimiento(),
                    autor.getLibrosEscritos().stream()
                            .map(LibroInfo::getTitulo)
                            .collect(Collectors.joining(", "))));
        } else {
            System.out.println("Sin resultados");
        }
        menu();
    }

    private void listarAutoresVivos() {
        System.out.print("Ingrese el año que quieres consultar: ");
        int anio = sc.nextInt();
        sc.nextLine(); // Consumir el salto de línea

        List<AutorInfo> autoresVivos = repositorioAutor.getAutoresVivos(anio);
        if (!autoresVivos.isEmpty()) {
            System.out.println("************** Autores **************");
            autoresVivos.forEach(autor -> System.out.printf("Nombre: %s\n", autor.getNombreCompleto()));
            System.out.println("***********************************");
        } else {
            System.out.println("Sin resultados");
        }
        menu();
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                ES = Español
                EN = Inglés
                FR = Francés
                PR = Portugués
                DE = Alemán
                """);
        System.out.print("Ingrese el idioma con el que quiere filtrar: ");
        String idioma = sc.nextLine().toUpperCase();

        try {
            List<LibroInfo> librosFiltrados = repositorioLibro.findByIdiomasDisponibles(Collections.singletonList(IdiomaType.valueOf(idioma)));
            if (!librosFiltrados.isEmpty()) {
                System.out.println("************** Libros **************");
                librosFiltrados.forEach(libro -> System.out.println(libro.getTitulo()));
                System.out.println("**********************************");
            } else {
                System.out.println("Sin resultados");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma no válido");
        }

        menu();
    }
}

