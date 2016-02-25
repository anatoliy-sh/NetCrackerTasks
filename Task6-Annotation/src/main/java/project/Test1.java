package project;

import annotations.Component;
import annotations.Initialize;
import org.apache.log4j.Logger;

/**
 * Created by Анатолий on 10.01.2016.
 */
@Component
public class Test1 {
    private static final Logger log = Logger.getLogger(Test1.class);

    @Initialize(lazy = true)
    public void init(){
        log.info("lazy = true class Test1 method init");
    }
    @Initialize(lazy = false)
    public void init2(){
        log.info("lazy = false class Test1 method init2");
    }

    public void init3(){
        log.info("class Test1 method init3");
    }
}
