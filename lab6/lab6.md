# Lab 6: Persistence

## 1. Java and Compilation

```
javac -version
java -version

javac *.java
The " *.java " wildcard(通配符) simply returns all the .java files in the current directory.

cat main.class
"cat" equals "display"

java capers.Main story "this is a single argument"
To run a Java file that is within a package, we must enter the parent directory (in our case, lab6) and use the fully canonical name.
```

Open Main.java, and find the line (around line number 40) that looks like:
```
if (args.length == 0) {
Utils.exitWithError("Must have at least one argument");
}
```
Immediately below this code add `System.out.println("args: " + Arrays.toString(args));`, so that the code now reads:
```
if (args.length == 0) {
Utils.exitWithError("Must have at least one argument");
}
System.out.println("args: " + Arrays.toString(args));
```
The word `Arrays` will be in red, so you’ll want to put your cursor on the red word and press alt+enter (or option+enter on a Mac) to `import the java.util.Arrays` class. 


## 2. Files and Directions in Java

### File System Basics

1. CWD(current work directory): the directory from where you execute that Java program.
   - System.getProperty("user.dir") : access the CWD from within a Java program.
2. Absolute and Relative Paths(绝对路径和相对路径):
    - Path: the location of a file or directory.
    - Absolute path: the location of a file or directory relative to the root of the file system.
    - Relative path: the location of a file or directory relative to the CWD of your program.
    - We use `~` as a shorthand to refer to your home directory.
    - When using paths, `.` refers to the CWD. `..` refers to the parent (or enclosing) directory. 

### File & Directory Manipulation in Java

The Java `File` class represents a file or directory in your operating system and allows you to do operations on those files and directories. 

In this class, you usually will want to be doing operations on files and directories by referring to them to their relative paths.

1. Files
```
File f = new File("dummy.txt"); // creating the new File object, we aren’t actually creating the file itself, we are just saying, “in the future, when I do operations with f, I want to do these operations on the file”.

f.createNewFile(); // create the file actually
f.exists(); //  check if the file already exists or not

// Actually writing to a file is pretty ugly in Java. To keep things simple, we’ve provided you with a Utils.java.
Utils.writeContents(f, "Hello World");
```

2. Directories

Directories in Java are also represented with File objects.
```
File d = new File("dummy");
d.mkdir(); // actually create the folder
```
