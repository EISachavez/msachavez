package com.delph.msachavez.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delph.msachavez.domain.dto.EmpleadoDTO;
import com.delph.msachavez.domain.service.EmpleadoService;

@RestController
@RequestMapping("/api/v1/empleado")
public class EmpleadosController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable Integer codigo) {
        return empleadoService.getEmpleado(codigo).map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
