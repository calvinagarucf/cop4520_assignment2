// Note: Minotaur has a 1/5 chance of choosing the first guest (the counter)
// and a 4/5 chance of picking another random guest.

public class Minotaur extends Thread {

    public void run() {
        while (!BirthdayParty.allGuestsHaveEntered.get()) {
            if (Labyrinth.guestIsInside.compareAndSet(false, true)) {

                int guestToEnter;

                // Check for n = 1
                if (BirthdayParty.GUEST_COUNT == 1) {
                    BirthdayParty.nextToEnter.set(0);
                    continue;
                }


                int d5 = (int)(Math.random() * 5) + 1;
                if (d5 == 1)
                    guestToEnter = 0;
                else
                    guestToEnter = (int)(Math.random() * (BirthdayParty.GUEST_COUNT - 1)) + 1;
    
                System.out.println("Minotaur: Sending Guest " + guestToEnter + " into the labyrinth.");
                BirthdayParty.nextToEnter.set(guestToEnter);
            }
        }
    }
}