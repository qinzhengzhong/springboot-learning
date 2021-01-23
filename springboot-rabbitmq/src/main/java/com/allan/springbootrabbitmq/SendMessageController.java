package com.allan.springbootrabbitmq;

import com.allan.springbootcommon.util.DateUtil;
import com.allan.springbootcommon.util.UUIDUtils;
import com.allan.springbootrabbitmq.common.RabbitConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: qinzhengzhong
 * @Date: 2021/1/23 11:01
 * @Description:
 */
@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = UUIDUtils.getUuid();
        String messageData = "test message, hello!";
        String createTime = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_NAME, RabbitConstant.ROUTING_NAME, map);
        return "ok";
    }

}
