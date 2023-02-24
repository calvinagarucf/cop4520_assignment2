import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Calvin Agar
// COP4520
// Juan Parra
// Programming Assignment 2

// Problem 1: Minotaur's Birthday Party

// To solve this problem, the guests will count themselves
// and one guest will be a designated counter.

// Then the guests will develop the two following strategies,
// based on whether a guest is the counter or not:

// Not the counter:
// If you have already eaten the cupcake, always leave, no matter if a cupcake is present or not.
// If you have not already eaten the cupcake and the cupcake is present, eat it.
// If you have not already eaten the cupcake and the cupcake is not present, leave.

// The counter:
// Never eat the cupcake.
// If the cupcake is present, leave.
// If the cupcake is not present, add one to a ongoing count
// and request a new one from the servant.
// If the count is equal to the total number of guests minus one,
// the counter can declare on behalf of all the guests that 
// everyone has entered the labyrinth.
// Otherwise, ask the servant for a cake and leave.
// If the poor soul happens to be the only person at the party,
// they can eat the cupcake.

// The counter can check only needs to check up to the total minus one
// because the counter can only increment their count if they have entered,
// guaranteeing they will enter if the find the count.
// Also, I will manually check for the base case of the number of guests
// equaling one because this do not work with the algorithm. 

// This effectively allows a guest who has not eaten a cupcake to
// announce to the counter "I've been here", once the counter finally
// sees it. The other guests who happen to enter in between the announcing guest
// and the counter will not mess with the announcement, and will instead wait
// until a cupcake appears when they eventually return if they need to announce themselves.

// I do not think this is the fastest method of solving this problem,
// but it does guarantee the guests know that everyone has entered the labyrinth.

// Since the Minotaur decides mostly randomly amongst which guests should enter the
// labyrinth, it is difficult to find an exact runtime for the algorithm.

// The Minotaur's "randomness" is currently as follows:
// 1/5 chance of choosing the first guest (the counter)
// 4/5 chance of choosing any other guest randomly

// An optimization to this could be to scale the rate at which the counter
// is picked by the Minotaur based on the number guests.

// So, samples were taken at different values of guests.
// 5: 513ms 771ms 1295ms 693ms 842ms
// 10: 1602ms 3006ms 4365ms 3274ms 3110ms
// 20: 10566ms 10451ms 11587ms 11141ms 11585ms
// 30: 25280ms 27586ms 20410ms 23996ms 26968ms

// From the samples it appears that lower values of guests may have
// higher variation in runtime when compared to larger values.


public class BirthdayParty {

    public static int GUEST_COUNT = 30;

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