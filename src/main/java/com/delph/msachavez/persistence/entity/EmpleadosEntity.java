package com.delph.msachavez.persistence.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_empleados")
public class EmpleadosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_codigo")
    private Integer codigo;

    @Length(min = 5, max = 20)
    @Column(name = "emp_usuario", unique = true, nullable = false)
    private String usuario;

    @Column(name = "emp_nombre", nullable = false)
    private String nombre;

    @Column(name = "emp_telefono", nullable = false)
    private String telefono;

    @Email
    @Column(name = "emp_correo", nullable = false)
    private String correo;

    @Column(name = "emp_direccion", nullable = false)
    private String direccion;

    @Column(name = "emp_cargo", nullable = false)
    private String cargo;

    @Column(name = "emp_clave", nullable = false)
    private String clave;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "emp_create_at", nullable = false, updatable = false, insertable = true)
    private Date fechaCreacion;

    @Past
    @UpdateTimestamp
    @Column(name = "emp_update_at", nullable = false, updatable = true, insertable = true)
    private Date fechaModificacion;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

}
