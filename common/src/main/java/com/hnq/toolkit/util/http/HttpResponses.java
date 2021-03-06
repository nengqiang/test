package com.hnq.toolkit.util.http;

import com.hnq.toolkit.util.http.cookie.HttpCookies;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Static utilities relating to {@link HttpResponse}.
 *
 * @author henengqiang
 * @date 2019/07/01
 */
@Slf4j
public class HttpResponses {

    private HttpResponses() {
    }

    /**
     * @return response's status code.
     * @see org.apache.http.HttpStatus
     */
    public static int getStatus(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();

        log.debug("[HttpClient] >> response status: {}", statusCode);

        return statusCode;
    }

    /**
     * read the response body as a string.
     *
     * @return string read from the response body
     */
    public static String readEntity(HttpResponse response) throws IOException {
        String body = HttpEntities.readToString(response.getEntity());

        log.debug("[HttpClient] >> response body: {}", body);

        return body;
    }

    public static byte[] readEntityAsByteArray(HttpResponse response) throws IOException {
        return HttpEntities.readToByteArray(response.getEntity());
    }

    /**
     * read the response body as a entity.
     *
     * @return {@link T} deserialized from the response body
     */
    @SuppressWarnings("unchecked")
    public static <T> T readEntity(HttpResponse response, final Class<T> clazz) throws IOException {
        if (clazz == null) {
            return (T) response.getEntity();
        } else if (clazz == byte[].class) {
            return clazz.cast(readEntityAsByteArray(response));
        } else if (clazz == String.class) {
            return clazz.cast(readEntity(response));
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            return Jackson.parse(entity.getContent(), clazz);
        }

        return null;
    }

    /**
     * ??????cookie.
     *
     * @param response ???????????? {@link HttpResponse}
     * @return ????????????Set-Cookie??????????????????
     */
    public static List<String> getSetCookies(@Nonnull final HttpResponse response) {
        return HttpHeaders.getHeaderValues(Objects.requireNonNull(response), HttpHeaders.SET_COOKIE);
    }

    /**
     * ??????cookie.
     *
     * @param response ???????????? {@link HttpResponse}
     * @return ????????????Set-Cookie??????????????????
     */
    public static List<HttpCookie> getHttpCookies(@Nonnull final HttpResponse response) {
        Header[] headers = Objects.requireNonNull(response).getHeaders(HttpHeaders.SET_COOKIE);

        return HttpCookies.getHttpCookies(headers);
    }

    /**
     * ??????cookie.
     *
     * @param response ???????????? {@link HttpResponse}
     * @return ????????????Set-Cookie??????????????????
     */
    public static List<String> getSimpleSetCookies(@Nonnull final HttpResponse response) {
        Header[] headers = Objects.requireNonNull(response).getHeaders(HttpHeaders.SET_COOKIE);

        return HttpCookies.getCookies(headers);
    }

    /**
     * ??????cookie.
     *
     * @param response ???????????? {@link HttpResponse}
     * @return ????????????Set-Cookie??????????????????
     */
    public static String getSetCookieString(@Nonnull final HttpResponse response) {
        Header[] headers = Objects.requireNonNull(response).getHeaders(HttpHeaders.SET_COOKIE);

        return HttpCookies.getCookieString(headers);
    }

    /**
     * ??????cookie.
     *
     * @param response ???????????? {@link HttpResponse}
     * @return ????????????Set-Cookie??????????????????
     */
    public static Map<String, String> getSetCookieMap(@Nonnull final HttpResponse response) {
        Header[] headers = Objects.requireNonNull(response).getHeaders(HttpHeaders.SET_COOKIE);

        return HttpCookies.getCookieMap(headers);
    }

    /**
     * ????????????cookie??????.
     *
     * @param response ???????????? {@link HttpResponse}
     * @param key cookie??????????????????????????????
     * @return ????????????Set-Cookie????????????key????????????
     */
    public static String getSetCookieValue(@Nonnull final HttpResponse response,
                                           @Nonnull final String key) {
        return getSetCookieMap(Objects.requireNonNull(response)).get(Objects.requireNonNull(key));
    }

}
