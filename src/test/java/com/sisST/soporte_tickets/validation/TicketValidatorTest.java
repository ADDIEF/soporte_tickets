package com.sisST.soporte_tickets.validation;

import com.sisST.soporte_tickets.dto.TicketDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TicketValidatorTest {

    @Autowired
    TicketValidator ticketValidator;     // ← bean específico

    @Test
    void tecnicoObligatorioSiResuelto() {
        TicketDTO dto = TicketDTO.builder()
                .titulo("No puedo imprimir")
                .descripcion("Error E13")
                .categoriaId(2L)
                .urgencia(TicketDTO.Urgencia.ALTA)
                .estado(TicketDTO.Estado.RESUELTO)   // sin técnico
                .build();

        Errors errors = new BeanPropertyBindingResult(dto, "ticketDTO");
        ticketValidator.validate(dto, errors);       // ← usamos tu validador

        assertThat(errors.hasFieldErrors("tecnicoAsignadoId")).isTrue();
    }
}
