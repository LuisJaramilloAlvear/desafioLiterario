package cl.antol.dominio;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record DatosAPI(
        Integer total,
        @SerializedName("results") List<LibroData> libros
) {}

