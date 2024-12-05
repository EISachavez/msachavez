package com.delph.msachavez.domain.repository;

import java.util.List;
import java.util.Optional;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.persistence.entity.EmpleadosEntity;

public interface EmpleadoDTORepository {

    List<EmpleadoDTO> getAll();
    Optional<EmpleadoDTO> getEmpleado(Integer codigo);
    EmpleadoDTO saveEmpleado(EmpleadosEntity empleado);
    void deleteEmpleado(Integer codigo);
    EmpleadoDTO updateEmpleado(EmpleadosEntity empleado);
    List<EmpleadoDTO> getEmpleadoByCargo(String cargo);
}
