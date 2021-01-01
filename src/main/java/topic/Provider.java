package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2021/1/1$ 10:30$
 * @ UpdateUser    :
 * @ UpdateDate    :  2021/1/1$ 10:30$
 * @ Version       :  1.0
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");

        String routingKey = "user";
        channel.basicPublish("topics",routingKey,null,("基于topic动态路由routingKey: [" + routingKey + "]发布消息").getBytes());

        RabbitMQUtils.closeChannelAndConnection(channel,connection);
    }
}
