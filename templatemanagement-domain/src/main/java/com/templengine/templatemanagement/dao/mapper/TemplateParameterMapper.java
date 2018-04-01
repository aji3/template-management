package com.templengine.templatemanagement.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import com.templengine.templatemanagement.dao.mapper.gen.TemplateParameterMapperGen;

@Mapper
@Primary
public interface TemplateParameterMapper extends TemplateParameterMapperGen {

}
