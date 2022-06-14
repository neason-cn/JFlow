package con.jflow.test.fulllink;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.api.client.request.commands.SaveDraftFlowSpecCommand;
import com.jflow.core.domain.repository.FlowSpecRepository;
import com.jflow.core.engine.flow.aggregate.FlowInstance;
import com.jflow.core.engine.flow.aggregate.FlowSpec;
import com.jflow.core.engine.service.FlowInstanceService;
import com.jflow.core.engine.service.FlowSpecService;
import con.jflow.test.AbstractSpringTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * @author neason
 * @since 0.0.1
 */
public abstract class AbstractFlowTest extends AbstractSpringTest {

    protected FlowSpec flowSpec;

    @Autowired
    protected FlowSpecService flowSpecService;
    @Autowired
    protected FlowSpecRepository flowSpecRepository;
    @Autowired
    protected FlowInstanceService flowInstanceService;

    @Before
    public void setup() {
        String json = FileUtil.readString(path(), StandardCharsets.UTF_8);
        SaveDraftFlowSpecCommand command = JSONObject.parseObject(json, SaveDraftFlowSpecCommand.class);
        String flowSpecId = flowSpecService.saveDraft(command.getFlowSpec());
        flowSpecService.release(flowSpecId);
        flowSpec = flowSpecRepository.getById(flowSpecId);
    }

    protected FlowInstance newInstance(JSONObject args) {
        return flowInstanceService.start(flowSpec.getFlowSpecCode(), args);
    }

    protected abstract String path();

}
