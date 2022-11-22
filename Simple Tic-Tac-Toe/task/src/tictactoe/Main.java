package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);
        String input = "_________";
        printArray(input);
        int numberOfXs = countXs(input);
        int numberOfOs = countOs(input);
        char[][] ticTacToe2D = make2dTicTacToe(input);
        //System.out.println(make2dTicTacToe(input));

        int userInputIsValid = -1;
        char currentPlayerChar = 'X';
        while (userInputIsValid != 0) {
            int rowIndex = scanner.nextInt();
            int columnIndex = scanner.nextInt();

            if (!(rowIndex >= 1 && rowIndex <= 3)  || !(columnIndex >= 1 && columnIndex <= 3)) {
                System.out.println("You should enter numbers!");
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (ticTacToe2D[rowIndex - 1][columnIndex - 1] == 'O' || ticTacToe2D[rowIndex - 1][columnIndex - 1] == 'X') {
                System.out.println("This cell is occupied!");
                System.out.println("Choose another one!");
            } else {

                ticTacToe2D[rowIndex - 1][columnIndex - 1] = currentPlayerChar;
                if (currentPlayerChar == 'X') {
                    currentPlayerChar = 'O';
                } else {
                    currentPlayerChar = 'X';
                }


                input = makeStringFrom2dTicTacToe(ticTacToe2D);
                numberOfXs = countXs(input);
                numberOfOs = countOs(input);
                printArray(input);
                //userInputIsValid = 0;
                if (Math.abs(numberOfOs - numberOfXs) >= 2) {
                    System.out.println("Impossible");
                    return;
                }

                String[] rowsArray = new String[3];
                String[] columnsArray = new String[3];
                for (int i =0; i < 3; i++) {
                    rowsArray[i] = getRowAsString(input, i);
                    columnsArray[i] = getColumnAsString(input, i);
                }

                int threeOsInARowOrColumn = 0;
                int threeXsInARowOrColumn = 0;

                for (int i =0; i < 3; i++) {
                    if ("XXX".equals(rowsArray[i]) || "XXX".equals(columnsArray[i])) {
                        threeXsInARowOrColumn++;
                    }
                    if ("OOO".equals(rowsArray[i]) || "OOO".equals(columnsArray[i])) {
                        threeOsInARowOrColumn++;
                    }
                }

                if (threeOsInARowOrColumn > 0 && threeXsInARowOrColumn > 0) {
                    System.out.println("Impossible");
                    return;
                }

                if (threeOsInARowOrColumn == 1 && numberOfOs >= numberOfXs && threeXsInARowOrColumn == 0) {
                    System.out.println("O wins");
                    break;
                }

                if (threeXsInARowOrColumn == 1 && numberOfOs <= numberOfXs && threeOsInARowOrColumn == 0) {
                    System.out.println("X wins");
                    break;
                }

                String[] diagonals = getDiagonals(input);
                if ("XXX".equals(diagonals[0]) || "XXX".equals(diagonals[1])) {
                    System.out.println("X wins");
                    break;
                }

                if ("OOO".equals(diagonals[0]) || "OOO".equals(diagonals[1])) {
                    System.out.println("O wins");
                    break;
                }

                if ((numberOfXs == 5 && numberOfOs == 4) || (numberOfOs == 5 && numberOfXs == 4) && threeOsInARowOrColumn == 0 && threeOsInARowOrColumn == 0) {
                    System.out.println("Draw");
                    break;
                }

                if (numberOfXs < 4 && numberOfOs < 4) {
                    System.out.println("Game not finished");
                    continue;
                }
            }
        }











//        System.out.println(getRowAsString(input, 0));
//        System.out.println(getRowAsString(input, 1));
//        System.out.println(getRowAsString(input, 2));
//
//        System.out.println(getColumnAsString(input, 0));
//        System.out.println(getColumnAsString(input, 1));
//        System.out.println(getColumnAsString(input, 2));
//
//        System.out.println(getDiagonals(input)[0]);
//        System.out.println(getDiagonals(input)[1]);





    }

    private static void printArray(String array) {
        System.out.printf("%n---------");
        System.out.printf("%n| %c %c %c |", array.charAt(0), array.charAt(1), array.charAt(2));
        System.out.printf("%n| %c %c %c |", array.charAt(3), array.charAt(4), array.charAt(5));
        System.out.printf("%n| %c %c %c |", array.charAt(6), array.charAt(7), array.charAt(8));
        System.out.printf("%n---------%n");
    }

    private static String getRowAsString(String ticTacToe, int rowIndex) {
        int startIndex = rowIndex * 3;
        int endIndex = startIndex + 3;
        return ticTacToe.substring(startIndex, endIndex);
    }

    private static String getColumnAsString(String ticTacToe, int columnIndex) {
        return String.format("%c%c%c",ticTacToe.charAt(columnIndex), ticTacToe.charAt(columnIndex + 3), ticTacToe.charAt(columnIndex + 6));
    }

    private static String[] getDiagonals(String ticTacToe) {
        String[] diagonals = new String[2];
        diagonals[0] = String.format("%c%c%c",ticTacToe.charAt(0), ticTacToe.charAt(4), ticTacToe.charAt(8));
        diagonals[1] = String.format("%c%c%c",ticTacToe.charAt(6), ticTacToe.charAt(4), ticTacToe.charAt(2));
        return diagonals;
    }

    private static int countXs(String ticTacToe) {
        return ticTacToe.length() - ticTacToe.replace("X", "").length();
    }

    private static int countOs(String ticTacToe) {
        return ticTacToe.length() - ticTacToe.replace("O", "").length();
    }

    private static char[][] make2dTicTacToe(String ticTacToeSingleLine) {
        char[][] ticTacToe2d = new char[3][3];
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe2d[i][j] = ticTacToeSingleLine.charAt(counter);
                counter++;
            }
        }
        return ticTacToe2d;
    }

    private static String makeStringFrom2dTicTacToe(char[][] ticTacToe2D) {
        String output = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                output += ticTacToe2D[i][j];
            }
        }
        return output;
    }


}
