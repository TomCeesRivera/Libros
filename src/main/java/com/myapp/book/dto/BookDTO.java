package com.myapp.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    @NotBlank(message = "El título es un campo obligatorio")
    private String title;

    @NotBlank(message = "El autor es un campo obligatorio")
    private String author;

    @NotBlank(message = "El ISBN es un campo obligatorio")
    private String isbn;

    @NotNull(message = "El año de publicación es un campo obligatorio")
    @Min(value = 1450, message = "El año de publicación debe ser igual o superior a 1450")
    private Integer publishedYear;

    @NotNull(message = "El precio es un campo obligatorio")
    @Positive(message = "El precio introducido debe ser mayor a 0")
    private Double price;
}
