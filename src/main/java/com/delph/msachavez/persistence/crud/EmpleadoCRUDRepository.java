package com.delph.msachavez.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.delph.msachavez.persistence.entity.EmpleadosEntity;

public interface EmpleadoCRUDRepository extends CrudRepository<EmpleadosEntity, Integer> {

}
