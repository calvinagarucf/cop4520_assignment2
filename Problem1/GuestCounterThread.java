
public class GuestCounterThread extends GuestThread {

    public int missingCupcakeCount;

    GuestCounterThread(String name, int guestNumber) {
        super(name, guestNumber);

        this.missingCupcakeCount = 0;
    }

    public void run() {
        while (!BirthdayParty.allGuestsHaveEntered.get()) {
            if (BirthdayParty.nextToEnter.get() != this.guestNumber)
                continue;

            // Check for n = 1
            if (BirthdayParty.GUEST_COUNT == 1) {
                System.out.println(this.name + " took the cupcake.");
                Labyrinth.cupcakeIsPresent.set(false);
                this.hasEatenCupcake = true;
    
                System.out.println(this.name + " left the labyrinth.");
                BirthdayParty.nextToEnter.set(-1);
                Labyrinth.guestIsInside.set(false);

                System.out.println(this.name + " announces everyone has entered.");
                BirthdayParty.allGuestsHaveEntered.set(true);
                continue;
            }

            boolean cupcakePresence = Labyrinth.cupcakeIsPresent.get();
            
            if (cupcakePresence) {
                System.out.println(this.name + " left the labyrinth.");
                BirthdayParty.nextToEnter.set(-1);
                Labyrinth.guestIsInside.set(false);
                continue;
            }

            System.out.println(this.name + " found a missing cupcake and added to the count: " + this.missingCupcakeCount);
            this.missingCupcakeCount++;

            System.out.println(this.name + " asked for cupcake.");
            Labyrinth.cupcakeIsPresent.set(true);

            System.out.println(this.name + " left the labyrinth.");                
            BirthdayParty.nextToEnter.set(-1);
            Labyrinth.guestIsInside.set(false);

            if (this.missingCupcakeCount == BirthdayParty.GUEST_COUNT - 1) {
                System.out.println(this.name + " announces everyone has entered.");
                BirthdayParty.allGuestsHaveEntered.set(true);
                continue;
            }
        }
    }
}