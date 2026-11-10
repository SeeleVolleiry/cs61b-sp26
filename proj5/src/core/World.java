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
    Avatar avatar;
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
        avatar = new Avatar(rooms.get(0).x, rooms.get(0).y); // modify
    }

    // generation algorithm
    private void generation() {
        Random r = new Random(this.seed);
        // 生成房间，维护房间列表。
        int roomNumber = r.nextInt(12, 15); // 房间的数量：12到14。
        while (roomNumber >= 0) {
            int trial = 200; // 每间房间的生成循环最多试200次，失败就少一间，继续下一间。
            while (trial >= 0) {
                int roomX = r.nextInt(1, w); // 房间的左下角坐标:x,y
                int roomY = r.nextInt(1, h); // 房间的左下角坐标:x,y
                int roomWidth = r.nextInt(14, 17); // 房间的宽度：14到16， x + 房间的宽度 < 世界宽度-1 && x >= 1
                int roomHeight = r.nextInt(14, 17); // 房间的高度：14到16， y + 房间的高度 < 世界高度-1 && y >= 1
                Room trialRoom = new Room(roomX, roomY, roomWidth, roomHeight);
                // 如果房间是贴边的且被完全包含：跳过，进入下一次trial随机生成。
                if (!trialRoom.isLlegal() && trialRoom.isSubRoom(rooms)) {
                    trial--;
                    continue;
                }
                rooms.add(trialRoom);  // 前两项检验通过，房间是合法的: 加入房间列表
                this.fillRoom(trialRoom); // 房间生成：用floor铺满矩形内的空间 fillRoom
                break; // break，跳出该次生成房间的循环，进入下一个房间生成。
            }
            roomNumber--;
        }
        // 生成走廊，维护走廊列表
          // 连接房间列表中第 i 和 i+1 个房间的中心 ——> 生成一条走廊，宽度为1。
          // 两个房间的中心坐标不一致则生成L型走廊，一致则生成直线型
          // 更新走廊列表；填充floor
        for (int i = 0; i < rooms.size() - 1; i++) {
            int prevX = rooms.get(i).coreX;
            int prevY = rooms.get(i).coreY;
            int nextX = rooms.get(i+1).coreX;
            int nextY = rooms.get(i+1).coreY;

            Hallway connectHallway = new Hallway(prevX, prevY, nextX, nextY);
            fillHallway(connectHallway);
            hallways.add(connectHallway);
        }
        // 铺墙
        for (int x = 0; x < w; x++) { // 扫描整个tiles，对于是NOTHING的tile：
            for (int y = 0; y < h; y++) {
                if (tiles[x][y] == NOTHING) { // 如若以自身为中心的九宫格范围内的八个邻居tile存在floor，修改为WALL
                    if ((tiles[x-1][y] == FLOOR)
                            || (tiles[x+1][y] == FLOOR)
                            || (tiles[x][y-1] == FLOOR)
                            || (tiles[x][y+1] == FLOOR)) {
                        tiles[x][y] = WALL;
                    }
                }
            }
        }

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

    // 根据传入的Hallway对象的参数，在世界tiles中填充FLOOR
    private void fillHallway(Hallway hallway) {
        if (hallway.startX < hallway.endX && hallway.startY < hallway.endY) {
            int horizon = hallway.endX - hallway.startX;
            int vertical = hallway.endY - hallway.startY;
            for (int h = 0; h < horizon; h++) {
                if (tiles[hallway.startX + h][hallway.middleY] != FLOOR) {
                    tiles[hallway.startX + h][hallway.middleY] = FLOOR;
                }
            }
            for (int v = 0; v < vertical; v++) {
                if (tiles[hallway.middleX][hallway.middleY + v] != FLOOR) {
                    tiles[hallway.middleX][hallway.middleY + v] = FLOOR;
                }
            }
        } else if (hallway.startX < hallway.endX && hallway.startY > hallway.endY) {
            int horizon = hallway.endX - hallway.startX;
            int vertical = hallway.startY - hallway.endY;
            for (int h = 0; h < horizon; h++) {
                if (tiles[hallway.startX + h][hallway.middleY] != FLOOR) {
                    tiles[hallway.startX + h][hallway.middleY] = FLOOR;
                }
            }
            for (int v = 0; v < vertical; v++) {
                if (tiles[hallway.middleX][hallway.middleY - v] != FLOOR) {
                    tiles[hallway.middleX][hallway.middleY - v] = FLOOR;
                }
            }
        }
        else if (hallway.startX > hallway.endX && hallway.startY > hallway.endY) {
            int horizon = hallway.startX - hallway.endX;
            int vertical = hallway.startY - hallway.endY;
            for (int v = 0; v < vertical; v++) {
                if (tiles[hallway.middleX][hallway.startY - v] != FLOOR) {
                    tiles[hallway.middleX][hallway.startY - v] = FLOOR;
                }
            }
            for (int h = 0; h < horizon; h++) {
                if (tiles[hallway.middleX - h][hallway.middleY] != FLOOR) {
                    tiles[hallway.middleX - h][hallway.middleY] = FLOOR;
                }
            }
        }
        else {
            int horizon = hallway.startX - hallway.endX;
            int vertical = hallway.endY - hallway.startY;
            for (int v = 0; v < vertical; v++) {
                if (tiles[hallway.middleX][hallway.startY + v] != FLOOR) {
                    tiles[hallway.middleX][hallway.startY + v] = FLOOR;
                }
            }
            for (int h = 0; h < horizon; h++) {
                if (tiles[hallway.startX - h][hallway.middleY] != FLOOR) {
                    tiles[hallway.startX - h][hallway.middleY] = FLOOR;
                }
            }
        }
    }

    public class Avatar {
        // 坐标
        int x;
        int y;
        // appearance：AVATAR
        TETile appr = AVATAR;
        // name
        String name = "avatar";

        public Avatar(int avatarX, int avatarY) {
            x = avatarX;
            y = avatarY;
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
            return ( (this.x + this.width < w - 1)
                     && (this.y + this.height < h - 1));
        }
        // 判断是否被other这一房间完全包含
        private boolean isSubRoom(Room other) {
            if ((other.x <= this.x && other.x + other.width >= this.x + this.width)
                    && (other.y <= this.y && other.y + other.height >= this.y + this.height)) {
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
