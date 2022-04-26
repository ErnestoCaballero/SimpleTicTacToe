package com.ernesto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();

        TicTacToe game = new TicTacToe(input);

        game.printGrid();
        game.checkState();
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
        gameState state;

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

        void checkState() {
            if (Math.abs(countX - countO) > 1 || checkWins('X') && checkWins('O')) {
                state = gameState.IMPOSSIBLE;
                System.out.println("Impossible");
            } else if (!checkWins('X') && !checkWins('O') && countB > 0) {
                state = gameState.NOT_FINISHED;
                System.out.println("Game not finished");
            } else if (!checkWins('X') && !checkWins('O') && countB == 0) {
                state = gameState.DRAW;
                System.out.println("Draw");
            } else if (checkWins('X')) {
                state = gameState.X_WINS;
                System.out.println("X wins");
            } else if (checkWins('O')) {
                state = gameState.O_WINS;
                System.out.println("O wins");
            } else {
                System.out.println("Not a valid state");
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
