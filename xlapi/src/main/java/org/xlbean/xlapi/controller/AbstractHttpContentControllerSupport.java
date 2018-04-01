package org.xlbean.xlapi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractHttpContentControllerSupport {

    private String rootUri;
    private String rootContentPath;

    /**
     * @param rootUri
     * @param rootContentPath
     */
    public AbstractHttpContentControllerSupport(String rootUri, String rootContentPath) {
        this.rootUri = rootUri;
        this.rootContentPath = rootContentPath;
    }

    protected void handleContent(HttpServletRequest req, HttpServletResponse res) {
        String relativeContentPath = req.getRequestURI().replace(rootUri, "");
        if (relativeContentPath.endsWith("/")) {
            relativeContentPath += "index.html";
        }
        System.out.println(rootContentPath + relativeContentPath);
        try (InputStream in = resolveContent(rootContentPath + relativeContentPath);
                OutputStream out = res.getOutputStream();) {
            if (in == null) {
                res.setStatus(404);
                return;
            }
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) >= 0) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream resolveContent(String path) {
        return AbstractHttpContentControllerSupport.class.getClassLoader().getResourceAsStream(path);
    }
}
