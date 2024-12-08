package com.delph.msachavez.web.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.delph.msachavez.domain.dto.ErrorDTO;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public final ResponseEntity<Object> handleNotFound(NotFound exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo("ERR-01");
        errorDTO.setMensaje(exception.getMessage());
        errorDTO.setSeveridad(0);

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotProcessable.class)
    public final ResponseEntity<Object> handleNotProcessable(NotProcessable exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo("ERR-02");
        errorDTO.setMensaje(exception.getMessage());
        errorDTO.setSeveridad(1);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo("ERR-SQL-01");
        errorDTO.setMensaje("Error transaccional: " + exception.getMessage());
        errorDTO.setSeveridad(3);

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo("ERR-MTH-01");
        errorDTO.setMensaje("Error general: " + exception.getMessage());
        errorDTO.setSeveridad(0);

        return new ResponseEntity<>(errorDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCodigo("ERR-03-LST");
        errorDTO.setMensaje("Error en los parametros de entrada");
        errorDTO.setSeveridad(2);

        errorDTO.setErrores(new ArrayList<>());

        for(String errorStr : errores) {
            ErrorDTO lstErrores = new ErrorDTO();
            lstErrores.setCodigo("ERR-03");
            lstErrores.setMensaje(errorStr);
            lstErrores.setSeveridad(1);

            errorDTO.getErrores().add(lstErrores);
        }

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
