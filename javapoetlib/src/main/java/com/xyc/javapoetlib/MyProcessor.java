package com.xyc.javapoetlib;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.xyc.annotationlib.MyAnnotation;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)  // 需要添加
public class MyProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;
    private Messager mMessager;


    private static final String PACK_NAME = "com.yxhuang.temp";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();  // 版本
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(MyAnnotation.class.getCanonicalName());      // 注解的类
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.print("MyProcessor process");

        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC) // 添加标识符 public static
                .returns(void.class) // 返回类型
                .addParameter(String[].class, "args") // 方法的参数
                .addStatement("$T.out.println($S)", System.class, "Hello World!")  // 方法里面的语句声明
                .build();

        // 构建 HelloWorld 类
        TypeSpec hellWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        // 生成 java 文件
        JavaFile javaFile = JavaFile.builder(PACK_NAME, hellWorld).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (Exception e){
            e.printStackTrace();

        }

        return true;
    }
}