package com.sisST.soporte_tickets.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/* dto/TecnicoDTO.java */
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor

@Valid                               // activa el validador de objeto completo
public class TecnicoDTO {
    private Long id;

    @NotBlank @Size(max = 60)
    private String nombre;

    @NotBlank @Size(max = 60)
    private String apellido;

    @Email @NotBlank
    private String email;

    @Size(max = 100)
    private String especialidad;

    @NotEmpty(message = "Debe asignar al menos una categoría")
    private List<Long> categoriasSoportadas;
}
