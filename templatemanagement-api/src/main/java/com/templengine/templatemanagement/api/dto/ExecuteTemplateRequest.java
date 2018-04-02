package com.templengine.templatemanagement.api.dto;

import lombok.Data;

@Data
public class ExecuteTemplateRequest {
    private Integer templateId;
    private String dataJsonStr;
}
