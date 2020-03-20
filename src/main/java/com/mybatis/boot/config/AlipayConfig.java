package com.mybatis.boot.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author LX
 * @Date 2020/2/21 9:33
 * @Description
 */

@Configuration
public class AlipayConfig {

    // Ӧ��ID,����APPID���տ��˺ż�������APPID��Ӧ֧�����˺�
    public static String app_id = "2016101900722813";

    // �̻�˽Կ������PKCS8��ʽRSA2˽Կ
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCB/51z+z21EL+5gmQR2Xtg9apONOx6hIEpwL4b3wmCMUdwAZlyvT5kE22CX6pMD+t3QB8kTMchnfDRTBVhkorp6SHCNVv7LNVWjenLhFsbIPyPDp7G/h0XGortZHZzzjrhFdCSDaPFcFA0/zEvV8ui9I6QrIaezQ6v3lXxiAZuIOylipu3BIF19cyZYfKI3gFqA3wOozUFUGbD77GkIxumhZUYXH5kb691pXoUx4XYZg/yMsGQTgU6ZmqAoB5Jxf6g3P7kDnJBTq3k4BGOKEsqyObtu8qafRUygaWAAwhO/OeUFeYW1WEhkfwFEhFVLLeTMIO5UpNwOolw0DLXlRPXAgMBAAECggEAHRbe29onh8HUtSU5UUkyM5MlZdSv6gaGhJpNsBzrmsd+LdEdzjAMqwYx5g+UIusZDJk7nrrTgHFANwVob2oETa1OUY/xIjZ5EqI048adQGfUPArQesTYK5hT6CUn4GgQgteH0Xbd48trF0DXd7mUpsVEtZj1ksld9Pqyjpaaob/EoL3hfpFT2RioNmwM2F21Nax5fLdQDEPKkS70Z31D+WF2hYjlPGbWpqI0hyeswqOayao+Ipq6suTnzugXbfdkXYawneYYJQSHlxhkp7QjZCZ6fcWtcbpfvtvdKJ3xekw6WNCjYSlyj7yWV7jC5V9MugB/CckRagSH7GtualB6yQKBgQD5u2BGFBxUGyjO7ypF3UXywQ4WsvNp7TRuEhYpfrdbRbqRipEWCP6olWbMsSdpGURViRplCGh8kNbzVLHHmzSPY1PgUAGhmodqcmyF5vETRDdaRH60awdrSryDKBq8k/pNW2df6X98JNCSC56Tz7yhMiKoHTIh6muihxARU1gtOwKBgQCFQufNtkTzjHkkS3nvT+KaoyI0O242mU7OzKva88NJsoRRD5gSblQDYFxeN68Oeqku8hN1q8Ezlfaw8EQvFuitLXUKvMf7soPNAtWn04qbRpB0uUuxqeKr5C5KvzInGj2cmEGpbOFcCaLGwB9AdLqAvIo3q7IztSa6EXDCS646FQKBgGmf5fVd9ilgn17+6ytHjplBLDtPjwKjcESJ49PDI7w3/tQl/n2KamNVZXh4CUo7hJqE9aP77W2aOUAL93GMZKutAEHktPvsBxBvuxTbe+WBOcQ1Kj4W8rlfj+INaVmPYROs8Ekx0ljonUrYHUc5Wvf244+lYZnUs5e3doVNcgMXAoGAWVpGIVnQm9kZtStihAL1dyhhG9KpkLR08hasOLBvxi+Lpuqk1d7iOC1ZYddGapf2bsLkJ2dVsSghc/x2S3i55LxxGRUBppPI6iK3bcNa0cibvZgs1+CIWycxnu7aSvu+SgyRInAeH19apJdXnRlnoEBLEQ7v+tnBMMvW5oBmPw0CgYEA2AD6mF1UT6dnUm0Lv0Xe9N48uq+NWQSzxgzla0392q0Mdm/Ft9Akd3Tc9OgXzLS27/8ID59n0gTxqWLb2PBo9dR1HBt1PYq5mFAqNTBqoYM3Xpieo4q0m7mZezWp58npst/JwXEtwaejeP7Jq/CEwXsBf1fqIAGcrJELOQhSrx8=";

    // ֧������Կ,�鿴��ַ��https://openhome.alipay.com/platform/keyManage.htm ��ӦAPPID�µ�֧������Կ��
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    // �������첽֪ͨҳ��·��  ��http://��ʽ������·�������ܼ�?id=123�����Զ���������������������������ʣ�
    public static String notify_url = "http://th7a4z.natappfree.cc/boot/paySuccess";

    // ҳ����תͬ��֪ͨҳ��·�� ��http://��ʽ������·�������ܼ�?id=123�����Զ����������������������������
    public static String return_url = "http://th7a4z.natappfree.cc/boot/payFail";

    // ǩ����ʽ
    public static String sign_type = "RSA2";

    // �ַ������ʽ
    public static String charset = "utf-8";

    // ֧��������
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // ֧��������
    public static String log_path = "E:\\";


//�����������������������������������Ļ�����Ϣ������������������������������

    /**
     * д��־��������ԣ�����վ����Ҳ���ԸĳɰѼ�¼�������ݿ⣩
     *
     * @param sWord Ҫд����־����ı�����
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
