package helloworld;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 15:27$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 15:27$
 * @ Version       :  1.0
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        /*ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.153.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/emp");
        connectionFactory.setUsername("emp");
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //通道绑定队列
        channel.queueDeclare("hello", true, false, true, null);

        //消费消息
        //参数 要消费的队列名称
        //     开始消息的自动确认机制
        //     消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override  //最后一个参数：消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("===========" + new String(body));
            }
        });

        //注释掉会一直消费，只到消息队列没有消息为止
//        channel.close();
//        connection.close();
    }
}
