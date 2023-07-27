package com.hilltop.search.controller;

import com.hilltop.search.configuration.Translator;
import com.hilltop.search.domain.response.ResponseDto;
import com.hilltop.search.domain.response.ResponseWrapper;
import com.hilltop.search.enums.ErrorMessage;
import com.hilltop.search.enums.SuccessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Base controller
 */
@CrossOrigin
@Component
public class BaseController {
    protected final Translator translator;

    @Autowired
    public BaseController(Translator translator) {
        this.translator = translator;
    }

    protected ResponseEntity<ResponseWrapper> getSuccessResponse(SuccessMessage successMessage,
                                                                 ResponseDto responseDto, HttpStatus httpStatus) {
        ResponseWrapper responseWrapper = new ResponseWrapper(successMessage.getMessage(), responseDto);
        return new ResponseEntity<>(responseWrapper, httpStatus);
    }

    protected ResponseEntity<ResponseWrapper> getBadRequestErrorResponse(ErrorMessage errorMessage) {
        ResponseWrapper responseWrapper = new ResponseWrapper(errorMessage.getMessage());
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ResponseWrapper> getInternalServerError() {
        ResponseWrapper responseWrapper = new ResponseWrapper(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity<>(responseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
