package com.templengine.templatemanagement.entity;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Template {
    public enum ContentType {TEXT, HTML, }; 

    @NotNull 
    private Integer templateId;
    @Size(max = 128) 
    private String name;
    @Size(max = 1024) 
    private String description;
    @Size(max = 512) 
    private String path;
    @Size(max = 1000000) 
    private String content;
    @Size(max = 20) 
    private ContentType contentType;
    @Size(max = 10000) 
    private String sampleDataJsonStr;
    private LocalDateTime createdTimestamp;
    @Size(max = 255) 
    private String createdBy;
    private LocalDateTime updatedTimestamp;
    @Size(max = 255) 
    private String updatedBy;

    public void updateKey(Integer templateId) {
        if (templateId != null) {
            this.templateId = templateId;
        }
    }
}
