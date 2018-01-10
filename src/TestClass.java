
/*
Robot class
Timing class
Automation
Multithreading? (i.e. continue to click until something)
Extend, Polymorphism


 */

public class TestClass  {

    TestClass(){
        go();
    }

    public static void main(String[] args){
        new TestClass();
    }

    void go(){
        GUI gui = new GUI();
        System.out.println("MAKING THREAD NOW");
    }
}
