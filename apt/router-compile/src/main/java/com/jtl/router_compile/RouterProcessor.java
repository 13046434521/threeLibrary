package com.jtl.router_compile;

import com.google.auto.service.AutoService;
import com.jtl.router_annotation.BindView;
import com.jtl.router_annotation.Route;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
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
    private int count = 0;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.WARNING,"process 执行次数:"+count++);
        routeBranch(set, roundEnvironment);

        bindViewBranch(set, roundEnvironment);

        return true;
    }

    public void routeBranch(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment){
        // bindView 代码逻辑
        Set<? extends Element> routeSet = roundEnvironment.getElementsAnnotatedWith(Route.class);

        if (routeSet.isEmpty()){
            messager.printMessage(Diagnostic.Kind.WARNING,"Route:"+routeSet.size());
            return;
        }

        // 我需要拿到path 和 当前的全类名：包名+类名
        HashMap<String,String> clazzMap = new HashMap<>();
        String packageName ="";
        for (Element element:routeSet){
            TypeElement typeElement = (TypeElement) element;
            // 当前包名
            packageName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
            // 注解修饰的类的全类名
            String clazzPath = typeElement.getQualifiedName().toString();
            // 注解修饰的类的类名
            String clazzName = typeElement.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.WARNING,"elementUtils.getPackageOf(typeElement).getQualifiedName().toString():"+packageName);
            messager.printMessage(Diagnostic.Kind.WARNING,"typeElement.getQualifiedName().toString():"+clazzPath);
            messager.printMessage(Diagnostic.Kind.WARNING,"typeElement.getSimpleName().toString():"+clazzName);

            String path = typeElement.getAnnotation(Route.class).path();
            messager.printMessage(Diagnostic.Kind.WARNING,"typeElement.getAnnotation(Route.class).path():"+path);

            // 添加 Route(path)的值 和 Route修饰的类的全类名
            clazzMap.put(path,clazzPath);
        }

        javaFile(clazzMap,packageName);
    }


    public void javaFile(HashMap<String,String> clazzMap,String packageName){
        String className = "Router$$Route";

        // 变量
        FieldSpec fieldSpec = FieldSpec.builder(HashMap.class,"pathMap")
                .addModifiers(Modifier.PRIVATE)
                .build();

        // 构造方法
        // 构造器中的代码块：创建hashMap
        CodeBlock.Builder constructorBuilder = CodeBlock.builder()
                .addStatement("pathMap = new HashMap()");

        // 构造器中的代码块：给hashMap添加数据
        Set<String> keySet = clazzMap.keySet();
        for (String path:keySet){
            String clazzPath = clazzMap.get(path);
            constructorBuilder.addStatement("pathMap.put($S,$S)",path,clazzPath);
        }
        CodeBlock constructorCodeBlock = constructorBuilder.build();

        // 构造方法
        MethodSpec constructorMethod = MethodSpec.constructorBuilder()
                .addCode(constructorCodeBlock)
                .addModifiers(Modifier.PUBLIC)
                .build();

        // inject方法参数
        ParameterSpec parameterSpec = ParameterSpec.builder(String.class,"path").build();
        // inject方法体
        CodeBlock injectBlock = CodeBlock.builder()
                .addStatement("String clazzPath = (String)pathMap.get(path)")
                .addStatement("return clazzPath")
                .build();
        // inject方法
        MethodSpec injectSpec = MethodSpec.methodBuilder("inject")
                .addParameter(parameterSpec)
                .addModifiers(Modifier.PUBLIC)
                .addCode(injectBlock)
                .returns(String.class)
                .build();
        // 编译出的类
        TypeSpec typeSpec = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldSpec)
                .addMethod(constructorMethod)
                .addMethod(injectSpec)
                .build();

        // 编译到类要编译到哪个package中
        // 此处library无法调用application中的类，导致api无法调用Router$$Route类
        // 所以暂将Router$$Route类创建到com.jtl.router_api中
        // packageName = "com.jtl.router_api";
        JavaFile javaFile = JavaFile.builder(packageName,typeSpec)
                .build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void bindViewBranch(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment){
        // bindView 代码逻辑
        Set<? extends Element> bindViewSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        if (bindViewSet.isEmpty()){
            messager.printMessage(Diagnostic.Kind.WARNING,"BindView:"+bindViewSet.size());
            return;
        }

        // 类名
        String beforeClassName = "";
        // BindView 分出不同的activity逻辑
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
                .addModifiers(Modifier.PUBLIC)
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