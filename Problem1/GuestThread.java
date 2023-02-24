public class GuestThread extends Thread {

    protected String name;
    protected int guestNumber;
    protected boolean hasEatenCupcake;

    GuestThread(String name, int guestNumber) {

        this.name = name;
        this.guestNumber = guestNumber;
        this.hasEatenCupcake = false;
    }

    public void run() {
        while (!BirthdayParty.allGuestsHaveEntered.get()) {
            if (BirthdayParty.nextToEnter.get() != this.guestNumber)
                continue;

            boolean cupcakePresence = Labyrinth.cupcakeIsPresent.get();
            
            if (this.hasEatenCupcake || !cupcakePresence) {
                System.out.println(this.name + " left the labyrinth.");
                BirthdayParty.nextToEnter.set(-1);
                Labyrinth.guestIsInside.set(false);
                continue;
            }
            
            System.out.println(this.name + " took the cupcake.");
            Labyrinth.cupcakeIsPresent.set(false);
            this.hasEatenCupcake = true;

            System.out.println(this.name + " left the labyrinth.");
            BirthdayParty.nextToEnter.set(-1);
            Labyrinth.guestIsInside.set(false);
        }
    }
}