package com.templengine.templatemanagement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.templengine.templatemanagement.dao.TemplateParameterDao;
import com.templengine.templatemanagement.entity.TemplateParameter;

@RestController
@RequestMapping(path="/templatemanagement/templates/{templateId}/parameters")
public class TemplateParameterApi {

    @Autowired
    private TemplateParameterDao dao;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<TemplateParameter> getList(@PathVariable Integer templateId, 
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 100;
        }
        return dao.selectList(templateId, offset, limit);
    }

    @RequestMapping(path = "/{parameterId}", method = RequestMethod.GET)
    public TemplateParameter getOne(@PathVariable Integer templateId, @PathVariable Integer parameterId) {
        return dao.selectOne(templateId, parameterId);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Transactional
    public TemplateParameter create(@RequestBody TemplateParameter object) {
        return dao.insert(object, true);
    }

    @RequestMapping(path = "/{parameterId}", method = RequestMethod.PUT)
    @Transactional
    public TemplateParameter upsert(@PathVariable Integer templateId, @PathVariable Integer parameterId, @RequestBody TemplateParameter object) {
        object.updateKey(templateId, parameterId);
        return dao.upsert(object);
    }

    @RequestMapping(path = "/{parameterId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer templateId, @PathVariable Integer parameterId) {
        dao.delete(templateId, parameterId);
    }
}

