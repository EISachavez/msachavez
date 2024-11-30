package com.delph.msachavez.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.domain.repository.EmpleadoDTORepository;

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

}
