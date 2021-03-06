package com.hnq.toolkit.util.http.servlet;

import com.hnq.toolkit.util.Preconditions;
import org.apache.logging.log4j.util.Strings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Static utilities relating to {@link javax.servlet.ServletResponse}.
 *
 * @author henengqiang
 * @date 2019/07/01
 */
public class ServletResponses {

    private static final String CHARSET_MARK = ";charset=";
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_JSON_UTF8_VALUE =
            APPLICATION_JSON + CHARSET_MARK + StandardCharsets.UTF_8.name();
    private static final String APPLICATION_HTML = "text/html";
    private static final String APPLICATION_HTML_UTF8_VALUE =
            APPLICATION_HTML + CHARSET_MARK + StandardCharsets.UTF_8.name();
    private static final String APPLICATION_PLAIN = "text/plain";
    private static final String APPLICATION_PLAIN_UTF8_VALUE =
            APPLICATION_PLAIN + CHARSET_MARK + StandardCharsets.UTF_8.name();

    private ServletResponses() {
    }

    /**
     * write {@code responseBody} into {@code response}.
     */
    public static void writeResponse(@Nonnull final HttpServletResponse response, final int status,
                                     @Nonnull final String contentType, @Nullable final String charset, String responseBody) {
        Preconditions.notNull("response", response);
        Preconditions.notNull("contentType", contentType);
        try {
            response.setStatus(status);
            response.setContentType(contentType);
            if (Strings.isNotEmpty(charset)) {
                response.setCharacterEncoding(charset);
            }
            PrintWriter writer = response.getWriter();
            writer.write(responseBody);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * write {@code responseBody} into {@code response}.
     */
    public static void response(@Nonnull final HttpServletResponse response, final int status,
                                @Nonnull final String contentType, @Nullable final String charset, String responseBody) {
        writeResponse(response, status, fix(contentType, charset), charset, responseBody);
    }

    private static String fix(String contentType, String charset) {
        if (contentType.contains(CHARSET_MARK) || Strings.isEmpty(charset)) {
            return contentType;
        }
        return contentType + CHARSET_MARK + charset;
    }

    /**
     * write {@code responseBody} into {@code response} with the content-type 'json'.
     */
    public static void responseJson(@Nonnull final HttpServletResponse response, final int status,
                                    @Nullable final String charset, final String responseBody) {
        response(response, status, APPLICATION_JSON, charset, responseBody);
    }

    /**
     * write {@code responseBody} into {@code response} with the content-type 'html'.
     */
    public static void responseHtml(@Nonnull final HttpServletResponse response, final int status,
                                    @Nullable final String charset, final String responseBody) {
        response(response, status, APPLICATION_HTML, charset, responseBody);
    }

    /**
     * write {@code responseBody} into {@code response} with the content-type 'plain'.
     */
    public static void responsePlain(@Nonnull final HttpServletResponse response, final int status,
                                     @Nullable final String charset, final String responseBody) {
        response(response, status, APPLICATION_PLAIN, charset, responseBody);
    }

    /**
     * write {@code responseBody} into {@code response}.
     */
    public static void response(@Nonnull final HttpServletResponse response, final int status,
                                @Nonnull final String contentType, String responseBody) {
        response(response, status, contentType, StandardCharsets.UTF_8.name(), responseBody);
    }

    /**
     * write {@code responseBody} into {@code response} with the content-type 'json'.
     */
    public static void responseJson(@Nonnull final HttpServletResponse response, final int status,
                                    final String responseBody) {
        writeResponse(response, status, APPLICATION_JSON_UTF8_VALUE, StandardCharsets.UTF_8.name(), responseBody);
    }

    /**
     * write {@code responseBody} into {@code response} with the content-type 'html'.
     */
    public static void responseHtml(@Nonnull final HttpServletResponse response, final int status,
                                    final String responseBody) {
        writeResponse(response, status, APPLICATION_HTML_UTF8_VALUE, StandardCharsets.UTF_8.name(), responseBody);
    }

    /**
     * write {@code responseBody} into {@code response} with the content-type 'plain'.
     */
    public static void responsePlain(@Nonnull final HttpServletResponse response, final int status,
                                     final String responseBody) {
        writeResponse(response, status, APPLICATION_PLAIN_UTF8_VALUE, StandardCharsets.UTF_8.name(), responseBody);
    }

}
