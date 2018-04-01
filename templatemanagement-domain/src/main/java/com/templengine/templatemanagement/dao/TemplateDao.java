package com.templengine.templatemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.templengine.templatemanagement.dao.mapper.TemplateMapper;
import com.templengine.templatemanagement.entity.Template;
import org.xlbean.xlapi.dao.DaoUtil;

@Component
public class TemplateDao {

    @Autowired
    private TemplateMapper mapper;
    
    public Template selectOne(Integer templateId) {
        return mapper.selectOne(templateId);
    }
    
    public List<Template> selectList(int offset, int limit) {
        return mapper.selectList(offset, limit);
    }
    
    public Template insert(Template object) {
        return insert(object, false);
    }
    
    public Template insert(Template object, boolean autoGenerateId) {
        if (autoGenerateId) {
            object.updateKey(generateTemplateId());
        }
        DaoUtil.updateSystemValueForInsert(object);
        mapper.insert(object);
        return object;
    }

    public void update(Template object) {
        DaoUtil.updateSystemValueForUpdate(object);
        mapper.update(object);
    }

    public void updatePartial(Template object) {
        DaoUtil.updateSystemValueForUpdate(object);
        mapper.updateSelective(object);
    }
    
    public Template upsert(Template object) {
        Template registeredObject = mapper.selectOne(object.getTemplateId());
        if (registeredObject == null) {
            insert(object, false);
            return object;
        } else {
            DaoUtil.updateSystemValueForUpdate(object);
            mapper.updateSelective(object);
            return object;
        }
    }
    
    public void delete(Integer templateId) {
        mapper.delete(templateId);
    }

    public Integer generateTemplateId() {
        return mapper.generateTemplateId();
    }


}
