package com.hnq.study.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {


    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;



    public ResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        buffer = new ByteArrayOutputStream();
        out = new ByteServletOutputStream(buffer);
        writer = new PrintWriter(buffer);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }


    @Override
    public void reset(){
        buffer.reset();
    }


    public byte[] getresult(){
        try {
            flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return buffer.toByteArray();

    }
}
