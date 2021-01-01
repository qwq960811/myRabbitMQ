package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 17:06$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 17:06$
 * @ Version       :  1.0
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1); //每一次只消费一个消息

        channel.queueDeclare("work", true, false,false,null);
        channel.basicConsume("work",false, new DefaultConsumer(channel){ //第二个参数设为false,关闭消息的自动确认机制(最好关了)
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: " + new String(body));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
