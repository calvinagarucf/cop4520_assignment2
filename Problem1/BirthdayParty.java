import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Calvin Agar
// COP4520
// Juan Parra
// Programming Assignment 2

public class BirthdayParty {

    public static int GUEST_COUNT = 20;

    public static AtomicBoolean allGuestsHaveEntered = new AtomicBoolean(false);
    public static AtomicInteger nextToEnter = new AtomicInteger(-1);
    
    public static void main(final String[] args) throws InterruptedException {

        System.out.println("Running simulation with " + GUEST_COUNT + " guests...");

        Minotaur minotaur = new Minotaur();

        // Guests Strategizing
        GuestThread[] guests = new GuestThread[GUEST_COUNT];
        GuestCounterThread counter = new GuestCounterThread("Counter", 0);
        guests[0] = counter;
        for (int i = 1; i < guests.length; i++) 
        {
            String name = "Guest " + i;
            guests[i] = new GuestThread(name, i);
        }

        // Start threads
        long start = System.nanoTime();
        for (int i = 0; i < guests.length; i++)
            guests[i].start();
        minotaur.start();

        // Wait to finish
        minotaur.join();
        for (int i = 0; i < guests.length; i++)
            guests[i].join();
        long end = System.nanoTime();

        String formattedTime = ((end - start) / 1000000L) + "ms";

        System.out.println("Runtime: " + formattedTime);
        System.out.println("The designated counter found " + counter.missingCupcakeCount + " missing cupcakes, " + 
                          ((counter.missingCupcakeCount == GUEST_COUNT - 1)
                           ? "which means we know everyone entered."
                           : "which means we do NOT know if everyone entered."));
    }
}
