package main.com.qw.study.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.response.AlipayOpenOperationOpenbizmockBizQueryResponse;

/**
 * @program: study
 * @description: 尝试支付宝接口测试
 * @author: HyJan
 * @create: 2020-04-16 14:08
 **/
public class AlipayTest {

    public static void main(String[] args) {
        try {
            // 1. 创建AlipayClient实例
//            AlipayClient alipayClient = new DefaultAlipayClient(getClientParams());

            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                    "2016102300741623",
                    "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzfpdCFJhdzc5n7lhzxCbCXceW9Ni+OQ6HBnScyYwUIX8h5dWVsHYGeoHzNBVDgEXMxa4jKYfrac2aIVdAp7r6zqYKsg9XTrvqQE0i6vvX9gTWXxkxhs8NhPqX4Af2IoEcCXD/GLpNkgRRhElS/mnvWC6W1n0kHgrDvlVYDSvsCV1K3vFfOgg3m3f7BdvRcRAHzVcNwgotiGVPWi1f3I3Nd9P6k+/Z7fY91SJULShaOYE8MS0Uv0mpSuLAwcSB/HHTzWXbuRyYexjVeb6poNwzZOOLOhVjwBvzzyQlGbK1U7kUtC6DUrQnBUXXsMy2HzqK9w3xmUPdO/7CvV8XBgEjAgMBAAECggEAMPviEYGHV0ocAxbzgpxMK4zmNBcbs0Xk5gpn9oW2OaQuirSrRg+DFh+GqTVUfDhcVBa6gBQMaL1WA7MHmbhseZe9jnurXhs5gyMLKfVQx033Yf1P5ckxZjIdJSdZZN1juKAG+0bnSNYJYamlTQXrKQs6o6VjP2EHdQXTSpVFoEMzLK7cgGOq3Gc5ymAs1U+329C0wof5R06Nwgb9dT0LKA+EcFFnVPUE+djaPN4nWYrbiDVFMrwrtOTCK68eG/d6NmUmrk/ncXoP+BaZQbGFjxM+i9q4jHmX3W7inAz3ZnRIkw8ix+LDU2BzFcYhBdrVveH5TLgvrYTUsi5WnNzvQQKBgQD/UEs5pdx2PBcXz4FUFxBxSC/KfdpiYyeVaBKQniw6mKk7PV18HRhSpXmi1qwGmuHJ1Eo9wKOUQRO+2UpY1CVxYc44/A4xsCBPg9xku2xdipvfQOvU0qrlxf4qWo9XBUtZBzLZFWbflDP8hc0opFjiIY3VIbL7S0PmeggLCvjgIQKBgQCz+h5UID/v9wzL5n9nJKYGUIiDmS8ZAfXnAv6ettxG0s5U87FasqcI4W5gZAGUYCsHMHfTi67VIBFdlPkBV3LNsNtygMnLsskfxW7IsyZoEFVIJmOLkUun7ejLB4IqDl1E6LzPLmAC607PiTVf9xicntVuP9qns/xFeNvoFypIwwKBgQD1UjW2AsU/ZqXZ3y0pm6/U6cPiEePRPe0/Bm0wQIvHEgEqRuCY7Wv9nbqu2Q8amZ/yyuU7SOrk/VL3wtpJWC9SLuR0pN0OqWmifdU7hUuu5gy4DC2OpY5IkmKSV8n7l766oMwfAw2CL2l0byyerKwrPIqIDIVqKPvpbcBku/o/gQKBgG/vR52a0Sk1xceq++HycS/MNptiUmCDT6T1v9aGdEm4IovDZLK8Ghbz3eolSLY3eYtnIZ94aKw03ZgOZy/ma4hyTonQf7Eo6N3RtS0t/t0KC4BsH81V+G/hD7/2U4ZEmM2a0pMojJ5EKyf4djHRRIVkY+nF3QAgeO3cVSjr/2hxAoGBAJaP7d9H9gky4l6OLQBOc/FCrhzOmYA3BJFJ5CLVj8TDPIpyJ75GdE1zTblOxu4/tud717VyNMD6q9mWBXV6PHVw5isG+vIvFKPRVVEnAODIj2IUxjWWYvnTq///Th5Bus+rVgmQDPgdY33EGmQ8N9teX3Hy3g20GOmmXzmSqs+r",
                    "json","utf-8",
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhhCEz5Q6DZdYLPLT2DMlcBC5dMIPutkWo0WGFNpZSiWcthC7OBpVp4Ldjd5haRC6uHKz+sTXl+Ad1dzQt57g5/mOdRtsMDNczW7QAjxcRKAvBirCR7UoF5oaO3taOdGwSlWovVK2W3W8FvSfXkMm7SrQZ1l5HWFrqNw9MBgGzUWLQL2Es98bEd3syJzAI6vGmIGiUMn7TUVW6lI+HFMIqYxqI4RaS46peGBxPJu3SNwXwIKvs1XDecJeWaIBkx/ibIoPlBxPkOhGq0SG4adC8Xpc1qmBAq/IUtF9M3jb7Ol1xWprM1Rp3k3Q7UzjOvFZ61PGq1QmCTutnjMJ/ae5uwIDAQAB",
                    "RSA2");

            // 2. 创建使用的Open API对应的Request请求对象
            AlipayOpenOperationOpenbizmockBizQueryRequest request = getRequest();
            // 3. 发起请求并处理响应
            AlipayOpenOperationOpenbizmockBizQueryResponse response = alipayClient.certificateExecute(request);
            if (response.isSuccess()) {
                //成功后的逻辑
                System.out.println("调用成功。");
            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "，" + response.getSubMsg());
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static CertAlipayRequest getClientParams() {
        CertAlipayRequest certParams = new CertAlipayRequest();
        certParams.setServerUrl("https://openapi.alipaydev.com/gateway.do");
        //请更换为您的AppId
        certParams.setAppId("2016102300741623");
        //请更换为您的PKCS8格式的应用私钥
        certParams.setPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZb9DVmO9UCyKG6jZHby0J79oDCTcEHBzG3/MMnjYjKfV0O/QwRuUCc77jS0aWKn9CIUGgF0RBCWn6PigarPzfspEgOk1euKyehYUU/gjZ3MN3hNd0T2x1WnHG/gFtwUp2cyMuijq9p0il9CllAo8BQ6Ibvyw0HdijZzZCfSiJ3PqhcbGXE+1BpNU9kzvghIf8HMt6vKJiwFS3kGYl6nyOJdTvn8IVbw3YztaIVBTLrF9pBzK7xDTpXdAgmmxaHlqBkkn5k8nPF6fUuu+iVXda2/4yIgkiccJ1kd2/tK2kJqRkThQKuf5nXaiUDlSpNQJhBnRyHxkksLZHTw3BQyYXAgMBAAECggEAZK8wDYnOuoqX/IoiVhvJJTUkDUv1sxezdnksZ0JGaiq1a7GP2kIbMtG7GDGkPxPh8AYcpy29VyQTS+ePr3mGclAd1VUMwjRS6a7aFl1gd33bMkmUjDk2BYf1E0xIqbnl3/ZLxtVkGGF2VZfztdnI915nOOfo3MYdC5TF0TmJ6XR5UGJOM90+Yufjqwe0msbYZXGF2KaPuUT1Mq38EXCpSQflsK0Vs7PSnF0H8ztJ3285FWCccEPPMR1v0K1IhbSinawRAog7Y14Zh75w8ppCmtSkn2ZlylWpE2DqFiNS3lxUHZCHlhJL9T2jWJpGn5FSnpl5qKypJOBO7taSVVRFiQKBgQDH6paU/JlC0LJF4+FBaCfjirMswjjSfVOWr9plTNkFEXHjAca+9b3c+K+UzZsMjcXkLR4DBoU7DYy0lH2NfcyvzpkT4+C2GADnsD8zLmJuQ146QJBQI6/3s1XwaBKamJYQWfAH1GVtRFViCyXEi5E1K2DbY8YMwkn7DWSoXO77xQKBgQDEezMB2b4pvajX035AMsbnmcp23aJUcTE/TZ6Xq+BPaKWYMXeD2rI0/N1EpQqRKGLnVU6tOrlc3zlad7rVL/kEDgFqhnxXbj3ZYJnOOMV4uVior3454XdwbMCCz45B61vkqOwWI05PdNmXF7HIsrK8bdi+JaOk2aOZjmyo+wUsKwKBgQCcWZyxIqjed4elX2aRF9tyesxMqg10ma2hASFl7mcgLpeKOZ5tRJXb1ubDEtY7SDnHjX2WUigqmhs8M5HJ8eP+by6LPsJEx5sVmGKlLZavs6JjHpF/C5oYuDnzDgHWxJVAboZqKTO19CZuylccUycalTVn/60p112yqbPadOw3HQKBgE4GxujTOcuFwAmFubtw7sCGuHqf5Zk3ImGLR5Hf9cJTQn/AFUBNlzunt49NI3cl3LEVlZsBmR8inroolg+Xb4KGtVVCdxqZcwJidHIK5ZDdujGzD6OYwD/rUEAigNH4z5Ns6eSYvL6dHtP72uRi4swlq09Hz0bmDywp/UjV9uXlAoGBAJV0xxJ696fhVmszqdvDJ9NOKsTROhHeLTKpEpfnSBASTJ87JTi1dNdOdVTNPvMDdJ4GoaEhptShbZsnHXbYRZUnDaLaphFrJWPupYlSpONPm1D4Jrf8eAIEIyYi5hSe+stOxT9/B64qpB/yxm9qUtreEGVBxFDW0gKbQ4PJDt07");
        //请更换为您使用的字符集编码，推荐采用utf-8
        certParams.setCharset("utf-8");
        certParams.setFormat("json");
        certParams.setSignType("RSA2");
        certParams.setPrivateKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzz9B2BjIXiewdeOyBlJtzU1tPxRtLGUpJ1H915tR0hWbaEZYsCnhZK5446o4E+Y2DFZEhTCUAfaYe/SbkPc3HQ6bcT44x90vaaV1MtTb542IxYF1HE8IkS3rRoOC5c5HCFamYpfYdtnOkNZ7e72pV2iy65gq9DlNQ27QN5YiR1DLsn+A5/TeVV8skQ7ltr7coKd1KagWmdyjfRpJCuDTqFAubiriQGjEls/l+h9nZHuoPAptOibj9RQp7NS+4Cl8bX8XbvpxFw54zV04dzVSvFZiz1o88ll13A04bOr+Ut8sGTXSeQhaVtwBxOobsk2ruSNsG08tke86YvQoRWbtEwIDAQAB");
        //请更换为您的应用公钥证书文件路径
//        certParams.setCertPath("/home/foo/appCertPublicKey_2019091767145019.crt");
        //请更换您的支付宝公钥证书文件路径
//        certParams.setAlipayPublicCertPath("/home/foo/alipayCertPublicKey_RSA2.crt");
        //更换为支付宝根证书文件路径
//        certParams.setRootCertPath("/home/foo/alipayRootCert.crt");
        return certParams;
    }

    private static AlipayOpenOperationOpenbizmockBizQueryRequest getRequest() {
        // 初始化Request，并填充Model属性。实际调用时请替换为您想要使用的API对应的Request对象。
        AlipayOpenOperationOpenbizmockBizQueryRequest request = new AlipayOpenOperationOpenbizmockBizQueryRequest();
        AlipayOpenOperationOpenbizmockBizQueryModel model = new AlipayOpenOperationOpenbizmockBizQueryModel();
        model.setBizNo("test");
        request.setBizModel(model);
        return request;
    }
}
