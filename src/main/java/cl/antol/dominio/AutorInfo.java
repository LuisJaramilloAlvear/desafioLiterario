package cl.antol.dominio;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class AutorInfo {
    @Id
    private String nombreCompleto;
    private Integer nacimiento;
    private Integer fallecimiento;
    @OneToMany(mappedBy = "autorInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LibroInfo> librosEscritos;

    public AutorInfo() {}

    public AutorInfo(String nombreCompleto, Integer nacimiento, Integer fallecimiento) {
        this.nombreCompleto = nombreCompleto;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<LibroInfo> getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(List<LibroInfo> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }
}
