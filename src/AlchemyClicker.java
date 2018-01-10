
class AlchemyClicker extends SmartClicker{

    // Is it bad practice to have no constructor, even when not needed???

    @Override
    void commenceClicking(){
        super.commenceClicking();

        // Begin auto clicking.
        while (continueClicking) {
            elapsedTime = System.currentTimeMillis() - startingTime;
            doubleClick(elapsedTime);

            // 2-3.1s sleep
            try{
                Thread.sleep((2000 +  (long) (Math.random() * 3100)));
            } catch (InterruptedException ex) {
                System.out.println("DID NOT SLEEP");
            }
        }
        System.out.println("Clicker officially stopped.");
    }
}
