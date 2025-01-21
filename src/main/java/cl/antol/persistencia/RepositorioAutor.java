package cl.antol.persistencia;

import cl.antol.dominio.AutorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioAutor extends JpaRepository<AutorInfo, String> {
    Optional<AutorInfo> findByNombreCompleto(String nombreCompleto);

    @Query("SELECT a FROM AutorInfo a JOIN a.librosEscritos l")
    List<AutorInfo> getAutores();

    @Query("SELECT a FROM AutorInfo a WHERE :anio BETWEEN a.nacimiento AND a.fallecimiento")
    List<AutorInfo> getAutoresVivos(Integer anio);
}

