package com.jflow.core.domain.flow.reference.instance.action;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.engine.ActionResult;
import com.jflow.core.domain.engine.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.List;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DubboAction extends AbstractAction {

    private String appName;
    private String registryAddress;

    private String interfaceName;
    private String method;
    private String version;
    private JSONObject body;
    private List<String> paramsType;

    @Override
    public ActionResult onExecute(Context ctx) {
        GenericService genericService = init();
        try {
            Object response = genericService.$invoke(method, paramsType.toArray(new String[]{}),
                    new Object[]{body});
            return resolveResult(response);
        } catch (Exception e) {
            return ActionResult.error("invoke dubbo error: ".concat(e.getMessage()));
        }
    }

    private GenericService init() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName(appName);
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(registryAddress);
        application.setRegistry(registry);
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setInterface(interfaceName);
        reference.setVersion(version);
        reference.setGeneric(true);
        return reference.get();
    }
}
