public class RunnableExiter implements Runnable {

    public SmartClicker clicker;

    public RunnableExiter(){
        clicker = null;
    }

    public RunnableExiter(SmartClicker clicker){
       this.clicker = clicker;
    }

    @Override
    public void run() {
        System.out.println("BEGIN CLICKING");
        clicker.commenceClicking();
    }
}
