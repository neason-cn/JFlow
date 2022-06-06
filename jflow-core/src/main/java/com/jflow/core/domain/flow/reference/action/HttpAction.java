package com.jflow.core.domain.flow.reference.action;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.engine.ActionResponse;
import com.jflow.core.domain.engine.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * @author neason
 * @since 0.0.1
 */
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

    @Override
    public ActionResponse onExecute(Context ctx) {
        try {
            HttpResponse response = HttpUtil.createRequest(Method.valueOf(method), connectParams())
                    .header(headers)
                    .body(body)
                    .execute();
            try {
                return JSONObject.parseObject(response.body(), ActionResponse.class);
            } catch (JSONException e) {
                return ActionResponse.error("http response parse error: ".concat(e.getMessage()));
            }
        } catch (Exception e) {
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
