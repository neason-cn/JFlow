package con.jflow.test.unit.action;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.factory.ActionFactory;
import com.jflow.core.engine.ctx.Context;
import com.jflow.core.engine.ctx.Runtime;
import com.jflow.core.engine.ctx.ScriptContext;
import com.jflow.core.engine.flow.action.AbstractAction;
import com.jflow.core.engine.flow.spec.ActionSpec;
import con.jflow.test.AbstractSpringTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * @author neason
 * @since 0.0.1
 */
public abstract class AbstractActionTest extends AbstractSpringTest {

    protected AbstractAction action;
    protected Context context;

    @Autowired
    private ActionFactory actionFactory;
    @Autowired
    private Runtime runtime;

    @Before
    public void setup() {
        String json = FileUtil.readString(path(), StandardCharsets.UTF_8);
        ActionSpec actionSpec = JSONObject.parseObject(json, ActionSpec.class);
        action = actionFactory.create(actionSpec, mock());
        Context ctx = new Context();
        ctx.setRuntime(runtime);
        context = ctx;
    }

    protected abstract String path();

    protected ScriptContext mock() {
        return new ScriptContext(new JSONObject(), new JSONObject());
    }

}
