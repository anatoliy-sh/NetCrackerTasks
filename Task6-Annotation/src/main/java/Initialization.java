import annotations.Component;
import annotations.Initialize;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Анатолий on 10.01.2016.
 */
public class Initialization {
    private static final Logger log = Logger.getLogger(Initialization.class);

    public static Map list;
    public void scan() {
        Set<Class<?>> classes = getClasses();
        list = new HashMap<String, Object>();
        for (Class cl : classes) {
            if (cl.isAnnotationPresent(Component.class)) {
                log.info(cl.getName() + " annotated");
            }
            Method[] methods = cl.getMethods();
            Object instance = getInstance(cl);
            list.put(instance.getClass().getName(),instance);
            log.info(list.size());
            scanMethods(methods, instance);
        }
    }


    private Set<Class<?>> getClasses() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("project"))));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        System.out.println(classes.size());
        return classes;
    }

    private void scanMethods(Method[] method, Object instance) {
        for (Method md : method) {
            Initialize initialize = md.getAnnotation(Initialize.class);
            if (initialize != null) {
                if (!initialize.lazy()) {
                    try {
                        md.invoke(instance);
                    } catch (IllegalArgumentException | IllegalAccessException|
                            InvocationTargetException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
    }

    private Object getInstance(Class cl) {
        Object instance = null;
        try {
            instance = cl.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e){
            log.error(e.getMessage());
        }
        return instance;
    }
}
