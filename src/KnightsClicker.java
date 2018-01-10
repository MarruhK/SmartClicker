class KnightsClicker extends SmartClicker{

    // Is it bad practice to have no constructor, even when not needed???

    @Override
    void commenceClicking() {
        super.commenceClicking();

        // Begin auto clicking.
        while (continueClicking) {
            elapsedTime = System.currentTimeMillis() - startingTime;

            singleClick(elapsedTime);
            clicker.delay(timeBetweenSpamClicks(elapsedTime));
        }
    }
}
