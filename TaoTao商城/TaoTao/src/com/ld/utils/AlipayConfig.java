package com.ld.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016110200785306";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCL7LHoYW0IF0xOQm2BI2F7k9gDOGsk6v/yrsp79wF28Oz5Mrmbk5MXZ0TEK9w8MUdQEJyJaVYwo79sRBmcdaHc7pRGYsHTqoTDCWN6ac0M/hFzwxSqMGLXAasLTkJzcs36VHRaDK3+j1gSBWfoMlX0jCPU7bh95CjEboHlakvf5Ks36n0CJQ6GTqOjkI2ITlwzZ09KvSboUtNgPtXQN9coJqT5ucCamkBLK7fmPPi0qsN1RigXkJkADXgFUCK2ob26wfATYRHY/y58KWrrmH+xmOmmHxi8tPCpbjZNmcwf9YsQ+RL7ZSdrgg6+moy0JSDt9Oe5wSxjxu/5PuV1G0jhAgMBAAECggEAdo+x07CxvThYjLtWpCTBlf+Y+mmUx+xjwVN/+Pg8And0pFbp1Lx/10XGi96KN2qd/WaF7R0XThGBwpAVj3BRHa3+b4GAsQR8h/Jtmy0I9ShWy1sSKH7lUT91mhfO0JoHlER5bk/uIpN9UY/JEoOMN7GDLwna2Lk6He9xG14tOmD7y7otY64D5jSvxK+VJGhUmDO6ZGdKzdM9k7cbHkS+qETtU8XRGcCPhCvF4XGZmwsI/8uR7y4OAVzvtJ+LCiCJVYWlzgEQAh98VXGDQ0SHoz291Qfhi31avU4Dh+/T7H3pPhH3Bnx+LfziekHpqWpT5h38+LXjKKJ9nkA7izt+kQKBgQDHsyuZJxTNcoa5cZtycmZNTvVEqOphuaL+IYRWrheIzO21veopnyyRfNIUO2EnTxpDD1WIG3nbPJnurdtncFSwYgD6S8N8i0UQXEjgBhrMM3ePqBAOaxDD55PLHReMB0iYtdDPlIDaxIJnL0sBHJnvhh+Kty7Rhe/kg49TU1odZQKBgQCzX2Uv+jF2VgUUt2cP5tObqEv8z4T772xQAYSUENWmxtwGki8T/Dycw3ujW4gSYHoEqTT9ChumbPCKvZEyCjhm0pjddXOTlU4HVAWfWPuOPEUAKFydiL2uXYtmGHrIYtVZrSQnwg/nF0tUrN+DNG2O54JLbcKlTD4pMhW9SyJTzQKBgE/Zn5w8mT/RBHfQKmTnw2o0ITRUCmwLC1ZsVntGX1ivAHnEN4ZvBCYkb+fxGlbadVjNH9kti1sTb0WVtP42V1azyUpL0x3Qnkv9apJRPpPaHHuPquPpAaojxd/YgRrXqrqNMLJf4eoX20ChE2+dXRv+WExiFNFoqdSIml2qHYVVAoGBAKlBvgi8rIm+d8LpoP23UTwTXcirxQ+6B9mi5QiWrhRWl2436OaR4FwIdhVanJbCpMDbLd4L+LVdHMm+QVEXpcsYtBhc6Pd9Lojptvh7DbxUkMOqUmJwkpQDPzPSq6GE6U9aesBtdXYZXAMFsU9sXGk5eZBVfRNfU1SqJytcx4KNAoGBAKBaO8+RgvigQPBfkTUrJPCvt/n3ChayCO8R58pbsYG5kUEcRNTylhpC7ThSiTd/tZsHaWQu4ChmalYTzcb8c0jnXRGiQcWlVlTmKTjhzHe8z1gb2wtXN+NA1Jf0lonTL3+tm1n+l2cGdwa/Fg4Y9oKVpTtLKHg+aMT1+PlTm93c";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp/DSCh96UCbG+4spi7TDUSKC7BjbtzwST2rg6xe+Ky/fNPeIJuKE1dUfThx4XIKuARf+X6pqxduO2Y0e200QSVKk8IpZX+UQt7kgZ70We1v0/UgsnPDDAMNqQQS5I+rNvuQSkBRXbruCZm02XnM0lYwWtB9hH/wwBYYhGGxMBOOaGk8vnyLKn9Iqnr2DVVs8K9mONWAk9yAeDqsOtURq3OUL6w75aTw4Q2kFAbyk2MXWmrHpjw2A03bANN0ZohnJKyK73jqHnceddsXfhy52FVlDKMrvIhYsfrwVURTe7QMvP7VMI+vt7uMJKGjkp0njC8+CfFinV4v4Z89bsPBZSwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/TaoTao/index";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://172.16.28.33:8080/TaoTao/return_url";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
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

