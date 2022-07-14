package com.testing.demo.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class AppError {
    private List<String> message;
    private String exception;
    private String path;
    private String httpMethod;
}