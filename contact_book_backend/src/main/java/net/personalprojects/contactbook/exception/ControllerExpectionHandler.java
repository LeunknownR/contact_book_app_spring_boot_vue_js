package net.personalprojects.contactbook.exception;

import net.personalprojects.contactbook.common.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllerExpectionHandler {
    @InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseAPI> generalExceptionHandler(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ResponseAPI("ERROR", ex.getAllErrors().get(0).getDefaultMessage()));
    }
}
