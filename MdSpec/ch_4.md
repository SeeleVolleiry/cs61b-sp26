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
