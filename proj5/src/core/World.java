package core;

import tileengine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tileengine.Tileset.*;

public class World {

    // build your own world!
    //接收参数，负责生成一个合法且伪随机的世界 —— 2D TETile对象的数组。

    // attributes
    private int w; // width
    private int h; // height
    private long seed; // pseudorandom seed
    private TETile[][] tiles;
    public Avatar avatar;
    List<Room> rooms;
    List<Hallway> hallways;

    // constructor
    public World(int width, int height, long randomSeed) {
        w = width;
        h = height;
        seed = randomSeed;
        tiles = new TETile[w][h];

        rooms = new ArrayList<>();
        hallways = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                tiles[i][j] = NOTHING;
            }
        }
        this.generation();
        avatar = new Avatar(rooms.get(0).coreX, rooms.get(0).coreY); // modify
    }

    private void generation() { // generation algorithm
        Random r = new Random(this.seed);
        // 生成房间，维护房间列表。
        int roomNumber = r.nextInt(14, 18); // 房间的数量：14到17。
        while (roomNumber > 0) {
            int trial = 200; // 每间房间的生成循环最多试200次，失败就少一间，继续下一间。
            while (trial > 0) {
                int roomX = r.nextInt(1, w); // 房间的左下角坐标:x,y
                int roomY = r.nextInt(1, h); // 房间的左下角坐标:x,y
                int roomWidth = r.nextInt(12, 18); // 房间的宽度：12到17， x + 房间的宽度 < 世界宽度-1 && x >= 1
                int roomHeight = r.nextInt(12, 18); // 房间的高度：12到17， y + 房间的高度 < 世界高度-1 && y >= 1
                Room trialRoom = new Room(roomX, roomY, roomWidth, roomHeight);
                // 如果房间是贴边的且被完全包含：跳过，进入下一次trial随机生成。
                if (trialRoom.isLlegal() && !trialRoom.isSubRoom(rooms)) {
                    rooms.add(trialRoom);  // 前两项检验通过，房间是合法的: 加入房间列表
                    this.fillRoom(trialRoom); // 房间生成：用floor铺满矩形内的空间 fillRoom
                    break; // break，跳出该次生成房间的循环，进入下一个房间生成。
                }
                trial--;
            }
            roomNumber--;
        }

        for (int i = 0; i < rooms.size() - 1; i++) { // 生成走廊，维护走廊列表
            int prevX = rooms.get(i).coreX;
            int prevY = rooms.get(i).coreY;
            int nextX = rooms.get(i+1).coreX;
            int nextY = rooms.get(i+1).coreY;
            // 连接房间列表中第 i 和 i+1 个房间的中心 ——> 生成一条走廊，宽度为1。
            Hallway connectHallway = new Hallway(prevX, prevY, nextX, nextY);
            fillHallway(connectHallway); // 更新走廊列表、填充floor
            hallways.add(connectHallway);
        }

        this.fillWall(); // 铺墙
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }

    // 根据传入的Room对象的参数，在世界tiles中填充FLOOR
    private void fillRoom(Room room) {
        for (int x = 0; x < room.width; x++) {
            for (int y = 0; y < room.height; y++) {
                tiles[room.x + x][room.y + y] = FLOOR;
            }
        }
    }

    // 根据传入的Hallway对象的参数，在世界tiles中填充FLOOR——先横后竖
    private void fillHallway(Hallway hallway) {
        fillHorizonLine(hallway.startX, hallway.endX, hallway.startY);
        fillVerticalLine(hallway.startY, hallway.endY, hallway.endX);
    }

    private void fillHorizonLine(int xa, int xb, int y) {
        for (int x = Math.min(xa, xb); x <= Math.max(xa, xb); x++) {
            tiles[x][y] = FLOOR;
        }
    }

    private void fillVerticalLine(int ya, int yb, int x) {
        for (int y = Math.min(ya, yb); y <= Math.max(ya, yb); y++) {
            tiles[x][y] = FLOOR;
        }
    }

    private void fillWall() {
        for (int x = 0; x < w; x++) { // 扫描整个tiles，对于是NOTHING的tile：
            for (int y = 0; y < h; y++) {
                for (TETile neighbor: findNeighbors(tiles, x, y)) {
                    // 如若以自身为中心的八个邻居tile存在floor，修改为WALL
                    if (neighbor == FLOOR && tiles[x][y] == NOTHING) {
                        tiles[x][y] = WALL;
                        break;
                    }
                }
            }
        }
    }

    private List<TETile> findNeighbors(TETile[][] tiles, int x, int y) {
        List<TETile> neighbors = new ArrayList<>();
        if (x - 1 >= 0) {
            neighbors.add(tiles[x-1][y]);
            if (y - 1 >= 0) {
                neighbors.add(tiles[x][y-1]);
                neighbors.add(tiles[x-1][y-1]);
            }
            if (y + 1 < tiles[0].length) {
                neighbors.add(tiles[x][y+1]);
                neighbors.add(tiles[x-1][y+1]);
            }
        }
        if (x + 1 <= tiles.length - 1) {
            neighbors.add(tiles[x+1][y]);
            if (y - 1 >= 0) {
                neighbors.add(tiles[x+1][y-1]);
            }
            if (y + 1 < tiles[0].length) {
                neighbors.add(tiles[x+1][y+1]);
            }
        }
        return neighbors;
    }

    public class Avatar {
        // 坐标
        int x;
        int y;
        // appearance：AVATAR
        TETile look;
        // name
        String name;

        public Avatar(int avatarX, int avatarY) {
            look = AVATAR;
            name = "avatar";
            x = avatarX;
            y = avatarY;
        }

        public void draw() {
            look.draw(x, y);
        }
    }

    private class Room {
        // 左下角
        int x;
        int y;
        // 中心
        int coreX;
        int coreY;
        // 宽度
        int width;
        // 高度
        int height;

        // constructor
        private Room(int roomX, int roomY, int w, int h) {
            x = roomX;
            y = roomY;
            width = w;
            height = h;
            coreX = x + width / 2;
            coreY = y + height / 2;
        }
        // 判断房间是否不贴边
        private boolean isLlegal() {
            //房间满足不贴边的要求
            return ( (this.x + this.width <= w - 1)
                     && (this.y + this.height <= h - 1));
        }
        // 判断是否被other这一房间包含过多，而不能清晰分辨出两个房间
        private boolean isSubRoom(Room other) {
            // 与已有房间 b 的重叠面积
            int ox = Math.min(this.x + this.width, other.x + other.width)  - Math.max(this.x, other.x);
            int oy = Math.min(this.y + this.height, other.y + other.height) - Math.max(this.y, other.y);
            int overlapArea = (ox > 0 && oy > 0) ? ox * oy : 0;
            // 超过较小那间房面积的 30% → 重叠过多，返回true
            if (overlapArea > 0.35 * Math.min(this.width * this.height, other.width * other.height)) {
                return true;
            }
            else return false;
        }
        // 判断是否被房间列表中的某一个房间完全包含
        private boolean isSubRoom(List<Room> others) {
            for (Room other: others) {
                if (this.isSubRoom(other)) {
                    return true;
                }
            }
            return false;
        }

    }

    private class Hallway {
        // 起点
        int startX;
        int startY;
        // 终点
        int endX;
        int endY;
        // 中间点
        int middleX;
        int middleY;
        // 宽度设定为1
        int width;

        // constructor
        private Hallway(int startx, int starty, int endx, int endy) {
            width = 1;
            startX = startx;
            startY = starty;
            endX = endx;
            endY = endy;
            if (startX < endX) {
                middleX = endX;
                middleY = startY;
            } else {
                middleX = startX;
                middleY = endY;
            }
        }

    }

}
