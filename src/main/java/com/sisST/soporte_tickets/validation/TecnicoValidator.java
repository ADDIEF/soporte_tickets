package com.sisST.soporte_tickets.validation;

import com.sisST.soporte_tickets.dto.TecnicoDTO;
import org.springframework.lang.NonNull;          // ← nuevo
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;

@Component
public class TecnicoValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return TecnicoDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {        TecnicoDTO tec = (TecnicoDTO) target;

        if (tec.getCategoriasSoportadas() != null) {
            long duplicados = tec.getCategoriasSoportadas().stream()
                                  .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                                  .values().stream().filter(v -> v > 1).count();
            if (duplicados > 0) {
                errors.rejectValue("categoriasSoportadas", "tec.categorias.duplicadas",
                                   "No repitas categorías soportadas");
            }
        }
    }
}
