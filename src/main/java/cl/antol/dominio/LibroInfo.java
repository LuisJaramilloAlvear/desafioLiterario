package cl.antol.dominio;

import jakarta.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "libros")
public class LibroInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String tituloUnico;
    @ManyToOne(fetch = FetchType.EAGER)
    private AutorInfo autorInfo;
    @Enumerated(EnumType.STRING)
    private List<IdiomaType> idiomasDisponibles;
    private Long totalDescargas;

    public LibroInfo() {}

    public LibroInfo(Long totalDescargas, List<IdiomaType> idiomasDisponibles, AutorInfo autorInfo, String tituloUnico) {
        this.totalDescargas = totalDescargas;
        this.idiomasDisponibles = idiomasDisponibles;
        this.autorInfo = autorInfo;
        this.tituloUnico = tituloUnico;
    }

    public LibroInfo(LibroData libroData) {
        this.tituloUnico = libroData.titulo();
        this.idiomasDisponibles = Collections.singletonList(
                IdiomaType.fromCodigo(libroData.idiomas().get(0).toLowerCase())
        );
        this.totalDescargas = libroData.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloUnico() {
        return tituloUnico;
    }

    public void setTituloUnico(String tituloUnico) {
        this.tituloUnico = tituloUnico;
    }

    public AutorInfo getAutorInfo() {
        return autorInfo;
    }

    public void setAutorInfo(AutorInfo autorInfo) {
        this.autorInfo = autorInfo;
    }

    public List<IdiomaType> getIdiomasDisponibles() {
        return idiomasDisponibles;
    }

    public void setIdiomasDisponibles(List<IdiomaType> idiomasDisponibles) {
        this.idiomasDisponibles = idiomasDisponibles;
    }

    public Long getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Long totalDescargas) {
        this.totalDescargas = totalDescargas;
    }
}
