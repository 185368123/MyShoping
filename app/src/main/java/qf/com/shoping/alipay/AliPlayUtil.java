package qf.com.shoping.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/16.
 */
public class AliPlayUtil {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016073000127713";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088102169192555";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "nnoqjc3819@sandbox.com";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSn7KGfVrDR6AW0VGNoH94v7xNzmo6GPst+GXFtkBHVgyx7JDBWPKrgjMkviCOAuFmZ3Sz9kQ8MyyG4WtyfmG2j/zvqAwLrc3DVHHApx3wyf3VM9IItFBojtV1faBetyE9byF9+fcce1f/TkD1UIEno/tfMd/bpH1N34LG7Oub00Ed4uSpYWgz4pzusXDdtJTaTlLgzydZVf0tr+bjeQ5R7EyiYLgsGQWHWe3VI178AzbMsP4xrRxtIr7ul0cW5wN8nBp46HAfcDET4pudouGVkxe0UD5J3GFr1dU57HhqDFKzIhfGNLdpDjjuYoH8wG48xcqiitPKuypN4i9K4l23AgMBAAECggEAIqDg0u6MHw6tenYZIq/DK0ttN5sGb44y1FqT/qvaZn6mJOdQELnnJiZr2cOlgKShg0PvxgjleKpFW92lOLcscKd0/0za+28i53zsGKnfYlzyPAYvFqsvSmJhe4wd8gCiMxYp/MyqVemzkLJ0mPtYZXpPBd6Llh41Ec0137FYu8Y+u+aamv3FrVzD4sHZfvbjE5qMr62LhzOW7NMF6x2wqZAlJc9XYMSg5E+au2tDeCcwaghdujtci7TNTdfdffhvTx+KozMTbpa9QZbUkG5httRNZQx3df03U/RYStC6I5sw/haCuho+67y6lz/Dxyu5hQPHJZ4EtaQEqQkk8XcTcQKBgQDZiCFqJnOsrPi6xAJ5l6dc2ohAzdc0iKPdEqctmGsP+svAa3t94jzvAELm3G1O2SW1fjnqwZhM83LAbiXZ2mrhJVpr83dIM5IQoaUfNxNpqEXfvZvlfUrS6nh7dLsaIdWERTXsdShUerptCsdXKrRh1PjIdvLJYUFMKqrsmSFXywKBgQCsjX82aHWC+dLOfhSdN/QcZycFXi/VGQWEGOKBSav7xcOEaJKCkJU86shQhdPA41efyQs1ZnhmKqOSnqh7Q7r/HmmURvXLCl0nSchqhFlhrX3XYhT2eur7JrMv5+i+XyYoWcCfnOp/FOdKjLv3AMNcrS32AdQj435DJN+AcXKcRQKBgGFL2y5AbqLAZywaOcUrCMemUyUxLF2p9CkQi8gAEXr/QR0atgyTIZAA/OuHkjifNZedEsimuLlKdnwNiE6NvBph+7SnHCYmMuHPeyygFMXAZyhxBYa66Lm3+BW4G2vP0FGuJzVRHa3Tiwj0tj6NskrD2FbJpURfhLZNq0/SGjDLAoGAepqXdytFw+iKltoS/5rQQpYwram8xudeefvMhRy5rM1aHOzInSjqQTx5VG2ihf2DNy98XVcP2VgWnYlKITuA1ODhF9BLuRcGFXJhiv27HKJPNgik6HVyHT3kN/fJ8epdjHIpYb/3BpDtvpS8wcazCVirvY6TE2UDQ+YhQHxbeRkCgYEAuXUZjhseI0dSj8f23MgcnSFQoBfQAGBAdfBaXagQJgRYLzVfE0RgA6C/1/ieam+OaNEwmtkj8UP4K0NLRN7SdClQ8MZN9S8Z3+SVEnPHaeXDdsCWfjzXKq0+jOytxRNEM7W3elepdUs7Sr7mqzO8QIbhxfIGw7IgmbhRiiUUidk=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    public static void pay(final Activity act, final Handler mHandler, PayBean bean) {

        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, true, bean);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = RSA2_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, true);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(act);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        //沙箱测试代码
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
}
