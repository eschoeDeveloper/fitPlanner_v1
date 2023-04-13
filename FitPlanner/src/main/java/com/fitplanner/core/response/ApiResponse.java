package com.fitplanner.core.response;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponse {

    private int count;
    private String message;
    private Object data;
    private int code;

}
