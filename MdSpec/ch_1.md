# 1.Introduction

This section covers basic features of Java and how Java programs are compiled and run.

## 1.1 Your First Java Program

```java
void main(){
    IO.println("Hello world!");
}
```

we can observe the following features of this simplest Java program:

    The main function: all the code that runs must be inside of a function called main. We'll cover what the void means later.

    Curly braces {} enclose sections of code (functions, classes, and other types of code that will be covered in future chapters).

    All statements must end with a semi-colon.

    Printing requires us to use dot notation, going to some library called IO and asking for its print function.

## 1.2 Basic Java Features

### Variables and Loops

```java
void main(){
    int x;
    
    x = 0;
    while (x < 10){
        IO.print(x + " ");
        x = x + 1;
    }
}
```
Some interesting features of this program that might jump out at you:

    Our variable x must be declared before it is used, and it must be given a type!

    Our loop definition is contained inside of curly braces, and the boolean expression that is tested is contained inside of parentheses.

    Our print statement is just IO.print instead of IO.println. This means we should not include a newline (a return).

    Our print statement adds a number to a space. This makes sure the numbers don't run into each other. Try removing the space to see what happens.

Of these features the most important one is the fact that variables have a declared type.

### Static Typing

Java is a statically typed language, which means that all variables, parameters, and methods must have a declared type. After declaration, the type can never change. Expressions also have an implicit type.

因为这一机制，Java在编译前会在程序运行之前，检查类型是否匹配。

To summarize, static typing has the following advantages:

    The compiler ensures that all types are compatible, making it easier for the programmer to debug their code.

    Since the code is guaranteed to be free of type errors, users of your compiled programs will never run into type errors. For example, Android apps are written in Java, and are typically distributed only as .class files, i.e. in a compiled format. As a result, such applications should never crash due to a type error since they have already been checked by the compiler.

    Every variable, parameter, and function has a declared type, making it easier for a programmer to understand and reason about code.

However, static typing also has several disadvantages, which will be discussed further in later chapters. To name a few:

    More verbose code.
    Less generalizable code.

### Defining Function In Java

The code below declares a function that returns the larger of two arguments
```java
int larger(int x, int y){
    if (x > y){
        return x;
    }
    return y;
}
void main(){
    IO.println(larger(5, 10));
}
```
Here we observe:

    Java function parameters must have a type.

    Java functions can return only one value.

    Java functions must have a declared return type. Note that the void in void main function is specifying that the return type of main is void, i.e. it returns nothing.

One downside of static types is the resulting verbosity. If we wanted to use larger on strings, we'd have to make another copy of the method that takes strings as input. 

### Code Style, Comments, Javadoc

Some of the most important features of good coding style are:

    Consistent style (spacing, variable naming, brace style, etc)

    Size (lines that are not too wide, source files that are not too long)

    Descriptive naming (variables, functions, classes), e.g. variables or functions with names like year or getUserName instead of x or f.

    Avoidance of repetitive code: You should almost never have two significant blocks of code that are nearly identical except for a few changes.

    Comments where appropriate. Line comments in Java use the // delimiter. Block (a.k.a. multi-line comments) comments use /* and */.

The golden rule is this: Write your code so that it is easy for a stranger to understand.

One special note is that all of your methods and almost all of your classes should be described in a comment using the so-called Javadoc format.
In a Javadoc comment, the block comment starts with an extra asterisk, e.g. /**, and the comment often (but not always) contains descriptive tags.
```java
// a example without tags
public class LargerDemo {
    /** Returns the larger of x and y. */
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```
A widely used javadoc tool can be used to generate HTML descriptions of your code.

## 1.3 Java Workflow

Taking a program from a .java file into an executable has two main steps in Java: compilation and interpretation.

To run the code in Hello.java, we must first compile the code into a `.class` file, which we can do using the command `javac HelloWorld.java`. Then, to run the code, we would use the command `java HelloWorld`.

There are several reasons for the usage of .class files, which we will only cover briefly here. First of all, .class files are guaranteed to have been type-checked, making the distributed code safer. They are also more efficient to execute, and protect the actual source code in cases of intellectual property.
