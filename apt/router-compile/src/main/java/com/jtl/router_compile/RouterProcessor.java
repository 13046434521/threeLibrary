package com.jtl.router_compile;

import com.google.auto.service.AutoService;
import com.jtl.router_annotation.BindView;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)//jdk版本
@SupportedAnnotationTypes({"com/jtl/router_annotation/Route","com/jtl/router_annotation/BindView"})//注解全类名
public class RouterProcessor extends AbstractProcessor {
    private Messager messager;
    private Elements elementUtils;
    private Filer filer;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> bindViewSet =roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element:bindViewSet){
            String packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
            String className = elementUtils.getPackageOf(element).getSimpleName().toString();

        }


        return false;
    }
}