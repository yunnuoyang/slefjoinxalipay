# slefjoinxalipay
调用支付宝的测试接口进行支付功能的实现
步骤：1进入阿里的平台尽心注册申请https://openhome.alipay.com/platform/appDaily.htm?tab=account
     获取公钥私钥
     2.maven种导入demo的依赖
     <dependency>
      <groupId>com.alipay.sdk</groupId>
      <artifactId>alipay-sdk-java</artifactId>
      <version>3.1.0</version>
    </dependency>
    3.编写代码调用。
    //获得初始化的AlipayClient，主要是其对外提供的调用接口
      AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
    AlipayConfig类中的主要的参数
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
