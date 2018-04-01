package com.templengine.templatemanagement.api;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.DelegatingLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.templengine.templatemanagement.dao.TemplateDao;
import com.templengine.templatemanagement.engine.PebbleStringLoader;
import com.templengine.templatemanagement.entity.Template;

@RestController
@RequestMapping(path = "/templatemanagement/templates")
@CrossOrigin
public class TemplateApi {

    @Autowired
    private TemplateDao dao;

    @RequestMapping(path = "/_executeByContent", method = RequestMethod.POST)
    public String executeTemplateEngineByContent(@RequestBody ExecuteTemplateEngineByContentRequest request) {
        return executeTemplateEngineInternal("TMP_PATH", request.getTemplateContents(), request.getDataJsonStr());
    }

    @RequestMapping(path = "/{templateId}/_execute", method = RequestMethod.POST)
    public String executeTemplateEngine(@PathVariable Integer templateId, @RequestBody String dataJsonStr) {
        Template template = dao.selectOne(templateId);
        return executeTemplateEngineInternal(template.getPath(), template.getContent(), dataJsonStr);
    }

    private String executeTemplateEngineInternal(String templatePath, String templateContent, String dataStr) {
        PebbleStringLoader stringLoader = new PebbleStringLoader();
        stringLoader.putTemplate(templatePath, templateContent);

        List<Loader<?>> defaultLoadingStrategies = new ArrayList<>();
        defaultLoadingStrategies.add(stringLoader);
        Loader<?> loader = new DelegatingLoader(defaultLoadingStrategies);

        PebbleEngine engine = new PebbleEngine.Builder().loader(loader).build();

        Map<String, Object> data = parseJson(dataStr);

        try {
            StringWriter writer = new StringWriter();
            PebbleTemplate pebbleTemplate = engine.getTemplate(templatePath);
            pebbleTemplate.evaluate(writer, data);
            return writer.toString();
        } catch (PebbleException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> parseJson(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, HashMap.class);
        } catch (IOException e) {
            // JSON Format error
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Template> getList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 100;
        }
        return dao.selectList(offset, limit);
    }

    @RequestMapping(path = "/{templateId}", method = RequestMethod.GET)
    public Template getOne(@PathVariable Integer templateId) {
        return dao.selectOne(templateId);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Transactional
    public Template create(@RequestBody Template object) {
        return dao.insert(object, true);
    }

    @RequestMapping(path = "/{templateId}", method = RequestMethod.PUT)
    @Transactional
    public Template upsert(@PathVariable Integer templateId, @RequestBody Template object) {
        object.updateKey(templateId);
        return dao.upsert(object);
    }

    @RequestMapping(path = "/{templateId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer templateId) {
        dao.delete(templateId);
    }
}
