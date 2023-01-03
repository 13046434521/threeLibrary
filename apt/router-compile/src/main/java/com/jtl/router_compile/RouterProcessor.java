package com.jtl.router_compile;

import com.google.auto.service.AutoService;
import com.jtl.router_annotation.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)//jdk版本
@SupportedAnnotationTypes({"com.jtl.router_annotation.Route", "com.jtl.router_annotation.BindView"})
//注解全类名
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
        // 类名
        String beforeClassName = "";

        // bindView 代码逻辑
        Set<? extends Element> bindViewSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        if (bindViewSet.isEmpty()){
            messager.printMessage(Diagnostic.Kind.WARNING,"又走了一遍:"+bindViewSet.size());
            return false;
        }


        HashMap<String,ArrayList<Element>> classMap = new HashMap<>();
        for (Element element : bindViewSet){
            beforeClassName = element.getEnclosingElement().getSimpleName().toString();
            ArrayList<Element> arrayList;
            if (!classMap.containsKey(beforeClassName)){
                arrayList= new ArrayList<>();
                classMap.put(beforeClassName,arrayList);
            }else {
                arrayList = classMap.get(beforeClassName);
            }
            arrayList.add(element);
        }

        Iterator<String> iterator = classMap.keySet().iterator();
        while (iterator.hasNext()){
            ArrayList<Element> arrayList = classMap.get(iterator.next());

            javaFile(arrayList);
        }

        return true;
    }

    public void javaFile(ArrayList<Element> arrayList){
        String afterClassName = "$$BindView";
        String beforeClassName = "";
        // 包名
        String packageName ="";
        // 代码
        CodeBlock.Builder code = CodeBlock.builder();
        for (Element element : arrayList) {
            VariableElement variableElement = (VariableElement) element;

            packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
            beforeClassName = element.getEnclosingElement().getSimpleName().toString();

            String name = variableElement.toString();
            long id =variableElement.getAnnotation(BindView.class).id();
            code.addStatement("activity.$N = activity.findViewById($L)",name,id);
        }

        ParameterSpec parameterSpec = ParameterSpec
                .builder(ClassName.get(packageName,beforeClassName),"activity")
                .build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(parameterSpec)
                .addCode(code.build())
                .addStatement("$T.out.println($S)",System.class,afterClassName)
                .returns(TypeName.VOID)
                .build();

        String className = beforeClassName+afterClassName;
        messager.printMessage(Diagnostic.Kind.OTHER, className);
        TypeSpec typeSpec = TypeSpec.classBuilder(className)

                .addMethod(methodSpec)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName,typeSpec).build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}