package io.milu;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "ROCKETMQ_TRANSACTION_TEST",
        consumerGroup = "transaction_consumer")
public class TransactionConsumer implements RocketMQListener<String> {
    public void onMessage(String message) {
        System.out.println("receive msg: " + message);
    }
}
