package com.templengine.templatemanagement.api;

import lombok.Data;

@Data
public class ExecuteTemplateRequest {
    private Integer templateId;
    private String dataJsonStr;
}
