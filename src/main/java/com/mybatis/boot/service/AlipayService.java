package com.mybatis.boot.service;

import com.alipay.api.AlipayApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LX
 * @Date 2020/2/21 10:21
 * @Description
 */
public interface AlipayService {
    /**
     * ֧����֧�����ýӿ�
     *
     * @param response
     * @param request
     * @throws IOException
     */
    void aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException;

    /**
     * web�˶���֧��
     * @param outTradeNo    ������ţ�Ψһ��
     * @param totalAmount   �����۸�
     * @param subject       ��Ʒ����
     */
    String webPagePay(String outTradeNo,Integer totalAmount,String subject) throws Exception;

    /**
     * app�˶���֧��
     * @param outTradeNo    �������
     * @param totalAmount   �����۸�
     * @param subject       ��Ʒ����
     */
    String appPagePay(String outTradeNo,Integer totalAmount,String subject) throws Exception;

    /**
     * �˿�
     * @param outTradeNo    �������
     * @param refundReason  �˿�ԭ��
     * @param refundAmount  �˿���
     * @param outRequestNo  ��ʶһ���˿�����ͬһ�ʽ��׶���˿���Ҫ��֤Ψһ�����貿���˿��˲����ش�
     */
    String refund(String outTradeNo,String refundReason,Integer refundAmount,String outRequestNo) throws AlipayApiException;

    /**
     * ���ײ�ѯ
     * @param outTradeNo ������ţ�Ψһ��
     */
    String query(String outTradeNo) throws AlipayApiException;

    /**
     * ���׹ر�
     * @param outTradeNo ������ţ�Ψһ��
     */
    String close(String outTradeNo) throws AlipayApiException;

    /**
     * �˿��ѯ
     * @param outTradeNo ������ţ�Ψһ��
     * @param outRequestNo ��ʶһ���˿�����ͬһ�ʽ��׶���˿���Ҫ��֤Ψһ�����貿���˿��˲����ش�
     */
    String refundQuery(String outTradeNo,String outRequestNo) throws AlipayApiException;


}
