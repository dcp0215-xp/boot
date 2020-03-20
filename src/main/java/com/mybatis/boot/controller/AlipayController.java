package com.mybatis.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.mybatis.boot.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LX
 * @Date 2020/2/21 10:49
 * @Description
 */
@RestController
public class AlipayController {
    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/paySuccess")
    public String paySuccess() {
        return "SUCCESS";
    }

    @RequestMapping("/payFail")
    public String payFail() {
        return "ERROR!";
    }


    /**
     * web ����֧��
     */
    @GetMapping("getPagePay")
    public Map<Object, Object> getPagePay() throws Exception {
        /** ģ�����ݿ⣬�Ӻ�̨������*/
        String outTradeNo = "19960310621211";
        Integer totalAmount = 1;
        String subject = "ƻ��28";

        String pay = alipayService.webPagePay(outTradeNo, totalAmount, subject);
        System.out.println(pay);

        Map<Object, Object> pays = new HashMap<>();
        pays.put("pay", pay);

        return pays;
    }

    /**
     * app ����֧��
     */
    @GetMapping("getAppPagePay")
    public Object getAppPagePay() throws Exception {
        /** ģ�����ݿ⣬�Ӻ�̨������*/
        String outTradeNo = "131233";
        Integer totalAmount = 1000;
        String subject = "��è����012";

        String pay = alipayService.appPagePay(outTradeNo, totalAmount, subject);

        Object json = JSONObject.toJSON(pay);

        System.out.println(json);

        return json;
    }

    /**
     * ���ײ�ѯ
     */
    @GetMapping("aipayQuery")
    public Object alipayQuery() throws Exception {
        /**��ȡ֧��������*/
        String outTradeNo = "20200221110158";

        String query = alipayService.query(outTradeNo);

        Object json = JSONObject.toJSON(query);

        /*JSONObject jObject = new JSONObject();
        jObject.get(query);*/
        return json;
    }

    /**
     * �˿�
     *
     * @throws AlipayApiException
     */
    @GetMapping("alipayRefund")
    public Object alipayRefund(
            @RequestParam("outTradeNo") String outTradeNo,
            @RequestParam(value = "outRequestNo", required = false) String outRequestNo,
            @RequestParam(value = "refundAmount", required = false) Integer refundAmount
    ) throws AlipayApiException {

        /** ��ȡ����*/
        //String outTradeNo = "15382028806591197";
        String refundReason = "�û����빺��";
        //refundAmount = 1;
        //outRequestNo = "22";

        String refund = alipayService.refund(outTradeNo, refundReason, refundAmount, outRequestNo);

        System.out.println(refund);

        return refund;
    }

    /**
     * �˿��ѯ
     *
     * @throws AlipayApiException
     */
    @PostMapping("refundQuery")
    public Object refundQuery() throws AlipayApiException {

        /** ��ȡ����*/
        String outTradeNo = "13123";
        String outRequestNo = "2";

        String refund = alipayService.refundQuery(outTradeNo, outRequestNo);

        return refund;

    }

    /**
     * ���׹ر�
     *
     * @throws AlipayApiException
     */
    @GetMapping("alipayclose")
    public Object alipaycolse() throws AlipayApiException {

        /** ��ȡ����*/
        String outTradeNo = "13123";

        String close = alipayService.close(outTradeNo);

        return close;
    }

}

