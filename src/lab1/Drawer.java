package lab1;

import java.util.Collections;

public class Drawer {
    private static void drawLine(int n, char ch) {
        for(int i = 0; i  < n; i++)
            System.out.print(ch);
    }

    private static void drawPyramid(int n, int m){
        for(int i = 0; i < n; i++){
            drawLine(m + n - i -1, '.');
            drawLine(1 + 2*i, 'X');
            drawLine(m + n - i -1, '.');
            System.out.println();
        }
    }

    public static void drawPyramid(int n) {
        drawPyramid(n, 0);
    }

    public static void drawChristmassTree(int n) {
        for(int i = 1; i <= n; i++)
            drawPyramid(i, n - i);
    }

    public static void drawSquare(int n){
        drawLine(n, 'X');
        System.out.println();
        for(int i =0; i < n-2; i++){
            System.out.print("X");
            drawLine(n - 2, '.');
            System.out.println("X");
        }
        drawLine(n, 'X');
        System.out.println();
    }


    /*
    public static void OldDrawPyramid(int n) {
        int dots = n - 1;
        String xesOut = "";
        while (dots >= 0) {
            String dotsOut = "";

            for (int i = 0; i < dots; i++) {
                dotsOut = dotsOut.concat(".") ;
            }
            int sideX = n - dots - 1;
            for (int i = 0; i < sideX; i++) {
                xesOut = xesOut.concat("X");
            }
            System.out.print(dotsOut + xesOut + "X" + xesOut + dotsOut + "\n");
            dots--;
        }
    }

    public static void OldDrawChristmassTree(int n) {

        for(int l = 1; l <= n; l++) {
            String dotsOut = "";
            String xesOut = "";
            int extraDots = n - l;
            for (int i = 0; i < extraDots; i++) {
                dotsOut = dotsOut.concat(".");
            }
            int dots =l - 1;
            while (dots >= 0) {
                dotsOut = dotsOut.substring(0, extraDots);
                xesOut = "";
                for (int i = 0; i < dots; i++) {
                    dotsOut = dotsOut.concat(".");
                }
                int sideX = l - dots - 1;
                for (int i = 0; i < sideX; i++) {
                    xesOut = xesOut.concat("X");
                }
                System.out.print(dotsOut + xesOut + "X" + xesOut + dotsOut + "\n");
                dots--;
            }
        }
    }

    */

}