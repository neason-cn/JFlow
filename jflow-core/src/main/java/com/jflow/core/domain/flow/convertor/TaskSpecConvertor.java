package com.jflow.core.domain.flow.convertor;

import com.jflow.api.client.vo.spec.TaskSpecVO;
import com.jflow.common.utils.JsonUtils;
import com.jflow.core.domain.enums.type.TaskTypeEnum;
import com.jflow.core.domain.flow.reference.spec.task.AbstractTaskSpec;
import com.jflow.core.domain.flow.reference.spec.task.AsyncTaskSpec;
import com.jflow.core.domain.flow.reference.spec.task.SyncTaskSpec;
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

    public AbstractTaskSpec convert(TaskSpecVO vo) {
        if (null == vo) {
            return null;
        }
        TaskTypeEnum type = TaskTypeEnum.of(vo.getTaskType());

        if (TaskTypeEnum.SYNC == type) {
            SyncTaskSpec syncTaskSpec = new SyncTaskSpec();
            syncTaskSpec.setTaskName(vo.getTaskName());
            syncTaskSpec.setTaskType(TaskTypeEnum.SYNC);
            syncTaskSpec.setOnExecute(actionSpecConvertor.convert(vo.getOnExecute()));
            return syncTaskSpec;
        }

        AsyncTaskSpec asyncTaskSpec = new AsyncTaskSpec();
        asyncTaskSpec.setTaskName(vo.getTaskName());
        asyncTaskSpec.setTaskType(TaskTypeEnum.ASYNC);
        asyncTaskSpec.setOnSubmit(actionSpecConvertor.convert(vo.getOnExecute()));
        asyncTaskSpec.setOnQuery(actionSpecConvertor.convert(vo.getOnQuery()));
        asyncTaskSpec.setOnCancel(actionSpecConvertor.convert(vo.getOnCancel()));
        return asyncTaskSpec;
    }

    public TaskSpecVO convert(AbstractTaskSpec spec) {
        if (null == spec) {
            return null;
        }
        TaskSpecVO vo = new TaskSpecVO();
        vo.setTaskName(spec.getTaskName());
        vo.setTaskType(spec.getTaskType().getType());
        if (TaskTypeEnum.SYNC == spec.getTaskType()) {
            SyncTaskSpec syncSpec = (SyncTaskSpec) spec;
            vo.setOnExecute(JsonUtils.toJson(syncSpec.getOnExecute()));
            return vo;
        }

        AsyncTaskSpec asyncSpec = (AsyncTaskSpec) spec;
        vo.setOnSubmit(JsonUtils.toJson(asyncSpec.getOnSubmit()));
        vo.setOnQuery(JsonUtils.toJson(asyncSpec.getOnQuery()));
        vo.setOnCancel(JsonUtils.toJson(asyncSpec.getOnCancel()));
        return vo;
    }

}
