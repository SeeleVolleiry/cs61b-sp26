# 3. References, Recursion, and Lists

In this chapter, we'll build our own list from scratch, along the way learning some key features of Java.

## 3.1 Bits

All information stored in computer memories is as a sequence of zeros and ones.

Java interpret bits through `Type`s. There are 8 primitive types: byte, short, int, long, float, double, boolean and char.

## 3.2 Declare a Variable

You can think of your computer as containing a vast number of memory bits for storing information, each of which has a unique address. Many billions of such bits are available to the modern computer.

When you declare a variable of a certain type, Java finds a contiguous block with exactly enough bits to hold a thing of that type.

In addition to setting aside memory, the Java interpreter also creates an entry in an internal table that maps each variable name to the location of the first bit in the box.

## 3.3 The Golden Rule of Equals(GRoE)

This simple idea of copying the bits is true for ANY assignment using = in Java. 

## 3.4 Reference Types

Except 8 primitive types, everything else is rather a reference type.

例如下面自定义的Walrus。
```java
public static class Walrus {
    public int weight;
    public double tuskSize;

    public Walrus(int w, double ts) {
          weight = w;
          tuskSize = ts;
    }
}
```

### 3.4.1 Object instantiation:实例化出一个对象

When we instantiate an Object using new (e.g. Dog, Walrus, Planet), Java first allocates a box for each instance variable of the class, and fills them with a default value. 

The constructor then usually (but not always) fills every box with some other value.

In real implementations of the Java programming language, there is actually some additional overhead for any object
一个类的实例/对象，除了定义的各种变量外，开头一般还有其他信息。

### 3.4.2 Refence Variable Declaration

任何引用类型的变量声明时，Java会分配一个64 bits的空间来储存变量的地址，而不是变量本身。也就是说：变量名等于引用类型的对象的地址（类似于C语言，一个指针），地址指向的才是真正储存实例内容/信息的地方。

When we declare a variable of any reference type (Walrus, Dog, Planet, array, etc.), Java allocates a box of 64 bits, no matter what type of object.

The 64 bit box contains not the data about the reference type, but instead the address of that type in memory.

```java
Walrus someWalrus;
someWalrus = new Walrus(1000, 8.3);
```
The first line creates a box of 64 bits.

The second line creates a new Walrus, and the address is returned by the new operator.

These bits are then copied into the someWalrus box according to the GRoE.

## 3.5 Parameter Passing

When you pass parameters to a function, you are also simply copying the bits. In other words, the GRoE also applies to parameter passing. 

Copying the bits is usually called "`pass by value`". In Java, we always pass by value.

```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```

## 3.6 Instantiation of Arrays

Instantiating an array is very similar to instantiating an object.
```java
int[] x;
x = new int[]{0, 1, 2, 3, 4,};
// the new keyword creates 5 boxes of 32 bits each and returns the address of the overall object for assignment to x.
```

## 3.6 The Law of the Broken Futon



## 3.7 == Vs. Arrays.equals

Whenever we write x==y we are asking Java to compare the literal bits in memory boxes x and y.
```java
int[] x = new int[]{0, 1, 2, 95, 4};
int[] y = new int[]{0, 1, 2, 95, 4};
System.out.println(x == y); // false

int[] x = new int[]{0, 1, 2, 95, 4};
int[] y = new int[]{0, 1, 2, 95, 4};
System.out.println(Arrays.equals(x, y)); // true
```

## 3.8 IntLists

Now, we're ready to build our own list class.

```java
// Linked List in cs61a. BUr it's ugly to use and prone to errors.
public class IntLists {
    public int first;
    public IntLists rest;
    
    public InLists(int f, IntLists r){
        first = f;
        rest = r;
    }
    
    public int size() {
        if (rest == null){
            return 1;
        }
        return 1 + this.rest.size();
    }
    
    public int iterativeSize() {
        IntLists curr = this;
        int size = 0;
        while(curr != null) {
            size += 1;
            curr = curr.rest;
        }
        return size;
    }
    // exercise: written a method to get ith item of IntLists.
    public int get(int i) {
        int item;
        if(i < 0 || i >= this.size() ) {
            return ;
        }
        if (i == 0){
            return first;
        }
        else{
          return this.rest.get(i-1);  
        }
    }
}
```
