package com.delph.msachavez.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.domain.repository.EmpleadoDTORepository;
import com.delph.msachavez.persistence.entity.EmpleadosEntity;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDTORepository empleadoDTORepository;

    public List<EmpleadoDTO> getAll() {
        return empleadoDTORepository.getAll();
    }

    public Optional<EmpleadoDTO> getEmpleado(Integer codigo) {
        return empleadoDTORepository.getEmpleado(codigo);
    }

    public EmpleadoDTO saveEmpleado(EmpleadosEntity empleado) {
        return empleadoDTORepository.saveEmpleado(empleado);
    }

    public void deleteEmpleado(Integer codigo) {
        empleadoDTORepository.deleteEmpleado(codigo);
    }

    public EmpleadoDTO updateEmpleado(EmpleadosEntity empleado) {
        return empleadoDTORepository.updateEmpleado(empleado);
    }

    public List<EmpleadoDTO> getEmpleadoByCargo(String cargo) {
        return empleadoDTORepository.getEmpleadoByCargo(cargo);
    }
}
