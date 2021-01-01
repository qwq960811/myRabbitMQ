package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 23:13$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 23:13$
 * @ Version       :  1.0
 */
public class Provider {


    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs_direct","direct");

        String routingKey = "info";
        channel.basicPublish("logs_direct",routingKey,null,("direct模型基于routingKey: [" +routingKey+ "]发布消息").getBytes());

        RabbitMQUtils.closeChannelAndConnection(channel, connection);

    }
}
