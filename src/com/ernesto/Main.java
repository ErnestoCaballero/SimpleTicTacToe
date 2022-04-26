package com.ernesto;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        GameState state;
        int turn = 0;

        game.printGrid();

        while (true) {
            game.selectCell(turn);
            turn++;
            game.printGrid();
            state = game.checkState();

            switch (state) {
                case DRAW:
                    System.out.println("Draw");
                    System.exit(0);
                case X_WINS:
                    System.out.println("X wins");
                    System.exit(0);
                case O_WINS:
                    System.out.println("O wins");
                    System.exit(0);
                case IMPOSSIBLE:
                    System.out.println("Impossible");
                    System.exit(0);
                case UNDEFINED:
                    System.out.println("Not a valid state");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    enum GameState {
        NOT_FINISHED,
        DRAW,
        X_WINS,
        O_WINS,
        IMPOSSIBLE,
        UNDEFINED
    }

    static class TicTacToe {
        char[][] grid = new char[3][3];
        int countX;
        int countO;
        int countB;

        TicTacToe() {
            String input = "_________";
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = input.charAt(count);
                    if (input.charAt(count) == 'X') {
                        countX++;
                    } else if (input.charAt(count) == 'O') {
                        countO++;
                    } else if (input.charAt(count) == '_') {
                        countB++;
                    } else {
                        System.out.println("Not a valid input!");
                        System.exit(0);
                    }
                    count++;
                }
            }
        }

        public char[][] getGrid() {
            return grid;
        }

        public int getCountX() {
            return countX;
        }

        public int getCountO() {
            return countO;
        }

        public int getCountB() {
            return countB;
        }

        void printGrid() {
            System.out.println("---------");
            for (int i = 0; i < grid.length; i++) {
                System.out.printf("| %c %c %c |%n", grid[i][0], grid[i][1], grid[i][2]);
            }
            System.out.println("---------");
        }

        GameState checkState() {
            if (Math.abs(countX - countO) > 1 || checkWins('X') && checkWins('O')) {
                return GameState.IMPOSSIBLE;
            } else if (!checkWins('X') && !checkWins('O') && countB == 0) {
                return GameState.DRAW;
            } else if (checkWins('X')) {
                return GameState.X_WINS;
            } else if (checkWins('O')) {
                return GameState.O_WINS;
            } else if (!checkWins('X') && !checkWins('O') && countB > 0) {
                return GameState.NOT_FINISHED;
            } else {
                return GameState.UNDEFINED;
            }
        }

        void selectCell(int turn) {
            char mark = turn % 2 == 0 ? 'X' : 'O';
            Scanner scanner = new Scanner(System.in);
            String row1;
            String column1;

            while (true) {
                System.out.print("Enter the coordinates: ");

                row1 = scanner.next();
                if (!row1.matches("\\d+")) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                    continue;
                }

                column1 = scanner.next();
                if (!column1.matches("\\d+")) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                int row = Integer.parseInt(row1);
                int column = Integer.parseInt(column1);

                if (row > grid.length || column > grid.length || row < 0 || column < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if ('X' == grid[row - 1][column - 1] || 'O' == grid[row - 1][column - 1]) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    grid[row - 1][column - 1] = mark;
                    if (mark == 'X') {
                        countX++;
                        countB--;
                    }

                    if (mark == 'O') {
                        countO++;
                        countB--;
                    }
                    break;
                }
            }
        }

        boolean checkWins(char c) {
            boolean win = true;
            if (c == 'X' || c == 'O') {
                // check columns
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[j][i] != c) {
                            win = false;
                            break;
                        }
                    }

                    if (win) {
                        return true;
                    }

                    win = true;
                }

                // Check rows
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j] != c) {
                            win = false;
                            break;
                        }
                    }

                    if (win) {
                        return true;
                    }

                    win = true;
                }

                // Check negative diagonal
                for (int i = 0; i < grid.length; i++) {
                    if (grid[i][i] != c) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }

                win = true;

                // Check positive diagonal for wins
                for (int i = 0; i < grid.length; i++) {
                    if (grid[i][grid.length - 1 - i] != c) {
                        win = false;
                        break;
                    }
                }

                return win;
            }

            return false;
        }

    }
}
