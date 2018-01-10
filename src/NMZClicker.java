
class NMZClicker extends SmartClicker {

    // Is it bad practice to have no constructor, even when not needed???

    @Override
    void commenceClicking() {
        super.commenceClicking();

        // Begin auto clicking.
        while (continueClicking) {
            elapsedTime = System.currentTimeMillis() - startingTime;
            doubleClick(elapsedTime);

            // 35s-56s
            try{
                Thread.sleep((35000 +  (long) (Math.random() * 21000)));
            } catch (InterruptedException ex) {
                System.out.println("DID NOT SLEEP");
            }
        }
    }
}
