package com.templengine.templatemanagement.api;

import lombok.Data;

@Data
public class ExecuteTemplateEngineByContentRequest {
    private String templateContents;
    private String dataJsonStr;
}
