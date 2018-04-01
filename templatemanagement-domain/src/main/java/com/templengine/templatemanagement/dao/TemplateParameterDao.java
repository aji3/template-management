package com.templengine.templatemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.templengine.templatemanagement.dao.mapper.TemplateParameterMapper;
import com.templengine.templatemanagement.entity.TemplateParameter;
import org.xlbean.xlapi.dao.DaoUtil;

@Component
public class TemplateParameterDao {

    @Autowired
    private TemplateParameterMapper mapper;
    
    public TemplateParameter selectOne(Integer templateId, Integer parameterId) {
        return mapper.selectOne(templateId, parameterId);
    }
    
    public List<TemplateParameter> selectList(Integer templateId, int offset, int limit) {
        return mapper.selectList(templateId, offset, limit);
    }
    
    public TemplateParameter insert(TemplateParameter object) {
        return insert(object, false);
    }
    
    public TemplateParameter insert(TemplateParameter object, boolean autoGenerateId) {
        if (autoGenerateId) {
            object.updateKey(null, generateParameterId(object.getTemplateId()));
        }
        DaoUtil.updateSystemValueForInsert(object);
        mapper.insert(object);
        return object;
    }

    public void update(TemplateParameter object) {
        DaoUtil.updateSystemValueForUpdate(object);
        mapper.update(object);
    }

    public void updatePartial(TemplateParameter object) {
        DaoUtil.updateSystemValueForUpdate(object);
        mapper.updateSelective(object);
    }
    
    public TemplateParameter upsert(TemplateParameter object) {
        TemplateParameter registeredObject = mapper.selectOne(object.getTemplateId(), object.getParameterId());
        if (registeredObject == null) {
            insert(object, false);
            return object;
        } else {
            DaoUtil.updateSystemValueForUpdate(object);
            mapper.updateSelective(object);
            return object;
        }
    }
    
    public void delete(Integer templateId, Integer parameterId) {
        mapper.delete(templateId, parameterId);
    }

    public Integer generateParameterId(Integer templateId) {
        return mapper.generateParameterId(templateId);
    }


}
