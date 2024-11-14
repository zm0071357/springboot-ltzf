package springbootLtzf.dev.tech;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ltzf.payments.nativepay.NativePayService;
import ltzf.payments.nativepay.model.PrepayRequest;
import ltzf.payments.nativepay.model.PrepayResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private NativePayService nativePayService;

    @Test
    public void test_nativePayService_prePay() throws IOException {
        // 请求参数
        PrepayRequest request = new PrepayRequest();
        request.setMchId("1698731613");
        request.setOutTradeNo("LTZF202411142212");
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("http://123.57.226.115:7600/api/pay_notify");
        // 创建支付订单
        PrepayResponse reponse = nativePayService.prePay(request);
        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("返回结果:{}", JSON.toJSONString(reponse));
    }
}
