# Mini-Project 2: ArrayDeque61B

1. Lectures needed for this project:
   - Lecture 4 (Testing)
   - Lecture 7 (DLLists, ArrayLists)
   - Lecture 8 (ArrayLists)
   - Lecture 10 (Iterators, Object Methods) This is only needed for the last two tasks.

2. Follow the "Assign Workflow Guide".
    - Modify "Data Structure"
    - Add library

3. Hover your mouse over the red squiggle, and click the “implement methods” button when the error message box pops up. This will autogenerate the method headers for you.

顺序： 1 2 3 6 5 7

# Task 1: Creating the File

Start by creating a file called `ArraryDeque61B` in `src`.

Modify the class declaration like:
   
    public class ArrayDeque61B<T> implements Deque61B<T>

# Task 2: Constructor

Your deque must use a Java array as the backing data structure.

You will need to somehow keep track of what array indices hold the deque’s front and back elements. We strongly recommend that you treat your array as circular for this exercise.

You may add any private helper classes or methods in ArrayDeque61B.java if you deem it necessary. We recommend creating one (or more) helper functions to avoid implementing the wraparound logic in multiple places.

1. Declare the necessary instance variables, and implement the constructor.
2. For `autograder` purposes, the starting size of your backing array must be 8.

根据课件和网页要求有：size, nextFirst, nextLast, items（a array of length 8）。

# Task 3: addFirst and addLast

These two methods must not use looping or recursion. A single add operation must take `constant time`.

Implement `addFirst` and `addLast`. Don't forget to modify the size of deque.

You will need to complete `Task 7: toList` before writing tests for addFirst and addLast since a working toList is needed to test these methods. Once you’ve done so, write tests and verify your implementation.

# Task 4: getFIrst and getLast

1. Writing Tests.
   - Write a test for the `getFirst` and `getLast` methods.
   - Make sure to test the cases where Deque61B has no items or is empty. In these cases getFirst and getLast should `return null`.

2. After you’ve written tests and verified that they fail, implement `getFirst` and `getLast`.

# Task 5: get and getRecursive

The get method must take `constant time`. `get` should return null when the index is invalid (too large or negative).

Note that you should return null if the index is larger than the size of your deque, not the underlying array.

After you’ve written tests and verified that they fail, implement `get`.

Just copy this code block for implementing `getRecursive`:
```java
@Override
public T getRecursive(int index) {
    throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
}
```

# Task 6: isEmpty and size

Write tests for the `isEmpty` and `size` methods, and check that they fail. Then, implement the methods.

These two methods must take constant time.

# Task 7: toList

`toList` will continue to be useful to test your Deque61B.

Write tests for the `toList` method, and check that they fail. Then, implement the method. You are not given tests this time, so you will need to write them!

Hint:
- The first line of the method should be something like `List<T> returnList = new ArrayList<>()`. This is one location where you are allowed to use a built-in Java data structure.
- One of the earlier methods may be helpful for implementing this method.
- Your toList method should return a list with the elements in ArrayDeque61B and in the order that the elements were added (i.e. the “conceptual version of the Deque”).
- Be sure to return only values added as elements into the deque (e.g. don’t return null unless it was explicitly added as an element).

# Task 8: removeFirst and removeLast





# Task 9: Resizing Up






# Task 10: Resizing Down





# Task 11: Writing Tests





# Task 12: iterator





# Task 13: equals





# Task 14: toString
