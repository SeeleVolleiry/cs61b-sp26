# Disc02: Lists, Maps, Arrays, References

## 1. Class Creation

Translate the following Python Planet class into Java.

Make sure to use correct Java syntax and naming
conventions (e.g. camelCase for methods).

Assume x, y, and mass are doubles. For an example Java class, see
https://tinyurl.com/dog-java.

```python
import math
class Planet:
  def __init__(self, x, y, mass):
    self.x = x
    self.y = y
    self.mass = mass
  def distance_to(self, other):
    return math.sqrt(
      (other.x - self.x)**2 +
      (other.y - self.y)**2)
  @staticmethod
  def total_mass(planets):
    total = 0
    for p in planets:
      total += p.mass
    return total
p1 = Planet(5, 10, 100)
p2 = Planet(1, 2, 200)
p1.distance_to(p2)
Planet.total_mass([p1, p2])
```
```java
public class Planet {
    double x, y, mass;
    public Planet(double x, double y, double mass) {
        this.x = x;
        this.y = y;
        this.mass = mass;
    }
    
    public double distance_to(Planet p1, Planet p2) {
        xDiff = Math.abs(p1.x - p2.x);
        yDiff = Math.abs(p1.y - p2.y);
        
        double distance;
        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        return distance;
    }
    
    public static double total_mass(Planet[] ps) {
        double totalMass = 0.0;
        for (int i = 0; i < ps.length; i += 1) {
            totalMass += ps[i];
        }
        return totalMass;
    }
    
    public static void main(String[] args) {
        p1 = Planet(5, 10, 100);
        p2 = Planet(1, 2, 200);
        p1.distance_to(p2);
        Planet.total_mass({p1, p2});
    }
}
```

## 2. Helpful LLM Use

On HW2, you had a chance to play around with large language models for solving the starTriangle problem.

You’ve probably also used LLMs in prior programming classes.

What are some ways that you’ve used LLMs to help your learning?
What are some ways that you’ve used LLMs that actually hindered your learning? 
Did you find it helpful to see what an LLM came up with for
starTriangle?

## 3. Lists Exercises

(a) The code below shows the equivalent Java code for common List operations in Python.

```python
# 每段一个操作，与下面的Java代码一一对应。
lst = []

lst.append("zero")
lst.append("one")

lst[0] = "zed"

print(lst[0])

print(len(lst))

if "one" in lst:
    print("one in lst")

for elem in lst:
    print(elem)
```
```java
List<String> lst = new ArrayList<>();

lst.add("zero");
lst.add("one");

lst.set(0, "zed");

System.out.println( lst.get(0) );
System.out.println( lst.size() );

if (lst.contain("one")) {
    System.out.println("One in lst.");
}

for (String elem : lst) {
    System.out.println(elem);
}
```
