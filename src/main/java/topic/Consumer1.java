package topic;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2021/1/1$ 10:34$
 * @ UpdateUser    :
 * @ UpdateDate    :  2021/1/1$ 10:34$
 * @ Version       :  1.0
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");

        //临时队列
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,"topics","user.*"); //通配符 * 匹配一个单词

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
