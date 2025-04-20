package springboot.project.basic.projetospring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record RequestClientDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email) {
}
