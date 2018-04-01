package com.templengine.templatemanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xlbean.xlapi.controller.AbstractHttpContentControllerSupport;

@RequestMapping("/templatemanagement/html")
@Controller
public class HttpContentController extends AbstractHttpContentControllerSupport {

    public HttpContentController() {
        super("/templatemanagement/html", "html");
    }

    @RequestMapping("/**")
    public void handleContent(HttpServletRequest req, HttpServletResponse res) {
        super.handleContent(req, res);
    }

}
