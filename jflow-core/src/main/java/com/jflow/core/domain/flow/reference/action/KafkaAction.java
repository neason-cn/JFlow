package com.jflow.core.domain.flow.reference.action;

import com.alibaba.fastjson2.JSONObject;
import com.jflow.core.domain.engine.ActionResponse;
import com.jflow.core.domain.engine.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KafkaAction extends AbstractAction {
    private Map<String, String> properties;
    private String topic;
    private Integer partition;
    private Long timestamp;
    private String key;
    private List<String> messages;

    @Override
    public ActionResponse onExecute(Context ctx) {
        if (CollectionUtils.isEmpty(messages)) {
            return ActionResponse.success(new JSONObject());
        }
        KafkaProducer<String, String> producer = new KafkaProducer<>(convert());
        try {
            producer.initTransactions();
            producer.beginTransaction();
            for (String message : messages) {
                producer.send(new ProducerRecord<>(topic, partition, timestamp, key, message));
            }
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            producer.close();
            return ActionResponse.error("send message error: ".concat(e.getMessage()));
        } catch (Exception e) {
            producer.abortTransaction();
            return ActionResponse.error("send message error: ".concat(e.getMessage()));
        } finally {
            producer.close();
        }
        return ActionResponse.success(new JSONObject());
    }

    private Properties convert() {
        Properties props = new Properties();
        if (MapUtils.isNotEmpty(properties)) {
            properties.forEach(props::setProperty);
        }
        return props;
    }


}
