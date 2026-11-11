package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import utils.FileUtils;

import java.io.File;

import static tileengine.Tileset.FLOOR;

public class Game {
    private final int windowWidth;
    private final int windowHeight;
    private final int HUDHeight = 5;
    private long seed;
    private TERenderer ter;
    private Menu menu;
    private World world;
    StringBuilder actionHistory;

    public Game(int w, int h) {
        windowWidth = w;
        windowHeight = h;

        ter = new TERenderer();
        ter.initialize(windowWidth, windowHeight);

        menu = new Menu();
        actionHistory = new StringBuilder();
    }

    public void gameRun() {
        String state = "MainMenu";
        while (!state.equals("Quit")) {
            switch (state) {
                case "MainMenu": state = menuLoop(); break;
                case "CreateGame": state = createGame(); break;
                case "Game": state = gameLoop(); break;
                default: break;
            }
        }
        System.exit(0);
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
                        return createGame();
                    case 'l': // load game
                        return loadGame();
                    case 'q': // quit game
                        System.exit(0);
                        return "Quit";
                    default:
                        break;
                }
            }
        }
    }

    public String createGame() {
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
        }
    }

    public String gameLoop() {
        this.ter.renderFrame(this.world.getTiles());
        this.world.avatar.draw();
        char operation;
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                operation = StdDraw.nextKeyTyped();
                operation = Character.toLowerCase(operation);
                switch(operation) {
                    case 'w':
                        if (world.avatar.y + 1 < world.getTiles()[0].length-1) {
                            if (world.getTiles()[world.avatar.x][world.avatar.y + 1] == FLOOR) {
                                world.avatar.y += 1;
                                this.actionHistory.append('w');
                                this.ter.renderFrame(this.world.getTiles());
                                this.world.avatar.draw();
                            }
                        }
                        break;
                    case 'a':
                        if (world.avatar.x - 1 >= 0) {
                            if (world.getTiles()[world.avatar.x - 1][world.avatar.y] == FLOOR) {
                                world.avatar.x -= 1;
                                this.actionHistory.append('a');
                                this.ter.renderFrame(this.world.getTiles());
                                this.world.avatar.draw();
                            }
                        }
                        break;
                    case 's':
                        if (world.avatar.y - 1 >= 0) {
                            if (world.getTiles()[world.avatar.x][world.avatar.y - 1] == FLOOR) {
                                world.avatar.y -= 1;
                                this.actionHistory.append('s');
                                this.ter.renderFrame(this.world.getTiles());
                                this.world.avatar.draw();
                            }
                        }
                        break;
                    case 'd':
                        if (world.avatar.x + 1 < world.getTiles().length-1) {
                            if (world.getTiles()[world.avatar.x + 1][world.avatar.y] == FLOOR) {
                                world.avatar.x += 1;
                                this.actionHistory.append('d');
                                this.ter.renderFrame(this.world.getTiles());
                                this.world.avatar.draw();
                            }
                        }
                        break;
                    case ':':
                        if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == 'q') {
                            actionHistory.append(":Q");
                            saveGame();
                            return "Quit"; // 存档后退出游戏
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public String loadGame() {
        String filename = "savedGameData.txt";
        FileUtils fileIO = new FileUtils();
        if (!fileIO.fileExists(filename)) {
            return "Quit";
        }
        String fileString = fileIO.readFile(filename);
        String[] history = fileString.split("\t");
        this.seed = Long.parseLong(history[0]);
        this.world = new World(windowWidth, windowHeight - HUDHeight, this.seed);
        for (int i = 0; i < history[1].length(); i++) {
            char move = history[1].charAt(i);
            switch(move) {
                case 'w':
                    if (world.avatar.y + 1 < world.getTiles()[0].length-1) {
                        if (world.getTiles()[world.avatar.x][world.avatar.y + 1] == FLOOR) {
                            world.avatar.y += 1;
                            this.actionHistory.append('w');
                            this.ter.renderFrame(this.world.getTiles());
                            this.world.avatar.draw();
                        }
                    }
                    break;
                case 'a':
                    if (world.avatar.x - 1 >= 0) {
                        if (world.getTiles()[world.avatar.x - 1][world.avatar.y] == FLOOR) {
                            world.avatar.x -= 1;
                            this.actionHistory.append('a');
                            this.ter.renderFrame(this.world.getTiles());
                            this.world.avatar.draw();
                        }
                    }
                    break;
                case 's':
                    if (world.avatar.y - 1 >= 0) {
                        if (world.getTiles()[world.avatar.x][world.avatar.y - 1] == FLOOR) {
                            world.avatar.y -= 1;
                            this.actionHistory.append('s');
                            this.ter.renderFrame(this.world.getTiles());
                            this.world.avatar.draw();
                        }
                    }
                    break;
                case 'd':
                    if (world.avatar.x + 1 < world.getTiles().length-1) {
                        if (world.getTiles()[world.avatar.x + 1][world.avatar.y] == FLOOR) {
                            world.avatar.x += 1;
                            this.actionHistory.append('d');
                            this.ter.renderFrame(this.world.getTiles());
                            this.world.avatar.draw();
                        }
                    }
                    break;
            }
        }
        return "Game";
    }

    public void saveGame() {
        String filename = "savedGameData.txt";
        FileUtils fileIO = new FileUtils();

        if (fileIO.fileExists(filename)) { // 如果文件存在，不遗漏信息地覆盖掉源文件
            fileIO.writeFile(filename, this.actionHistory.toString());
        }
        else { // 文件不存在，写入
            File file = new File(filename);
            fileIO.writeFile(filename, this.actionHistory.toString());
        }
    }

    public static void main(String[] args) {

    }
}
