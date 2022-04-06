package org.hamburger.boot.starter.filter;

import com.google.common.collect.ImmutableSet;
import org.hamburger.boot.core.constant.Constants;
import org.hamburger.boot.core.utils.TraceIdGenerator;
import org.hamburger.boot.starter.context.GlobalContext;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(filterName = "hamburgerHttpFilter", urlPatterns = "/*")
public class HamburgerHttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String traceId = null;
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            if (!HTTP_HEADERS.contains(key.toLowerCase())) {
                GlobalContext.put(key, httpServletRequest.getHeader(key));
            }
            if (key.equalsIgnoreCase(Constants.TRACE_ID)) {
                traceId = httpServletRequest.getHeader(key);
            }
        }
        if (traceId == null || traceId.isEmpty()) {
            traceId = TraceIdGenerator.generate();
        }
        MDC.put(Constants.TRACE_ID, traceId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public static final ImmutableSet<String> HTTP_HEADERS = ImmutableSet.of(
            "accept",
            "accept-charset",
            "accept-encoding",
            "accept-language",
            "accept-patch",
            "accept-ranges",
            "access-control-allow-credentials",
            "access-control-allow-headers",
            "access-control-allow-methods",
            "access-control-allow-origin",
            "access-control-expose-headers",
            "access-control-max-age",
            "access-control-request-headers",
            "access-control-request-method",
            "age",
            "allow",
            "authorization",
            "cache-control",
            "connection",
            "content-encoding",
            "content-disposition",
            "content-language",
            "content-length",
            "content-location",
            "content-range",
            "content-type",
            "cookie",
            "date",
            "etag",
            "expect",
            "expires",
            "from",
            "host",
            "if-match",
            "if-modified-since",
            "if-none-match",
            "if-range",
            "if-unmodified-since",
            "last-modified",
            "link",
            "location",
            "max-forwards",
            "origin",
            "pragma",
            "proxy-authenticate",
            "proxy-authorization",
            "range",
            "referer",
            "retry-after",
            "server",
            "set-cookie",
            "set-cookie2",
            "te",
            "trailer",
            "transfer-encoding",
            "upgrade",
            "user-agent",
            "vary",
            "via",
            "warning",
            "www-authenticate");
}
