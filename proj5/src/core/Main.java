package core;

import tileengine.TERenderer;

public class Main {
    public static void main(String[] args) {

        // build your own world!
        int WIDTH = 80;
        int HEIGHT = 60;
        int worldHeight = 55;
        int HUD_height = 5;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        char userInput;
        StringBuilder actionHistory = new StringBuilder();

        // main menu
        Menu startMenu = new Menu();
        // 开始菜单的流程循环

        // new game
        // 生成所需要的参数：宽、高、随机种子等，具体的世界由world class的实例接收参数后内部完成
        /* 6578897764558030256L
           1013758890894698811L
           7162790311120124118L
           7003957710856902839L
           6206686636164176845L
        */
        long seed = 6578897764558030256L; // Long.parseLong(startMenu.seedBuffer);
        World world = new World(WIDTH, worldHeight, seed);
        // main负责最后渲染到窗口，使之可见。
        ter.renderFrame(world.getTiles());
    }
}
