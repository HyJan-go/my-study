package com.toutiao;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.wefly.freemaker.Field;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.lang.model.element.Modifier;
import javax.net.ssl.*;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @program: test
 * @description: 获取页面的值并生成类(带上注释)
 * 头条不知道是不是做了反爬虫，是拿不到数据的
 * 先拿去sublime text 进行处理一下，然后组成一个数组
 * 此时的解决方案要改一下，复制页面的值下来做成数组，然后构建来生成
 * @author: HyJan
 * @create: 2020-05-27 15:12
 **/

public class GetPojo {


    public static void main(String[] args) throws Exception {
        getArticleListFromUrl();
    }

    /**
     * 获取文章列表
     * https://blog.csdn.net/guoxiaolongonly/article/details/51497495
     * http://xiaolongonly.cn/2016/05/08/Reptile2/
     */
    public static void getArticleListFromUrl() throws Exception {

        String[] table = new String[]{"stat_datetime string 数据起始时间",
                "cost float 展现数据-总花费",
                "show number 展现数据-展示数",
                "avg_show_cost float 展现数据-平均千次展现费用",
                "avg_click_cost float 展现数据-平均点击单价",
                "ctr float 展现数据-点击率",
                "convert number 转化数据-转化数",
                "convert_cost float 转化数据-转化成本",
                "convert_rate float 转化数据-转化率",
                "deep_convert number 转化数据-深度转化数",
                "deep_convert_cost float 转化数据-深度转化成本",
                "deep_convert_rate float 转化数据-深度转化率",
                "attribution_convert number 转化数据（计费时间）-转化数（计费时间）",
                "attribution_convert_cost float 转化数据（计费时间）-转化成本（计费时间）",
                "attribution_deep_convert number 转化数据（计费时间）-深度转化数（计费时间）",
                "attribution_deep_convert_cost float 转化数据（计费时间）-深度转化成本（计费时间）",
                "download_start number 应用下载广告数据-安卓下载开始数",
                "download_start_cost float 应用下载广告数据-安卓下载开始成本",
                "download_start_rate float 应用下载广告数据-安卓下载开始率",
                "download_finish number 应用下载广告数据-安卓下载完成数",
                "download_finish_cost float 应用下载广告数据-安卓下载完成成本",
                "download_finish_rate float 应用下载广告数据-安卓下载完成率",
                "click_install number 应用下载广告数据-点击安装数",
                "install_finish number 应用下载广告数据-安卓安装完成数",
                "install_finish_cost float 应用下载广告数据-安卓安装完成成本",
                "install_finish_rate float 应用下载广告数据-安卓安装完成率",
                "active number 应用下载广告数据-激活数",
                "active_cost float 应用下载广告数据-激活成本",
                "active_rate float 应用下载广告数据-激活率",
                "register number 应用下载广告数据-注册数",
                "active_register_cost float 应用下载广告数据-注册成本",
                "active_register_rate float 应用下载广告数据-注册率",
                "next_day_open number 应用下载广告数据-次留（未对回）",
                "next_day_open_cost float 应用下载广告数据-次留成本（未对回",
                "next_day_open_rate float 应用下载广告数据-次留率（未对回)",
                "attribution_next_day_open_cnt number 应用下载广告数据-次留数",
                "attribution_next_day_open_cost number 应用下载广告数据-次留成本",
                "attribution_next_day_open_rate number 应用下载广告数据-次留率",
                "game_addiction number 应用下载广告数据-关键行为数",
                "game_addiction_cost float 应用下载广告数据-关键行为成本",
                "game_addiction_rate float 应用下载广告数据-关键行为率",
                "pay_count number 应用下载广告数据-付费数",
                "active_pay_cost float 应用下载广告数据-付费成本",
                "active_pay_rate float 应用下载广告数据-付费率",
                "loan_completion number 应用下载广告数据-完件数",
                "loan_completion_cost float 应用下载广告数据-完件成本",
                "loan_completion_rate float 应用下载广告数据-完件率",
                "pre_loan_credit number 应用下载广告数据-预授信数",
                "pre_loan_credit_cost float 应用下载广告数据-预授信成本",
                "loan_credit number 应用下载广告数据-授信数",
                "loan_credit_cost float 应用下载广告数据-授信成本",
                "loan_credit_rate float 应用下载广告数据-授信率",
                "in_app_uv number 应用下载广告数据-APP内访问",
                "in_app_detail_uv number 应用下载广告数据-APP内访问详情页",
                "in_app_cart number 应用下载广告数据-APP内加入购物车",
                "in_app_pay number 应用下载广告数据-APP内付费",
                "in_app_order number 应用下载广告数据-APP内下单",
                "phone number 落地页转化数据-点击电话按钮",
                "form number 落地页转化数据-表单提交",
                "map_search number 落地页转化数据-地图搜索",
                "button number 落地页转化数据-按钮button",
                "view number 落地页转化数据-关键页面浏览",
                "download number 落地页转化数据-下载开始",
                "qq number 落地页转化数据-QQ咨询",
                "lottery number 落地页转化数据-抽奖",
                "vote number 落地页转化数据-投票",
                "message number 落地页转化数据-短信咨询",
                "redirect number 落地页转化数据-页面跳转",
                "shopping number 落地页转化数据-商品购买",
                "consult number 落地页转化数据-在线咨询",
                "wechat number 落地页转化数据-微信复制",
                "phone_confirm number 落地页转化数据-智能电话-确认拨打",
                "phone_connect number 落地页转化数据-智能电话-确认接通",
                "consult_effective number 落地页转化数据-有效咨询",
                "coupon number 落地页转化数据-建站卡券领取",
                "coupon_single_page number 落地页转化数据-卡券页领取",
                "total_play number 视频数据-播放数",
                "valid_play number 视频数据-有效播放数",
                "valid_play_cost float 视频数据-有效播放成本",
                "valid_play_rate float 视频数据-有效播放率",
                "play_25_feed_break number 视频数据-25%进度播放数",
                "play_50_feed_break number 视频数据-50%进度播放数",
                "play_75_feed_break number 视频数据-75%进度播放数",
                "play_100_feed_break number 视频数据-99%进度播放数",
                "average_play_time_per_play float 视频数据-平均单次播放时长",
                "play_over_rate float 视频数据-播完率",
                "wifi_play_rate float 视频数据-WiFi播放占比",
                "wifi_play number 视频数据-WiFi播放量",
                "play_duration_sum number 视频数据-播放时长，单位ms",
                "advanced_creative_phone_click number 附加创意-附加创意电话按钮点击",
                "advanced_creative_counsel_click number 附加创意-附加创意在线咨询点击",
                "advanced_creative_form_click number 附加创意-附加创意表单按钮点击",
                "advanced_creative_coupon_addition number 附加创意-附加创意卡券领取",
                "share number 互动数据-分享数",
                "comment number 互动数据-评论数",
                "like number 互动数据-点赞数",
                "follow number 互动数据-新增关注数",
                "home_visited number 互动数据-主页访问量",
                "ies_challenge_click number 互动数据-挑战赛查看数",
                "ies_music_click number 互动数据-音乐查看数",
                "location_click number 互动数据-POI点击"};


        // 字段名称
        String[] name = new String[table.length];

        // 对应的码 code(原来的字段名字,eg: a_b_c)
        String[] code = new String[table.length];

        // 对应的值 msg
        String[] values = new String[table.length];

        // 注释值
        String[] desc = new String[table.length];

        for (int i = 0; i < table.length; i++) {
            String[] s = table[i].split(" ");
            // 字段类型
            name[i] = firstToUpperCase(toDeleteTrim(s[1]));

            System.out.println(s[0]);

            System.out.println(name[i]);

            // 获取到的字段名
            code[i] = toDeleteTrim(s[0]);

            // 获取注释字段
            desc[i] = s[2];

            System.out.println("注释: " + s[2]);

            // 字段名
            values[i] = toCaseName(s[0]);
        }

        System.out.println("==============================");

//        System.out.println(name.toString() + ":" + code.toString() + ":" + values.toString());

        // 不带注释的实体类
//        toData("TencentAdCreativeAddRequest",name,values,code,"template-pojo.ftl");
        // 生成带注释的实体类
        toData("ToutiaoAdvertiserReport", name, values, code, desc, "template-pojo-desc.ftl");

    }

    public static void toData(String className, String[] fields, String[] value, String[] code, String tpl) throws Exception {

        if (StringUtils.isBlank(tpl)) {
            tpl = "template.ftl";
        }

        File file = new File("src/main/java");

        Map<String, Object> data = new HashMap<>();
        List<Field> list = new ArrayList<>();
        if (Objects.isNull(code)) {
            for (int i = 0; i < fields.length; i++) {
                list.add(new Field(fields[i], String.valueOf(i), value[i]));
            }
        } else {
            for (int i = 0; i < fields.length; i++) {
                list.add(new Field(fields[i], code[i], value[i]));
            }
        }
        data.put("fields", list);
        data.put("className", className);

        generate(tpl, data, file);
    }

    /**
     * 带注释的实体类生成
     *
     * @param className
     * @param fields
     * @param value
     * @param code
     * @param desc
     * @param tpl
     * @throws Exception
     */
    public static void toData(String className, String[] fields, String[] value, String[] code, String[] desc, String tpl) throws Exception {

        if (StringUtils.isBlank(tpl)) {
            tpl = "template.ftl";
        }

        File file = new File("src/main/java");

        Map<String, Object> data = new HashMap<>();
        List<Field> list = new ArrayList<>();
        if (Objects.isNull(code)) {
            for (int i = 0; i < fields.length; i++) {
                list.add(new Field(fields[i], String.valueOf(i), value[i]));
            }
        } else {
            for (int i = 0; i < fields.length; i++) {
                list.add(new Field(fields[i], code[i], value[i], desc[i]));
            }
        }
        data.put("fields", list);
        data.put("className", className);
        generate(tpl, data, file);
    }

    /**
     * 去掉一些指定的字符
     *
     * @param str
     * @return
     */
    public static String toDeleteTrim(String str) {
        if (str.contains("*")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 将小写下划线变成驼峰名形式
     * eg: a_bcdd_e -> aBcddE
     *
     * @param name
     * @return
     */
    public static String toCaseName(String name) {
        name = toDeleteTrim(name);
        String[] s = name.split("_");
        String className = "";
        for (int i = 0; i < s.length; i++) {
            if (i != 0) {
                className += s[i].substring(0, 1).toUpperCase() + s[i].substring(1);
            } else {
                className += s[i];
            }
        }
        System.out.println("field name :    " + className);
        return className;
    }

    /**
     * 将小写下划线变成类名形式
     * eg: a_bcdd_e -> ABcddE
     *
     * @param name
     * @return
     */
    public static String toClassName(String name) {
        String[] s = name.split("_");
        String className = "";
        for (String str : s
        ) {
            className += str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        System.out.println("class name" + className);
        return className;
    }

    /**
     * 将传入值首字母大写
     *
     * @param string
     * @return
     */
    public static String firstToUpperCase(String string) {
        if (Objects.equals(string, "struct")) {
            return "Object";
        }
        if (Objects.equals(string, "struct[]")) {
            return "List<>";
        }
        if (Objects.equals(string, "string[]")) {
            return "List<String>";
        }
        if (Objects.equals(string, "integer[]")) {
            return "List<Integer>";
        }
        if (Objects.equals(string,"float")){
            return "BigDecimal";
        }
        if (Objects.equals(string,"number")){
            return "Integer";
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * 生成类代码
     * https://blog.csdn.net/u010782846/article/details/80213939
     */
    public static void generatorEnumClass(String className, String[] name, String[] values) throws Exception {
        File file = new File(System.getProperty("user.dir"), "\\src\\main\\java");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TypeSpec.Builder builder = TypeSpec.enumBuilder(className).addModifiers(Modifier.PUBLIC);

        for (int i = 0; i < name.length; i++) {
            builder.addEnumConstant(name[i], TypeSpec.anonymousClassBuilder("$S", values[i]).build());
        }

        builder.addField(String.class, "msg", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(String.class, "msg")
                        .addStatement("this.$N=$N", "msg", "msg").build())
                .addMethod(MethodSpec.methodBuilder("getMsg")
                        .returns(String.class)
                        .addStatement("return this.$N", "msg").build());

        TypeSpec typeSpec = builder.build();

        // 类名称
//        TypeSpec typeSpec = TypeSpec.enumBuilder("EnumsTest")
//                .addModifiers(Modifier.PUBLIC)
////                .addEnumConstant("Rock", TypeSpec.anonymousClassBuilder("$S", "Page")
////                        .addMethod(MethodSpec.methodBuilder("toString")
////                                .addAnnotation(Override.class)
////                                .addModifiers(Modifier.PUBLIC)
////                                .addStatement("return $S", "avalanche")
////                                .returns(String.class)
////                                .build())
////                        .build())
//                // 对应名称和参数值
//                .addEnumConstant("Sci", TypeSpec.anonymousClassBuilder("$S", "ssss").build())
//                .addEnumConstant("Apple", TypeSpec.anonymousClassBuilder("$S", "sad").build())
//                .addField(String.class, "hand", Modifier.PRIVATE, Modifier.FINAL)
//                .addMethod(MethodSpec.constructorBuilder()
//                        .addParameter(String.class, "hand")
//                        .addStatement("this.$N=$N", "hand", "hand").build())
//                .build();

        JavaFile javaFile = JavaFile.builder("com.wefly.common.CodeEnum.java", typeSpec).build();

        javaFile.writeTo(file);
    }

    /**
     * 获取文章内容
     *
     * @param detailurl
     */
    public static void getArticleFromUrl(String detailurl) {
        try {
            Document document = Jsoup.connect(detailurl).userAgent("Mozilla/5.0").timeout(3000).post();
            Element elementTitle = document.getElementsByClass("link_title").first();//标题。 这边根据class的内容来过滤
            System.out.println(elementTitle.text());
            String filename = elementTitle.text().replaceAll("/", "或");
            Element elementContent = document.getElementsByClass("article_content").first();//内容。
            // String Content =elementContent.te  xt().replaceAll(" ", "\t");
            // System.out.println(elementContent.text()+"\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 保存文章到本地
     *
     * @param titile
     * @param content
     * @param blogName
     */
    public static void saveArticle(String titile, String content, String blogName) {
        String lujing = "d:\\MyLoadArticle\\" + blogName + "\\" + titile + ".txt";//保存到本地的路径和文件名
        File file = new File(lujing);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 信任任何站点，实现https页面的正常访问
     */

    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    /**
     * 随机获取一个代理
     *
     * @return
     */
    public static String getUserAgent() {

        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};

        Integer index = new Random().nextInt(ua.length);

        return ua[index];
    }

    /**
     * freemarker 生成
     *
     * @param configurationTemplate 模板路径
     * @param data                  数据
     * @throws IOException
     * @throws TemplateException
     */
    private static void generate(String configurationTemplate, Map<String, Object> data, File dir) throws IOException, TemplateException {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        Configuration configuration = freeMarkerConfigurer.getConfiguration();

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(dir);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = cfg.getTemplate(configurationTemplate);

        // 指定生成的文件格式 java ,StringBuilder就是指定生成的文件名路径等等
        File file = new File(System.getProperty("user.dir"), new StringBuilder("\\src\\main\\java\\com\\qw\\wefly\\core\\modules\\tencent\\").append(data.get("className")).append(".java").toString());

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        template.process(data, writer);

        writer.close();
    }
}

