package com.ernesto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter cells: ");
        String input = scanner.nextLine();

        TicTacToe game = new TicTacToe(input);

        game.printGrid();

        System.out.println(game.getCountX());
        System.out.println(game.getCountO());
        System.out.println(game.getCountB());
        System.out.println(game.checkWins('X'));
    }

    enum gameState {
        NOT_FINISHED,
        DRAW,
        X_WINS,
        O_WINS,
        IMPOSSIBLE
    }

    static class TicTacToe {
        char[][] grid = new char[3][3];
        int countX;
        int countO;
        int countB;
        boolean xWins;
        boolean yWins;

        TicTacToe(String input) {
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
                        return;
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
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.printf("%c ", grid[i][j]);
                }
                System.out.printf("|%n");
            }
            System.out.println("---------");
        }

        void checkState() {
            if (Math.abs(countX - countO) > 2) {
                System.out.println("Impossible");
            }




        }

        boolean checkWins(char c) {
            boolean win = true;
            if (c == 'X' || c == 'Y') {
                // check columns
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[j][i] != c) {
                            win = false;
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
                    }
                }
                if (win) {
                    return true;
                }
            } else {
                System.out.println("Not a valid symbol!");
                return false;
            }

            return false;
        }


    }
}
