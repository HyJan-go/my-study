package com.util;

import com.wefly.freemaker.Field;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @description: freemarker 生成辅助类
 * @author: HyJan
 * @create: 2020-07-30 11:33
 **/
public class FreemarkerGeneratorUtil {

    /**
     * 不带注释的实体类生成
     * @param className
     * @param fields
     * @param value
     * @param code
     * @param tpl
     * @throws Exception
     */
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
        setTime(data);
        generate(tpl, data, file);
    }

    private static void setTime(Map<String,Object> data){
        // ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
        String YEAR = String.valueOf(LocalDateTime.now().getYear());
        String MONTH = String.valueOf(LocalDateTime.now().getMonthValue());
        String DAY = String.valueOf(LocalDateTime.now().getDayOfMonth());
        String HOUR = String.valueOf(LocalDateTime.now().getHour());
        String MINUTE = String.valueOf(LocalDateTime.now().getMinute());
        data.put("YEAR",YEAR);
        data.put("MONTH",MONTH);
        data.put("DAY",DAY);
        data.put("HOUR",HOUR);
        data.put("MINUTE",MINUTE);
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
        setTime(data);
        generate(tpl, data, file);
    }

    /**
     * 带注释和类描述的
     * @param className
     * @param fields
     * @param value
     * @param code
     * @param desc
     * @param description 生成的类名描述
     * @param tpl
     * @throws Exception
     */
    public static void toData(String className, String[] fields, String[] value, String[] code, String[] desc,String description, String tpl) throws Exception {

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
        data.put("desc",description);
        setTime(data);
        generate(tpl, data, file);
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

        // 判断一下类描述,没有值就赋初始值
        if (Objects.isNull(data.get("desc"))){
            data.put("desc","TODO");
        }
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
