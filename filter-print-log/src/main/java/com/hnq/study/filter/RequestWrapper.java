package com.hnq.study.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class RequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);


    private Map<String, Object> paramsMap = Maps.newHashMap();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        InputStream ins = null;

        try {
            ins = request.getInputStream();
            String jsonStr = IOUtils.toString(ins);
            Map<String,Object> requestMap = JSON.parseObject(jsonStr);

            if(MapUtils.isNotEmpty(requestMap)){
                requestMap.forEach((k,v) -> {
                    if(v instanceof String){
                        requestMap.put(k,StringUtils.trim((String)v));
                    }
                });
                paramsMap.putAll(requestMap);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            IOUtils.closeQuietly(ins);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException{
        return new ByteServletInputStream(JSONObject.toJSONBytes(this.paramsMap));

    }


}
