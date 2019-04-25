package io.milu;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "ROCKETMQ_SPRING_TEST",
        consumerGroup = "user_consumer")
public class UserConsumer implements RocketMQListener<UserDto>  {
    public void onMessage(UserDto message) {
        System.out.println(message);
    }
}
