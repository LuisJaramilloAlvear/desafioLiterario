package cl.antol.persistencia;

import cl.antol.dominio.LibroInfo;
import cl.antol.dominio.IdiomaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioLibro extends JpaRepository<LibroInfo, Long> {
    @Query("SELECT l FROM LibroInfo l")
    List<LibroInfo> getLibros();

    List<LibroInfo> findByIdiomasDisponibles(List<IdiomaType> idiomas);
}

