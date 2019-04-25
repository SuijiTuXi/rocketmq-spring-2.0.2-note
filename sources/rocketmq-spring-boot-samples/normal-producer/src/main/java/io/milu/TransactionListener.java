package io.milu;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RocketMQTransactionListener(txProducerGroup = "TRANSACTION_GROUP")
public class TransactionListener implements RocketMQLocalTransactionListener {
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        Integer i = (Integer) arg;

        if (i == 5 || i == 6) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        if (i % 2 == 0) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String str = new String((byte[]) msg.getPayload());

        if (str.contains("5")) {
            return RocketMQLocalTransactionState.COMMIT;
        } else if (str.contains("6")) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        return RocketMQLocalTransactionState.UNKNOWN;
    }
}
