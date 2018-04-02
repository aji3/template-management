package com.templengine.templatemanagement.dao.mapper.gen;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.templengine.templatemanagement.entity.Template;

@Mapper
public interface TemplateMapperGen {

    Template selectOne(@Param("templateId") Integer templateId);

    List<Template> selectList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    void insert(@Param("record") Template record);

    void update(@Param("record") Template record);

    void updateSelective(@Param("record") Template record);

    void delete(@Param("templateId") Integer templateId);

    Integer generateTemplateId();

}
