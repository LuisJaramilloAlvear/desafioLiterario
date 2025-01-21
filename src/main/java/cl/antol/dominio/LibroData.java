package cl.antol.dominio;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record LibroData(
        @SerializedName("title") String titulo,
        @SerializedName("authors") List<AutorData> autores,
        @SerializedName("languages") List<String> idiomas,
        @SerializedName("download_count") Long descargas
) {}
