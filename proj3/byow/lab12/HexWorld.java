package byow.lab12;



import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /* p is the left top position */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (! parms(world,p,s)) throw new IllegalArgumentException("p is wrong");
        int i;
        for (i = 0;i<=world.length-1;i++) {
            for (int j = 0;j<=world[0].length-1;j++)
                world[i][j] = Tileset.NOTHING;
        }
        for (i = 0; i <= (2*s-1)/2;i++)
            drawUpLine(s,world,p.x+i,p.y,t,i);

        for (;i<=2*s-1;i++)
            drawDoLine(s,world,p.x+i,p.y,t);

    }

    private static void drawUpLine(int s,TETile[][] world, int x,int y,TETile t,int row) {
        int i;
        for (i = s-1+row;i<=3*s-4+row;i++)
            world[y][i] = t;


    }

    private static void drawDoLine(int s,TETile[][] world, int row,int col,TETile t) {

    }

    private static boolean parms(TETile[][] world, Position p, int s) {
        if (p.x <0 || p.x > world.length-1 || p.y <0 || p.y>world[0].length-1)
            return false;
        if (p.x + 3*s-3 > world.length-1 || p.y + 2 * s - 1 > world[0].length -1)
            return false;
        return true;
    }



    public static void main(String[] args) {
        TERenderer te = new TERenderer();
        te.initialize(20,20);
        TETile[][] randomTiles = new TETile[20][20];
        for (int i =0;i<=20-1;i++) {
            for(int j = 0;j<=19;j++)
                randomTiles[i][j] = Tileset.NOTHING;
        }
       randomTiles[1][1] = Tileset.FLOWER;

    }
}
