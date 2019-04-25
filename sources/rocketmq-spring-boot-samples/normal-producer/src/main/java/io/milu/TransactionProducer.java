package io.milu;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@SpringBootApplication
public class TransactionProducer implements CommandLineRunner {

    private static final String TOPIC = "ROCKETMQ_TRANSACTION_TEST";

    private static final String GROUP = "TRANSACTION_GROUP";

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TransactionProducer.class, args);
    }

    public void run(String... args) throws Exception {
        for (int i = 0 ; i < 10 ; ++i) {
            Message<String> msg = MessageBuilder.withPayload("Hello RocketMQ " + i).setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build();

            rocketMQTemplate.sendMessageInTransaction(GROUP, TOPIC, msg, Integer.valueOf(i));
        }
    }
}
