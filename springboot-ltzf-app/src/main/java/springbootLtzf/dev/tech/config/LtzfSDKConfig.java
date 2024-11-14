package springbootLtzf.dev.tech.config;

import lombok.extern.slf4j.Slf4j;
import ltzf.factory.PayFactory;
import ltzf.factory.defaults.DefaultPayFactory;
import ltzf.payments.h5.H5PayService;
import ltzf.payments.nativepay.NativePayService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(LtzfSDKConfigProperties.class)
public class LtzfSDKConfig {

    @Bean(name = "payFactory")
    @ConditionalOnProperty(value = "ltzf.sdk.config.enabled", havingValue = "true", matchIfMissing = false)
    public PayFactory payFactory(LtzfSDKConfigProperties properties) {
        ltzf.factory.Configuration configuration = new ltzf.factory.Configuration(
                properties.getAppId(),
                properties.getMerchantId(),
                properties.getPartnerKey()
        );
        return new DefaultPayFactory(configuration);
    }

    @Bean(name = "nativeService")
    @ConditionalOnProperty(value = "ltzf.sdk.config.enabled", havingValue = "true", matchIfMissing = false)
    public NativePayService nativePayService(PayFactory payFactory) {
        log.info("蓝兔支付 SDK 启动成功,扫码支付服务已装配");
        return payFactory.nativePayService();
    }

    @Bean(name = "h5Service")
    @ConditionalOnProperty(value = "ltzf.sdk.config.enabled", havingValue = "true", matchIfMissing = false)
    public H5PayService h5PayService(PayFactory payFactory) {
        log.info("蓝兔支付 SDK 启动成功,H5支付服务已装配");
        return payFactory.h5PayService();
    }
}
