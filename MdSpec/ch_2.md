# 2. Defining and Using Classes

## 2.1 Static vs. Non-Static Methods

### 2.1.1 Static Methods

All code in Java must be part of a class(or something similar to a class.)
```java
public class Dog {
    public static void makeNoise() {
        System.out.println("Bark!");
    }
}
/*The Dog class we've defined doesn't do anything.
  We've simply defined something that Dog can do, namely make noise.
  To actually run the class, we'd either need to add a main method to the Dog class.
  Or we could create a separate DogLauncher class that runs methods from the Dog class. */
public class DogLauncher() {
    public static void main() {
        Dog.makeNoise();
    }
}
```
A class that uses another class is sometimes called a "`client`" of that class, i.e. DogLauncher is a client of Dog. 

### 2.1.2 Instance Variables and Object Instantiation

```java
public class Dog {
    public int weightInPounds;
    
    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("Yip yip yip!");
        }else if (weightInPounds < 30) {
            System.out.println("Bark bark!");
        } else {
            System.out.println("Woof!");
        }
    }
}

public class DogLauncher {
    public static void main(String[] args) {
        Dog d;
        d = new Dog();
        d.weightInPounds = 20;
        d.makeNoise();
    }
}
```
Some key observations and terminology:

    An Object in Java is an instance of any class.

    The Dog class has its own variables, also known as instance variables or non-static variables. These must be declared inside the class, unlike languages like Python or Matlab, where new variables can be added at runtime.

    The method that we created in the Dog class did not have the static keyword. We call such methods instance methods or non-static methods.

    To call the makeNoise method, we had to first instantiate a Dog using the new keyword, and then make a specific Dog bark. In other words, we called d.makeNoise() instead of Dog.makeNoise().

    Once an object has been instantiated, it can be assigned to a declared variable of the appropriate type, e.g. d = new Dog();

    Variables and methods of a class are also called members of a class.

    Members of a class are accessed using dot notation.

### 2.1.3 Constructor in Java: new keyword

As you've hopefully seen before, we usually construct objects in object-oriented languages using a constructor.

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(20);
        d.makeNoise();
    }
}

public class Dog {
    public int weightInPounds;

    // Constructor 构造函数/实例化
    public Dog(int w) {
        weightInPounds = w;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }
    }
}
```
The constructor with signature `public Dog(int w)` will be invoked anytime that we try to create a Dog using the `new` keyword and a single integer parameter.

For those of you coming from Python, the constructor is very similar to the \_\_init__ method.

### 2.1.4 Arrays

Arrays are also instantiated in Java using the new keyword.
```java
public class Dog {
    public int weightInPounds;

    // Constructor 构造函数/实例化
    public Dog(int w) {
        weightInPounds = w;
    }
}   
    
public class ArrayDemo {
    public static void main(String[] args) {
        int[] someArray = new in[5];
        someArray[0] = 1;
        someArray[1] = 4;
    }
}

public class DogArrayDemo {
    public static void main(String[] args){
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(8);
        dogs[1] = new Dog(20);
        
        dogs[0].makeNoisr();
    }
}
```
Observe that new is used in two different ways: Once to create an array that can hold two Dog objects, and twice to create each actual Dog.

## 2.2 Class Methods vs. Instance Methods

类方法和实例方法

Java allows us to define two types of methods:

    Class methods, a.k.a. static methods.
    Instance methods, a.k.a. non-static methods.

Instance methods are actions that can be taken only by a specific instance of a class.

Static methods are actions that are taken by the class itself. Both are useful in different circumstances.
```java
double x = Math.sqrt(100); // static method

Math m = new Math();
int x = m.sqrt(16); // non-static method/ instance method. 
```

```java
public static Dog maxDog(Dog d1, Dog d2) {
    if (d1.weightInPounds > d2.weightInPounds) {
        return d1;
    }
    return d2;
}

Dog d = new Dog(15);
Dog d2 = new Dog(100);
Dog.maxDog(d, d2);
```
```java
public Dog maxDog(Dog d2) {
    if (this.weightInPounds > d2.weightInPounds) {
        return this;
    }
    return d2;
}

Dog d = new Dog(15);
Dog d2 = new Dog(100);
d.maxDog(d2);
```

### 2.2.1 Static Variables

It is occasionally useful for classes to have static variables. These are properties inherent to the class itself, rather than the instance.

类属性和实例属性?

Static variables should be accessed using the name of the class rather than a specific instance, e.g. you should use `Dog.binomen`, not d.binomen.
```java
public class Dog {
    public int weightInPounds;
    public static String binomen = "Canis familiaris";
    // ...
}
```

## 2.3 public static void main(String[] args)

Breaking it into pieces, we have:


    public: So far, all of our methods start with this keyword.
    static: It is a static method, not associated with any particular instance.
    void: It has no return type.
    main: This is the name of the method.
    String[] args: This is a parameter that is passed to the main method.

### 2.3.1 Command Line Arguments

Since main is called by the Java interpreter itself rather than another Java class, it is the interpreter's job to supply these arguments. They refer usually to the `command line arguments`. 

```java
public class ArgsDemo {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
// $ java ArgsDemo these are command line arguments
// these
```
In the example above, args will be an array of Strings, where the entries are {"these", "are", "command", "line", "arguments"}.
