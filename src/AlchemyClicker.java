
class AlchemyClicker extends AbstractClicker {
    @Override
    void commenceClicking(){
        super.commenceClicking();

        // 2.45-3.15s sleep
        int variance =  (int) (Math.random() * 700);
        int sleepTime = 2450 +  variance;
        super.commenceClicking(sleepTime);
    }
}
