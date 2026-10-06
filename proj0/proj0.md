# Project 0: Particle Simulator (standard mode)

在开始project 0前应该完成HW1、HW2和Lecture1~3。

This is the standard mode of project 0. In this version of project 0, we’ll provide a lot of the structure of the code for you, and you’ll fill in the details.

In this mini-project, you’ll get some practice with Java by creating a physics sandbox. We’ll provide a chunk of starter code (much of which uses syntax you’ve never seen before), and you’ll fill in the most interesting pieces.

For this project, you are welcome to look at other files, but you will only need to modify `Particle.java` and `ParticleSimulator.java`. 

The two test files hold tests for each of the tasks below so make sure you run every test and check whether you have completed it correctly.

## Task 0: Starting Particle Simulator

写代码之前检查环境是否符合要求。

同时按照之前的文件提到的每次做作业时的流程来一步步行进。（尤其是设置项目结构）

## Task 1: Particle Color

Modify the `public Color color()` method in `Particle.java` so that it behaves as shown below:
- If the flavor is EMPTY, then return Color.BLACK.
- If the flavor is SAND, then return Color.YELLOW.
- If the flavor is BARRIER, then return Color.GRAY.
- If the flavor is WATER, then return Color.BLUE.
- If the flavor is FOUNTAIN, then return Color.CYAN.
- If the flavor is PLANT, then return new Color(0, 255, 0). This is a color that has 0 red, 255 green, and 0 blue as its three components.
- If the flavor is FIRE, then return a Color which has 255 red, 0 green, 0 blue.
- If the flavor is FLOWER, then return a Color which has 255 red, 141 green, 161 blue.

## Task 2: Visually Testing the Color Function

测试任务，观察Task 1写对了没有。

We can try testing the color function using the Particle Simulator class!

## Task 3: Testing the Color Function Automatically

运行指定的测试函数，来自动测试Task 1写对了没有。

Throughout this course, we’ll be writing and running tests using a library called Google Truth, covered in lecture 4.

Open the TestParticle.java file and look at the method called testColor. Here, you can see the code we’ve set up to check your color method. This is also the exact code that we have running in our gradescope autograder.

## Task 4: MoveInto

We will want our particles to be able to move around. To allow our particles to move, we’ll implement a moveInto function that transfers the color and lifespan of a particle into a different particle.

Fill in the `moveInto` function in `Particle.java`. The behavior of this function is that after running it:
- `other.flavor` should be equal to the current particle’s flavor
- `other.lifespan` should be equal to the current particle’s lifespan
- The current particle’s flavor should be set to EMPTY (because it moved, leaving emptiness behind)
- The current particle’s lifespan should be set to -1 (because it moved, leaving emptiness behind)

别忘记运行对应的自动测试函数来验证正确性。

下面给出了对应的Python代码以供参考/提示。
```python
def move_into(self, other: 'Particle'):    
    other.flavor = self.flavor
    other.lifespan = self.lifespan
        
    self.flavor = ParticleFlavor.EMPTY
    self.lifespan = -1
```

## Task 5: Fall

Fill in the `public void fall(Map<Direction, Particle> neighbors)` method in `Particle.java`.

The method is given a Map that goes from each of the four possible directions `Direction.DOWN`, `Direction.LEFT`, `Direction.RIGHT`, `Direction.UP` to a Particle.

The fall method should:
- Check the neighbor in the down direction, e.g. neighbors.get(Direction.DOWN).
- If that neighbor has a flavor equal to ParticleFlavor.EMPTY, then the current particle should moveInto that particle. You’ll want to use your moveInto function from earlier.

To test your fall function call the testFall method in TestParticle.java. Run the test and verify your fall method works correctly.

## Task 6:



## Task 7:



## Task 8:



## Task 9:


## Task 10:



## Task 11:



## Task 12:



## Task 13:
