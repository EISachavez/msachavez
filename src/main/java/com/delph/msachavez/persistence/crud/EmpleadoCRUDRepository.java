package com.delph.msachavez.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.delph.msachavez.persistence.entity.EmpleadosEntity;

public interface EmpleadoCRUDRepository extends CrudRepository<EmpleadosEntity, Integer> {

    @Query("SELECT emp FROM EmpleadosEntity emp WHERE emp.cargo = ?1")
    List<EmpleadosEntity> findAllByCargo(@Param("cargo") String cargo);
}
