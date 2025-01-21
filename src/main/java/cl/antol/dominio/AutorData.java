package cl.antol.dominio;

import com.google.gson.annotations.SerializedName;

public record AutorData(
        @SerializedName("name") String nombre,
        @SerializedName("birth_year") Integer nacimiento,
        @SerializedName("death_year") Integer fallecimiento
) {}

