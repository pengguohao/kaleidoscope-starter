
package com.pgh.kaleidoscope.core.secure.interceptor;

import com.pgh.kaleidoscope.core.secure.KaleidoscopeUser;
import com.pgh.kaleidoscope.core.secure.utils.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.pgh.kaleidoscope.core.tool.api.CommonResult;
import com.pgh.kaleidoscope.core.tool.api.ResultCode;
import com.pgh.kaleidoscope.core.tool.constant.KaleidoscopeConstant;
import com.pgh.kaleidoscope.core.tool.jackson.JsonUtil;
import com.pgh.kaleidoscope.core.tool.utils.StringUtil;
import com.pgh.kaleidoscope.core.tool.utils.WebUtil;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 客户端校验
 *
 * @author Chill
 */
@Slf4j
@AllArgsConstructor
public class ClientInterceptor extends HandlerInterceptorAdapter {

	private final String clientId;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		KaleidoscopeUser user = SecureUtil.getUser();
		if (user != null && StringUtil.equals(clientId, SecureUtil.getClientIdFromHeader()) && StringUtil.equals(clientId, user.getClientId())) {
			return true;
		} else {
			log.warn("客户端认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIP(request), JsonUtil.toJson(request.getParameterMap()));
			CommonResult result = CommonResult.fail(ResultCode.UN_AUTHORIZED);
			response.setHeader(KaleidoscopeConstant.CONTENT_TYPE_NAME, MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(KaleidoscopeConstant.UTF_8);
			response.setStatus(HttpServletResponse.SC_OK);
			try {
				response.getWriter().write(Objects.requireNonNull(JsonUtil.toJson(result)));
			} catch (IOException ex) {
				log.error(ex.getMessage());
			}
			return false;
		}
	}

}
