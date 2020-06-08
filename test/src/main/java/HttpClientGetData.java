
//import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wefly.common.DataClass;
import com.wefly.common.ResponseList;
import com.wefly.common.Result;
import com.wefly.common.TencentModelField;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: test
 * @description: 使用httpclient去获取数据
 * @author: HyJan
 * @create: 2020-06-05 17:31
 **/
public class HttpClientGetData {

    /**
     * 非在创建对象时指定的泛型，使用此方式进行泛型操作
     * 有个特点，就是返回的也是一个泛型数据，比如是List<T>
     * @param url
     * @return
     * @throws Exception
     */
    public static Result getForData(String url) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        Result result = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 请求体内容(获取到的内存)
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
//                System.out.println(JSON.toJSONString(content));
//                result = JSON.parseObject(JSON.toJSONString(content),Result.class);
                JSONObject jsonObject = JSONObject.fromObject(content);
                Map<String, Class> classMap = new HashMap<String, Class>();
                classMap.put("REPORT_LEVEL_ADVERTISER",TencentModelField.class);
                classMap.put("REPORT_LEVEL_CAMPAIGN",TencentModelField.class);
                classMap.put("REPORT_LEVEL_ADGROUP",TencentModelField.class);
                classMap.put("REPORT_LEVEL_AD",TencentModelField.class);
                classMap.put("REPORT_LEVEL_PROMOTED_OBJECT",TencentModelField.class);
                classMap.put("REPORT_LEVEL_UNION_POSITION",TencentModelField.class);
                classMap.put("REPORT_LEVEL_CREATIVE_TEMPLATE",TencentModelField.class);
                classMap.put("REPORT_LEVEL_DEEPLINK_ADGROUP",TencentModelField.class);
                classMap.put("REPORT_LEVEL_EXPAND_TARGETING_ADGROUP",TencentModelField.class);
                classMap.put("REPORT_LEVEL_ADVERTISER_WECHAT",TencentModelField.class);
                classMap.put("REPORT_LEVEL_CAMPAIGN_WECHAT",TencentModelField.class);
                classMap.put("REPORT_LEVEL_ADGROUP_WECHAT",TencentModelField.class);
                classMap.put("REPORT_LEVEL_AD_WECHAT",TencentModelField.class);
                classMap.put("ALL",TencentModelField.class);
                result = (Result) JSONObject.toBean(jsonObject, Result.class, classMap);
            }
        } finally {
            //相当于关闭浏览器
            httpclient.close();
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        Result result = getForData("https://developers.e.qq.com/webservice/docs/get_report_response_conditionally?path=daily_reports&g_tk=5381");
        ResponseList responseList = result.getData().getResponseList();


//        System.out.println(responseList.toString());
        List<TencentModelField> report_level_ad = responseList.getREPORT_LEVEL_ADVERTISER();

        report_level_ad.forEach(System.out::println);

        List<TencentModelField> data = responseList.getALL();
        data.forEach(System.out::println);
    }

}
