package project;

import annotations.Component;
import annotations.Initialize;
import org.apache.log4j.Logger;

/**
 * Created by Анатолий on 10.01.2016.
 */
@Component
public class Test {
    private static final Logger log = Logger.getLogger(Test.class);

    @Initialize(lazy = true)
    private void init(){
        log.info("lazy = true class Test method init");
    }
    @Initialize(lazy = false)
    private void init2(){
        log.info("lazy = false class Test method init2");
    }
    @Initialize(lazy = true)
    private void init3(){
        log.info("lazy = true class Test method init3");
    }
}
