package com.slef.joinx.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {
   @Value("${app_id}")
   public  String app_id ;
   
   @Value("${merchant_private_key}")
   public  String merchant_private_key ;
   @Value("${alipay_public_key}")
   public  String alipay_public_key ;
   
   @Value("${notify_url}")
   public  String notify_url ;
   
   @Value("${return_url}")
   public  String return_url ;
   
   @Value("${sign_type}")
   public  String sign_type ;
   
   @Value("${charset}")
   public  String charset ;
   
   @Value("${gatewayUrl}")
   public  String gatewayUrl;
}
