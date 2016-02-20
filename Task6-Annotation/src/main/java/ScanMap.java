import annotations.Initialize;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Анатолий on 02.02.2016.
 */
public class ScanMap {
    private static final Logger log = Logger.getLogger(Initialization.class);
    public static Map list;
    public static List<String> listBool;

    public ScanMap() {
        list = Initialization.list;
        listBool = new ArrayList<>();
    }

    public void callMap() {
        for (Object instanse : list.values()) {
            if (!listBool.contains(instanse.getClass().getName())) {
                Method[] methods = instanse.getClass().getMethods();
                scanMethods(methods, instanse);
                listBool.add(instanse.getClass().getName());
            }
        }
    }

    private void scanMethods(Method[] method, Object instance) {
        for (Method md : method) {
            Initialize initialize = md.getAnnotation(Initialize.class);
            if (initialize != null) {
                if (initialize.lazy()) {
                    try {
                        md.invoke(instance);
                    } catch (IllegalArgumentException | IllegalAccessException |
                            InvocationTargetException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
    }
}
