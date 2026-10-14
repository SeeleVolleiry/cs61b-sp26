# Mini-Project 2: ArrayDeque61B

1. Lectures needed for this project:
   - Lecture 4 (Testing)
   - Lecture 7 (DLLists, ArrayLists)
   - Lecture 8 (ArrayLists)
   - Lecture 10 (Iterators, Object Methods) This is only needed for the last several tasks.

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

和project 1的writing tests一样，自学没有伯克利学生权限的，我感觉没必要做。要写的话可以参考以下列出来的方法及其作用来写。

Flags for add tests：
- `add_first_from_empty`: Check that addFirst works on an empty deque.
- `add_last_from_empty`: Check that addLast works on an empty deque.
- `add_first_nonempty`: Check that addFirst works on a non-empty deque.
- `add_last_nonempty`: Check that addLast works on a non-empty deque.
- `add_first_trigger_resize`: Check that addFirst works when called on a full underlying array
- `add_last_trigger_resize`: Check that addLast works when called on a full underlying array

Flags for add after remove tests
- `add_first_after_remove_to_empty`: Add some elements to a deque and remove them all, then check that addFirst still works.
- `add_last_after_remove_to_empty`: Add some elements to a deque and remove them all, then check that addLast still works.

Flags for remove Tests
- `remove_first`: Check that removeFirst works.
- `remove_last`: Check that removeLast works.
- `remove_first_to_empty`: Add some elements to a deque and remove almost all of them. Check that removing the last element with removeFirst works.
- `remove_last_to_empty`: Add some elements to a deque and remove almost all of them. Check that removing the last element with removeLast works.
- `remove_first_to_one`: Add some elements to a deque and remove almost all of them. Check that removing the second to last element with removeFirst works.
- `remove_last_to_one`: Add some elements to a deque and remove almost all of them. Check that removing the second to last element with removeLast works.
- `remove_first_trigger_resize`: Called when usage factor is <= 25% and array size > 8. Checks that the array resizes appropriately.
- `remove_last_trigger_resize`: Called when usage factor is <= 25% and array size > 8. Checks that the array resizes appropriately.

Flags for get Tests
- `get_first_empty`: Check that getFirst works on an empty ArrayDeque.
- `get_first_valid`: Check that getFirst works on a nonempty ArrayDeque.
- `get_last_empty`: Check that getLast works on an empty ArrayDeque.
- `get_last_valid`: Check that getLast works on a nonempty ArrayDeque.
- `get_valid`: Check that get works on a valid index.
- `get_oob_large`: Check that get works on a large, out of bounds index.
- `get_oob_neg`: Check that get works on a negative index.

Flags for size tests
- `size`: Check that size works.
- `size_after_remove_to_empty`: Add some elements to a deque and remove them all, then check that size still works.
- `size_after_remove_from_empty`: Remove from an empty deque, then check that size still works.

Flags for isEmpty Tests
- `is_empty_true`: Check that size works on an empty deque.
- `is_empty_false`: Check that size works on a non- empty deque.

Flags for toList tests
- `to_list_empty`: Check that toList works with empty ArrayDeque.
- `to_list_nonempty`: Check that toList works with non-empty ArrayDeque.

Flags for advanced resize tests
- `resize_up_and_resize_down`: Trigger a resize up and then a resize down in the same test.

# Task 12: iterator

In the remainder of the project, you are going to expand upon the functionality of the Deque61B interface.

One shortcoming of our Deque61B interface is that it can not be iterated over.

1. To fix this, you should first modify the `Deque61B` interface(In `Deque61B.java`). This is the only place in the project where you should modify `Deque61B.java`.:
```
public interface Deque61B<T> extends Iterable<T> {
```

2. Implement the `iterator()` method in `ArrayDeque61B.java`, using the techniques described in lecture.

解题思路：
- 修改首行添加： implements Iterable<T> ，表明该类将要继承Iterable interface。使自身这一类能够成为迭代对象（iterable）。
- 因为implements，所以类需要实现接口中的抽象方法。Iterable接口中有一个方法——Iterator()，这是我们为了完成继承（实现迭代）必须要补充完整的方法。所以接下来就是实现该方法。
- 要实现抽象方法，一定要看接口中该方法的签名。
  - ```java
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
       */
       Iterator<T> iterator();
    ```
- 通过观察，我们可以发现：Iterator()方法需要返回一个Iterator<T>（包括其子类）的实例/对象。在已有的类代码当中，并没有Iterator类。因此，我们需要写一个Iterator类或者其子类。
- 在之前的学习中，我们了解到解决的方法就是继承 Iterator interface。注意：有两个Iterator，但是一个是方法名，一个是接口名，二者并不相同。
- 于是，新写一个类继承Iterator接口。这一接口同样要求必须实现两个抽象方法（非default方法）——hasnext()和next()。如下所示：
  - ```java
    public interface Iterator<E> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    E next();
    // default Methods are omitted.
    }
    ```
- 于是，接下来就要在新写的子类中实现这两个方法。

# Task 13: equals

Override the `equals()` method in the ArrayDeque61B class.

The instanceof keyword is very powerful in Java.
- Checks to see if o is pointing at the class.
- If no, returns false.
- If yes, returns true and puts o in a new variable of that class(new variable's name is behind on class-name).
- Works correctly, even if o is null.

课件上的示例代码如下所示：
```java
@Override
   public boolean equals(Object o) {
       if (o instanceof ArraySet oas) {
           // check sets are of the same size
           if (oas.size != this.size) {
               return false;
           }

           // check that all of MY items are in the other array set
           for (T x : this) {
               if (!oas.contains(x)) {
                   return false;
               }
           }


       }
       // o is not an arrayset, so return false
       return false;


   }
```

# Task 14: toString

Since you didn’t override `toString` method, it uses the default, which is given by the code below (you don’t need to understand how this code works).
```java
public String toString() {
return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

In turn the `hashCode` method, which you have also not overridden, simply returns the address of the object, which in the example above was 1a04f701.

Override the `toString` method in ArrayDeque61B.java, such that the code above prints out [front, middle, back].

课件上的示例代码如下：
```java
@Override
public String toString() {
   StringBuilder returnSB = new StringBuilder("{");
   for (int i = 0; i < size; i += 1) {
       returnSB.append(items[i]);
       returnSB.append(", ");
   }
   returnSB.append("}");
   return returnSB.toString();
}
```
