package com.mybatis.boot.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

/**
 * @Author LX
 * @Date 2019/12/13 16:17
 * @Description TODO
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Autowired
    ExchangeAndQueue exchangeAndQueue;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MessageConverter messageConverter() {
        //��Ϣת���������л�����
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //*��ʹ��confirm-callback��return-callback��
        //*����Ҫ����publisherConfirms��publisherReturnsΪtrue
        //*ÿ��rabbitTemplateֻ����һ��confirm-callback��return-callback
        template.setConfirmCallback((correlationData, b, s) -> {
            log.info("=========================��Ϣ����ȷ��=========================");
            log.info("MsgSendConfirmCallBack  , �ص�id:" + correlationData);
            if (b) {
                log.info("��Ϣ�����ɹ�");
            } else {
                log.error("��Ϣ����ʧ��:" + s + "���·���");
            }
            log.info("=============================================================");

        });

        /**
         * ʹ��return-callbackʱ��������mandatoryΪtrue������������������mandatory-expression��ֵΪtrue��
         * �����ÿ���������Ϣȥȷ����mandatory����booleanֵ��
         * ֻ�����ṩ��return -callback��ʱʹ�ã���mandatory����
         */
        template.setReturnCallback((message, i, s, s1, s2) -> {
            String msg = "";
            try {
                msg = new String(message.getBody(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            log.info("============================û�ж��н�����Ϣ==============================");
            log.info("��Ϣ����:{}, ������:{}, ·�ɼ�:{}:, ʧ��ԭ��:{}", msg, s1, s2, s);
        });
        template.setMandatory(true);
        template.setMessageConverter(this.messageConverter());
        return template;
    }


}
