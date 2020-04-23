## Project Retrospection: Non-Determinism and Testing

### Sources of Non-Determinism and Testing

#### Random Booleans

We have struggled with testing and specifically sources of non-determinism in iteration 2.
In iteration 2 in the class AccessFlights we had a random boolean value that choose the value of flight status (either 'on-time' or 'delayed').
Since the value was choosen by a random value this mean that the value could not be consistently determined for testing.

#### Changing Date-Times

Another source of non-determinism was the date. 
The date was used in reference to the flights to see whether they were in progress already.
Flights would listed as 'in progress' if there departure date was before the current date.
Since the current date is changing current flights would also be changing, meaning that our test cases were constantly changing.
These changing test cases made writing tests for these sections of code impossible.
This meant that we had no way of knowing if the code was working properly since no tests could be implemented for those sections.
Making this code and future code for iteration 3 to eliminate these untestable sections will be a priority.

### Improving in Iteration 3

There are some simple ways we can change this code to make it testable in iteration 3.
For the date instead of getting the current date we need to call a function.
In this function we can call the current date in the main code and we can set whatever date we want to in the test code.
This would allow the date to be fixed for testing so there isnt changing in the tests.
Changing the function is reasonable to do in iteration 3 and will be our metric of success.
These changes will make the dateTime determined in testing.
We can do a similar change for flight status to get rid of the non-determinism.
We can add a function that will call a random boolean value in the main code but for testing will set specific values.
This will mean that in the testing code the values wont change and will be wont have changing test cases.
This will remove the non-determinism in the flight status, and is a realistic goal for iteration 3.
Once the changes to the code have been completed we can write tests to check to see if these areas of code are working as expected.