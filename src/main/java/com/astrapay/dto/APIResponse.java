package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private String status;
    private List<String> messages;
    private T data;

    public APIResponse(String status, List<String> messages){
        this.status = status;
        this.messages = messages;
    }
}