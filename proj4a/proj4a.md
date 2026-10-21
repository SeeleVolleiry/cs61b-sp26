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

1个构造函数 + 7个方法。

Fill out the constructor of `NGramMap`:
- 解析提供的文件，决定保留哪些信息以及用什么样的数据结构去存储这些信息。
- Parse provided files: read files in Java - `In` class. demo中有读取文件的示例代码。
- Choose the right data structure: look thorough the methods to help decision
- Implement the constructor.

解决办法：
1. 数据结构选型思路：数据结构如何选择要看数据的样子及操作数据的便捷性。
    - 观察两个文件的数据格式，以及proj明确指出了哪些列是有效数据，哪些列是无效数据。 word中只有一二三共三列，year中只有一二共两列。数据量并不大，可以在实现一个版本之后，有余力再去优化结构。
    - word_history给出的直观信息是：一个word和多个（year-frequency）键值对相关联；存储许多个word；
    - year_history给出的直观信息是：一个year唯一对应一个total，（year-total）键值对。
    - 这种一对一、一对多的数据关系，更偏向于树、图或者抽象列表。在Task 1中就已经实现了一个一对一的数据结构。直接拿来利用，避免重复工作。即year_history中的数据存为`TimeSeries`这一类的实例。
    - 一对一解决了，一对多呢？转化为一对一+一对多。一个word和它的TimeSeries对象一一对应。TimeSeries对象存储多个键值对。这样就可以了，即word_history中的数据使用 `Map<String, TimeSeries>`这一数据结构存储。
    - project提示可以通过观察方法的返回值类型，来辅助数据结构选型。很明显，最后的结构与TimeSeries相关。印证了之前的选择不会太差。
2. 写构造函数：创建两个实例变量，分别保存两个文件的数据。类型在选型时已经定好了。
    - 创建对应文件的In class对象。当对象还有下一行时就读取那一行，一直循环读取直到结束。
    - 循环中：按照文件实际的分隔符将读取到的String对象划分为String[]对象。选取需要的列（依靠数组的索引），然后强制转换为需要的数据结构（如Integer.parseInt，Double.parseDouble）。提取出有效数据后，存入实例变量中即可。
    - 注意：word_history的实例变量没有刚读取到的word这一key时，就新建一个new TimeSeries对象。有就put进word对应的TimeSeries对象。
3. Implement Methods:
    - countHistory: 返回word对应的TimeSeries对象。如果有起止年份，就筛选[start, end]区间内的数据。
        - 如果含有word关键字，返回一个TimeSeries对象—— `this.wordHistory.get(word)`的`副本`。有起止年份，用需要起止年份的的三参数TimeSeries构造函数。
        - 否则返回一个空的TimeSeries， `return new TimeSeries()`
    - totalCountHistory: 返回新的对象，是一个`副本`/copy
        - 直接返回yearHistory在最小年份和最大年份两个宏之间的副本即可。用TimeSeries的三参数构造函数。
    - weightHistory:返回的同样要求是副本。思路与之前的方法类似。
    - summedWeightHistory:返回的同样要求是副本。思路与之前的方法类似。多了循环而已。主要注意用一个变量记录多个对象的和（使用plus相加），最后dividedBy、三参数构造函数按需选用。

In tasks 3 and 4 of Project 4A, we’ll do a bit of software engineering to set up a web server that can handle NgordnetQueries.

# Task 3: HistoryTextHandler

按照网页说明一步步进行即可：

In the `main` folder, create a new file called `HistoryTextHandler.java` that takes the given NgordnetQuery and returns a String in the same format as above.

Then, modify `Main.java` so that your HistoryTextHandler is used when someone clicks History (Text). In other words, instead of registering DummyHistoryTextHandler, you should register your HistoryTextHandler class instead.

Tips:
- Use the `DummyHistoryTextHandler.java` as a guide, pattern matching where appropriate. Being able to tinker with example code and bend it to your will is an incredibly important real world skill. Experiment away, don’t be afraid to break something!
- For Project 4A, you can ignore the k instance variable of NgordnetQuery.
- Use the `.toString()` method built into the TimeSeries class that gets inherited from TreeMap.
- For your HistoryTextHandler to be able to do something useful, it’s going to need to be able to access the data stored in your NGramMap. **Do not make the NGramMap into a static variable!** This is known as a “global variable” and is rarely the appropriate solution for any problem. Hint: Your HistoryTextHandler class can have a constructor like this: public HistoryTextHandler(NGramMap map).
- If a word is invalid, think about how NGramMap is handling this situation.
- The provided test is already mostly comprehensive. If you’d like to write additional tests, see the `Testing` spec for more information.

解决思路：
- 创建要求的文件 & 修改Main中的两行代码（一行原本为注释内容，一行为对应的htn.register）
- 写HistoryTextHandler的构造函数（参数为NGramMap对象）和实例变量来存储那一个参数的数据。
- 覆写extends来的Handle方法：参考demo中的代码 + 一个循环
    - 遍历words中的元素，把每个元素的weightHistory计算结果转为就字符串。
    - 按照格式拼接字符串，并更新最新的字符串记录变量
    - 遍历完后返回记录结果的字符串

# Task 4: HistoryHandler

按照网页说明一步步进行即可

In the `main` folder, create a new file called `HistoryHandler.java` that takes the given NgordnetQuery and returns a String that contains a base-64 encoded image of the appropriate plot.

Then, modify the `Main.java` so that your HistoryHandler is called when someone clicks the History button.

Tips:
- The constructor for HistoryHandler should be of the following form: public HistoryHandler(NGramMap map).
- Just like before, use DummyHistoryHandler.java as a guide. As mentioned in the previous section, we really want you to learn the skill of tinkering with complex library code to get the behavior you want.
-  the `Plotter.generateTimeSeriesChart` method, which takes two arguments. The first is `a list of strings`, and the second is `a List<TimeSeries>`. The TimeSeries are all plotted in a different color, and each is assigned the label given in the list of strings. Both lists must be of the same length (since the ith string is the label for the ith time series).
- The `Plotter.generateTimeSeriesChart` method returns an object of type `XYChart`. This object can in turn either be converted into base-64 by the `Plotter.encodeChartAsString` method, or can be displayed to the screen directly by `Plotter.displayChart`.

解题思路就是：参考demo文件夹下的`PlotDemo.java`和`DummyHistoryHandler.java`两个文件。历程类似于Task 3。
