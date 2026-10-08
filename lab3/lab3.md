# Lab3: Randomize Comparison Tests

CS61B SP26的Lab3有两大部分。处于时间原因，这里只做了第二大部分：Randomize Comparison Tests。

## Simple Comparison Test: 简单的对比测试

One technique for testing code is to do a “`comparison test`”.

In such a test, we have two implementations of the same class. One implementation is known (or strongly believed) to be correct, and the other is under development and not yet verified.

任务： 写一个 JUnit 测试， 在`TestBuggyAList`总新建一个名字为 `testThreeAddThreeRemove` 的方法 —— 把同一个值分别加进"正确实现"和"有 bug 实现"，再检查随后 3 次 removeLast 的结果是否一致。

操作步骤：
- 在 randomizedtest 包里建 JUnit 测试。
- 建两个对象：AListNoResizing<Integer> 和 BuggyAList<Integer>。
- 对两者依次 addLast(4) → addLast(5) → addLast(6)。
- 调 3 次 removeLast()，每次用 assertEquals 比较两者返回值是否相等（第 3 次取出的应是 4）。
- 运行 —— 应该通过。
- 结论："This test is not strong enough to identify the bug."（这个测试强度不够，抓不到 bug。）

## Randomized Function Calls

An alternate and complementary strategy is to use a randomized approach where we make random calls to both implementations and use JUnit methods to verify that they always return the same values.

任务： 新建 JUnit 测试 randomizedTest()，原样粘贴lab给出的代码（随机调用 addLast / size，N = 500）。

## Conditional Breakpoints: 条件断点

Introducing two debugger features: `resume` and `conditional brealpoints`.

1. 在 int operationNumber = StdRandom.uniform(0, 2); 这一行下断点。
2. 用 Debug 跑，停在这行。（原文：不会用 debug 就去看 lab 2。）
3. 点 visualizer，会看到一个装满 null 的数组（将来存数据用）。
4. 点 step over，看 operationNumber 被置成 0 或 1。原文解释：StdRandom.uniform(0, 2) 返回 [0, 2)​ 内的随机整数（不含右端）；0 → 随机数加到末尾，1 → 打印 size。
5. 点 `resume` 按钮（原文图里黄框高亮那个），代码会跑到再次命中断点。
6. 连点几次 resume，看数组开始被填。
7. 切回 Debugger（或 Console，视机器而定）视图，继续点 resume，每点一次多一条 addLast / size 的打印。
8. 右键那个断点 → 弹出 “Condition:”框 → 输入 L.size() == 12。
9. 点 resume，代码跑到 size == 12 才停。点 visualizer 应看到 size 为 12、数组里 12 个元素。点过头了只能重启测试。
10. 结束后，取消打的断点。

## Adding More Randomized Calls

本小节的任务是：改`randomizedTest()`，增加两个操作：getLast 和 removeLast；把 StdRandom.uniform 改成能取 0~3。

重要提示：只有 L.size > 0 时才能调 getLast / removeLast！ size 为 0 会程序崩溃 —— 必须加 if 跳过。

## Adding Randomized Comparisons

任务： 上面的代码只对已知正确实现（AListNoResizing）调用。改成 —— 每对 AListNoResizing 调一次方法，就同时对有 bug 的实现调同一个方法，并且比较所有"有返回值"方法的返回值。卡住再看 /materials/lab/lab3/partialRandomizedComparisons.txt（先自己写）。

操作步骤：
- 再建一个 BuggyAList<Integer> 对象。
- 每个分支里，两个对象做同一操作：addLast 传同一个 randVal；size / getLast / removeLast 各调一次。
- 有返回值的用 assertEquals 比较两者结果。

## Running our Randomized Test

任务：
- 多跑几次，可能过也可能挂。
- 把 N 提到 5000，原文说 "It should fail almost every time."

重要提醒：
- 如果 bug 较隐蔽，随机操作序列可能碰不到它 —— 有改进手段但超出课程范围。
- 随机测试不能替代精心设计的单元测试，作者本人更偏好非随机测试，视随机测试为补充。

## Fixing the Bug and Execution Breakpoints

任务： 找到 bug → 修掉 → 重跑验证 BuggyAList 正确。

## Cleaning Up

print 是为教学留的，真实测试里只会产生一大坨没用的文本。原文补充：通常应该 “记录日志(log)”，而不是 print
