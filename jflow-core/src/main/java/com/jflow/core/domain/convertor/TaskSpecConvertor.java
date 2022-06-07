package com.jflow.core.domain.convertor;

import com.jflow.api.client.vo.spec.TaskSpecVO;
import com.jflow.core.engine.enums.type.TaskTypeEnum;
import com.jflow.core.engine.flow.spec.TaskSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author neason
 * @since 0.0.1
 */
@Component
@RequiredArgsConstructor
public class TaskSpecConvertor {

    private final ActionSpecConvertor actionSpecConvertor;

    public TaskSpec convert(TaskSpecVO vo) {
        if (null == vo) {
            return null;
        }
        TaskTypeEnum type = TaskTypeEnum.of(vo.getTaskType());
        TaskSpec spec = new TaskSpec();
        spec.setTaskName(vo.getTaskName());
        spec.setTaskType(type);
        spec.setOnExecute(actionSpecConvertor.convert(vo.getOnExecute()));
        spec.setOnSubmit(actionSpecConvertor.convert(vo.getOnSubmit()));
        spec.setOnQuery(actionSpecConvertor.convert(vo.getOnQuery()));
        spec.setOnCancel(actionSpecConvertor.convert(vo.getOnCancel()));
        return spec;
    }

    public TaskSpecVO convert(TaskSpec spec) {
        if (null == spec) {
            return null;
        }
        TaskSpecVO vo = new TaskSpecVO();
        vo.setTaskName(spec.getTaskName());
        vo.setTaskType(spec.getTaskType().getType());
        vo.setOnExecute(actionSpecConvertor.convert(spec.getOnExecute()));
        vo.setOnSubmit(actionSpecConvertor.convert(spec.getOnSubmit()));
        vo.setOnQuery(actionSpecConvertor.convert(spec.getOnQuery()));
        vo.setOnCancel(actionSpecConvertor.convert(spec.getOnCancel()));
        return vo;
    }

}
