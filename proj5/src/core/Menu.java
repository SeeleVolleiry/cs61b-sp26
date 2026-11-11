package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.*;

public class Menu {
    public String[] mainMenu;
    public String[] newGame;
    public StringBuilder seedBuffer;

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

    public static void main(String[] args) {
        // 渲染菜单
        TERenderer ter = new TERenderer();
        ter.initialize(80, 60);

        Menu startMenu = new Menu();

        startMenu.mainMenuRender();
        //startMenu.newGameRender();

    }
}
