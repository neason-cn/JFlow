package com.jflow.core.engine.flow.action;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.ctx.Context;
import com.jflow.infra.spi.script.ScriptResult;
import com.jflow.infra.spi.script.ScriptSpi;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class HttpAction extends AbstractAction {

    /**
     * Http url, eg: http:<a href="https://github.com/neason-cn/JFlow"></a>
     */
    private String url;

    /**
     * Http method, eg: GET, POST, DELETE.
     */
    private String method;

    /**
     * Http headers.
     */
    private Map<String, List<String>> headers;

    /**
     * Http params, eg: a=1&b=2&c=3
     */
    private Map<String, String> params;

    /**
     * Http body.
     */
    private String body;

    /**
     * Convert http response body to ActionResponse by using this script.
     */
    private String convertScript;

    private static final String HTTP_RESPONSE_BODY = "response";

    @Override
    public ActionResponse onExecute(Context ctx) {
        try {
            log.debug("http invoke url: {}, method: {}, headers: {}, body: {}", connectParams(), method, headers, body);
            HttpResponse response = HttpUtil.createRequest(Method.valueOf(method), connectParams())
                    .header(headers)
                    .body(body)
                    .execute();
            log.debug("http invoke response body: {}", response.body());
            JSONObject resContext = new JSONObject();
            resContext.put(HTTP_RESPONSE_BODY, response.body());
            ScriptSpi scriptSpi = ctx.getRuntime().getScriptSpi();
            ScriptResult<ActionResponse> result = scriptSpi.execute(this.convertScript, resContext, new TypeReference<ActionResponse>() {
            });
            if (result.hasError()) {
                return ActionResponse.error(
                        StrFormatter.format("the http response body is {}, and the script has error: {}", response.body(), result.getError()));
            }
            return result.getResult();
        } catch (Exception e) {
            log.error("http invoke error: {}", e.getMessage());
            return ActionResponse.error("http invoke error: ".concat(e.getMessage()));
        }
    }

    private String connectParams() {
        if (MapUtils.isNotEmpty(params)) {
            StringBuilder builder = new StringBuilder(url);
            builder.append("?");
            params.forEach((k, v) -> {
                builder.append(k).append("=").append(v).append("&");
            });
            return builder.toString();
        }
        return url;
    }

}
