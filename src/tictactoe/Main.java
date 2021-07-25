package tictactoe;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        gameMenu();
    }

    static void gameMenu() {
        char[][] field = new char[3][3];
        clearField(field);
        boolean endGame = false;
        boolean endCaseGame;
        int userValue;
        while (!endGame) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input command: ");
            String inputCommand = scanner.nextLine();
            switch (inputCommand) {
                case "start easy easy":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        stupidMove(field);
                        endCaseGame = checkWin(field);
                    }
                    gameResults(field);
                    break;
                case "start easy user":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        stupidMove(field);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            addToField(field);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start user easy":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        addToField(field);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            stupidMove(field);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start medium medium":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        mediumMove(field);
                        endCaseGame = checkWin(field);
                    }
                    gameResults(field);
                    break;
                case "start medium user":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        mediumMove(field);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            addToField(field);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start user medium":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        addToField(field);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            mediumMove(field);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start hard hard":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        userValue = -1;
                        hardMove(field, userValue);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            userValue = 1;
                            hardMove(field, userValue);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start user hard":
                    userValue = 1;
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        addToField(field);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            hardMove(field, userValue);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start hard user":
                    userValue = -1;
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        hardMove(field, userValue);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            addToField(field);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "start user user":
                    endCaseGame = false;
                    printField(field);
                    while (!endCaseGame) {
                        addToField(field);
                        endCaseGame = checkWin(field);
                        if (!endCaseGame) {
                            addToField(field);
                            endCaseGame = checkWin(field);
                        }
                    }
                    gameResults(field);
                    break;
                case "exit":
                    endGame = true;
                    break;
                default:
                    System.out.println("Bad parameters!");
                    break;
            }
        }
    }

    static void addToField(char[][] field) {
        boolean isNotError = false;
        Scanner scanner = new Scanner(System.in);
        char nowTurn = turnXOr0(field);
        while (!isNotError) {
            System.out.print("Enter the coordinates: ");
            String enterCells = scanner.nextLine();
            if (!enterCells.equals("") && enterCells.charAt(0) > '0' && enterCells.charAt(0) < '4'
                    && enterCells.charAt(2) > '0' && enterCells.charAt(2) < '4' ) {
                int y = Character.getNumericValue(enterCells.charAt(0)) - 1;
                int x = Character.getNumericValue(enterCells.charAt(2)) - 1;
                if (field[y][x] == ' '){
                    field[y][x] = nowTurn;
                    isNotError = true;
                }
            }
        }
        printField(field);
    }

    static void stupidMove(char[][] field) {
        Random random = new Random();
        System.out.println("Making move level \"easy\" ");
        boolean added = false;
        char nowTurn = turnXOr0(field);
        while (!added){
            int y = random.nextInt(3);
            int x = random.nextInt(3);
            if (field[y][x] == ' '){
                field[y][x] = nowTurn;
                added = true;
            }
        }
        printField(field);
    }

    static void mediumMove(char[][] field) {
        Random random = new Random();
        System.out.println("Making move level \"medium\" ");
        boolean added = false;
        boolean successfulMove;
        char nowTurn = turnXOr0(field);
        char TurnOpponent = (nowTurn == 'X' ? 'O' : 'X');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    field[i][j] = nowTurn;
                    successfulMove = checkWin(field);
                    if (successfulMove) {
                        i = 2;
                        j = 2;
                        added = true;
                    } else {
                        field[i][j] = ' ';
                    }
                }
            }
        }
        if (!added) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == ' ') {
                        field[i][j] = TurnOpponent;
                        successfulMove = checkWin(field);
                        if (successfulMove) {
                            field[i][j] = nowTurn;
                            i = 2;
                            j = 2;
                            added = true;
                        } else {
                            field[i][j] = ' ';
                        }
                    }
                }
            }
        }
        while (!added){
            int y = random.nextInt(3);
            int x = random.nextInt(3);
            if (field[y][x] == ' '){
                field[y][x] = nowTurn;
                added = true;
            }
        }
        printField(field);
    }

    static int miniMax(char[][] imaginaryField, int depth, int nowValue, int userValue) {
        char[][] imaginaryFieldMiniMax = copyField(imaginaryField);
        if (checkWinX(imaginaryFieldMiniMax)) {
            if (userValue == 1) {
                return -1 * depth;
            } else {
                return depth;
            }
        } else if (checkWin0(imaginaryFieldMiniMax)) {
            if (userValue == -1) {
                return -1 * depth;
            } else {
                return depth;
            }
        } else if (isClearField(imaginaryFieldMiniMax)) {
            return 0;
        } else {
            return addToHardMove(imaginaryFieldMiniMax, depth, nowValue, userValue);
        }
    }

    static int addToHardMove(char[][] imaginaryField, int depth, int nowValue, int userValue) {
        depth = depth / 8 - 9;
        char[][] imaginaryFieldMiniMax = copyField(imaginaryField);
        char nowTurn = turnXOr0(imaginaryFieldMiniMax);
        nowValue = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (imaginaryFieldMiniMax[y][x] == ' ') {
                    imaginaryFieldMiniMax[y][x] = nowTurn;
                    nowValue = nowValue + miniMax(imaginaryFieldMiniMax, depth, nowValue, userValue);
                    imaginaryFieldMiniMax[y][x] = ' ';
                }
            }
        }
        return nowValue;
    }

    static void hardMove(char[][] field, int userValue) {
        // if user plays for the 'X' "userValue = 1"
        // if user plays for the 'O' "userValue = -1"
        char nowTurn = turnXOr0(field);
        char[][] imaginaryField = copyField(field);
        System.out.println("Making move level \"hard\" ");

        int[][] bestValue = new int[3][3];
        int bestBestValue = Integer.MIN_VALUE;
        int bestY = 0;
        int bestX = 0;
        int nowValue = 0;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (imaginaryField[y][x] == ' ') {
                    imaginaryField[y][x] = nowTurn;
                    bestValue[y][x] = miniMax(imaginaryField, Integer.MAX_VALUE, nowValue, userValue);
                    nowValue = 0;
                    imaginaryField[y][x] = ' ';
                }
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (bestValue[y][x] > bestBestValue && imaginaryField[y][x] == ' ') {
                    bestBestValue = bestValue[y][x];
                    bestY = y;
                    bestX = x;
                }
            }
        }
        field[bestY][bestX] = nowTurn;
        printField(field);
    }

    static boolean checkWinX(char[][] field) {
        boolean isWin = false;
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == 'X' && field[i][1] == 'X' && field[i][2] == 'X') {
                isWin = true;
                break;
            } else if (field[0][i] == 'X' && field[1][i] == 'X' && field[2][i] == 'X') {
                isWin = true;
                break;
            }
            if (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X') {
                isWin = true;
                break;
            } else if (field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {
                isWin = true;
                break;
            }
        }
        if (isClearField(field)) {
            isWin = true;
        }
        return isWin;
    }

    static boolean checkWin0(char[][] field) {
        boolean isWin = false;
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == 'O' && field[i][1] == 'O' && field[i][2] == 'O') {           // rows
                isWin = true;
                break;
            } else if (field[0][i] == 'O' && field[1][i] == 'O' && field[2][i] == 'O') {    // columns
                isWin = true;
                break;
            }
             if (field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O') {          // diagonal
                isWin = true;
                break;
            } else if (field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {    // diagonal
                isWin = true;
                break;
            }
        }
        if (isClearField(field)) {
            isWin = true;
        }
        return isWin;
    }

    static boolean checkWin (char[][] field) {
        boolean isWin = false;
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == 'X' && field[i][1] == 'X' && field[i][2] == 'X') {           // rows for X
                isWin = true;
                break;
            } else if (field[i][0] == 'O' && field[i][1] == 'O' && field[i][2] == 'O') {    // rows for 0
                isWin = true;
                break;
            } else if (field[0][i] == 'X' && field[1][i] == 'X' && field[2][i] == 'X') {    // columns for X
                isWin = true;
                break;
            } else if (field[0][i] == 'O' && field[1][i] == 'O' && field[2][i] == 'O') {    // columns for 0
                isWin = true;
                break;
            }
            if (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X') {           // diagonal
                isWin = true;
                break;
            } else if (field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O') {    // diagonal
                isWin = true;
                break;
            } else if (field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {    // diagonal
                isWin = true;
                break;
            } else if (field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {    // diagonal
                isWin = true;
                break;
            }
        }
        if (isClearField(field)) {
            isWin = true;
        }
        return isWin;
    }

    static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void clearField(char[][] field) {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(field[i], ' ');
        }
    }

    static char turnXOr0(char[][] field){
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') {
                    countX++;
                } else if (field[i][j] == 'O') {
                    countO++;
                }
            }
        }
        return countX > countO ? 'O' : 'X';
    }

    static boolean isClearField(char[][] field) {
        int countSpaces = 0;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (field[a][b] == ' ') {
                    countSpaces++;
                }
            }
        }
        return countSpaces == 0;
    }

    static void gameResults(char[][] field) {
        if (isClearField(field)) {
            System.out.println("Draw!");
        } else if (turnXOr0(field) == 'O') {
            System.out.println("X wins");
        } else {
            System.out.println("O wins");
        }
        clearField(field);
    }

    static char[][] copyField(char[][] field) {
        char[][] newField = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') {
                    newField[i][j] = 'X';
                } else if (field[i][j] == 'O') {
                    newField[i][j] = 'O';
                } else {
                    newField[i][j] = ' ';
                }
            }
        }
        return newField;
    }

}