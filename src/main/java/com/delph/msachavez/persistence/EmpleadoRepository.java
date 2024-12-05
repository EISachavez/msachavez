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

    @Override
    public EmpleadoDTO saveEmpleado(EmpleadosEntity empleado) {
        return empleadoMapper.toEmpleadoDTO(empleadoCRUDRepository.save(empleado));
    }

    @Override
    public void deleteEmpleado(Integer codigo) {
        empleadoCRUDRepository.deleteById(codigo);
    }

    @Override
    public EmpleadoDTO updateEmpleado(EmpleadosEntity empleado) {
        Optional<EmpleadosEntity> existeEmpleado = empleadoCRUDRepository.findById(empleado.getCodigo());

        EmpleadosEntity existente = existeEmpleado.get();
        if (empleado.getUsuario() == null) {
            empleado.setUsuario(existente.getUsuario());
        }

        if (empleado.getNombre() == null) {
            empleado.setNombre(existente.getNombre());
        }

        if (empleado.getTelefono() == null) {
            empleado.setTelefono(existente.getTelefono());
        }

        if (empleado.getCorreo() == null) {
            empleado.setCorreo(existente.getCorreo());
        }

        if (empleado.getDireccion() == null) {
            empleado.setDireccion(existente.getDireccion());
        }

        if (empleado.getCargo() == null) {
            empleado.setCargo(existente.getCargo());
        }

        if (empleado.getClave() == null) {
            empleado.setClave(existente.getClave());
        }

        return empleadoMapper.toEmpleadoDTO(empleadoCRUDRepository.save(empleado));
    }

    @Override
    public List<EmpleadoDTO> getEmpleadoByCargo(String cargo) {
        List<EmpleadosEntity> empleados = (List<EmpleadosEntity>) empleadoCRUDRepository.findAllByCargo(cargo);
        return empleadoMapper.toEmpleadosDTO(empleados);
    }
}
