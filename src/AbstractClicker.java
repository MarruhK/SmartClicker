import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Random;

public abstract class AbstractClicker {
    Robot clicker;
    private boolean continueClicking = true;
    private long startingTime;
    private long elapsedTime;

    // If i call super() in sub-c will this be called twice or once?
    AbstractClicker(){
        try {
            this.clicker = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Commences auto-clicking.
     *
     * This method is meant to be over-ridden to add more specific specification based on the type of clicking you
     * want.
     */
    void commenceClicking(){
        int startTimeDelay = 5000;
        this.clicker.delay(startTimeDelay);
        this.startingTime = System.currentTimeMillis();
    }

    protected void commenceClicking(int sleepTime) {
        while (this.continueClicking) {
            this.elapsedTime = System.currentTimeMillis() - this.startingTime;
            long beforeClickTime = System.currentTimeMillis();
            doubleClick();

            this.clicker.delay(sleepTime);

            writeTime(System.currentTimeMillis() - beforeClickTime);
        }
        System.out.println("Clicker officially stopped.");
    }

    /**
     * Clicks twice with the time between clicks slowly increasing with respect to the parameter, {@code elapsedTime}.
     *
     * Note: This method sleeps the thread so there is no need to add an additional sleep in subclasses for the sake of
     * less CPU usage.
     *
     * @param elapsedTime The time, in milliseconds, the {@code SmartClicker} has been running.
     */
    final void doubleClick() {
        int time = timeBetweenSpamClicks();
        this.singleClick();
        this.clicker.delay(time);
        this.singleClick();

        try {
            GUI.writer.write("Time between clicks in double click: " + time + " ms");
            GUI.writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    final int timeBetweenSpamClicks() {
        long maxTime = 21600000;
        long minTime = 110;
        Random random = new Random();

        // Generate a random double from 0.95 to 1.05
        double variance = 0.95 + (random.nextDouble() * 0.1);  // 0.1 is the range width (1.05 - 0.95)
        long flatTimeBetweenClicks = (320 * this.elapsedTime / maxTime) + minTime;
        return (int) (flatTimeBetweenClicks * variance);
    }

    // Mimics a single click.
    final void singleClick(){
        this.clicker.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.clicker.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    final void writeTime(long time) {
        try {
            GUI.writer.write("Iteration time: " + time + " ms");
            GUI.writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Breaks the {@code commenceClicking} infinite loop by setting the used boolean to false.
    final void stopClicking(){
        this.continueClicking = false;
    }
}
