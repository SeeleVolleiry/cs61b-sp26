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

该project推荐使用逻辑上为环形、首尾连接的结构，并用数字来实现。这就导致——添加元素和减少元素会让nextFirst和nextLast会“转圈”。当越界后，要能够靠取模使它回到正确的空位，保证它的指示功能是正确的

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

Write some tests that test the behavior of `removeFirst` and `removeLast`, and again, ensure that the tests fail. Then, implement the methods.

Do not maintain references to items that are no longer in the deque.

`removeFirst` and `removeLast` may not use looping or recursion. Like addFirst and addLast, these operations must take constant time. Refer to the sections above for more information on what this means.


# Task 9: Resizing Up

Make sure to resize by a geometric factor. If you get an ExecutionTimeoutException when working on resizing, make sure you’re resizing using a multiplicative (geometric) factor and not an additive factor!

Some tips as you work on resizing:

Try to get everything working for a fixed-size array first. This would be good point to start to familiarize yourself.
Once you are confident working solution for a fixed-size array, try resizing - consider having a helper method for it!
When in doubt, draw it out! We suggest drawing box-and-pointer diagrams (or diagrams in general) when faced with issues in your methods, it will help you understand both your code as well as the intended logic better.
We do not recommend using arraycopy with a circular implementation. It will work, but results in a significantly more complex (and harder to debug!) implementation than necessary. Instead, we suggest thinking forward to how you might implement get and using a for loop in some way.

After you have a working addFirst and addLast (without resizing), modify the methods so they support resizing. Use tests to verify that they are correct.

当数组满了时，能够成几何倍数的增加。换句话说，容量要乘以一个因子，而不是加一个因子。再调整过大小后，之前的属性和方法也要修正以适应新的数组。

怎么扩容？我的想法是把旧的数据放在扩容后的数组的中间部分。

# Task 10: Resizing Down

Before performing a remove operation, if the number of elements in the array is at or under 25% the length of the array, you should resize the array down.

For arrays length 15 or less, your usage factor can be arbitrarily low.

After you have a working removeFirst and removeLast (without resizing), modify the methods so they support resizing. Use tests to verify that they are correct.

# Task 11: Writing Tests

和project 1的writing tests一样，自学没有伯克利学生权限的，我感觉没必要做。

# Task 12: iterator





# Task 13: equals





# Task 14: toString
