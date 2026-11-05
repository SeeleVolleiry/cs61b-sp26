# Project 5: BYOW

BYOW: Build Your Own World

# Project Roadmap

1. Software engineering Design Doc (Project 5A)
2. Implement code to generate random worlds (Project 5B).
3. Add a main menu for the user to start the game from (Project 5C).
4. Add support for saving a game and loading it back (Project 5C).
5. Add several game mechanics/features to your project (Project 5C).

# Project 5A: Design

## Overview

In this project, you will create an engine for generating explorable worlds. This is a large design project requiring to work through every stage of development from ideation to presentation.

This project is about software engineering, and will achieve several goals that come with large design projects
- Emulating a real product development cycle. This comes from handling a larger pieces of code with little starter code
- Recognize and mitigate code complexity. Inevitably, whenever a team builds a large software system, they’ll make some bad design decisions and cut corners, resulting in “technical debt”. Your code becomes complicated to understand and modify, which in the real world translates to software that becomes stale and expensive to maintain. It is very likely that your code will feel sort of hacked together by the end of this project. As you grow as a programmer over the course of your career, arguably the main skill you’ll be building is to avoid such hacks. However, the only way to know and defeat this is to suffer from it.
- Exploring and experimenting with resources. Know that there are no right and wrong answers, as this is a very open-ended project. In fact, it is not required to use any of the data structures from class, but it will likely make your solution significantly simpler and more efficient. You will likely go through several iterations before settling on your final implementation

Your task is to design and implement a 2D tile-based world exploration engine. The system you build can either use graphical tiles, or it can use text based tiles.

There will be a tile renderer and a small set of starter tiles for you to use. This will be the same tile renderer you used in Project 0 and 3.

## Task 0: Partnership Formation

伯克利选课学生需要做的组队事项，其余人并无必要。

## Task 1: Setup

接收邀请，获得骨架代码。对于校外人员，去找fa25 project5的骨架代码以继续接下来的任务（或者让AI直接给你搜寻、下载吧）。

## Task 2: Writing Your TDD

TDD: Technical Design Document

Before getting started, please read the Project 5B: World Generation and Project 5C: Gameplay specs. You won’t be able to begin either task without understanding the BYOW spec first!

**Design Tips**: We’ve included below a few questions for you to consider while brainstorming your design:

`General Questions`
- Look through the provided code:
  - Notice what’s already built for you. What may be useful for your implementation?
  - Notice what isn’t. What gaps does your implementation need to fill?
- Where do tasks overlap? Are there opportunities to reuse methods across different cases? Think about opportunities for helper methods.
- Can you find groups of data structures and methods that serve a similar, clearly defined purpose? If so, consider moving them to a separate class. Building abstraction barriers may be helpful for managing code complexity.

`World Generation`
- What are the core components of each world? Does it make sense to have a separate class for each component?
- How will you ensure that every generated world is valid?
  - Will you decide on a layout first, validate it, then build the world after?
  - Or, will you gradually generate the world, ensuring that only valid components are built as you go?
- Ultimately, each world is a 2D grid of values, each representing a Tile. What data structures would be helpful for tracking the state of the world on top of this grid? Representing specific aspects of your world with data structures may be helpful for building ambition features later.

`Interactivity`
- Read through the StdDraw library documentation. This library acts as the bridge between your implementation and what is outputted on screen (e.g. the /tileengine implementation uses library methods to draw to the screen).
  - Can you find methods for drawing on the screen, not specifically for tile drawing?
  - Can you find methods for soliticiting user inputs?
- Rendering video is much like a classic film projector, in that by rapidly rendering images, we can create the illusion of motion. Video games are essentially the same, except that user inputs can alter what’s displayed in the next frame. (Read about common paradigms for implementing video games and graphics engines here)
  - Does your implementation clearly separate the steps of collecting, processing, and displaying the result of user inputs?
- Consider that your computer can compute thousands of instructions each second. If your implementation has real-time elements (i.e. timers, slow-motion movement, NPCs moving at user-like speed, etc.), how can you adjust the speed of your event loop to match real-time?

`Ambition Features`
- Most importantly, which ambition features are you most excited about building?
- For each feature, determine where it fits within your current abstraction barriers:
  - Does it require fundamental changes to the process of generating and representing worlds?
  - Does it alter the process for collecting user input?
  - Does it change the process for rendering the world state?
- Do any features span multiple domains of this project? If so, consider creating separate classes to contain their complexity.
- How could you structure your core project implementation (world generation and interactivity) to make feature integration seamless?

# Project 5B: World Generation

## Code Overview

In the `TileEngine` package, you’ll find the following useful files:
- `TERenderer.java` contains rendering-related methods.
- `TETile.java` represents a tile.
- `Tileset.java` has some pre-made tile designs that you can use.

In the Utils package, you’ll find the following useful file:
- `RandomUtils.java` has methods for generating randomness for your world.

In the `Core` package, you’ll write code for your game. We recommend putting all your code in this package (though this is not required).
- `Main.java` is how your user starts the entire system.
- `World.java` represents your world!
- You can add other classes of your own, too!

This project makes heavy use of `StdDraw`, which is a package that has basic graphics rendering capabilities. Additionally, it supports user interaction with keyboard and mouse clicks. You will likely need to consult the API specification for StdDraw at some points in the project.

Your project should only use standard Java libraries or any libraries we provided with your repo and library-sp26.

## Getting Started

`TETile Array`

At a high level, *your goal is to create a 2D array of TETile objects, and fill in the array with different tiles, to create a nice-looking world*. Then, you can call one of our library functions to display this world to the user.

Go through the files in the tileengine folder: `TETile.java`, `Tileset.java`, and `TERenderer.java`. Overall, you do not need to understand these implementations deeply, but they provide useful context.

`TETile.java`:
- class description:
  * The TETile object is used to represent `a single tile` in your world. 
  * A 2D array of tiles make up a board, and can be drawn to the screen using the TERenderer class.
  * All TETile objects must have a `character`, `textcolor`, and `background color` to be used to represent the tile when drawn to the screen. You can also optionally provide a path to an image file of an appropriate size (16x16) to be drawn in place of the unicode representation. If the image path provided cannot be found, draw will fallback to using the provided character and color representation, so you are free to use image tiles on your own computer.
  * The provided TETile is `immutable`, i.e. none of its instance variables can change. 
  * You are welcome to make your TETile class mutable, if you prefer.
- methods description:
  - constructor: 
    - TETile(char character, Color textColor, Color backgroundColor, String description, String filepath, int id)
    - TETile(char character, Color textColor, Color backgroundColor, String description, int id)
    - TETile(TETile t, Color textColor): creates a copy of TETile t, except with given color.
    - TETile(TETile t, char c): creates a copy of TETile t, except with given character.
  - draw(double x, double y): Draws the tile to the screen at location (x, y).
  - Get the properties of instance variable: character(), descroption() ,id().
  - TETile colorVariant(TETile t, int dr, int dg, int db, Random r): Creates a copy of the given tile with a slightly different text color.
  - String toString(TETile[][] world): Converts the given 2D array to a String.
  - TETile[][] copyOf(TETile[][] tiles): makes a copy of the given 2D tile array.
  - equals(Object o):  Checks if two tiles are equal by comparing their IDs.

`Tileset.java`:


Important note: world[0][0] corresponds to the bottom-left tile of the world.






# Project 5C: Interactivity
