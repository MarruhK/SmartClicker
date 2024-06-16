
class NMZClicker extends AbstractClicker {
    @Override
    void commenceClicking() {
        super.commenceClicking();

        // 35s-56s sleep
        int variance =  (int) (Math.random() * 21000);
        int sleepTime = 35000 +  variance;
        super.commenceClicking(sleepTime);
    }
}
