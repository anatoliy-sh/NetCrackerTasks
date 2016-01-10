package project;

import annotations.Component;
import annotations.Initialize;

/**
 * Created by Анатолий on 10.01.2016.
 */
@Component
public class Test {

    @Initialize(lazy = true)
    public void init(){
        System.out.println("Test: init");
    }
    @Initialize(lazy = false)
    public void init2(){
        System.out.println("Test: init2");
    }
    @Initialize(lazy = true)
    public void init3(){
        System.out.println("Test: init3");
    }
}
