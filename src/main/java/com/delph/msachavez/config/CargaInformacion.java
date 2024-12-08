package com.delph.msachavez.config;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.delph.msachavez.domain.service.FakerService;
import com.delph.msachavez.persistence.EmpleadoRepository;
import com.delph.msachavez.persistence.entity.EmpleadosEntity;

@Component
public class CargaInformacion implements CommandLineRunner {

    private final EmpleadoRepository empleadoRepository;
    private final FakerService       fakerServ;

    @Value("${cantidad.data.faker}")
    private String cantidadData;

    public CargaInformacion(EmpleadoRepository empleadoRepository, FakerService fakerServ) {
        this.empleadoRepository = empleadoRepository;
        this.fakerServ          = fakerServ;
    }

    public void run(String... args) throws Exception {
        String cargos[]        = { "PO", "PM", "Developer I", "Developer II", "Analyst", "Architech", "Scrum" };
        int    numeroRegistros = (cantidadData != null) ? Integer.parseInt(cantidadData) : 0;

        for(int i = 0; i < numeroRegistros; i++) {
            EmpleadosEntity empleado = new EmpleadosEntity();

            int random = new Random().nextInt(cargos.length);

            empleado.setUsuario(fakerServ.fakeUsuario());
            empleado.setNombre(fakerServ.fakeNombre());
            empleado.setTelefono(fakerServ.fakeTelefono());
            empleado.setCorreo(fakerServ.fakeCorreo());
            empleado.setDireccion(fakerServ.fakeDireccion());
            empleado.setCargo(cargos[random]);
            empleado.setClave(fakerServ.fakeClave());
            empleado.setFechaCreacion(new Date());
            empleado.setFechaModificacion(new Date());

            empleadoRepository.saveEmpleado(empleado);
        }

    }

}
