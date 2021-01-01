package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 21:41$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 21:41$
 * @ Version       :  1.0
 */
public class Provider {

    @Test
    public void sendMessage() throws IOException {

        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机  参数一：交换机名称  参数二：交换机类型
        channel.exchangeDeclare("logs","fanout");  //广播一条消息，多个消费者同时消费

        //发布消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes());

        RabbitMQUtils.closeChannelAndConnection(channel, connection);



    }
}
