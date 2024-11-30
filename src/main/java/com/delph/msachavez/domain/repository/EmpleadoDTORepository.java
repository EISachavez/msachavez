package com.delph.msachavez.domain.repository;

import java.util.List;
import java.util.Optional;

import com.delph.msachavez.domain.dto.EmpleadoDTO;

public interface EmpleadoDTORepository {

    List<EmpleadoDTO> getAll();
    Optional<EmpleadoDTO> getEmpleado(Integer codigo);

}
