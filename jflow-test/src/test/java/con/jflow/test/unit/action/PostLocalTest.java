package con.jflow.test.unit.action;

import com.jflow.core.engine.ctx.ActionResponse;
import com.jflow.core.engine.enums.status.TaskInstanceStatusEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author neason
 * @since 0.0.1
 */
public class PostLocalTest extends AbstractActionTest {
    @Override
    protected String path() {
        return "spec/action/http/post_local.json";
    }

    @Test
    public void post_test() {
        Assert.assertNotNull(action);
        ActionResponse response = action.onExecute(context);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getResult());
        Assert.assertEquals(TaskInstanceStatusEnum.SUCCESS, response.getStatus());
    }

}
