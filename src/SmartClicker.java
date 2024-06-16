
/*
Robot class
Timing class
Automation
Multithreading? (i.e. continue to click until something)
Extend, Polymorphism


 */

public class SmartClicker {
    SmartClicker(){
        initializeGui();
    }

    public static void main(String[] args){
        new SmartClicker();
    }

    private void initializeGui() {
        new GUI();
    }
}
