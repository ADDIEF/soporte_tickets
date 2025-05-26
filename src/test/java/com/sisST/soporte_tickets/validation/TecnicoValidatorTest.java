package com.sisST.soporte_tickets.validation;

import com.sisST.soporte_tickets.dto.TecnicoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TecnicoValidatorTest {

    @Autowired
    TecnicoValidator tecnicoValidator;   // ← bean específico

    @Test
    void categoriasDuplicadasDebenFallar() {
        TecnicoDTO tec = TecnicoDTO.builder()
                .nombre("Jane Tech")
                .email("jane@example.com")
                .categoriasSoportadas(List.of(1L, 1L, 2L)) // duplicado
                .build();

        Errors errors = new BeanPropertyBindingResult(tec, "tecnicoDTO");
        tecnicoValidator.validate(tec, errors);      // ← usamos tu validador

        assertThat(errors.hasFieldErrors("categoriasSoportadas")).isTrue();
    }
}
