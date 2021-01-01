package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 23:19$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 23:19$
 * @ Version       :  1.0
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct", "direct");

        //创建临时队列
        String queue = channel.queueDeclare().getQueue();

        //绑定队列和交换机
        channel.queueBind(queue,"logs_direct", "error");

        channel.basicConsume(queue,true, new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
