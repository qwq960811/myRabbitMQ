package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//生产者
public class Provider {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        /*//创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接的rabbitmq主机  端口号
        connectionFactory.setHost("192.168.153.131");
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/emp");
        connectionFactory.setUsername("emp");
        connectionFactory.setPassword("123456");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();*/

        Connection connection = RabbitMQUtils.getConnection();

        //获取连接中的通道
        Channel channel = connection.createChannel();

        //通道绑定对应的消息队列
        //参数1 ：队列名称 如果不存在则自动创建
        //参数2： 定义队列特性是否要持久化
        //参数3： 是否独占队列
        //参数4： 是否在消费完成后自动删除队列
        //参数5： 额外附加参数
        channel.queueDeclare("hello", true, false, true,null);

        //发布消息
        //参数： 交换机名称  队列名称  传递消息额外设置(MessageProperties.PERSISTENT_TEXT_PLAIN 代表队列中的消息也会持久化，不会随着重启rabbitmq而丢失)  消息的具体内容
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());


        RabbitMQUtils.closeChannelAndConnection(channel, connection);
        /*channel.close();
        connection.close();*/
    }
}
