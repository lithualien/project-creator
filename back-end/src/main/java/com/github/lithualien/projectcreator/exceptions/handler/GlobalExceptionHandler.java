package com.github.lithualien.projectcreator.exceptions.handler;

import com.github.lithualien.projectcreator.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.projectcreator.exceptions.ResourceIllogicalAmountException;
import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.vo.ExceptionVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( { ResourceNotFoundException.class, ResourceAlreadyExistsException.class,
            ResourceIllogicalAmountException.class} )
    public final ResponseEntity<ExceptionVO> handleBadRequestExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionVO(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    private ExceptionVO getExceptionVO(String error, Integer status, String description) {
        return new ExceptionVO(
                LocalDateTime.now(),
                status,
                error,
                description
        );
    }

}
