package com.delph.msachavez.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.domain.service.EmpleadoService;
import com.delph.msachavez.persistence.entity.EmpleadosEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/empleado")
public class EmpleadosController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        System.out.println("llega a get");
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable Integer codigo) {
        return empleadoService.getEmpleado(codigo).map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<EmpleadoDTO> createEmpleado(@Valid @RequestBody EmpleadosEntity empleado) {
        EmpleadoDTO empCreado = empleadoService.saveEmpleado(empleado);
        return new ResponseEntity<>(empCreado, HttpStatus.CREATED);
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<EmpleadoDTO> deleteEmpleado(@PathVariable Integer codigo) {
        empleadoService.deleteEmpleado(codigo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@RequestBody EmpleadosEntity empleado) {
        EmpleadoDTO empCreado = empleadoService.updateEmpleado(empleado);
        return new ResponseEntity<>(empCreado, HttpStatus.OK);
    }

    @PostMapping("/getByCargos")
    public ResponseEntity<List<EmpleadoDTO>> getEmpleadoByCargo(@RequestBody EmpleadosEntity empleado) {
        String cargo = empleado.getCargo();
        return new ResponseEntity<>(empleadoService.getEmpleadoByCargo(cargo), HttpStatus.OK);
    }
}
