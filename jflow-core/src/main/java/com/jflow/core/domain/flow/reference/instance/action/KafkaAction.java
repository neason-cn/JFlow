package com.jflow.core.domain.flow.reference.instance.action;

import com.jflow.core.domain.engine.ActionResult;
import com.jflow.core.domain.engine.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KafkaAction extends AbstractAction {
    @Override
    public ActionResult onExecute(Context ctx) {

        KafkaProducer kafkaProducer = new KafkaProducer<>(null);
        return null;
    }
}
