package com.allan.springbootrabbitmq.config;

import com.allan.springbootrabbitmq.common.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: RabbitMQ 基本配置类
 *
 * @Auther: qinzhengzhong
 * @Date: 2020/8/16 15:52
 * @Description:
 */
@Configuration
public class RabbitMqConfig {

    /**
     *
     * 功能描述: 队列名称
     *
     * @auther: qinzhengzhong
     * @date: 2021/1/23 上午10:08
     */
    @Bean
    public Queue TestDirectQueue() {
        /**
         * 1、durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
         * 2、exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         * 3、autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
         *    如return new Queue("TestDirectQueue",true,true,false);
         *  一般设置一下队列的持久化就好,其余两个就是默认false
         */
        return new Queue(RabbitConstant.QUEUE_NAME,true);
    }

    /**
     *
     * 功能描述: Direct交换机 起名：TestDirectExchange
     *
     * @auther: qinzhengzhong
     * @date: 2021/1/23 上午10:08
     */
    @Bean
    DirectExchange TestDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange(RabbitConstant.EXCHANGE_NAME,true,false);
    }

    /**
     *
     * 功能描述: 绑定  将队列和交换机绑定
     *  用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连
     * @auther: qinzhengzhong
     * @date: 2021/1/23 上午10:08
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with(RabbitConstant.ROUTING_NAME);
    }



    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

}
