package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.*;

public class Menu {
    String[] mainMenu;
    String[] newGame;
    static StringBuilder seedBuffer;

    public Menu() {
        seedBuffer = new StringBuilder();
        mainMenu = new String[] {"CS61B: BYOW", "(N) New Game", "(L) Load Game", "(Q) Quit Game"};
        newGame = new String[] {"CS61B: BYOW", "Enter seed followed by S", };
    }

    public void mainMenuRender() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 28));
        StdDraw.text(39 , 55, this.mainMenu[0]);
        StdDraw.text(39 , 35, this.mainMenu[1]);
        StdDraw.text(39 , 25, this.mainMenu[2]);
        StdDraw.text(39 , 15, this.mainMenu[3]);
        StdDraw.show();
    }

    public void newGameRender() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 28));
        StdDraw.text(39 , 55, this.newGame[0]);
        StdDraw.text(39 , 35, this.newGame[1]);
        StdDraw.text(39 , 25, seedBuffer.toString());
        StdDraw.show();
    }

    public void menuLoop() {}

    public static void main(String[] args) {
        // 渲染菜单
        TERenderer ter = new TERenderer();
        ter.initialize(80, 60);

        Menu startMenu = new Menu();

        startMenu.mainMenuRender();
        //startMenu.newGameRender();

        // 菜单循环
        char userInput;
        StringBuilder actionHistory = new StringBuilder();

        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                userInput = StdDraw.nextKeyTyped();
                userInput = Character.toLowerCase(userInput);

                switch(userInput) {
                    case 'n':
                        // create new game method
                        startMenu.newGameRender();
                        while (true) {
                            while (StdDraw.hasNextKeyTyped()) {
                                userInput = StdDraw.nextKeyTyped();
                                userInput = Character.toLowerCase(userInput);
                                if (userInput != 's') {
                                    seedBuffer.append(userInput);
                                    startMenu.newGameRender();
                                }
                                else {
                                    // 根据种子创建新世界开始游戏——进入游戏，一个新的调用方法
                                    long seed = Long.parseLong(seedBuffer.toString());
                                    World world = new World(80, 55, seed);
                                    ter.renderFrame(world.getTiles());
                                    world.avatar.draw();
                                }
                            }
                        }
                        //break;
                    case 'l':
                        // load;
                        break;
                    case 'q':
                        // quit game
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
