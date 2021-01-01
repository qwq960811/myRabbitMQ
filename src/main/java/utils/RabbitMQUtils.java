package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/31$ 15:52$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/31$ 15:52$
 * @ Version       :  1.0
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        //代码上移  shift+alt+方向键
        //重量级资源  只在类加载时执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.153.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/emp");
        connectionFactory.setUsername("emp");
        connectionFactory.setPassword("123456");
    }

    //获取连接
   public static Connection getConnection() {
        try {
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和连接
    public static void closeChannelAndConnection(Channel channel,Connection connection){
        try {
            if(channel != null)  channel.close();
            if(connection != null) connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
