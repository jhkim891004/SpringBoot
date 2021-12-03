package com.example.springboot.common.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestInterceptor implements HandlerInterceptor {
	private final ObjectMapper objectMapper;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if(request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return;

		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
		final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;

		JsonNode requestBody = null;
		if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
			if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0) {
				requestBody = objectMapper.readTree(cachingRequest.getContentAsByteArray());
			}
		}

		log.info("\n"
				+ "[{}] - {}\n"
				+ "ContentType: {}\n"
				+ "Request Body: {}"
				, request.getMethod()
				, request.getRequestURI()
				, request.getContentType() == null ? "-" : request.getContentType()
				, requestBody == null ? "-" : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody));
	}
}
