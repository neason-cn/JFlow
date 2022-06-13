package con.jflow.test.fulllink;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
public class SimpleFlowTest extends AbstractFlowTest {
    @Override
    protected String path() {
        return "spec/flow/simple_flow.json";
    }

    @Test
    public void save_release_test() {
        log.info("save and release ok.");
    }

}
