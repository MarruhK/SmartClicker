import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;

abstract class SmartClicker{
    Robot clicker;
    private double randomizingMultiplier;
    boolean continueClicking = true;
    long startingTime;
    long beforeClickTime;
    long elapsedTime;

    // If i call super() in sub-c will this be called twice or once?
    SmartClicker(){
        try {
            clicker = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the time between "spam clicks."
     *
     * Spam clicks are fast consecutive clicks. The time between clicks increases as time progresses, in a human like
     * fashion. This method is subject to further chance as more data regarding human clicking is obtained.
     *
     * @param elapsedTime The total time, in milliseconds, the {@code SmartClicker} has been running.
     * @return The time, in milliseconds, between each spam click.
     */
    final int timeBetweenSpamClicks(long elapsedTime){
        randomizingMultiplier = 1 + (.24 + (Math.random() * .11));
        if (elapsedTime < 120000){
            return (int) (10 * (11 + (25 * (elapsedTime / 120000.0))) * randomizingMultiplier);    // About 110 - 460ms
        } else {
            return (int) (10 * (36 + (16 * (elapsedTime / 480000.0))) * randomizingMultiplier);    // About 460 - 690ms
        }
    }

    /**
     * It breaks the  the {@code commenceClicking} infinite loop by setting the used boolean to false.
     *
     */
    final void stopClicking(){
        continueClicking = false;
    }

    /**
     * Clicks twice with the time between clicks slowly increasing with respect to the parameter, {@code elapsedTime}.
     *
     * Note: This method sleeps the thread so there is no need to add an additional sleep in subclasses for the sake of
     * less CPU usage.
     *
     * @param elapsedTime The time, in milliseconds, the {@code SmartClicker} has been running.
     */
    final void doubleClick(long elapsedTime) {
        singleClick();
        int time = timeBetweenSpamClicks(elapsedTime);
        clicker.delay(time);
        singleClick();

        try {
            GUI.writer.write("Time between clicks in double click: " + time + " ms");
            GUI.writer.write(System.getProperty( "line.separator" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mimics a single click.
     *
     */
    final void singleClick(){
        clicker.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        clicker.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Commences auto-clicking.
     *
     * This method is meant to be over-ridden to add more specific specification based on the type of clicking you
     * want.
     */
    void commenceClicking(){
        try{
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("DID NOT SLEEP");
        }

        startingTime = System.currentTimeMillis();
    }

    final void writeTime(long time){
        try {
            GUI.writer.write("Itteration time: " + time + " ms");
            GUI.writer.write(System.getProperty( "line.separator" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
