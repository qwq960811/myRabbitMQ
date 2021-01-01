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
public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare("work", true, false,false,null);
        channel.basicConsume("work",false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: " + new String(body));

                //手动确认  参数一：手动确认消息标识 （确认队列中的具体消息） 参数二：是否开启多个消息同时确认  false 每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
