package core;

import tileengine.TERenderer;

public class Main {
    public static void main(String[] args) {

        // build your own world!
        // 生成所需要的参数：宽、高、随机种子等，具体的世界由world class的实例接收参数后内部完成
        long seed = 6578897764558030256L;
        int WIDTH = 80;
        int HEIGHT = 60;
        int worldHeight = 55;
        int HUD_height = 5;
        World world = new World(WIDTH, worldHeight, seed);
        // main负责最后渲染到窗口，使之可见。
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world.getTiles());
    }
}
