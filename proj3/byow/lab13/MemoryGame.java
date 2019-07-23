package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40,seed);
        game.startGame();
//        game.flashSequence("jyp");
    }

    public MemoryGame(int width, int height,int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        this.rand =  new Random(seed);
        gameOver = false;
    }

    public String generateRandomString(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<=n-1;i++) {
            sb.append(CHARACTERS[rand.nextInt(26)]);
        }
        return sb.substring(0);
    }

    /* draw the frame type:Watch! and Type!  encouragement is in ENCOURAGEMENT */
    public void drawFrame(String s,String type,String encouragement) {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(20,20,s);
        if (!this.gameOver) {
            font = new Font("Monaco",Font.ITALIC,18);
            StdDraw.setFont(font);
            StdDraw.text(4,38,"Round: "+s.length());
            StdDraw.text(20,38,type);
            StdDraw.text(34,38,encouragement);
        }

        StdDraw.show();
    }

    /*display the letter*/
    public void flashSequence(String letters) {
        String en = ENCOURAGEMENT[rand.nextInt(7)];
        for (int i = 0;i<=letters.length()-1;i++) {
            drawFrame(String.valueOf(letters.charAt(i)),"Watch!",en);
            StdDraw.pause(1000);
            drawFrame(" ","Watch!",en);
            StdDraw.pause(500);
        }
    }

    /* interact with keyboard */
    public String solicitNCharsInput(int n) {

        StringBuilder sb = new StringBuilder();
        String s = ENCOURAGEMENT[rand.nextInt(7)];
        drawFrame(" ","Type!",s);
        while (sb.length()!=n) {
            if (StdDraw.hasNextKeyTyped()) {
                sb.append(StdDraw.nextKeyTyped());
                drawFrame(sb.substring(0),"Type!",s);
            }
        }
        return sb.substring(0);
    }

    /* follow the steps */
    public void startGame() {
        int round = 1;
        while (!this.gameOver) {
            String s = generateRandomString(round);
            this.gameOver = true;
            drawFrame("Round: "+round,null,null);
            StdDraw.pause(2000);
            this.gameOver = false;
            flashSequence(s);

            String input = solicitNCharsInput(round);
            if (s.equals(input)) {
                round += 1;
            }
            else {
                this.gameOver = true;
                drawFrame("Game Over! You made round: " +round,null,null);
            }
        }
    }


}
