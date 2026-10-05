# 4. Testing

`Testing code correctness and a sorting algorithm called Selection Sort`

In this chapter, we'll discuss how you can write tests to evaluate code correctness.
Along the way, we'll also discuss an algorithm for sorting called Selection Sort.

## 4.1 Ad Hoc Testing: 临时测试

```java
public class TestSort {
    /* Tests the sort method of the Sort class*/
    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        for (int i = 0; i < input.length; i += 1) {
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: "+ expected[i] + ". but got: " + input[i] + ".");
            }
        }
    }
    
    public static void main(String[] args) {
        testSort();
    }
}

public class Sort {
    /* Sorts strings destructively. */
    public static void main(String[] x) {
        //
    }
}
```

## 4.2 Truth Testing

The Google Truth library provides a number of helpful methods and useful capabilities for simplifying the writing of tests.

```java
import static com.google.common.truth.Truth.assertThat;
public class TestSort {
   /** Tests the sort method of the Sort class. */
   public static void testSort() {
       String[] input = {"cows", "dwell", "above", "clouds"};
       String[] expected = {"above", "clouds", "cows", "dwell"};
       Sort.sort(input);

       assertThat(input).isEqualTo(expected);
   }

   public static void main(String[] args) {
       testSort();
   }
}
```

## 4.3 Selection Sort

要完成Sort class就要先学会排序算法。这里讲最简单的sort algorithm —— 选择排序/selection sort。

Selection sort consists three steps:
- Find the smallest item.
- Move it to the front.
- Selection sort the remaining N-1 items (without touching the front item)

```java
public class SelectionSort {
    public static void sort(String[] s) {
        // find the smallest one.
        // swap it with the front.
        // repeat in the rest sequence.
    }

    public static void main(String[] args) {
        sort(args);
    }
}
```

具体的代码实现见`Sort.java`和`TestSort.java`。

## 4.4 Reflection on the Development Process

When you're writing and debugging a program, you'll often find yourself switching between different contexts. Trying to hold too much in your brain at once is a recipe for disaster at worst, and slow progress at best.

Having a set of automated tests helps reduce this cognitive load.

As mentioned earlier in this chapter, tests also allow you to gain confidence in the basic pieces of your program, so that if something goes wrong, you have a better idea of where to start looking.

## 4.5 Testing Philosophy

`Correctness Tool: JUnit Tests`: 
JUnit testing, as we have seen, unlocks a new world for you.

Rather than relying on an autograder written by someone else, you write tests for each piece of your program. `We refer to each of these pieces as a unit`. This allows you to have confidence in each unit of your code - you can depend on them. This also helps decrease debugging time as you can isolate attention to one unit of code at a time (often a single method). Unit testing also forces you to clarify what each unit of code should be accomplishing.

There are some downsides to unit tests, however. First, writing thorough tests takes time. It's easy to write incomplete unit tests which give a false confidence to your code. It's also difficult to write tests for units that depend on other units (consider the addFirst method in your LinkedListDeque).

`Correctness Tool: Integration Testing`:

Unit tests are great, but we should also make sure these units work properly together (unlike this meme). Integration testing verifies that components interact properly together. JUnit can in fact be used for this. You can imagine unit testing as the most nitty gritty, with integration testing a level of abstraction above this.

The challenge with integration testing is that it is tedious to do manually yet challenging to automate. And at a high level of abstraction, it's easy to miss subtle or rare errors.

## 4.6 Test-Driven Development (TDD)

TDD is a development process in which we write tests for code before writing the code itself. The steps are as follows:
- Identify a new feature.
- Write a unit test for that feature.
- Run the test. It should fail.
- Write code that passes the test. Yay!
- Optional: refactor code to make it faster, cleaner, etc. Except now we have a reference to tests that should pass.
- Test-Driven Development is not required in this class and may not be your style but unit testing in general is most definitely a good idea.
