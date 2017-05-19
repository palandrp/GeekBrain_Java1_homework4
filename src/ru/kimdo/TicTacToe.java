package ru.kimdo;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by kimdo on 19.05.17.
 */

public class TicTacToe {

    int x, y;
    final int SIZE = 5;
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
            if (checkWin(DOT_X, x, y)) {
                System.out.println("You win!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            aiTurn(DOT_X, x, y);
            printMap();
            if (checkWin(DOT_O, x, y)) {
                System.out.println("You lost!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
            }
        }
        System.out.println("GAME OVER!");
    }

    private boolean checkWin(char dot, int x, int y) {
        // Метод поддерживает любое кол-во фишек и полей
        boolean flag = false;
        
        // Диагонали проверяем только если оказались на диагонали
        if (x == y) {
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == dot) flag = true;
                else {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        if (x + y == SIZE - 1) {
            for (int i = 0; i < SIZE; i++) {
                if (map[i][(SIZE - 1) - i] == dot) flag = true;
                else {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        
        // Проверяем строку
        for (int i = 0; i < SIZE; i++) {
            if (map[y][i] == dot) flag = true;
            else {
                flag = false;
                break;
            }
        }
        if (flag) return true;

        // Проверяем столбец
        for (int i = 0; i < SIZE; i++) {
            if (map[i][x] == dot) flag = true;
            else {
                flag = false;
                break;
            }
        }
        if (flag) return true;
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

    private void aiTurn(char dot, int x, int y) {
        //---------------------------------------------------+
        // Блок, где ИИ определяет, что юзер вот-вот выиграет:
        // Сюда сразу попадают координаты предыдущего хода
        // юзера.
        boolean isAssInTheFire = checkIsAssInTheFire(dot, x, y);

        //---------------------------------------------------+


        if (!isAssInTheFire) {
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            map[y][x] = DOT_O;
        }
    }

    private void humanTurn() {
        do {
            System.out.println("Enter X and Y (1 - " + SIZE + "):");
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

    public boolean checkIsAssInTheFire(char dot, int x, int y) {
        boolean flag = false;
        int count;

        // Диагонали проверяем только если оказались на диагонали
        if (x == y) {
            count = 0;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == dot) count++;
            }
            if (count == SIZE - 1) {
                for (int i = 0; i < SIZE; i++) {
                    if (map[i][i] != dot && map[i][i] == DOT_EMPTY) {
                        x = i;
                        y = i;
                        map[y][x] = DOT_O;
                        return true;
                    }
                }
            }
        }

        if (x + y == SIZE - 1) {
            count = 0;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][(SIZE - 1) - i] == dot) {
                    if (map[i][SIZE - 1] == dot) count++;
                }
            }
            if (count == SIZE - 1) {
                for (int i = 0; i < SIZE; i++) {
                    if (map[i][(SIZE - 1) - i] != dot && map[i][(SIZE - 1) - i] == DOT_EMPTY) {
                        x = (SIZE - 1) - i;
                        y = i;
                        map[y][x] = DOT_O;
                        return true;
                    }
                }
            }
        }

        // Проверяем строку
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[y][i] == dot) {
                if (map[y][i] == dot) count++;
            }
        }
        if (count == SIZE - 1) {
            for (int i = 0; i < SIZE; i++) {
                if (map[y][i] != dot && map[y][i] == DOT_EMPTY) {
                    x = i;
                    map[y][x] = DOT_O;
                    return true;
                }
            }
        }

        // Проверяем столбец
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][x] == dot) {
                if (map[i][x] == dot) count++;
            }
        }
        if (count == SIZE - 1) {
            for (int i = 0; i < SIZE; i++) {
                if (map[i][x] != dot && map[i][x] == DOT_EMPTY) {
                    y = i;
                    map[y][x] = DOT_O;
                    return true;
                }
            }
        }
        return false;
    }
}