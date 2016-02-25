package project.lol;

import annotations.Component;
import annotations.Initialize;
import org.apache.log4j.Logger;

/**
 * Created by Анатолий on 10.01.2016.
 */
@Component
public class Test2 {
    private static final Logger log = Logger.getLogger(Test2.class);

    @Initialize(lazy = true)
    public void init(){
        log.info("lazy = true class Test2 method init");
    }
    @Initialize(lazy = false)
    public void init2(){
        log.info("lazy = false class Test2 method init2");
    }
    @Initialize(lazy = true)
    public void init3(){
        log.info("lazy = true class  Test2 method init3");
    }
}
