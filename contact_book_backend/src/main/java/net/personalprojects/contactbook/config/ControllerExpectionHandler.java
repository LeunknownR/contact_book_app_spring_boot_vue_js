package net.personalprojects.contactbook.config;

import net.personalprojects.contactbook.common.ResponseAPI;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
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
    public ResponseEntity<ResponseAPI> valueObjectExceptionHandler(final Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            final var exValidation = (MethodArgumentNotValidException) ex;
            return ResponseEntity
                .badRequest()
                .body(new ResponseAPI(
                    ResponseActionMessages.ERROR.toString(),
                    exValidation.getAllErrors().get(0).getDefaultMessage()
                ));
        }
        return ResponseEntity
            .internalServerError()
            .body(new ResponseAPI(
                ResponseActionMessages.ERROR.toString(),
                ex.getMessage()
            ));
    }
}
