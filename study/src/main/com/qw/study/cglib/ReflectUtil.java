package main.com.qw.study.cglib;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: study
 * @description: 给某个类动态添加属性
 * @author: HyJan
 * @create: 2021-01-07 14:43
 **/
@Slf4j
public class ReflectUtil {

    /**
     * 动态获取属性
     * @param dest
     * @param addProperties
     * @return
     */
    public static Object getTarget(Object dest, Map<String, Object> addProperties) {
        PropertyUtilsBean propertyUtilsBean =new PropertyUtilsBean();
        // 通过工具类获取当前类的所有属性
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        // 定义一个map来存在当前类的所有已有属性
        Map<String, Class> propertyMap = new HashMap<>();
        for(PropertyDescriptor d : descriptors) {
            // 判断当前类的属性是不是一个对象
            if(!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // add extra properties
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        // new dynamic bean
        DynamicBean dynamicBean =new DynamicBean(dest.getClass(), propertyMap);
        // add old value
        propertyMap.forEach((k, v) -> {
            try{
                // filter extra properties
                if(!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        // add extra value
        addProperties.forEach((k, v) -> {
            try{
                dynamicBean.setValue(k, v);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }

    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         * @return
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator =new BeanGenerator();
            if(null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    public static class Student <T>{
        private String name;
        private String email;
        private T data;
    }


    public static void main(String[] args) throws Exception{
        Student<Object> jack = Student.builder().name("jack").email("xy123zk@163.com").build();
        Student<Student> student = new Student("haha","dfasfasd",jack);
//        System.out.println(student.toString());
        Map<String,Object> properties = new HashMap<>();
        properties.put("address","浙江杭州");
        properties.put("age",26);
        Object target = getTarget(student, properties);
//        System.out.println(target.toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(getTarget(student,properties));
        System.out.println(json);
    }
}
