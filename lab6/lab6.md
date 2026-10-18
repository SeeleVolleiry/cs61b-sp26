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


