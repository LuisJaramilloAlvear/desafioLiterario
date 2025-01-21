package cl.antol.dominio;

public enum IdiomaType {
    ES("Español"),
    EN("Inglés"),
    FR("Francés"),
    PR("Portugués"),
    DE("Alemán");

    private final String descripcion;

    IdiomaType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

