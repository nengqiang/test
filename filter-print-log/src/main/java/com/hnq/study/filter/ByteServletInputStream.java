package com.hnq.study.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteServletInputStream extends ServletInputStream {

    private final ByteArrayInputStream inputStream;

    public ByteServletInputStream(byte[] bytes) {
        this.inputStream = new ByteArrayInputStream(bytes);
    }

    @Override
    public boolean isFinished() {
        return inputStream.available() == 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }


}
