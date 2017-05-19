package ru.kimdo;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by kimdo on 19.05.17.
 */

public class TicTacToe {

    final int SIZE = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    void go() {
        initMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("You win!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("You lost!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
            }
        }
        System.out.println("GAME OVER!");
    }

    private boolean checkWin(char dot) {
        // gorisontal
        if (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot)return true;
        if (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot)return true;
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot)return true;
        // vertical
        if (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot)return true;
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot)return true;
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot)return true;
        // diagonal
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot)return true;
        if (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot)return true;
        return false;
    }

    private void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    private void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1 - 3):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        
        return false;
    }

    void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}