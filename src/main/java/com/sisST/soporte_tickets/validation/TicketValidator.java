package com.sisST.soporte_tickets.validation;

import com.sisST.soporte_tickets.dto.TicketDTO;
import org.springframework.lang.NonNull;          // ← nuevo
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.EnumSet;

@Component
public class TicketValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return TicketDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {        TicketDTO t = (TicketDTO) target;

        if (EnumSet.of(TicketDTO.Estado.RESUELTO, TicketDTO.Estado.CERRADO).contains(t.getEstado())
                && t.getTecnicoAsignadoId() == null) {
            errors.rejectValue("tecnicoAsignadoId", "ticket.tecnico.requerido",
                               "Debe indicar el técnico que lo resolvió");
        }

        if (t.getEstado() == TicketDTO.Estado.ABIERTO && t.getTecnicoAsignadoId() != null) {
            errors.rejectValue("tecnicoAsignadoId", "ticket.tecnico.sobra",
                               "No asigne técnico cuando el ticket está ABIERTO");
        }
    }
}
