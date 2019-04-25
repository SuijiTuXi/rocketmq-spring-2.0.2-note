package io.milu;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "ROCKETMQ_SPRING_TEST",
        consumerGroup = "normal_consumer")
public class NormalConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    public void onMessage(String s) {
        System.out.println("receive msg " + s);
    }

    public void prepareStart(DefaultMQPushConsumer consumer) {
        System.out.println("Setup");
    }
}
