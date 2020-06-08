//package com.qw.study.elasticsearch;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.DocWriteResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//
//import java.io.IOException;
//
///**
// * @program: study
// * @description: restful风格的客户端查询
// * http 手段的端口是 9200  transport的是9300 （默认）
// * http 亲测是成功了的。
// * @author: HyJan
// * @create: 2020-04-21 16:03
// **/
//public class HttpRestfulClientTest {
//
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        //Es连接信息，我用的是伪集群哈哈哈
////                        new HttpHost("localhost", 9200, "http"),
////                        new HttpHost("localhost", 9201, "http"),
//                        new HttpHost("192.168.75.134", 9200, "http")
//                )
//        );
//
//
//        IndexRequest request = new IndexRequest("posts");
//        request.id("1");
//        String jsonString = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//        request.source(jsonString, XContentType.JSON);
//
//        saveOrUpdate(new Student("搞笑", 20));
//
//        //do something
//        //记得关闭，不然进程会等待
//        client.close();
//
//    }
//
//
//    public static void saveOrUpdate(Student book) {
//        IndexRequest request = new IndexRequest("terry").id("book");
//
//        String jsonStr = JSON.toJSONString(book);
//        request.source(jsonStr, XContentType.JSON);
//
//        IndexResponse indexResponse = null;
//        try(
//                RestHighLevelClient client = getInstance()
//        ) {
//            indexResponse = client.index(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (indexResponse!=null){
//            String index = indexResponse.getIndex();
//            String id = indexResponse.getId();
//            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
//                System.out.println("_index:"+index+" document:"+id +" created successfully!");
//            }else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED){
//                System.out.println("_index:"+index+" document:"+id +" updated successfully!");
//            }else {
//                System.out.println("不明操作类型:" + indexResponse.getResult());
//            }
//        }
//    }
//
//    private static RestHighLevelClient getInstance() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("192.168.75.134", 9200, "http")
//                        )
//                );
//        return client;
//    }
//
//}
