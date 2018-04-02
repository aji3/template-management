package com.templengine.templatemanagement.dao.mapper.gen;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.templengine.templatemanagement.entity.TemplateParameter;

@Mapper
public interface TemplateParameterMapperGen {

    TemplateParameter selectOne(@Param("templateId") Integer templateId, @Param("parameterId") Integer parameterId);

    List<TemplateParameter> selectList(@Param("templateId") Integer templateId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    void insert(@Param("record") TemplateParameter record);

    void update(@Param("record") TemplateParameter record);

    void updateSelective(@Param("record") TemplateParameter record);

    void delete(@Param("templateId") Integer templateId, @Param("parameterId") Integer parameterId);

    Integer generateTemplateId();

    Integer generateParameterId(Integer templateId);

}
