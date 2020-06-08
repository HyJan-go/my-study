import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.lang.model.element.Modifier;
import javax.net.ssl.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @program: test
 * @description: 获取页面的值并生成类
 * @author: HyJan
 * @create: 2020-05-27 15:12
 **/

public class MySearchTest {
    private static String url = "https://developers.e.qq.com/docs/reference/enum?version=1.2";
    private static String blogName = "enums";

    public static void main(String[] args) throws Exception {
        getArticleListFromUrl(url);
//        generatorEnumClass();
    }

    /**
     * 获取文章列表
     * https://blog.csdn.net/guoxiaolongonly/article/details/51497495
     * http://xiaolongonly.cn/2016/05/08/Reptile2/
     *
     * @param listurl
     */
    public static void getArticleListFromUrl(final String listurl) throws Exception {
        boolean isStop = false;

        Document doc = null;
        try {
            trustEveryone();
            doc = Jsoup.connect(listurl).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36").timeout(3000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 找到所有类名为 colunm 的元素
        Elements elements = doc.getElementsByClass("colunm");
        for (Element element : elements) {
            Elements className = element.getElementsByClass("colunm-tit");

            System.out.println("类名" + className.first().attr("id"));

            String classNameEnum = toClassName(className.first().attr("id"));

            // 将类名化为正确的权限

            Elements blk = element.getElementsByClass("blk");

            Elements flex = blk.first().getElementsByClass("flex");

//            Node table = blk.first().getElementsByClass("table").first().childNode(5);
            Elements tag = blk.first().getElementsByClass("table").first().getElementsByTag("tbody");

            String field = "";

            String[] name = new String[tag.first().childrenSize()];
            String[] values = new String[tag.first().childrenSize()];

            for (int i = 0; i < tag.first().childrenSize(); i++) {

                String[] s = tag.first().child(i).text().split(" ");
                name[i] = s[0];
                values[i] = s[1];
            }


            HtmlData.toData(classNameEnum,name,values,null,"template-enum.ftl");
//            generatorEnumClass(classNameEnum, name, values);

            System.out.println(field);

//            for (int i = 0; i < flex.size(); i++) {
//                System.out.println("左侧属性" + flex.get(i).text());
//            }

            System.out.println("==============");

        }


//        doc.getElementsByClass()

//        if (!isStop) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if (!listurl.contains("list")) {
//                        getArticleListFromUrl(listurl + "/article/list/1");//获取下一页的列表
//                    } else {
//                        getArticleListFromUrl(listurl.substring(0, listurl.length() - 1) +
//                                (Integer.valueOf(listurl.substring(listurl.length() - 1, listurl.length())) + 1));//获取下一页的列表
//                    }
//                }
//            }).start();
//        }
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
        return new StringBuilder("Tencent").append(className).append("Enums").toString();
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

        JavaFile javaFile = JavaFile.builder("com.wefly.common.enums", typeSpec).build();

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
            saveArticle(filename, elementContent.text(), blogName);
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
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

}

