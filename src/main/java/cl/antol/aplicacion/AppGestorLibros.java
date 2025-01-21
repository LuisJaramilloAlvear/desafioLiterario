package cl.antol.aplicacion;

import cl.antol.persistencia.RepositorioAutor;
import cl.antol.persistencia.RepositorioLibro;
import cl.antol.consola.InterfazPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cl.antol")
public class AppGestorLibros implements CommandLineRunner {

    @Autowired
    private RepositorioLibro repositorioLibro;

    @Autowired
    private RepositorioAutor repositorioAutor;

    public static void main(String[] args) {
        SpringApplication.run(AppGestorLibros.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InterfazPrincipal interfaz = new InterfazPrincipal(repositorioLibro, repositorioAutor);
        interfaz.menu();
    }
}

