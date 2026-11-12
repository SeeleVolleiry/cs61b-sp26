package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import static tileengine.Tileset.*;
import utils.FileUtils;

import java.awt.*;
import java.awt.Point;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Game {
    private final int windowWidth;
    private final int windowHeight;
    private final int HUDHeight = 5;
    private long seed;
    private TERenderer ter;
    private Menu menu;
    private World world;
    private HUD hud;
    StringBuilder actionHistory;

    public Game(int w, int h) {
        windowWidth = w;
        windowHeight = h;

        ter = new TERenderer();
        ter.initialize(windowWidth, windowHeight);

        menu = new Menu();
        hud = new HUD();
        actionHistory = new StringBuilder();
    }

    private class HUD {
        String left;
        String middle;
        String right;

        public HUD() {
            left = "World Gaming";
            middle = "Tile pointed: ";
            right = "Player: avatar at ";
        }

        private void draw() {
            int mx = (int) StdDraw.mouseX();
            int my = (int) StdDraw.mouseY();
            String tileDesc;
            if (mx >= 0 && mx < 80 && my >= 0 && my < 55) {
                tileDesc = world.getTiles()[mx][my].description();
            } else {
                tileDesc = "---";
            }
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 28));
            StdDraw.textLeft(1, 57, left);
            StdDraw.text(40, 57, middle + tileDesc);
            StdDraw.textRight(79, 57, right + "(" + world.avatar.x + " ," + world.avatar.y + ")");
        }
    }

    public void gameRun() {
        String state = "MainMenu";
        while (!state.equals("Quit")) {
            switch (state) {
                case "MainMenu": state = menuLoop(); break;
                case "CreateGame": state = createGame(); break;
                case "LoadGame": state = loadGame(); break;
                case "Game": state = gameLoop(); break;
                default: break;
            }
        }
        System.exit(0); // 退出
    }

    public void gameRender() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        ter.resetFont();
        this.ter.drawTiles(world.getTiles());
        this.world.avatar.draw();
        this.hud.draw();
        StdDraw.show();
    }

    public String menuLoop() {
        char userInput;
        this.menu.mainMenuRender();
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                userInput = StdDraw.nextKeyTyped();
                userInput = Character.toLowerCase(userInput);

                switch(userInput) {
                    case 'n':
                        return "CreateGame";
                    case 'l': // load game
                        return "LoadGame";
                    case 'q': // quit game
                        return "Quit";
                    default:
                        break;
                }
            }
            StdDraw.pause(20);
        }
    }

    public String createGame() {
        this.menu.seedBuffer.setLength(0); // 清空
        actionHistory.setLength(0);
        char userInput;
        this.menu.newGameRender();
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                userInput = StdDraw.nextKeyTyped();
                userInput = Character.toLowerCase(userInput);
                if (Character.isDigit(userInput)) {
                    this.menu.seedBuffer.append(userInput);
                    this.menu.newGameRender();
                }
                if ( userInput == 's') {
                    actionHistory.append(this.menu.seedBuffer.toString());
                    actionHistory.append("\t");
                    seed = Long.parseLong(this.menu.seedBuffer.toString());
                    this.world = new World(windowWidth, windowHeight - HUDHeight, seed);
                    return "Game"; // 切换游戏状态
                }
            }
            StdDraw.pause(20);
        }
    }

    public String gameLoop() {
        gameRender();
        return gameInteract();
    }

    public String loadGame() {
        String filename = "savedGameData.txt";
        FileUtils fileIO = new FileUtils();
        if (!fileIO.fileExists(filename)) {
            return "MainMenu";
        }
        String fileString = fileIO.readFile(filename);
        String[] history = fileString.split("\t", -1);
        this.seed = Long.parseLong(history[0]);
        this.world = new World(windowWidth, windowHeight - HUDHeight, this.seed);
        actionHistory.setLength(0);
        actionHistory.append(history[0]).append("\t");
        for (int i = 0; i < history[1].length(); i++) {
            char move = history[1].charAt(i);
            switch(move) {
                case 'w':
                    this.world.avatar.move(0, 1);
                    actionHistory.append('w');
                    break;
                case 'a':
                    this.world.avatar.move(-1, 0);
                    actionHistory.append('a');
                    break;
                case 's':
                    this.world.avatar.move(0, -1);
                    actionHistory.append('s');
                    break;
                case 'd':
                    this.world.avatar.move(1, 0);
                    actionHistory.append('d');
                    break;
            }
        }
        gameRender();
        return "Game";
    }

    public void saveGame() {
        String filename = "savedGameData.txt";
        FileUtils fileIO = new FileUtils();
        fileIO.writeFile(filename, this.actionHistory.toString());

    }

    public String gameInteract() {
        char operation;
        boolean waitingQ = false;
        boolean pressedNow = false;
        int lastX = Integer.MIN_VALUE; int lastY = Integer.MIN_VALUE;
        while (true) {
            int currX = (int) StdDraw.mouseX();
            int currY = (int) StdDraw.mouseY();
            pressedNow = StdDraw.isMousePressed();
            if (currX != lastX || currY != lastY) {
                gameRender();
                lastX = currX; lastY = currY;
            }

            if (pressedNow) {
                pathMoveDraw();
                pressedNow = false;
            }

            while (StdDraw.hasNextKeyTyped()) {
                operation = StdDraw.nextKeyTyped();
                operation = Character.toLowerCase(operation);
                if (waitingQ && operation == 'q') {
                    waitingQ = false;
                    actionHistory.append(":Q");
                    saveGame();
                    return "Quit"; // 存档后退出游戏
                }
                waitingQ = false;
                switch(operation) {
                    case 'w':
                        this.world.avatar.move(0, 1);
                        this.actionHistory.append('w');
                        break;
                    case 'a':
                        this.world.avatar.move(-1, 0);
                        this.actionHistory.append('a');
                        break;
                    case 's':
                        this.world.avatar.move(0, -1);
                        this.actionHistory.append('s');
                        break;
                    case 'd':
                        this.world.avatar.move(1, 0);
                        this.actionHistory.append('d');
                        break;
                    case ':':
                        waitingQ = true;
                        break;
                    default:
                        break;
                }
                gameRender();
            }
            StdDraw.pause(20);
        }
    }

    public void pathMoveDraw() {
        // BFS algorithm
        Deque<Point> deq = new ArrayDeque<>();
        Point[][] prev = new Point[windowWidth][windowHeight];
        boolean[][] visited = new boolean[windowWidth][windowHeight];

        // 起点start的坐标
        int sx = world.avatar.x; int sy = world.avatar.y;
        int dx = (int) StdDraw.mouseX(); int dy = (int) StdDraw.mouseY();
        Point start = new Point(sx, sy);
        Point destination = new Point(dx, dy);
        visited[sx][sy] = true;
        deq.add(new Point(sx, sy));

        while (!deq.isEmpty()) {
            Point curr = deq.poll();
            if (curr.x != destination.x && curr.y != destination.y) {
                break;
            }
            for (Point neighbor: pointNeighbors(curr)) {
                if (visited[neighbor.x][neighbor.y] || !isReachable(curr.x, curr.y)) {
                    continue;
                }
                visited[neighbor.x][neighbor.y] = true;
                prev[curr.x][curr.y] = neighbor;
                deq.add(neighbor);
            }
        }
        // prev数组中从destination索引可以回到start
        List path = new ArrayList<>();
        Point curr = destination;
        while (curr.x != start.x && curr.y != start.y) {
            path.add(curr);
            curr = prev[curr.x][curr.y];
        }
        Collections.reverse(path);

        //有了路径开始绘制
        for (Point p: path) {
            // move, update actionHistory
            int xDiff = p.x - world.avatar.x;
            int yDiff = p.y - world.avatar.y;
            world.avatar.move(xDiff, yDiff);
            if (xDiff == 1) {
                actionHistory.append('d');
            }
            if (yDiff == 1) {
                actionHistory.append('w');
            }
            if (xDiff == -1) {
                actionHistory.append('a');
            }
            if (yDiff == -1) {
                actionHistory.append('s');
            }
            gameRender();
            StdDraw.pause(30);
        }
    }

    private List<Point> pointNeighbors(Point p) {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(p.x - 1, p.y));
        neighbors.add(new Point(p.x + 1, p.y));
        neighbors.add(new Point(p.x, p.y - 1));
        neighbors.add(new Point(p.x, p.y + 1));
        return neighbors;
    }

    private boolean isReachable(int x, int y) {
        if (world.getTiles()[x][y] != FLOOR) {
            return false;
        }
        if (x < 0 || x > windowWidth - 1 || y < 0 || y > windowHeight - 1) {
            return false;
        }
        return true;
    }
}
