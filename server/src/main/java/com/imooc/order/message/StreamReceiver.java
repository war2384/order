package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by weiyuhang on 2019/1/3.
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    @StreamListener(value = StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver: {}", message);
//    }

//    /**
//     * 接收orderDTO对象 消息
//     * @param message
//     */
//    @SendTo(StreamClient.INPUT)
//    public String process(OrderDTO message) {
//        log.info("StreamReceiver: {}", message);
//        return "received.";
//    }

    @StreamListener(value = StreamClient.INPUT)
    public void process(String message) {
        log.info("StreamReceiver2: {}", message);
    }
}
