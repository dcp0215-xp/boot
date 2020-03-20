package com.mybatis.boot.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.*;
import com.mybatis.boot.config.AlipayConfig;
import com.mybatis.boot.service.AlipayService;
import com.mybatis.boot.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author LX
 * @Date 2020/2/21 10:22
 * @Description
 */
@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {


    @Override
    public void aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //��ó�ʼ����AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //�����������
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        //aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        //aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //�̻������ţ���̨����дһ������������һ�������ţ�����
        String order_number = new String(StringUtils.getOrderStr());
        //�������ǰ̨��ȡ������
        String total_amount = new String("100");
        //�������ƣ�����
        String subject = new String("������С�ɰ�");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //����
        String result = null;
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //���
        out.println(result);
        log.info("���ؽ��={}", result);

    }

    /**
     * ��ȡ֧�����ӿ� web��֧��
     */
    DefaultAlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

    /**
     * ��ȡ֧�����ӿ�app��֧��
     */
    AlipayClient alipayClients = new DefaultAlipayClient(
            AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);


    @Override
    public String webPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        /** ͬ��֪ͨ��֧����ɺ�֧���ɹ�ҳ��*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        /** �첽֪ͨ��֧����ɺ���Ҫ���е��첽����*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"��Ʒ����\","
                + "\"timeout_express\":\"90m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        /**ת����ʽ*/
        String result = alipayClient.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');
        return result;
    }

    @Override
    public String refund(String outTradeNo, String refundReason, Integer refundAmount, String outRequestNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        /** ��ȡ�ӿ�*/
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"refund_amount\":\"" + refundAmount + "\","
                + "\"refund_reason\":\"" + refundReason + "\","
                + "\"out_request_no\":\"" + outRequestNo + "\"}");
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    @Override
    public String query(String outTradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        /**����ӿ�*/
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"trade_no\":\"" + "" + "\"}");
        /**ת����ʽ*/
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    @Override
    public String close(String outTradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"trade_no\":\"" + "" + "\"}");

        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }

    @Override
    public String refundQuery(String outTradeNo, String outRequestNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        /** ����ӿ�*/
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"out_request_no\":\"" + outRequestNo + "\"}");

        /** ��ʽת��*/
        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }

    @Override
    public String appPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

        /** ͬ��֪ͨ��֧����ɺ�֧���ɹ�ҳ��*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        /** �첽֪ͨ��֧����ɺ���Ҫ���е��첽����*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        /**���۲�Ʒ�루�̶���*/
        String productCode = "QUICK_WAP_WAY";

        /** ���и�ֵ */
        AlipayTradeWapPayModel wapPayModel = new AlipayTradeWapPayModel();
        wapPayModel.setOutTradeNo(outTradeNo);
        wapPayModel.setSubject(subject);
        wapPayModel.setTotalAmount(totalAmount.toString());
        wapPayModel.setBody("��Ʒ����");
        wapPayModel.setTimeoutExpress("2m");
        wapPayModel.setProductCode(productCode);
        alipayRequest.setBizModel(wapPayModel);

        /** ��ʽת��*/
        String result = alipayClients.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');
        return result;
    }

}
