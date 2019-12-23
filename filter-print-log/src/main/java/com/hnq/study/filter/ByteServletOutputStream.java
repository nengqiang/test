package com.hnq.study.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteServletOutputStream extends ServletOutputStream {

    private ByteArrayOutputStream bos;

    public ByteServletOutputStream(ByteArrayOutputStream bos) {
        this.bos = bos;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        bos.write(b);
    }
    @Override
    public void write(byte[] b) throws IOException {
        bos.write(b,0,b.length);
    }

}
