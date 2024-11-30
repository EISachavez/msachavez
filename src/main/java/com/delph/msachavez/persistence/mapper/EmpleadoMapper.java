package com.delph.msachavez.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.persistence.entity.EmpleadosEntity;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mappings({ @Mapping(source = "codigo", target = "codigo"), @Mapping(source = "usuario", target = "usuario"), @Mapping(source = "nombre", target = "nombre"), @Mapping(source = "telefono", target = "telefono"), @Mapping(source = "correo", target = "correo"), @Mapping(source = "direccion", target = "direccion"), @Mapping(source = "cargo", target = "cargo"), @Mapping(source = "fechaCreacion", target = "fechaCreacion"), @Mapping(source = "fechaModificacion", target = "fechaModificacion") })
    EmpleadoDTO toEmpleadoDTO(EmpleadosEntity empleado);
    List<EmpleadoDTO> toEmpleadosDTO(List<EmpleadosEntity> empleados);

    @InheritInverseConfiguration
    EmpleadosEntity toEmpleadoEntity(EmpleadoDTO empleado);

}
