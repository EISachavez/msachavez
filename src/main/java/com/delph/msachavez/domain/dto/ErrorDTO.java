package com.delph.msachavez.domain.dto;

import java.util.List;

public class ErrorDTO {
    private String         codigo;
    private String         mensaje;
    private Integer        severidad;
    private List<ErrorDTO> errores;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getSeveridad() {
        return severidad;
    }

    public void setSeveridad(Integer severidad) {
        this.severidad = severidad;
    }

    public List<ErrorDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorDTO> errores) {
        this.errores = errores;
    }

}
