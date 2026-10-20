# Project 4A: NGrams

Needed for this project: Lecture 17(Extend, Sets, Maps, and BSTs)

# Overview

In this project, we will build a browser-based tool for exploring the history of word usage in English texts.

We have provided the front end code (in Javascript and HTML) that collects user inputs and displays outputs. Your Java code will be the back end for this tool, accepting input and generating appropriate output for display.

# Task 0: Start

1. Follow the Assignment Workflow Guide
2. Download the NGrams data files: follow Data spec to get data directory.
3. Word History File:
    - Column: word, year, number of times. Ignore the forth. 
4. Year History File:
    - Column: year, number of words. Ignore the third and forth.

# Task 1: TimeSeries

A `TimeSeries` is a special purpose extension of the existing TreeMap class, where:
- The key type parameter is always Integer, representing a year.
- The value type parameter is always Double, representing a numerical data point for that year.

Step 1: Write tests for each `TimeSeries` method in `TimeSeriesTest.java`.

Step 2: Implement the methods in `src/main/TimeSeries.java` according to the API provided in the file.

Tips:
- You may not add additional public methods to this class. You’re welcome to add additional private methods.
- `TimeSeries` objects should have no instance variables. A `TimeSeries` is-a `TreeMap`. That means your `TimeSeries` class also has access to all methods that a `TreeMap` has.
- Several methods require that you compare the data of two `TimeSeries`. You should not have any code which fills in a zero if a year or value is unavailable.
- You may assume that the `dividedBy` operation never divides by zero.

Useful methods in `TreeMap`: 通过观察要写的方法去寻找基类中的实用方法。
- keySet: keySet(), navigableKeySet(), descendingKeySet()
- values: ascending order of the corresponding keys.
- entrySet: return entries in ascending key order.
- putAll: copy all of the mapping from specified map to this map.
  - 只有这个方法是拷贝，其他的方法都是view，给出的是视图，背后连接着原图。修改视图会修改原图。
- subMap: return a view of the portion map from fromKey to toKey.
- 基类方法返回值为Set类，子类方法要求List类，所以需要转换类型。转换后List中的顺序取决于 Set 的顺序（二者相同）。
  - `new ArrayList<>(set)` 可变独立副本。迭代顺序沿用 set 的顺序
  - set.stream().toList()（Java 16+） 不可变短。且 add/set 会抛 UnsupportedOperationException
  - List.copyOf(set)（Java 10+） 不可变。同上且不允许 null 元素

# Task 2: NGramMap

The `NGramMap` class will provide various convenient methods for interacting with Google’s NGrams dataset. This task is more open-ended and challenging than the creation of the TimeSeries class.
