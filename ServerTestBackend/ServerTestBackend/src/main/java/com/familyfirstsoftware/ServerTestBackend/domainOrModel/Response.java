package com.familyfirstsoftware.ServerTestBackend.domainOrModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
// when something is null don't shot it
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    protected LocalDateTime timeStamp;

    protected int statusCode;

    protected HttpStatus status;

    protected String reason;

    protected String message;

    protected String developerMessage;

    // map of anything
    protected Map<?, ?> data;
}
