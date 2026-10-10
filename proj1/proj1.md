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

## Task 5: toList

依靠debugger的可视化插件来，判断代码之前Task完成地是否正确，这是很枯燥乏味且操作冗长。所以，有了toList这个方法来帮助判断deque是否符合预期。这样就比之前简单明了得多了。

When called, this method returns a List representation of the Deque61B.

For example, if the Deque61B has had addLast(5), addLast(9), addLast(10), then addFirst(3) called on it, then the result of toList() should be a List with 3 at the front, then 5, then 9, then 10. If printed in Java, it’d show up as `[3, 5, 9, 10]`.

If the Deque is empty, then toList should return an empty list with zero items, e.g. return new ArrayList<>(). The toList method should never return null.

1. write the `toList` method:

2. Verify the toList method:
- `LinkedListDeque61BTest.java`中给出的三个写好的测试能够通过，就说明写对了。

## Writing Tests

For other methods, you will need to write your own unit tests!

To write tests, we will use `Google’s Truth assertions library`. We love it because it’s easy to use and generates useful error messages.

`Arrange-Act-Assert pattern`: pattern of writing tests.
- Arrange the test case, such as instantiating the data structure or filling it with elements.
- Act by performing the behavior you want to test.
- Assert the result of the action in (2).

We will often have multiple “act” and “assert” steps in a single test method to reduce the amount of boilerplate (repeated) code.

简单来讲，就是执行操作后，比较预期结果和实际结果是否相同。

### Truth Assertions

```
assertThat(actual).isEqualTo(expected);

assertWithMessage("actual is not expected")
    .that(actual)
    .isEqualTo(expected);

assertThat(actualList)
    .containsExactly(0, 1, 2, 3)
    .inOrder();

assertThat(actualList)
    .containsExactlyElementsIn(expected)  // `expected` is a List
    .inOrder();

@Test
public void isEmptyTest() {
    Deque61B<String> lld = new LinkedListDeque61B<>();
    assertThat(lld.isEmpty()).isTrue;
}
```

### Example Test

```java
@Test
/* In this test, we use only one assertThat statement.Sometimes, the tedious work of adding the extra assertion statements isn't worth it. */
public void addLastTestBasic() {
    Deque61B<String> lld1 = new LinkedListDeque61B<>();

    lld1.addLast("front"); // after this call we expect: ["front"]
    lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
    lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
    assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
}
```

1. @Test tells Java that this is method is a test, and should be run when we run tests.
2. Arrange: We construct a new Deque61B, and add 3 elements to it using addLast.
3. Act: We call toList on Deque61B. This implicitly depends on the earlier addLast calls.
4. Assert: We use a Truth assertion to check that the toList contains specific elements in a specific order.

Now you should test and implement all the remaining methods. 

For the rest of this project, we’ll describe our suggested steps at a high level. We strongly encourage you to follow the remaining steps in the order given.

In particular, write tests before you implement. This is called `“test-driven development”` and helps ensure that you know what your methods are supposed to do before you do them.

## Task 6: isEmpty and size

1. write tests for the two methods.
2. After you’ve written tests, you can implement isEmpty and size.

Hint:
- For these tests, you can use the isTrue or isFalse methods on your assertThat statements.
- Your tests can range from very fine-grained, e.g. testIsEmpty, testSizeZero, testSizeOne to very coarse grained, e.g. testSizeAndIsEmpty. It’s up to you to explore and find what granularity you prefer.

注意：截至Task 6，只显示了add而没有实现remove，所以目前写的测试示例，不要调用没有被补全的method。

## Task 7: getFirst and getLast

1. Write a test for the getFirst and getLast methods.
- Make sure to test the cases where Deque61B has no items or is empty. In these cases getFirst and getLast should return null.

2. After you’ve written tests and verified that they fail, implement getFirst and getLast.

These methods must take `constant time`. That is, the time it takes to for either method to finish execution should not depend on how many elements are in the deque.

## Task 8: get and getRecursvie

1. Write a test for the get method.
- Make sure to test the cases where get receives an invalid argument, e.g. get(28723) when the Deque61B only has 1 item, or a negative index. In these cases get should `return null`.

Note: `get` must use iteration.

2. After you’ve written tests and verified that they fail, implement get.
- Since we’re working with a linked list, it is interesting to write a recursive get method, getRecursive.

3. Copy and paste your tests for the get method so that they are the same except they call getRecursive. (While there is a way to avoid having copy and pasted tests, though the syntax is not worth introducing – passing around functions in Java is a bit messy.)

4. After you’ve copy-pasted tests and verified that they fail, implement getRecursive.

在写getRecursive的代码时，我在该方法内定义了一个新的辅助函数。思路来源于Python的高阶函数思想。虽然该辅助函数确实能够完成Task的要求，但是在Java的语法中这是不允许的。

`Java不能在方法中实现方法，要想实现高阶函数得用其他办法`。

## Task 9: removeFirst and removeLast



## Task 10: Testing your Tests
