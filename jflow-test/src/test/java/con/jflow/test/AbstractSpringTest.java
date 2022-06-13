package con.jflow.test;

import com.jflow.app.main.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@Rollback
@Transactional
@SpringBootTest(classes = Application.class)
public abstract class AbstractSpringTest {
}
