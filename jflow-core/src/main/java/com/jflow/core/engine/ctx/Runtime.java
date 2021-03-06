package com.jflow.core.engine.ctx;

import com.jflow.core.engine.service.FlowInstanceService;
import com.jflow.core.engine.service.TaskInstanceService;
import com.jflow.infra.spi.cache.CacheSpi;
import com.jflow.infra.spi.scheduler.SchedulerSpi;
import com.jflow.infra.spi.script.ScriptSpi;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The ability providers, not all beans in applicationContext.
 *
 * @author neason
 * @since 0.0.1
 */
@Component
public class Runtime implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final Map<String, Object> actionBeanContainer = new ConcurrentHashMap<>();

    public void registerActionBean(String beanName) {
        registerActionBean(beanName, applicationContext.getBean(beanName));
    }

    public void registerActionBean(String beanName, Object bean) {
        actionBeanContainer.put(beanName, bean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public CacheSpi getCacheSpi() {
        return applicationContext.getBean(CacheSpi.class);
    }

    public SchedulerSpi getSchedulerSpi() {
        return applicationContext.getBean(SchedulerSpi.class);
    }

    public ScriptSpi getScriptSpi() {
        return applicationContext.getBean(ScriptSpi.class);
    }

    public FlowInstanceService getFlowInstanceService() {
        return applicationContext.getBean(FlowInstanceService.class);
    }

    public TaskInstanceService getTaskInstanceService() {
        return applicationContext.getBean(TaskInstanceService.class);
    }

    public Object getBean(String beanName) {
        return actionBeanContainer.get(beanName);
    }
}
