package com.jflow.core.engine.flow.action;

import cn.hutool.json.JSONObject;
import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.ctx.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeanAction extends AbstractAction {

    private String beanName;
    private String methodName;
    private List<String> paramsType;
    private List<JSONObject> paramsValue;

    @Override
    public ActionResponse onExecute(Context ctx) {
        try {
            return doInvoke(ctx);
        } catch (Exception e) {
            return ActionResponse.error("run method of bean error: ".concat(e.getMessage()));
        }
    }

    private ActionResponse doInvoke(Context ctx) {
        ApplicationContext springContext = ctx.getRuntime().getApplicationContext();
        Object bean = springContext.getBean(beanName);
        if (CollectionUtils.isEmpty(paramsType)) {
            Method method = ReflectionUtils.findMethod(bean.getClass(), methodName);
            if (null == method) {
                return ActionResponse.error("no method matches: ".concat(methodName));
            }
            Object result = ReflectionUtils.invokeMethod(method, bean);
            return resolveResult(result);
        }

        Class<?>[] paramTypes = parseClass();
        Object[] paramValues = parseValue(paramTypes);
        Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, paramTypes);
        if (null == method) {
            return ActionResponse.error("no method matches: ".concat(methodName));
        }
        Object result = ReflectionUtils.invokeMethod(method, bean, paramValues);
        return resolveResult(result);
    }

    private Class<?>[] parseClass() throws RuntimeException {
        return paramsType.stream()
                .map(paramClass -> {
                    try {
                        return Class.forName(paramClass);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList())
                .toArray(new Class[]{});
    }

    private Object[] parseValue(Class<?>[] paramTypes) {
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < paramsValue.size(); i++) {
            values.add(paramsValue.get(i).toBean(paramTypes[i]));
        }
        return values.toArray();
    }

}
