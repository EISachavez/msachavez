package com.delph.msachavez.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.domain.repository.EmpleadoDTORepository;
import com.delph.msachavez.persistence.crud.EmpleadoCRUDRepository;
import com.delph.msachavez.persistence.entity.EmpleadosEntity;
import com.delph.msachavez.persistence.mapper.EmpleadoMapper;

@Repository
public class EmpleadoRepository implements EmpleadoDTORepository {

    @Autowired
    private EmpleadoCRUDRepository empleadoCRUDRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Override
    public List<EmpleadoDTO> getAll() {
        List<EmpleadosEntity> empleados = (List<EmpleadosEntity>) empleadoCRUDRepository.findAll();
        return empleadoMapper.toEmpleadosDTO(empleados);
    }

    @Override
    public Optional<EmpleadoDTO> getEmpleado(Integer codigo) {
        return empleadoCRUDRepository.findById(codigo).map(empleado -> empleadoMapper.toEmpleadoDTO(empleado));
    }

}
