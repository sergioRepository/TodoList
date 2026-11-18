package cp.campus.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record  CategoriaRequest(
    @NotBlank
    @Size(max = 150)
    String nombre

) {
}
