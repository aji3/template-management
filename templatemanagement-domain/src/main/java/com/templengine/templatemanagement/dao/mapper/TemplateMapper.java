package com.templengine.templatemanagement.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import com.templengine.templatemanagement.dao.mapper.gen.TemplateMapperGen;

@Mapper
@Primary
public interface TemplateMapper extends TemplateMapperGen {

}
