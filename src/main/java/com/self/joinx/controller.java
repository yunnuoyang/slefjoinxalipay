package com.self.joinx;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.slef.joinx.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class controller {
   @Autowired AlipayConfig alipayConfig;
   
   @RequestMapping("/alipay/pay")
   public String pay(String orderId,String amount,String productName){
      //获得初始化的AlipayClient
      AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
      //设置请求参数
      AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//      alipayRequest.setReturnUrl(AlipayConfig);
//      alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
   
      //商户订单号，商户网站订单系统中唯一订单号，必填
      String out_trade_no = orderId;
      //付款金额，必填
      String total_amount = amount;
      //订单名称，必填
      String subject = productName;
      //商品描述，可空
      String body = "用户订购商品个数：" + amount;
      // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
      String timeout_express = "1c";
   
      alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
             + "\"total_amount\":\""+ total_amount +"\","
             + "\"subject\":\""+ subject +"\","
             + "\"body\":\""+ body +"\","
             + "\"timeout_express\":\""+ timeout_express +"\","
             + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
      String result="";
      //请求
      try {
          result = alipayClient.pageExecute(alipayRequest).getBody();
      } catch (AlipayApiException e) {
         e.printStackTrace();
      }
      return result;
   }
   
   /**
    * 同步验证接口
    * @param request
    * @return
    * @throws AlipayApiException
    * @throws UnsupportedEncodingException
    */
   @RequestMapping("/alipay/alipayReturnNotice")
   public String alipayReturnNotice(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
      //获取支付宝GET过来反馈信息
      Map<String,String> params = new HashMap<String,String>();
      Map<String,String[]> requestParams = request.getParameterMap();
      for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
         String name = (String) iter.next();
         String[] values = (String[]) requestParams.get(name);
         String valueStr = "";
         for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                   : valueStr + values[i] + ",";
         }
         //乱码解决，这段代码在出现乱码时使用
         valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
         params.put(name, valueStr);
      }
   
//      boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
      System.out.println("同步验证成功");
      
      return "success";
   }
   
   /**
    * 异步验证接口
    * @param request
    * @return
    * @throws AlipayApiException
    */
   @RequestMapping("/alipay/alipayNotifyNotice")
   public String alipayNotifyNotice(HttpServletRequest request) throws AlipayApiException {
      //获取支付宝POST过来反馈信息
      Map<String,String> params = new HashMap<String,String>();
      Map<String,String[]> requestParams = request.getParameterMap();
      for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
         String name = (String) iter.next();
         String[] values = (String[]) requestParams.get(name);
         String valueStr = "";
         for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                   : valueStr + values[i] + ",";
         }
         //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
         params.put(name, valueStr);
      }
   
//      boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
      System.out.println("异步验证成功");
   
      return "asy";
   }
   
}
