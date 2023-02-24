Calvin Agar

COP4520

Juan Parra

Programming Assignment 2


# Problem 1: Minotaur's Birthday Party

To compile (inside Problem1 folder): javac *.java && java BirthdayParty

Currently the number of guests is set to 20. This can be changed in the BirthdayParty.java file.


To solve this problem, the guests will count themselves
and one guest will be a designated counter.

Then the guests will develop the two following strategies,
based on whether a guest is the counter or not:

Not the counter:
If you have already eaten the cupcake, always leave, no matter if a cupcake is present or not.
If you have not already eaten the cupcake and the cupcake is present, eat it.
If you have not already eaten the cupcake and the cupcake is not present, leave.

The counter:
Never eat the cupcake.
If the cupcake is present, leave.
If the cupcake is not present, add one to a ongoing count
and request a new one from the servant.
If the count is equal to the total number of guests minus one,
the counter can declare on behalf of all the guests that 
everyone has entered the labyrinth.
Otherwise, ask the servant for a cake and leave.
If the poor soul happens to be the only person at the party,
they can eat the cupcake.

The counter can check only needs to check up to the total minus one
because the counter can only increment their count if they have entered,
guaranteeing they will enter if the find the count.
Also, I will manually check for the base case of the number of guests
equaling one because this do not work with the algorithm. 

This effectively allows a guest who has not eaten a cupcake to
announce to the counter "I've been here", once the counter finally
sees it. The other guests who happen to enter in between the announcing guest
and the counter will not mess with the announcement, and will instead wait
until a cupcake appears when they eventually return if they need to announce themselves.

I do not think this is the fastest method of solving this problem,
but it does guarantee the guests know that everyone has entered the labyrinth.

Since the Minotaur decides mostly randomly amongst which guests should enter the
labyrinth, it is difficult to find an exact runtime for the algorithm.

The Minotaur's "randomness" is currently as follows:
1/5 chance of choosing the first guest (the counter)
4/5 chance of choosing any other guest randomly

An optimization to this could be to scale the rate at which the counter
is picked by the Minotaur based on the number guests.

So, samples were taken at different values of guests.

5: 513ms 771ms 1295ms 693ms 842ms

10: 1602ms 3006ms 4365ms 3274ms 3110ms

20: 10566ms 10451ms 11587ms 11141ms 11585ms

30: 25280ms 27586ms 20410ms 23996ms 26968ms

From the samples it appears that lower values of guests may have
higher variation in runtime when compared to larger values.


# Problem 2: Minotaur's Crystal Vase

While I unfortunately do not have time to code up a representation of the most viable strategy, I do believe that it would be strategy three, the queue. 

Neither strategy one nor strategy two guarantee that each guest that announces their desire to enter the waiting room is able to. Strategy three solves this problem by allowing the guests to enter a 'line' so to speak. 

Another benefit of strategy three is order. Strategy three's allows priority for the guest at the front of the queue to enter next. Strategy one nor strategy two utilize any sort of priority, meaning it is a fight between the guests over who gets to enter the room when it is unoccupied.

On the other hand, strategy three could involve more overhead and waiting among the guests.

However, the guarantee that each guest that wants to enter will enter at some point, and that the guests have a fair advantage when it comes to actually getting into the room are much more important than those disadvantages. 

