package org.xlbean.xlapi.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class LoggableMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        LoggableHttpInputMessage httpInputMessageWrapper = new LoggableHttpInputMessage(inputMessage);
        Object ret = super.read(type, contextClass, httpInputMessageWrapper);
        System.out.println(httpInputMessageWrapper.getRequestBody());
        return ret;
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        LoggableHttpOutputMessage httpOutputMessageWrapper = new LoggableHttpOutputMessage(outputMessage);
        super.writeInternal(object, type, httpOutputMessageWrapper);
        System.out.println(httpOutputMessageWrapper.getRequestBody());
    }

    private class LoggableHttpInputMessage implements HttpInputMessage {

        private HttpInputMessage wrapped;

        private ByteArrayOutputStream baos = new ByteArrayOutputStream();

        public LoggableHttpInputMessage(HttpInputMessage inputMessage) {
            this.wrapped = inputMessage;
        }

        @Override
        public HttpHeaders getHeaders() {
            return wrapped.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException {
            return new InputStream() {
                @Override
                public int read() throws IOException {
                    int i = wrapped.getBody().read();
                    baos.write(i);
                    return i;
                }

                @Override
                public int read(byte[] b, int off, int len) throws IOException {
                    int l = super.read(b, off, len);
                    baos.write(b, off, l);
                    return l;
                }

                @Override
                public int read(byte[] b) throws IOException {
                    int ret = super.read(b);
                    baos.write(b);
                    return ret;
                }
            };
        }

        public String getRequestBody() {
            return StringUtil.trimJson(new String(baos.toByteArray()));
        }

    }

    private class LoggableHttpOutputMessage implements HttpOutputMessage {

        private HttpOutputMessage wrapped;

        private ByteArrayOutputStream baos = new ByteArrayOutputStream();

        public LoggableHttpOutputMessage(HttpOutputMessage outputMessage) {
            this.wrapped = outputMessage;
        }

        @Override
        public HttpHeaders getHeaders() {
            return wrapped.getHeaders();
        }

        @Override
        public OutputStream getBody() throws IOException {
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    wrapped.getBody().write(b);
                    baos.write(b);
                }

                @Override
                public void write(byte[] b, int off, int len) throws IOException {
                    super.write(b, off, len);
                    baos.write(b, off, len);
                }

                @Override
                public void write(byte[] b) throws IOException {
                    super.write(b);
                    baos.write(b);
                }
            };
        }

        public String getRequestBody() {
            return StringUtil.trimJson(new String(baos.toByteArray()));
        }

    }
}
