package com.hnq.study.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2018/11/14
 */
@Slf4j
public class WrapFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Enumeration<String> headNames = httpServletRequest.getHeaderNames();
        while (headNames.hasMoreElements()) {
            String s = headNames.nextElement();
            log.debug("httpRequestHeader: " + s + "=" + httpServletRequest.getHeader(s));
        }

        if (isApplicationJsonBody(httpServletRequest)) {
            httpServletRequest = new RequestWrapper(httpServletRequest);
        }
        Long mills = Clock.systemDefaultZone().millis();
//        log.info("requestUrl - {},requestParams - {},time - {}",
//                httpServletRequest.getRequestURI(), IOUtils.toString(httpServletRequest.getInputStream()), mills);
        log.info("requestUrl - {}, requestParams - {}, time - {}",
                httpServletRequest.getRequestURI(), getParameters(httpServletRequest), mills);
        ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);

        filterChain.doFilter(httpServletRequest, responseWrapper);

        byte[] result = responseWrapper.getresult();

        log.info("response - {}, time - {}", new String(result, StandardCharsets.UTF_8), mills);

        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.write(result);
        out.flush();
    }

    private <T> boolean isApplicationJsonBody(T t) {
        if (t instanceof HttpServletRequest) {
            return StringUtils.isNotEmpty(((HttpServletRequest)t).getContentType());
        }
        if (t instanceof HttpServletResponse) {
            return StringUtils.isNotEmpty(((HttpServletResponse)t).getContentType());
        }
        return false;
    }

    private String getParameters(HttpServletRequest request) {
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        paramMap.forEach((key, value) -> sb.append(key).append(": ")
            .append(Arrays.toString(value).replace("[", "").replace("]", "")).append(", "));
        // 当map有值时删掉最后的 ", "
        if (MapUtils.isNotEmpty(paramMap)) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

}
