package com.sisST.soporte_tickets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* dto/UsuarioDTO.java */
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor

public class UsuarioDTO {
    private Long id;

    @NotBlank @Size(max = 60)
    private String nombre;

    @NotBlank @Size(max = 60)
    private String apellido;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String rol;  // ADMIN | TECNICO | USUARIO
}
