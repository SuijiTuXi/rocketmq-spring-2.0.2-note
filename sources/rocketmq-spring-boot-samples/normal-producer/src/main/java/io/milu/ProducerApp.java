package io.milu;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ProducerApp  {

    private static final String TOPIC = "ROCKETMQ_SPRING_TEST";

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    public void run(String... args) throws Exception {
        for (int i = 0 ; i < 10 ; ++i) {

            UserDto user = new UserDto();
            user.setName("Jim" + i);
            user.setAge(i);

            SendResult sendResult = rocketMQTemplate.syncSend(TOPIC, user);
            System.out.println("发送消息成功" + sendResult.getMsgId());
        }

        String group = rocketMQTemplate.getProducer().getProducerGroup();
        String namesrv = rocketMQTemplate.getProducer().getNamesrvAddr();

        System.out.println("group = " + group);
        System.out.println("namesrv = " + namesrv);
    }
}
