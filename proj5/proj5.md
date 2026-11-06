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

The goal is to implement world generation for generating a world which satisfies the properties of valid and pseudorandom.

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

### TETile Array

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
- Tileset class description:
  - Contains constant tile objects, to avoid having to remake the same tiles in different parts of the code.
- pre-define TETile objects:
  - `AVATAR`: TETile('@', Color.white, Color.black, "you", 0);
  - `WALL`: TETile('#', new Color(216, 128, 128), Color.darkGray,
    "wall", 1);
  - `FLOOR`: TETile('·', new Color(128, 192, 128), Color.black, "floor", 2);
  - `NOTHING`: TETile(' ', Color.black, Color.black, "nothing", 3); 
  - `GRASS`: TETile('"', Color.green, Color.black, "grass", 4); 
  - `WATER`: TETile('≈', Color.blue, Color.black, "water", 5); 
  - `FLOWER`: TETile('❀', Color.magenta, Color.pink, "flower", 6);
  - `LOCKED_DOOR`: TETile('█', Color.orange, Color.black, "locked door", 7); 
  - `UNLOCKED_DOOR`: TETile('▢', Color.orange, Color.black,
    "unlocked door", 8);
  - `SAND`: TETile('▒', Color.yellow, Color.black, "sand", 9);
  - `MOUNTAIN`: TETile('▲', Color.gray, Color.black, "mountain", 10);
  - `TREE`: TETile('♠', Color.green, Color.black, "tree", 11);
  - `CELL`: TETile('█', Color.white, Color.black, "cell", 12);

`TERender.java`:
- class description:  Utility class for rendering tiles.
  -  instance attributes: TILE_SIZE, weight, height, xOffset, yOffset
- methods description:
  - initialize(): 
    - initialize(int w, int h, int xOff, int yOff)
    - initialize(int w, int h): initialize(w, h, 0, 0)
  - renderFrame(TETile[][] world): Takes in a 2d array of TETile objects and renders the 2d array to the screen, starting from xOffset and yOffset.
  - drawTiles(TETile[][] world): Draws all world tiles without clearing the canvas or showing the tiles.
  - resetFont(): Resets the font to default settings. You should call this method before drawing any tiles if you changed the pen settings.

Important note: world[0][0] corresponds to the bottom-left tile of the world.

### Tile Rendering

Open up the skeleton and check out the `BoringWorldDemo` file in the `demo` folder. Try running it. This code does 3 things:
- Initializing the tile rendering engine.
- Generating a two-dimensional TETile[][] array.
- Using the tile rendering engine to display the TETile[][] array.

### Randomness and Pseudo-Randomness

To generate random numbers in Java, we can use create a `Random` object, which represents a random number generator:
```
Random r = new Random(); // Totally random. Don't do this in Project 5.
System.out.println(r.nextInt());
System.out.println(r.nextInt());
System.out.println(r.nextInt());
```
Every time you run this program, you’ll get 3 totally random integers.

What if we want to control the output of the random number generator (e.g. for debugging purposes)? In that case, we can provide a `seed` to the constructor.

r is now a pseudorandom number generator, because it generates a deterministic sequence of random-looking numbers based on the seed you provide. If you provide the same seed, you will get the same sequence of values. For example, every time you run this program, you will get the same 3 integers.
```
Random r = new Random(82731);
System.out.println(r.nextInt());
System.out.println(r.nextInt());
r = new Random(82731);
System.out.println(r.nextInt());
```

The RandomUtils class provides some useful methods for getting other types of randomness out of a Random object, examples include:
- `int uniform(Random random, int a, int b)`: Returns an integer from the range [a, b)
- `T randomChoice(Random random, List<T> items)`: Returns a random item from items.
- `void shuffle(Random random, Object[] a)`: Shuffles the given array.
- `double gaussian(Random random, double mu, double sigma)`: Gives a sample from a Gaussian distribution.

Open up the skeleton and check out the `RandomWorldDemo.java` file in the `demo` folder. Try running it.

This world is sheer chaos – walls and flowers everywhere! If you look at the RandomWorldDemo.java file, you’ll see that we’re doing a few new things:
- We create and use an object of type `Random` that is a “pseudorandom number generator”.
- We use a new type of conditional called a `switch` statement.
- We have delegated work to functions instead of doing everything in main.

### World Requirements

**Your goal in Project 5B is to write a world generator that can create pseudorandom worlds with hallways and floors**.

`Valid`:
1. The world must include distinct rooms and hallways. For any floor square, it should be obvious whether that square belongs to a room or a hallway.
2. At least some rooms should be rectangular. You can optionally support rooms of other shapes as well.
3. Hallways should be width-1 or width-2. However, hallways must be visually distinct from rooms. The vast majority of students use width-1 hallways.
4. Your world generator must be capable of generating hallways that include turns (or equivalently, straight hallways that intersect). Random worlds should generate a turning hallway with high frequency, in that a majority of worlds should have a turning hallway.
5. The world should not have any dead-end hallways. Hallways should always lead into rooms.
6. Walls, floors, and empty spaces should all be visually distinct. The easiest way to do this is to use three different tile types: one for walls, one for floors, and one for empty spaces.
7. Walls in the corners of rooms and hallways are optional.
8. All floor tiles (rooms and hallways) should be reachable from each other.
9. All the space “inside” the walls should be floor tiles, and all the space “outside” the walls should be empty space tiles.
10. Rooms cannot clip off the edge of the world. In other words, there should be no floor tiles on the edge of the world.
11. The world must not have excess unused space. There’s no strict requirement, but try to consistently fill at least 50% of the world with rooms and hallways.

`Pseudorandom`—— Requirements for a pseudorandom world:
1. The world must be pseudorandom.
    - If the user chooses the same seed twice, the resulting world must be identical. 
    - If the user chooses two different seeds, the resulting worlds should be different.
2. Different worlds should different numbers of rooms and hallways.
3. Different worlds should have rooms and hallways in different locations.
4. Different rooms should have different widths and heights. 
5. Different hallways should have different lengths. 
6. The world should be substantially different each time, i.e. you should not have the same basic layout with easily predictable features. 
7. You can pick any window size you want, as long as the window size is reasonable (e.g. not something tiny like 3×3), and the entire window is visible on your computer. For example, a fixed, non-random window size is fine.

### Task: World Generation

TASK: Implement code that takes in a seed, generates a pseudo-random 2D world array, and displays that world back to the user.

Your seeds are(with 1270):
6578897764558030256
1013758890894698811
7162790311120124118
7003957710856902839
6206686636164176845


# Project 5C: Interactivity

## Task 1: Main Menu

修改程序启动界面——先出菜单，不再是5B中先渲染出世界。界面有三个选项（大小写不敏感）：
- N/n → 新游戏，进入输 seed 界面：每敲一个数字立即上屏，敲 S/s 确认，按该 seed 生成世界并开始
- L/l → 读档（读 save.txt 最近一次存档）
- Q/q → System.exit(0) 直接关窗口（菜单里单按 Q 就退，不存档）
- 明确不测的行为（代码怎么处理都行）：seed 超出 long 范围 [0, 9223372036854775807]；seed 界面按退格/逗号/字母等非法键。

## Task 2: Interactivity

When the user starts the game, the world you generated should additionally include a single `avatar tile` representing the user. In the example below, the @ tile is the avatar. Your avatar can look different, as long as there is a clear single avatar.

- 世界里加一个明确的 avatar tile（长什么样随意），出生点随意但必须确定——同 seed 两次出生在同一格（如固定取“最左下角的地板”）
- W/A/S/D（含小写）移动一格；撞墙不动、程序不能崩
- 不需要支持长按连走
- 核心架构是游戏循环：初始化 → 读输入更新状态 → 重渲染 → 循环。官方指路看骨架里的 GameLoopDemo.java
- 确定性硬要求：同样的按键序列 → 游戏状态完全一样。计时/刷怪这类机制要用“步数”衡量（每 5 步刷一个怪），不能用真实秒数——除非慢到别人能稳定复现。

`Animating the Game`: To animate your game, you need to continuously re-render updates to your game state. These updates will vary depending on the specific scene you are in. You can conceptualize this process as a “`game loop`”. Your world movement scene might be characterized by the following game loop:
- Initialize the game window and the world state.
- Update the movements of the avatar based on the player’s input.
- Render the updated game state in the game window.
- Repeat steps 2 and 3 until the player quits.

Check out `GameLoopDemo.java`, located in the `demo` folder of your Project 5 repo, for an example of what this might look like.

In general, good coding practice is to first build small procedures with explicit purposes and then compose more complex methods using the basic ones. It will give you a clear path forward in development and will be easier to break down your logic for unit testing.

`Non real-time gameplay`: The easiest way to produce deterministic behavior is for your game to not depend on real-time. Some examples of non-real-time behavior:
- If the user presses the same sequence of keys very quickly, or very slowly, the resulting behavior should be exactly the same.
- If you have features like a countdown timer, the timer should be measured by number of moves (e.g. decrement each time the user moves), as opposed to real-time like number of seconds.
- If you have features like enemy character spawning, you can spawn an enemy every 5 moves, as opposed to something real-time like every 5 seconds.

If you do intend to implement real-time behavior, that’s fine, as long as anybody can easily produce deterministic behavior by pressing the same keys in the same sequence.

For example, asking the user to press a key within a 10-second timer is fine and reproducible, but asking the user to press 20 keys within a 0.1-second timer is too hard to reproduce reliably.

## Task 3: HUD

Add a “Heads up Display” (HUD) that provides additional information that maybe useful to the user.

最低要求：屏幕上方留一条空带，用 StdDraw.textLeft(x, y, text) 实时显示鼠标当前指向的 tile 名字（如 "floor"、"goblin"）。

三个硬性要求：
- 鼠标移动时 HUD 必须更新（不能只在按键时刷新）
- 不能闪烁
- 想加分就自己加：血条、提示文字等

As a hint on how you can do this, we’d recommend two things:
- Modify your code so that there is some empty space at the top for you to put your HUD in.
- Look into the `StdDraw.textLeft(x, y, text)` function, which draws the given text at the given x and y position. Analogously, there are also the textRight and text functions with the same inputs but will make it so that the right and center of text is at the given x and y positions.

## Task 4: Saving and Loading

Implement the Saving and Loading feature that follows the requirements detailed below.

World 模式下按 :q 或 :Q → 立即存档并退出；单独的 q/Q/: 都不能触发退出
存进 save.txt（格式自定），内容要足以完美重建当时的游戏状态。

官方验收例子：
场景一：N999SDDDWWW（新游戏，seed 999，开始，右×3，上×3）
场景二：N999SDDD:Q（同上但只右×3，存档退出）→ 重开程序 → LWWW（读档，上×3）
两个场景最终世界必须完全相同——包括随机数生成器的内部状态

Go through the `ExampleSaveLoad.java` file in your demo folder and make sure you understand it.

## Task 5: Pathfinding

Now, we are going to add the ability for movement by clicking on a tile, rather than just using keyboard (WASD) movements.

鼠标点击某个 tile → 画出一条到该点的路径：只能走地板等可通行 tile，且必须是最短路（多条最短路任选一条；最短路算法 20 章的 BFS 就够——无权图最短路）

再点一次同一点 → avatar 沿这条路径动画走过去

## Task 6: Ambition Features

至少实现 1 个 primary + 1 个 secondary 野心特性（官方预批清单在 sp26.datastructur.es/proj5/ambition），或自己提（要在 Ed 上提前报备）。

存档必须覆盖野心特性的状态——读档后特性状态也要还原。

自选特性的一个硬规矩：AI 生成的非平凡代码必须注释标注来源，比如 // This method was written by Gemini 2.5.

`Primary Features`:
- `Line of Sight`: Create a system so that the tile renderer only displays tiles on the screen that are within the line of sight of the avatar. The line of sight must be able to be toggled on and off with a keypress. Note the line of sight should NOT go through walls although it is fine to be able to wrap around walls/corners. This also means that the line of sight must be long enough such that we can tell if it goes through a wall or not.
- `Light Sources`: Add the ability for light sources to affect how the world is rendered, with at least one light source that can be turned on and off with a keypress. The intensity of the light must diminish in a gradient as the distance from the source increases. Light sources should fill a majority of the room it belongs to, and light should not pass through walls. 
- `Encounters`: Create a system for “encounters”, where a new interface appears when the avatar interacts with entities in the world, returning the avatar to the original interface when the encounter ends (e.g. Pokémon). Note that the world must be in the same state it was before the encounter, after the encounter ends. It’s okay if save/load is not available during encounters. 
- `Undo, Redo and Restart`: Add the ability to undo moves, redo moves, and reset your world using three different keypresses. The redo key should do nothing if the most recent action was not an undo.
- `Perspective`: Add the ability for the user to change the perspective of their view (first-person, isometric 2.5D, 3D, etc.) (We’ve never seen anyone do isometric 2.5D or full 3D before! The Nintendo 64 game ‘Kirby 64 - The Crystal Shards’ is an example of what an isometric 2.5D world looks like). One particularly interesting example is Dorottya Urmossy and David Yang’s Fall 2022 submission, which is a 2.5D first-person view, i.e. the world is 3D but the entities are 2D.
- `Battle System`: Implement a battle system that allows players to interact with moving enemies and obstacles. Assign the player a health value, place collectibles that restore health around the map, and create randomly-moving enemies/objects that inflict damage on and receive damage from the player.
- `Explorer`: Make your world larger than the preset display size such that the player can “explore” beyond the starting world. The rendered world should move with the player beyond a certain point and be scrollable.
- `Wrap`: Make your world “wrap” around the border of the rendered world, where the player should be able to enter from one side of the screen and exit from the opposite side interchangeably. In order to wrap, you must have rooms on the edge of the world that clip off such that the opposite side also has a room that is clipped off, allowing the player to continue traveling on the floor tiles. The world should still follow the World Requirements from Part B excluding requirement #10.
- `Online Multiplayer`: Add online multiplayer where other users can join and leave the current game. java.net.Socket might be useful here. All players must have the ability to do interactivity (moving, save/load, etc)
- `NPCs`: Add NPC interaction and dialogue with the player. Allow the player to choose different response options depending on the NPC and/or game state. Let us know if you do any cool realtime API calls to an LLM service.
- `Levels`: Implement elevators/navigable floors with a toggleable multi-floor view or with a significantly different interface with the ability to go back to the main world.
`Altered Terrain`: Implement the ability for the avatar to significantly alter the landscape of the world, e.g. digging, triggering traps that cause parts of the world to become dangerous or impassible, solving puzzles that make some rooms accessible only after fulfilling some objective.

`Secondary Features`:
- `Replay`: Add the ability for the user to “replay” their most recent save, visually displaying all the actions taken since the last time a new world was created. This must result in the same final state as would occur if the user had loaded the most recent save. This means that the game should be playable once the replay is complete. 
- `Save Slots`: Add multiple save slots that can be accessed with a new menu option, and a new keyboard shortcut to save to a slot other than slot 1. You should be careful to still support the default behavior of saving and loading in order to be consistent with the replay string requirements. 
- `New World`: Add the ability to create a new world without closing and reopening the project, either as a special option you can press while exploring, or when you reach a “game over” state if you’ve turned your world into a game. 
- `Custom Avatar`: Make your engine render images instead of unicode characters, and add a menu option to change your avatar’s appearance to a custom image created by you. 
- `Theme`: Make your engine render images instead of unicode characters, and add a menu option to select the environment/theme of the world will be.
- `Language`: Add a menu option to change all text in the interface to a different language. English should be the default and there should be a way to switch it back to English.
- `Audio`: Add cool music to both your menu and exploration interface, as well as sound effects for interactions the avatar makes with the world.
- `Minimap`: Add a minimap somewhere which shows the entire map and the current avatar location. This feature is a lot more interesting if you also implement a map which is larger than the screen so that you are unable to see the entire map normally.
- `Rotate`: Add ability to rotate the world, i.e. turn the board 90 degrees and adjust movement keys accordingly.
- `Local Multiplayer`: Add support for 2 users to interact at the same time. This will require that you have two avatars on screen which can move around, and they should have separate control schemes.
- `Moveable Blocks`: Add ability for the player to place and remove blocks based on a sequence of keypresses.
- `Coins`: Add the ability for the user to collect coins. Once all coins are collected, the user should be returned to the main menu, where they are given the option to either start a new game, load a previous world, or quit.
