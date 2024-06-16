public class ClickingThread implements Runnable {
    private AbstractClicker clicker;

    public ClickingThread(){
        this.clicker = null;
    }
    public ClickingThread(AbstractClicker clicker){
       this.clicker = clicker;
    }

    @Override
    public void run() {
        System.out.println("Auto Clicking has begun.");
        this.clicker.commenceClicking();
    }
}
