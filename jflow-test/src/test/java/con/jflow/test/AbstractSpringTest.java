package con.jflow.test;

import com.jflow.app.main.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author neason
 * @since 0.0.1
 */
@Rollback
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class AbstractSpringTest {
}
