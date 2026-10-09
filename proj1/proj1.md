# Mini-Project 1: LinkedListDeque61B

In this assignment, we’ll use what we’ve learned in the first fosevenur lectures to build your own linked list implementation of a List.

To keep the workload lighter, we won’t build a full list, but rather a Double Ended Queue (deque, pronounced “deck”).

## Task 1: Read the Deque61B ADT and API

Deque(double ended queue)'s definition:

    A linear collection that supports element insertion and removal at both ends.
    The name deque is short for “double ended queue” and is usually pronounced “deck”.
    Most Deque implementations place no fixed limits on the number of elements they may contain, but this interface supports capacity-restricted deques as well as those with no fixed size limit.

We don’t need all the methods defined in Java’s Deque, and have defined our own interface, which can be found in src/Deque61B.java.

**Task**: Open the `Deque61B.java` file and read the documentation in it.

This spec doesn’t have all the information you need to complete the project, so it’s important that you read through all of Deque61B.java!

Seriously. Do not skip this. You will spend hours confused if you skip this step. Please save yourself the time and stress! And, You should not edit Deque61B.java.

## Task 2: Creating the file

1. Start by creating a file called `LinkedListDeque61B`.
- This file should be created in the `proj1/src` directory.
- To do this, right-click on the src directory, navigate to “New -> Java Class”, and give it the name `LinkedListDeque61B`.
- edit the declaration of your class so that it reads: `public class LinkedListDeque61B<T>`
- change the declaration of your class so that it reads: `public class LinkedListDeque61B<T> implements Deque61B<T>`

第一步会造成一个error，在下面会修正它。

2. Hover your mouse over the red squiggle, and click the “implement methods” button when the error message box pops up. This will autogenerate the method headers for you.

3. Create an empty constructor.

4. create a main method.

## Task 3: Constructor

For this project, you are required to implement a circular, doubly-linked topology with a sentinel.

1. Implement the constructor for `LinkedListDeque61B`.
- Add one or more instance variables to the LinkedListDeque61B class.
- Instantiate a sentinel node.
- Add one or more instance variables the to Node class.
- Initialize the instance variables in the constructor.

2. Verify the correctness of your constructor.
- Set a breakpoint in your main method and verify using the visualizer that your code matches the expected topology.

## Task 4: addFirst and addLast

We’ll implement the other methods called in your main method.

1. Implement `addFirst` and `addLast`.
- addFirst and addLast may not use looping or recursion. A single add operation must take "constant time," that is, adding an element should take approximately the same amount of time no matter how large the deque is. This means that you cannot use loops that iterate through all / most elements of the deque.

2. Verify by `Java Visualizer`
- After implementing these two methods, set a breakpoint at the end of your main method and verify that the created LinkedListDeque61B matches the expected topology below.

推荐画出 `boxes and pointers` 示意图，这样比纯靠脑子想要来的准确、实际和易懂。

## Task 5:



## Task 6:



## Task 7:



## Task 8:



## Task 9:



## Task 10:
