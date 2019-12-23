package com.hnq.study.web;

import com.hnq.study.annotation.MyAopBean;
import com.hnq.study.annotation.MyController;
import com.hnq.study.annotation.MyService;
import com.hnq.study.bean.BeanMessage;
import com.hnq.study.bean.MethodMessage;
import com.hnq.study.factory.BeanFactory;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class ObjectInstantiates {

    /**
     * 扫描类注解，进行对象实例化
     */
    public static void instance(List<Class> classes) {
        for (Class clazz : classes) {
            boolean annoFlag = false;
            BeanMessage bm = new BeanMessage();
            if (clazz.isAnnotationPresent(MyController.class)) {
                annoFlag = true;
                MyController controller = (MyController) clazz.getAnnotation(MyController.class);
                String value = controller.value();
                if (!StringUtils.isEmpty(value)) {
                    addNameClass(value, clazz.getName(), bm, BeanMessage.CONTROLLER);
                } else {
                    String name = controller.name();
                    if (!StringUtils.isEmpty(name)) {
                        addNameClass(name, clazz.getName(), bm, BeanMessage.CONTROLLER);
                    }
                }
            } else if (clazz.isAnnotationPresent(MyService.class)) {
                annoFlag = true;
                MyService service = (MyService) clazz.getAnnotation(MyService.class);
                String value = service.value();
                if(!StringUtils.isEmpty(value)) {
                    addNameClass(value, clazz.getName(), bm, BeanMessage.SERVICE);
                } else {
                    String name = service.name();
                    if (!StringUtils.isEmpty(name)) {
                        addNameClass(name, clazz.getName(), bm, BeanMessage.SERVICE);
                    }
                }
            } else if (clazz.isAnnotationPresent(MyAopBean.class)) {
                MyAopBean aopBean = (MyAopBean) clazz.getAnnotation(MyAopBean.class);
                String value = aopBean.value();
                addNameClass(value, clazz.getName(), bm, BeanMessage.AOP);
            }

            if (annoFlag) {
                try {
                    bm.setBean(clazz.newInstance());
                    Method[] methods = clazz.getDeclaredMethods();
                    List<MethodMessage> methodMessages = new ArrayList<>();
                    for (Method method : methods) {
                        MethodMessage mm = new MethodMessage();
                        mm.setMethod(method);
                        mm.setMethodName(method.getName());
                        mm.setParamNames(getParamNames(method.getName(), clazz));
                        mm.setParamTypes(Arrays.asList(method.getParameterTypes()));
                        methodMessages.add(mm);
                    }
                    bm.setMethods(methodMessages);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BeanFactory.getInstance().addBean(bm);
            }
        }
    }

    private static void addNameClass(String name, String className, BeanMessage bm, String type) {
        bm.setName(name);
        bm.setClassName(className);
        bm.setBeanType(type);
    }

    /**
     * 通过该类的方法名，获取该方法的入参名称列表
     */
    private static List<String> getParamNames(String methodName, Class clazz) {
        List<String> paramNames = null;
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get(clazz.getName());
            CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);

            MethodInfo methodInfo = ctMethod.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute)
                    codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (localVariableAttribute != null) {
                int len = ctMethod.getParameterTypes().length;
                int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
                paramNames = IntStream.range(0, len).mapToObj(
                        i -> localVariableAttribute.variableName(i + pos)).collect(Collectors.toList());
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return paramNames;
    }
}
