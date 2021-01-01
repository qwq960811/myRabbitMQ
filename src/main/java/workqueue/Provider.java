package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 17:02$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 17:02$
 * @ Version       :  1.0
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false,null);
        for (int i = 1; i <= 10; i++) {
            channel.basicPublish("","work",null,(i + "hello work queue").getBytes());
        }
        RabbitMQUtils.closeChannelAndConnection(channel, connection);
    }
}
