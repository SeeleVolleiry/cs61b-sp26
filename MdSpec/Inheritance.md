# Inheritance: 继承

CS61B电子教材的第8、第9和第10三章都是一个中心主题：继承。所以，将三张内容综合，写成本文。主要的内容归纳起来，无非如下几条：
- 为什么需要继承这一特性？
- 继承的两种形态/实现：接口继承、实现继承
- 继承的两种用法

A class that is derived from another class is called a subclass (also a derived class, extended class, or child class). The class from which the subclass is derived is called a superclass (also a base class or a parent class).

## 1. 为什么需要继承

同一段逻辑要为每个类型各抄一遍，份数随类型数增长。通用性不足。

继承把"这段逻辑能用在哪些类型上"，从每个类型各写一份，改成写一份、挂在共同的上位类型上——层级的概念。

类比Python的class之间的继承关系：超类与子类；类比生物分类——界门纲目科属种。

就像是 AList, SLList都是“列表”，列表有是数据结构的一种。

层级（hierarchy），越往上越是具有一般性/共性，越往下越是具有上层所没有的特性（除了继承上层的共性）。这样不断继承的能力，可以为我们解决通用性提供一种思路。

## 2.继承的两种实现/形态：extends, implements

在Java中实现层级（hierarchy）/继承（inheritance）有两步：
- 第一步是：定义一个superclass（定义层级） —— `interface` 一个完整的类
- 第二步是：声明谁是subclass（确定继承关系） —— `implements` `extends`

### 2.1 接口继承（interface inheritance）

“接口继承”：
- Interface Inheritance refers to a relationship in which a subclass inherits all the methods/behaviors of the superclass.
- The interface includes all the method signatures, but not implementations.
- The subclass actually provide those implementations."
- 接口继承 = 子类把父类的"方法/行为"整套接下来。
- 但接到的只有方法签名，没有实现。实现由子类自己补全。

`interface`：

    The superclass is what Java calls an interface. It is essentially a contract that specifies what a class must be able to do, but it doesn't provide any implementation for those behaviors.
```java
// step 1的实际例子，一个interface的实现
public interface List61B<Item> {
    // 每行都是一个方法签名，行尾是分号，没有一个 {} 方法体。
    // public可以删掉，对于接口来讲，默认是public的。
    void addFirst(Item x);
    void addLast(Item y);
    Item getFirst();
    Item getLast();
    Item removeLast();
    Item get(int i);
    void insert(Item x, int position);
    int size();
}
```

`implements`: A promise.

    In Java, we define this relationship in the class definition.
    The relationship-defining word/keyword is " implements ".
```java
// implements <superclass_name>:
// 结构就是：subclass implements superclass, 子类实现超类。
// 超类是一个接口（interface），里面有方法和属性。子类承诺会实现所有的方法和属性。
public class AList<Item> implements List61B<Item>{}
```

`@Override`: 实现 implements 的承诺.

    加不加，覆盖照样发生 。
    加的好处在于 @Override 是你向编译器下的断言 —— "这个方法上面一定有个同签名的等着被盖"。编译器一查上面没有，就报错。
    @Override 只能标"覆盖"（实例方法），不能标"隐藏"（同签名的静态方法 / 同名字段）
```java
// addFirst 在接口里只是一行签名，这里给了它身体，补全了{}的内容。
@Override
public void addFirst(Item x) {
insert(x, 0);
}
```

`default`：允许接口里的某个方法带上方法体。

    default 把"给接口加方法"从破坏性变更变成了兼容变更：因为没有default时，新加的方法必须要被其子类实现，不然编译会报错。而你又是新加的，以前的子类根本没有实现这一方法。所以说，没有default时添加方法是破坏性变更。
    Previously, we had an interface List61B that only had method headers identifying what List61B's should do.
    But, now we will see that we can write methods in List61B that already have their implementation filled out.
    These methods identify how hypernyms of List61B should behave.

    default 方法只能调接口声明过的东西，不能调实现类特有的东西
```java
@Override
public void print() {
    for (Node p = sentinel.next; p != null; p = p.next) {
        System.out.print(p.item + " ");
    }
}
```
### 2.2 extends继承

`extends`：继承了 members——字段/属性 + 方法 + 嵌套类。

    A subclass inherits all the members (fields, methods, and nested classes) from its superclass. 
    Constructors are not members, so they are not inherited by subclasses, but the constructor of the superclass can be invoked from the subclass.

    A subclass inherits all of the public and protected members of its parent, no matter what package the subclass is in. 
    If the subclass is in the same package as its parent, it also inherits the package-private members of the parent.

```java
public class Bicycle {
    // the Bicycle class has three fields
    public int cadence;
    public int gear;
    public int speed;

    // the Bicycle class has one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }

    // the Bicycle class has four methods
    public void setCadence(int newValue) { cadence = newValue; }
    public void setGear(int newValue) { gear = newValue; }
    public void applyBrake(int decrement) { speed -= decrement; }
    public void speedUp(int increment) { speed += increment; }
}
```
```java
// subclass extends superclass
// 子类 扩展/来源于 基类，这样就实现了继承。
public class MountainBike extends Bicycle {

    // the MountainBike subclass adds one field
    public int seatHeight;

    // the MountainBike subclass has one constructor
    public MountainBike(int startHeight,
                        int startCadence,
                        int startSpeed,
                        int startGear) {
        super(startCadence, startSpeed, startGear);
        seatHeight = startHeight;
    }

    // the MountainBike subclass adds one method
    public void setHeight(int newValue) { seatHeight = newValue; }
}
```

Excepting Object, which has no superclass, every class has one and only one direct superclass (single inheritance). In the absence of any other explicit superclass, every class is implicitly a subclass of Object.

implements 和 extends 的区别：
- class A extends B → B 必须是类
- class A implements C → C 必须是接口
- 两个关键字可以一起使用

### 2.3 ADT

An Abstract Data Type (ADT) is defined only by its operations, not by its implementation.

## 3 继承的用法

### 3.1 用法一：子类型多态 —— 通用的函数

逻辑链：
- 目标：我想要一个通用函数 —— 一段代码，喂任何类型都能跑。比如 max：给它一串东西，返回最大的那个。
- 卡点：因为"谁大谁小"每种类型都不一样。狗按体重比？按名字比？max 自己不可能知道。
- 于是只剩一个问题："怎么比"这份信息，从哪儿交给 max？
- 解决方案：**多态**（把"怎么比"写在类型自己身上，max 里只写 a > b）→ Java 走的是这条。Python的方法是**函数传递**（把怎么比写成函数，当参数传递进去）。

通用函数的"通用"，要么靠类型自己会做这件事（多态），要么靠你把做法传进去（函数传递）。

### 3.1.1 多态是什么 

多态 = 同一个接口，底下可以是不同的东西。例如，"同一个接口"是 >，"不同的东西"就是各种类型。

The get_the_max function in Python is general and can work on any type. It achieves this generality by harnessing ‘operator overloading’.

More generally, this ability to handle any type is sometimes called 'polymorphism', which I'll define via wikipedia as 'the ability in programming to present the same programming interface for differing underlying forms.

### 3.1.2 Java的多态实现: comparable, comparator

#### 3.1.2.1

Java doesn't have operator overloading, defined by programmers. Instead, we must utilize implementation inheritance.

Specifically, we'll implement the `Comparable` interface.

The idea here is that a supertype (Comparable) specifies the capability（in this case, comparison）.

Then a subtype (Dog) overrides the supertype's abstract method. At runtime, Java decides what to do based on the type of the object that is invoking the method.
```java
// compareTo 不返回布尔，返回符号。<0 我小、=0 相等、>0 我大。
public interface Comparable<T> {
    int compareTo(T o);
}
```
```java
public class Dog implements Comparable<Dog> {
    public String name;
    public int size;
    @Override
    public int compareTo(Dog uddaDog) {
        //  Returning a difference between two numbers is a common way to implement compareTo methods in Java.
        return this.size - uddaDog.size; // note: int type has its own range. Recommend writing " Integer.compare(size, uddaDog.size) "
    }
}
```

`comparator` interface:注意和comparable实现多态的区别——参数数量。

以前 Java 里函数不能单独当一个值传来传去，所以先把函数"装进一个对象"再传 —— 那个对象就是 Comparator。这不是两条不同的路，是同一条路，Java 拐了个弯。
```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}

public static class NameComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog a, Dog b) {
        return a.name.compareTo(b.name);
    }
    // using comparator
    Dog maxNameDog = Collections.max(dogs, new Dog.NameComparator());
    // 在Dog类中加上： public static final Comparator<Dog> NAME_COMPARATOR = new NameComparator();
    Dog maxNameDog = Collections.max(dogs, Dog.NAME_COMPARATOR);
    // 使用lambda：只有一个抽象方法的接口，可以用 (参数) -> 表达式 直接写，编译器自动帮你包成对象
    Comparator<Dog> dc = (a, b) -> a.name.compareTo(b.name);
    Dog maxNameDog = Collections.max(dogs, dc);
}
```

`Comparable vs. Comparator`:
- The Comparable interface specifies that a "natural order" exists. Instances of the class can compare themselves to other objects. Only one such order is possible.
- The Comparator interface is used to compare extrinsically (by other classes). May have many such classes, each specifying one such order.

"怎么比"这份信息，Java 是装进对象里传的 —— 装在自己身上叫 Comparable，装在外面那把"尺子对象"里叫 Comparator。

怎么选：
- 这个类型天生就该有一种排序 → 让它 implements Comparable（比如 String、Integer）。
- 只是想临时换个维度看看（按名字、按价格、按日期）→ 写 Comparator。好处是：不用改被比的类。（String 你改不了，但你可以给 String 写一堆 Comparator。）

#### 3.1.2.2 泛型方法：自己写Collections.max

泛型方法里要调某个方法，就得先在上界里把"这个能力"声明出来。

```java
public class Maximizer {
    // 这里的 extends 不是类继承 —— 它是类型上界（type bound），读作 “T 必须是一个 Comparable<T>
    //Comparable<? super T> = “T 的某个父类型实现了 Comparable 就行” → 放宽了范围。没有 ？super 则是精确对比。
   public static <T extends Comparable<? super T>> T max(T[] items) {
       T maxItem = items[0];
       for (int i = 0; i < items.length; i += 1) {
           int cmp = items[i].compareTo(maxItem);
           if (cmp > 0) {
               maxItem = items[i];
           }
       }
       return maxItem;
   }
}
```

If we want to write the code that actually uses a Comparable or Comparator, then we'll find the method specifications get a little vexing.(这堆绕人的泛型签名，只在"写库函数"时才会碰到)

Luckily for you, you won't have to deal with this much.

### 3.2 用法二：标准库与Object

第10章：Iterators， Object Methods。从空壳补到完整，写一个真实的类。会遇见四个大的bug：
- null会导致崩溃
- 无法使用 增强for 这一功能
- println方法会打印乱码
- 无法判断内容是否相等

#### Lists and Sets in Java（10.1节）

`List`: Java provides a built-in List interface and several implementations, e.g. ArrayList.

Since List is an interface we can't instantiate it! We must instantiate one of its implementations.
```java
/* java.util.List<Integer> L = new java.util.ArrayList<>();
 * This is a bit verbose. Instead, using code like below by importing Java libraries.
 * */
import java.util.List;
import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        List<Integer> L = new ArrayList<>();
        L.add(5);
        L.add(10);
        System.out.println(L);
    }
}
```

`Set`: A collection of unique elements.(unique and unordered)
- You can only have one copy of each element. 
- There is also no sense of order: you can't index into a set, nor can you control where each element is inserted into the set.

```java
public class setExample {
    Set<String> s = new HashSet<>();
    s.add("Tokyo");
    s.add("Lagos");
    System.out.println(s.contains("Tokyo")); // true
}
```

DIY `ArratSet`: the set, made by ourselves, with following methods.
- add(value): add the value to the set if not already present.
- contains(value): check to if ArraySet contains the key. 
- size(): return the number of values.

*这就是第十章需要我们补充完善的类——ArraySet*。

#### Exceptions:异常（10.2节）

Our ArraySet implementation from the previous section has a small error.

When we add `null` to our ArraySet, we get a NullPointerException.(本文3.2节中最开始提到的问题之一：特定情形下 null 会导致崩溃)。

The problem lies in the contains method where we check `items[i].equals(x)`. If the value at `items[i]` is `null`, then we are calling `null.equals(x)` -> `NullPointerException`.

`Exception`: causes normal flow of control to stop.
- Exceptions are objects.
- Throw exceptions using the format: `throw new ExceptionObject(parameter1, ...)`
