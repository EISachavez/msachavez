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
import com.delph.msachavez.web.exception.NotFound;
import com.delph.msachavez.web.exception.NotProcessable;

@Repository
public class EmpleadoRepository implements EmpleadoDTORepository {

    @Autowired
    private EmpleadoCRUDRepository empleadoCRUDRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Override
    public List<EmpleadoDTO> getAll() {
        List<EmpleadosEntity> empleados = (List<EmpleadosEntity>) empleadoCRUDRepository.findAll();
        if (empleados == null || empleados.isEmpty()) {
            throw new NotFound("No se han encontrado empleados en la base de datos");
        }

        return empleadoMapper.toEmpleadosDTO(empleados);
    }

    @Override
    public Optional<EmpleadoDTO> getEmpleado(Integer codigo) {
        Optional<EmpleadosEntity> empleadoEntity = empleadoCRUDRepository.findById(codigo);
        if (empleadoEntity == null || empleadoEntity.isEmpty()) {
            throw new NotFound("No se han encontrado el empleado con código " + codigo);
        }

        return empleadoEntity.map(empleado -> empleadoMapper.toEmpleadoDTO(empleado));
    }

    @Override
    public EmpleadoDTO saveEmpleado(EmpleadosEntity empleado) {
        if (empleado.getCodigo() != null) {
            throw new NotProcessable("En la creación de un nuevo empleado no se debe enviar el valor del campo \"codigo\" si desea actualizar un empleado use la operación de actualización");
        }

        return empleadoMapper.toEmpleadoDTO(empleadoCRUDRepository.save(empleado));
    }

    @Override
    public void deleteEmpleado(Integer codigo) {
        if (codigo == null) {
            throw new NotProcessable("Es necesario el código del empleado para realizar la eliminación.");
        }

        Optional<EmpleadosEntity> empleado = empleadoCRUDRepository.findById(codigo);
        if (empleado == null || empleado.isEmpty()) {
            throw new NotFound("No se han encontrado el empleado con código " + codigo);
        }

        empleadoCRUDRepository.deleteById(codigo);
    }

    @Override
    public EmpleadoDTO updateEmpleado(EmpleadosEntity empleado) {
        if (empleado.getCodigo() == null) {
            throw new NotProcessable("No se ha recibido el código del empleado a actualizar");
        }

        Optional<EmpleadosEntity> existeEmpleado = empleadoCRUDRepository.findById(empleado.getCodigo());

        if (existeEmpleado == null || existeEmpleado.isEmpty()) {
            throw new NotFound("No se han encontrado el empleado con código " + empleado.getCodigo());
        }

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

        try {
            EmpleadosEntity entity = empleadoCRUDRepository.save(empleado);
            return empleadoMapper.toEmpleadoDTO(entity);
        } catch(Exception e) {
            throw new NotProcessable("Ha ocurrido un error, por favor verifique los parámetros de la petición.");
        }

    }

    @Override
    public List<EmpleadoDTO> getEmpleadoByCargo(String cargo) {
        List<EmpleadosEntity> empleados = (List<EmpleadosEntity>) empleadoCRUDRepository.findAllByCargo(cargo);
        if (empleados == null || empleados.isEmpty()) {
            throw new NotFound("No se han encontrado empleados con cargo " + cargo);
        }

        return empleadoMapper.toEmpleadosDTO(empleados);
    }
}
