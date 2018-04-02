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
public class TemplateParameter {

    @NotNull 
    private Integer templateId;
    @NotNull 
    private Integer parameterId;
    @Size(max = 255) 
    private String parameterName;
    private LocalDateTime createdTimestamp;
    @Size(max = 255) 
    private String createdBy;
    private LocalDateTime updatedTimestamp;
    @Size(max = 255) 
    private String updatedBy;

    public void updateKey(Integer templateId, Integer parameterId) {
        if (templateId != null) {
            this.templateId = templateId;
        }
        if (parameterId != null) {
            this.parameterId = parameterId;
        }
    }
}
