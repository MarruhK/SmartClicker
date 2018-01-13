class KnightsClicker extends SmartClicker{

    // Is it bad practice to have no constructor, even when not needed???

    @Override
    void commenceClicking() {
        super.commenceClicking();

        // Begin auto clicking.
        while (continueClicking) {
            elapsedTime = System.currentTimeMillis() - startingTime;
            beforeClickTime = System.currentTimeMillis();
            singleClick();
            clicker.delay(timeBetweenSpamClicks(elapsedTime));
            writeTime(System.currentTimeMillis() - beforeClickTime);
        }
    }
}
