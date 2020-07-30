import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.util.FreemarkerGeneratorUtil;
import com.wefly.common.Result;
import com.wefly.common.TencentModelField;
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
import java.util.stream.Stream;

/**
 * @program: test
 * @description: 获取页面的值并生成类
 * @author: HyJan
 * @create: 2020-05-27 15:12
 **/

public class GetResponse {
    //    private static String url = "https://developers.e.qq.com/docs/reference/enum?version=1.2";
//    private static String url = "https://developers.e.qq.com/docs/api/adsmanagement/adcreatives/adcreatives_add?version=1.3";
//    private static String url = "https://developers.e.qq.com/docs/api/adsmanagement/adcreatives/adcreatives_update?version=1.3";
    private static String url = "https://developers.e.qq.com/docs/api/insights/ad_insights/daily_reports_get?version=1.3";
    private static String url_address = "https://developers.e.qq.com/webservice/docs/get_report_response_conditionally?path=daily_reports&g_tk=5381";

    public static void main(String[] args) throws Exception {
        getArticleListFromUrl(url_address);
//        generatorEnumClass();
//        System.out.println(toDeleteTrim("jfkdas;j*"));
    }

    /**
     * 获取文章列表
     * https://blog.csdn.net/guoxiaolongonly/article/details/51497495
     * http://xiaolongonly.cn/2016/05/08/Reptile2/
     *
     * @param url
     */
    public static void getArticleListFromUrl(final String url) throws Exception {
//        boolean isStop = false;
//
//        Document doc = null;
//        try {
//            trustEveryone();
//            doc = Jsoup.connect(listurl).userAgent(getUserAgent()).timeout(3000).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 爬取腾讯数据
        Result result = HttpClientGetData.getForData(url);

        String[] filters = new String[]{"网页","商品","电话","表单","销售"};

        List<TencentModelField> list = result.getData().getResponseList().getALL();

        System.out.println("list size before is  > > > >  " + list.size());

        // 进行过滤
        Iterator<TencentModelField> iterator = list.iterator();
        while (iterator.hasNext()){
            TencentModelField tencentModelField = iterator.next();
            Boolean isContain = false;

            for (String filter: filters
                 ) {
                if (tencentModelField.getDisplayName().contains(filter)){
                    isContain = true;
                }
            }

            if (isContain){
                // 要使用迭代器中的remove方法
                iterator.remove();
            }
        }

        System.out.println("list size after is  > > > >  " + list.size());

        // 字段名称
        String[] name = new String[list.size()];

        // 对应的码 code
        String[] code = new String[list.size()];

        // 对应的值 msg
        String[] values = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            // 字段类型
            name[i] = firstToUpperCase(toDeleteTrim(list.get(i).getType()));

            // 获取到的字段名（原来腾讯的字段名）
            code[i] = toDeleteTrim(list.get(i).getName());

            // 字段名(驼峰)
            values[i] = toCaseName(list.get(i).getName());
        }

        System.out.println("==============================");

//        System.out.println(name.toString() + ":" + code.toString() + ":" + values.toString());

//        toData("TencentAdCreativeAddRequest",name,values,code,"template-pojo.ftl");
        FreemarkerGeneratorUtil.toData("TencentDailyReportInfo",name,values,code,"template-pojo.ftl");

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
     * 去掉一些指定的字符
     * @param str
     * @return
     */
    public static String toDeleteTrim(String str){
        if (str.contains("*")){
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
     * @param string
     * @return
     */
    public static String firstToUpperCase(String string){
        if (Objects.equals(string,"struct")){
            return "Object";
        }
        if (Objects.equals(string,"struct[]")) {
            return "List<>";
        }
        if (Objects.equals(string,"string[]")){
            return "List<String>";
        }
        if (Objects.equals(string,"integer[]")){
            return "List<Integer>";
        }
        if (Objects.equals(string,"float")){
            return "BigDecimal";
        }
        return string.substring(0,1).toUpperCase() + string.substring(1);
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

