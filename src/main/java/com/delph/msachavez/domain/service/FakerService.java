package com.delph.msachavez.domain.service;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class FakerService {
    private Faker faker;

    public FakerService() {
        this.faker = new Faker();
    }

    public String fakeUsuario() {
        return faker.name().username();
    }

    public String fakeNombre() {
        return faker.name().fullName();
    }

    public String fakeTelefono() {
        return faker.phoneNumber().cellPhone();
    }

    public String fakeCorreo() {
        return faker.internet().emailAddress();
    }

    public String fakeDireccion() {
        return faker.address().streetAddress();
    }

    public String fakeCargo() {
        return faker.job().position();
    }

    public String fakeClave() {
        return faker.internet().password();
    }

}
