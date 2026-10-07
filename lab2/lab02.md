# Lab02: Debugger and JUnit in IntelliJ

lab02

## 1. Debugger Basics

先打断点再调试，前进步骤选择有很多：step into， step over， step out。每步执行后观察提供的信息，将其与预期相比，从而分析bug在哪里。

1. Setting Breakpoints

2. Stepping Through Code:

   - step over:
   - step into:
   - step out:
   - force step into:
   - drop frame:
   - run to cursor:
   - return button:
   - resume button:
   - pause program button:
   - stop program button:
   - view breakpoints:
   - mute breakpoints:
3. Analyzing the Current State: two places getting information.
   
   1. debugger view:
   - first column: shows the stack and frames.
   - second column: lists all the variables in current frame.
   - `evaluate expression` button in toolbar: allows you to essentially insert lines on the fly to the program.
   2. code view: on the code itself.

### Breakpoints and Step Into (Task 1)

通过step into观察变量的值，可以发现问题是出现在函数内部关于商的计算上。

1. 在 `DebugExercise1` 上 **Run**，会看到打印三行，其中一行明显不对。
2. 官方明确要求：**别用肉眼读代码找 bug**。用 **Debug**（不是 Run）启动；如果没有 Debug 选项，说明项目没导入对。
3. 设断点：找到 `int t3 = 3;` 这一行，**点行号右侧**，出现红色圆点。
4. 如果 Console 窗口没出现：把底部面板的 **Console** 标签拖到最右边，让 Debugger 和 Console 能同时显示。
5. 用 **step into**（向下的箭头，**不是**向右下的 step over）一步步走。**每点一次之前，先假设变量应该怎么变。**
6. 记住：**高亮的那一行是"即将执行"的行，不是刚执行完的行。**
7. 一直走到某一行结果与预期不符，想清楚为什么。错过就点红色方块停止、重新 Debug。找到后可以顺手修掉。
8. 建议顺手试试 **Watches** 标签和 **Evaluate Expression**（一排步进按钮里的计算器图标）；Lab 3、4 会讲更多调试器功能。

### 任务 2 · Step Over and Step Out (Task 2)

通过打断点、调试，观察后可以首先看出max，求出最值得数组这一Function求的是最小值组成的数组。再一次step over，可以发现sumofMaxes的值求错了，也就是说：求和对应的函数也有bug。

`DebugExercise2` 的 `main` 应该做这件事：取两个数组，求逐元素最大值，再把这些最大值加起来。

代码里有两个 bug，任务是修掉它们。

特殊规则：

- **不许 step into `max` 和 `add` 这两个函数**，也不需要理解它们（页面原话：这两个函数用怪异的语法和很烂的风格，把简单的事做得极其晦涩）。
- 误入就用 **step out**（向上箭头）逃出来。
- 即使不进函数内部，也应该能判断它有没有 bug —— 这就是抽象的意义（页面的鱼类比：不用懂鱼的分子结构，也能看出鱼死了）。
- 如果判定某个函数有 bug，**整个重写**，不要在它身上修补。

## 2. JUnit and Unit Testing(JUnit和单元测试)

（对应 lecture 3 的内容。）

- **"Unit" 的含义**：把程序拆成单元（可测试的最小部分）。单元测试因此强制了良好的代码结构（每个方法只做"一件事"），也让你能逐条考虑每个方法的边界情况。
- 本课用 **JUnit** 写测试。JUnit 测试失败时，正好是调试的最佳起点。
- 如果遇到难以修复的严重 bug，可以用 git 回退到"当时测试通过"的状态（Lab 4 会讲怎么回退）。

### 2.1 JUnit 语法

打开 `ArithmeticTest.java`。

1. 先看顶部的 **imports**（IntelliJ 有时会折叠成 `import ...;`，点开 `...` 就能展开）。这些 import 让你能直用 JUnit 的方法。
2. 文件里有两个方法：`testProduct` 和 `testSum`，格式如下：

```java
@Test
public void testMethod() {
    assertEquals(<expected>, <actual>);
}
```

3. `assertEquals` 用于判断实际值是否等于期望值。
4. 规则：
   - 每个测试方法前面都要加 **`@Test`** 注解。
   - 一个测试方法里可以有一个或多个 `assertEquals` / `assertTrue`。
   - **所有测试方法必须非 static。**（看起来奇怪，因为测试不用实例变量、通常也不会 new 这个类；但 JUnit 的设计者就是这么定的。原文自述原因"unclear"。）

## 在 IntelliJ（或其它 IDE）里运行 JUnit 测试

> 页面原话：如果你不用 IntelliJ，助教没受过培训、也不提供其它 IDE 或命令行编译运行的支持。

1. 让 `ArithmeticTest.java` 处于打开状态。
2. 顶部菜单 **Run → Run…**
3. 在列表里选带红绿箭头图标的 **"ArithmeticTest"**（列表项数量因人而异）。
4. 你应该看到类似这样的结果：**`ArithmeticTest.java` 第 25 行的测试失败** —— 期望 `5 + 6 = 11`，但 `Arithmetic` 类声称 `5 + 6 = 30`。
5. 注意：虽然 `testSum` 里有多个 assert，**只显示了一个失败**。原因是 **JUnit 是短路的**：方法里只要有一个 assert 失败，就会输出该失败并跳到下一个测试。
6. 点面板里的 **`ArithmeticTest.java:27`**，IntelliJ 会直接跳到导致失败的那一行（以后做项目时很好用）。
7. **任务：修掉这个 bug**（读 `Arithmetic.java` 找，或用调试器逐步走）。
8. 重跑测试。用默认渲染器的话，会得到一条绿色的条。

---

## Application: IntLists (Task 3)

背景：`IntList` 是本课讲的**裸递归链表**实现。每个 `IntList` 有 `first` 和 `rest` 两个变量：`first` 是节点里的整数，`rest` 是链上的下一个 `IntList`。

`IntListExercises.java` 里有三个方法，**每个都有 bug**。这一节的任务就是把它们找出来并修掉。

### Starter Code

在 Monday 的课堂实现之外，`IntList` 类新增了两个方法：`print` 和 `of`。

**`of`** —— 便捷构造方法。原本要这样写：

```java
IntList lst = new IntList(1, new IntList(2, new IntList(3, null)));
```

现在可以直接：

```java
IntList lst = IntList.of(1, 2, 3);
```

任意长度都行：

```java
IntList empty = IntList.of();            // 空表
IntList oneElem = IntList.of(7);         // 只有一个元素 7
IntList manyElems = IntList.of(5, 4, 3, 2, 1);
```

**`print`** —— 返回 `IntList` 的字符串表示：

```java
IntList lst = IntList.of(1, 2, 3);
System.out.println(lst.toString())

// Output: 1 -> 2 -> 3
```

这两个方法本身没有增加实质功能，但让创建和显示 `IntList` 变方便了 —— 页面明确说，就是为了让你写 JUnit 测试时更省事。

### Part A: IntList Iteration

调试 `IntListExercises.java` 里的 **`addConstant`**。它的意图是：**原地（mutatively）**给链表每个元素加上一个常数。

```java
/* Expected Behavior */
IntList lst = IntList.of(1, 2, 3);

addConstant(lst, 1);
System.out.println(lst.toString());
// Output: 2 -> 3 -> 4

addConstant(lst, 4);
System.out.println(lst.toString());
// Output: 6 -> 7 -> 8
```

**任务**：官方提供的实现是有 bug 的。`AddConstantTest.java` 里已经给了**三个测试**帮你定位。**用 Java Debugger 逐个 step through 这三个测试**，隔离出 bug，然后修掉。

### Part B: Nested Helper Methods and Refactoring for Debugging

调试 **`setToZeroIfMaxFEL`**。

这个方法做的事很怪：**当且仅当"从某个节点开始的子链表的最大值"首位数字和末位数字相同时**，把该节点的值置为 0。`FEL` = "first equals last"。

例：传入 `55 -> 22 -> 45 -> 44 -> 5`，结果是 `0 -> 22 -> 45 -> 0 -> 0`。原因逐条如下：

- 从 `55` 开始的链表最大值是 `55`，首末位相同 → 置 0。
- 从 `22` 开始的链表最大值是 `45`，首末位不同 → `22` 不变。
- 从 `45` 开始的链表最大值是 `45`，首末位不同 → `45` 不变。
- 从 `44` 开始的链表最大值是 `44`，首末位相同 → 置 0。
- 从 `5` 开始的链表最大值是 `5`，首末位相同 → 置 0。

**自测题**（页面给的）：`5 -> 535 -> 35 -> 11 -> 10 -> 0` 调用 `setToZeroIfMaxFEL` 后应该是什么？答案在 `SetToZeroIfMaxFELTest` 的 `testZeroOutFELMaxes3` 里。

**操作步骤**：

1. 跑测试。会看到方法是**有 bug 的，具体是 test 3 失败**。
2. 在 `setToZeroIfMaxFEL` 的**第一行设断点**，然后**只 debug `testZeroOutFELMaxes3` 这一个测试**。方法：打开 `SetToZeroIfMaxFELTest.java`，找到 `testZeroOutFELMaxes3()` 方法定义，点它左边的小绿色箭头。（如果这个测试之前跑过，绿色箭头可能变成绿色对勾+绿箭头（之前通过）或红色感叹号+绿箭头（之前失败）。）
3. **step in 两三次**，会走到 `if (firstDigitEqualsLastDigit(max(p)))` 这一行。**第三次点 step in 时，`firstDigitEqualsLastDigit` 和 `max` 会同时高亮** —— 因为是嵌套函数调用，IntelliJ 在问你要进哪一个。点 `max` 会进入 max 的细节；点 `firstDigitEqualsLastDigit`，那么 `max` 那一次调用就被 step over 掉了。
4. **先重构**（页面说：我个人觉得这种代码很难调，我常用的一招就是重构）。把代码改成：

```java
int currentMax = max(p);
boolean firstEqualsLast = firstDigitEqualsLastDigit(currentMax);
if (firstEqualsLast) {
    p.first = 0;
}
```

5. 重构后**用 step over** 找出是哪个 `max` 或 `firstDigitEqualsLastDigit` 调用给出了错误结果。
   **重要：找到之前不要用 step in。** 页面原话："如果你在盯着 `max` 每一次调用的每一次迭代，你就不是在正确使用调试器。"
6. 找到那个给出怪结果的调用后，**重新开始调试**，这次对那个参数用 **step in** 而不是 step out。
   （Lab 4 会讲"条件断点"，可以省去重新开始调试这一步。）
7. 定位到 bug 后修掉。**也可以把重构后的代码改回单行版本** `if (firstDigitEqualsLastDigit(max(p)));`。
8. 页面的提示：真实世界里，你本来应该**先分别测好 `max` 和 `firstDigitEqualsLastDigit`**，再在 `setToZeroIfMaxFEL` 里用它们。

### Part C: Tricky IntLists!

调试 **`squarePrimes`**。

意图：把链表里**所有质数元素平方**，合数（非质数）元素保持不动。**只要至少有一个元素被平方就返回 `true`，否则返回 `false`。**

例：链表 `14, 15, 16, 17, 18`。运行后，质数元素 `17` 被平方，合数元素 `14, 15, 16, 18` 保持不变；返回值应为 `true`。

```java
/* Expected Behavior */
IntList lst = IntList.of(14, 15, 16, 17, 18);
System.out.println(lst.toString());
// Output: 14 -> 15 -> 16 -> 17 -> 18

boolean changed = squarePrimes(lst);
System.out.println(lst.toString());
// Output: 14 -> 15 -> 16 -> 289 -> 18

System.out.println(changed);
// Output: true
```

`squarePrimes` 用 `Primes.isPrime(int x)` 做辅助方法：参数是质数返回 true，是合数返回 false。

**`isPrime` 当成黑盒** —— 页面的明确要求。调试时对 `isPrime` 用 **Step Over**，这样你可以核验它的输入输出对不对，而不用去关心它的实现。（可选：好奇的话可以搜 "Fermat Primality Test"。）

**任务三步**（页面原文）：

1. **写 JUnit 测试**，覆盖多种不同输入。**既要测它是否正确修改了传进去的 `IntList`，也要测返回的 `boolean` 值是否正确。**
2. 一旦你写出了一个让 `squarePrimes` **失败**的测试，就是进展！现在**用 Java Debugger 逐步走**，隔离出 bug。
3. 最后**写出修复**。页面原话：这个 bug 的修复没几行代码，**难的是找到它**。

**起点**：页面已经给了你一个测试 `SquarePrimesTest.testSquarePrimesSimple`，它检验上面那个例子（`14, 15, 16, 17, 18`）被正确修改、且返回正确的值（这里是 `true`）。**但很不巧，这个测试是能通过的 —— 你得自己再写一个会失败的测试。** 可以照 `testSquarePrimesSimple` 的写法来写。

---

## Submission

- 和之前一样：把代码 push 到 GitHub，提交到 Gradescope。
- 你会注意到有些测试是 **"Hidden"（隐藏）** 的：不告诉你它测什么，失败时给的报错信息**故意很含糊**。原因是：这个 lab 想让你专注学习**自己调试**，而不是依赖 autograder 给的提示信息。

## Full Recap

本 lab 覆盖了：

- 在 IntelliJ 调试器里 step into / over / out（做项目时很有用）
- 单元测试（大图）
- JUnit 的语法与细节
- 写 JUnit 测试
- 用 JUnit 调试
- 运行 Style Checker

## FAQ and Common Issues

### Things like `String` or `String.equals()` are red!

这是 JDK 问题。去 **File > Project Structure > Project > Project SDK** 排查。如果你的 Java 版本是 15.0，那你就应该配一个 15.0 的 SDK，以及 Level 15 的 "Project Language Level"。


