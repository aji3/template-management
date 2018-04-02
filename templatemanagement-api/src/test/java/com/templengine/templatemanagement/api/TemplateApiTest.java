package com.templengine.templatemanagement.api;

import org.junit.Test;

import com.templengine.templatemanagement.api.dto.ExecuteTemplateEngineByContentRequest;

public class TemplateApiTest extends TemplateApi {

    @Test
    public void executeTemplateEngine() {
        String template = "hello {{ name }}-san, \r\nThis is {{obj.key1}}. \r\nHere are your data{% for val in list %} \r\n{{val}}{% endfor %}";
        String dataStr = "{\"name\":\"Tanikawa\", \"obj\":{\"key1\":\"val1\",\"key2\": \"val2\"}, \"list\":[\"test1\", \"test2\"]}";
        ExecuteTemplateEngineByContentRequest request = new ExecuteTemplateEngineByContentRequest();
        request.setTemplateContents(template);
        request.setDataJsonStr(dataStr);

        String result = this.executeTemplateEngineByContent(request);
        System.out.println(result);
    }

}
