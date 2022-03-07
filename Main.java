package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static String player1;
    private static String player2;
    private static boolean turnX = true;
    private static int round = 0;
    private static char[][] field = new char[3][3];


    public static void main(String[] args) {
        startGame();
    }


    public static void startGame() {
        while (true) {
            String start = scanner.nextLine();
            if ("exit".equals(start)) {
                return;
            }
            String[] parameters = start.split(" ");
            if (parameters.length < 3 || !"start".equals(parameters[0]) ||
                    (!"user".equals(parameters[1]) && !"easy".equals(parameters[1]) &&
                    !"medium".equals(parameters[1]) && !"hard".equals(parameters[1])) ||
                    (!"user".equals(parameters[2]) && !"easy".equals(parameters[2]) &&
                    !"medium".equals(parameters[2]) && !"hard".equals(parameters[2]))) {
                System.out.println("Bad parameters!");
                continue;
            }
            fillField();
            showField();
            player1 = parameters[1];
            player2 = parameters[2];
            makeTurns();
        }
    }

    public static void fillField() {
        for (char[] row : field) {
            Arrays.fill(row, ' ');
        }
    }

    public static void showField() {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", field[0][0], field[0][1], field[0][2]);
        System.out.printf("| %c %c %c |\n", field[1][0], field[1][1], field[1][2]);
        System.out.printf("| %c %c %c |\n", field[2][0], field[2][1], field[2][2]);
        System.out.println("---------");
    }

    public static void makeTurns() {
        String currentTurn = turnX ? player1 : player2;
        switch (currentTurn) {
            case "user":
                userTurn();
                break;
            case "easy":
                easyTurn();
                break;
            case "medium":
                mediumTurn();
                break;
            case "hard":
                hardTurn();
                break;
            default:
        }
        showField();
        checkResult();
    }

    public static void userTurn() {
        System.out.print("Enter the coordinates:");
        String[] turn = scanner.nextLine().split(" ");
        if (turn[0].replaceAll("[^\\d]", "").length() != turn[0].length() ||
                turn[turn.length - 1].replaceAll("[^\\d]", "").length() != turn[turn.length - 1].length()) {
            System.out.println("You should enter numbers!");
            userTurn();
        } else if (Integer.parseInt(turn[0]) < 1 || Integer.parseInt(turn[0]) > 3 ||
                Integer.parseInt(turn[turn.length - 1]) < 1 || Integer.parseInt(turn[turn.length - 1]) > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            userTurn();
        } else if (field[Integer.parseInt(turn[0]) - 1][Integer.parseInt(turn[turn.length - 1]) - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            userTurn();
        } else {
            int i = Integer.parseInt(turn[0]) - 1;
            int j = Integer.parseInt(turn[turn.length - 1]) - 1;
            field[i][j] = turnX ? 'X' : 'O';
        }
    }

    public static void easyTurn() {
        System.out.println("Making move level \"easy\"");
        int i;
        int j;
        while (true) {
            i = random.nextInt(3);
            j = random.nextInt(3);
            if (field[i][j] == ' ') {
                field[i][j] = turnX ? 'X' : 'O';
                break;
            }
        }
    }

    public static void mediumTurn() {
        System.out.println("Making move level \"medium\"");
        char playerCell = turnX ? 'X' : 'O';
        char enemyCell = turnX ? 'O' : 'X';
        if (cell_0_0(playerCell)) {
            field[0][0] = playerCell;
        } else if (cell_0_1(playerCell)) {
            field[0][1] = playerCell;
        } else if (cell_0_2(playerCell)) {
            field[0][2] = playerCell;
        } else if (cell_1_0(playerCell)) {
            field[1][0] = playerCell;
        } else if (cell_1_1(playerCell)) {
            field[1][1] = playerCell;
        } else if (cell_1_2(playerCell)) {
            field[1][2] = playerCell;
        } else if (cell_2_0(playerCell)) {
            field[2][0] = playerCell;
        } else if (cell_2_1(playerCell)) {
            field[2][1] = playerCell;
        } else if (cell_2_2(playerCell)) {
            field[2][2] = playerCell;
        } else if (cell_0_0(enemyCell)) {
            field[0][0] = playerCell;
        } else if (cell_0_1(enemyCell)) {
            field[0][1] = playerCell;
        } else if (cell_0_2(enemyCell)) {
            field[0][2] = playerCell;
        } else if (cell_1_0(enemyCell)) {
            field[1][0] = playerCell;
        } else if (cell_1_1(enemyCell)) {
            field[1][1] = playerCell;
        } else if (cell_1_2(enemyCell)) {
            field[1][2] = playerCell;
        } else if (cell_2_0(enemyCell)) {
            field[2][0] = playerCell;
        } else if (cell_2_1(enemyCell)) {
            field[2][1] = playerCell;
        } else if (cell_2_2(enemyCell)) {
            field[2][2] = playerCell;
        } else {
            int i;
            int j;
            while (true) {
                i = random.nextInt(3);
                j = random.nextInt(3);
                if (field[i][j] == ' ') {
                    field[i][j] = playerCell;
                    break;
                }
            }
        }
    }

    public static boolean cell_0_0(char cell) {
        return field[0][0] == ' ' &&
                (field[0][1] == cell && field[0][2] == cell ||
                field[1][0] == cell && field[2][0] == cell ||
                field[1][1] == cell && field[2][2] == cell);
    }

    public static boolean cell_0_1(char cell) {
        return field[0][1] == ' ' &&
                (field[0][0] == cell && field[0][2] == cell ||
                field[1][1] == cell && field[2][1] == cell);
    }

    public static boolean cell_0_2(char cell) {
        return field[0][2] == ' ' &&
                (field[0][1] == cell && field[0][0] == cell ||
                field[1][2] == cell && field[2][2] == cell ||
                field[1][1] == cell && field[2][0] == cell);
    }

    public static boolean cell_1_0(char cell) {
        return field[1][0] == ' ' &&
                (field[0][0] == cell && field[2][0] == cell ||
                field[1][1] == cell && field[1][2] == cell);
    }

    public static boolean cell_1_1(char cell) {
        return field[1][1] == ' ' &&
                (field[0][0] == cell && field[2][2] == cell ||
                field[1][0] == cell && field[1][2] == cell ||
                field[0][1] == cell && field[0][2] == cell ||
                field[2][0] == cell && field[0][2] == cell);
    }

    public static boolean cell_1_2(char cell) {
        return field[1][2] == ' ' &&
                (field[0][2] == cell && field[2][2] == cell ||
                field[1][0] == cell && field[1][1] == cell);
    }

    public static boolean cell_2_0(char cell) {
        return field[2][0] == ' ' &&
                (field[0][0] == cell && field[1][0] == cell ||
                field[1][1] == cell && field[0][2] == cell ||
                field[2][1] == cell && field[2][2] == cell);
    }

    public static boolean cell_2_1(char cell) {
        return field[2][1] == ' ' &&
                (field[0][1] == cell && field[1][1] == cell ||
                field[2][0] == cell && field[2][2] == cell);
    }

    public static boolean cell_2_2(char cell) {
        return field[2][2] == ' ' &&
                (field[0][2] == cell && field[1][2] == cell ||
                field[0][0] == cell && field[1][1] == cell ||
                field[2][0] == cell && field[2][1] == cell);
    }

    public static void hardTurn() {
        System.out.println("Making move level \"hard\"");
        switch (round / 2) {
            case 1:
                hard1();
                break;
            case 2:
                hard2();
                break;
            case 3:
                hard3();
                break;
            case 4:
                hard4();
                break;
            default:
                field[1][1] = 'X';
        }
    }

    public static void hard1() {
        if (turnX) {
            if (field[0][0] == 'O' || field[2][2] == 'O') {
                field[0][2] = 'X';
            } else if (field[0][2] == 'O' || field[2][0] == 'O' || field[0][1] == 'O' || field[1][0] == 'O') {
                field[0][0] = 'X';
            } else if (field[1][2] == 'O' || field[2][1] == 'O') {
                field[2][0] = 'X';
            }
        } else {
            if (field[0][0] == 'X' || field[0][2] == 'X' || field[2][0] == 'X' || field[2][2] == 'X') {
                field[1][1] = 'O';
            } else if (field[0][1] == 'X' || field[1][0] == 'X' || field[1][1] == 'X') {
                field[0][0] = 'O';
            } else if (field[1][2] == 'X' || field[2][1] == 'X') {
                field[2][2] = 'O';
            }
        }
    }

    public static void hard2() {
        if (turnX) {
            if (cell_0_0('X')) {
                field[0][0] = 'X';
            } else if (cell_2_0('X')) {
                field[2][0] = 'X';
            } else if (cell_2_2('X')) {
                field[2][2] = 'X';
            } else if (cell_1_2('O')) {
                field[1][2] = 'X';
            } else if (cell_2_1('O')) {
                field[2][1] = 'X';
            } else if (cell_1_0('O')) {
                field[1][0] = 'X';
            }
        } else {
            if (cell_0_0('X')) {
                field[0][0] = 'O';
            } else if (cell_0_1('X')) {
                field[0][1] = 'O';
            } else if (cell_0_2('X')) {
                field[0][2] = 'O';
            } else if (cell_1_0('X')) {
                field[1][0] = 'O';
            } else if (cell_1_1('X')) {
                field[1][1] = 'O';
            } else if (cell_1_2('X')) {
                field[1][2] = 'O';
            } else if (cell_2_0('X')) {
                field[2][0] = 'O';
            } else if (cell_2_1('X')) {
                field[2][1] = 'O';
            } else if (cell_2_2('X')) {
                field[2][2] = 'O';
            } else if (field[1][1] == ' ') {
                field[1][1] = 'O';
            } else if (field[0][0] == ' ') {
                field[0][0] = 'O';
            } else if (field[0][2] == ' ') {
                field[0][2] = 'O';
            }
        }
    }

    public static void hard3() {
        if (turnX) {
            if (cell_0_1('X')) {
                field[0][1] = 'X';
            } else if (cell_1_0('X')) {
                field[1][0] = 'X';
            } else if (cell_1_2('X')) {
                field[1][2] = 'X';
            } else if (field[0][1] == ' ') {
                field[0][1] = 'X';
            } else if (field[1][0] == ' ') {
                field[1][0] = 'X';
            }
        } else {
            if (cell_0_0('O')) {
                field[0][0] = 'O';
            } else if (cell_0_1('O')) {
                field[0][1] = 'O';
            } else if (cell_0_2('O')) {
                field[0][2] = 'O';
            } else if (cell_1_0('O')) {
                field[1][0] = 'O';
            } else if (cell_1_2('O')) {
                field[1][2] = 'O';
            } else if (cell_2_0('O')) {
                field[2][0] = 'O';
            } else if (cell_2_1('O')) {
                field[2][1] = 'O';
            } else if (cell_2_2('O')) {
                field[2][2] = 'O';
            } else if (cell_0_0('X')) {
                field[0][0] = 'O';
            } else if (cell_0_1('X')) {
                field[0][1] = 'O';
            } else if (cell_0_2('X')) {
                field[0][2] = 'O';
            } else if (cell_1_0('X')) {
                field[1][0] = 'O';
            } else if (cell_1_2('X')) {
                field[1][2] = 'O';
            } else if (cell_2_0('X')) {
                field[2][0] = 'O';
            } else if (cell_2_1('X')) {
                field[2][1] = 'O';
            } else if (cell_2_2('X')) {
                field[2][2] = 'O';
            } else if (field[0][0] == ' ') {
                field[0][0] = 'O';
            } else if (field[0][2] == ' ') {
                field[0][2] = 'O';
            } else if (field[2][0] == ' ') {
                field[2][0] = 'O';
            } else if (field[2][2] == ' ') {
                field[2][2] = 'O';
            }
        }
    }

    public static void hard4() {
        if (turnX) {
            if (cell_1_2('X')) {
                field[1][2] = 'X';
            } else if (cell_2_1('X')) {
                field[2][1] = 'X';
            } else if (field[0][0] == ' ') {
                field[0][0] = 'X';
            } else if (field[0][2] == ' ') {
                field[0][2] = 'X';
            } else if (field[2][0] == ' ') {
                field[2][0] = 'X';
            } else if (field[2][2] == ' ') {
                field[2][2] = 'X';
            }
        } else {
            if (cell_0_0('O')) {
                field[0][0] = 'O';
            } else if (cell_0_1('O')) {
                field[0][1] = 'O';
            } else if (cell_0_2('O')) {
                field[0][2] = 'O';
            } else if (cell_1_0('O')) {
                field[1][0] = 'O';
            } else if (cell_1_2('O')) {
                field[1][2] = 'O';
            } else if (cell_2_0('O')) {
                field[2][0] = 'O';
            } else if (cell_2_1('O')) {
                field[2][1] = 'O';
            } else if (cell_2_2('O')) {
                field[2][2] = 'O';
            } else if (cell_0_0('X')) {
                field[0][0] = 'O';
            } else if (cell_0_1('X')) {
                field[0][1] = 'O';
            } else if (cell_0_2('X')) {
                field[0][2] = 'O';
            } else if (cell_1_0('X')) {
                field[1][0] = 'O';
            } else if (cell_1_2('X')) {
                field[1][2] = 'O';
            } else if (cell_2_0('X')) {
                field[2][0] = 'O';
            } else if (cell_2_1('X')) {
                field[2][1] = 'O';
            } else if (cell_2_2('X')) {
                field[2][2] = 'O';
            } else if (field[0][0] == ' ') {
                field[0][0] = 'O';
            } else if (field[0][2] == ' ') {
                field[0][2] = 'O';
            } else if (field[2][0] == ' ') {
                field[2][0] = 'O';
            } else if (field[2][2] == ' ') {
                field[2][2] = 'O';
            } else if (field[0][1] == ' ') {
                field[0][1] = 'O';
            } else if (field[1][0] == ' ') {
                field[1][0] = 'O';
            } else if (field[1][2] == ' ') {
                field[1][2] = 'O';
            } else if (field[2][1] == ' ') {
                field[2][1] = 'O';
            }
        }
    }

    public static void checkResult() {
        char playerCell = turnX ? 'X' : 'O';
        boolean win = field[0][0] == playerCell && field[0][1] == playerCell && field[0][2] == playerCell ||
                field[1][0] == playerCell && field[1][1] == playerCell && field[1][2] == playerCell ||
                field[2][0] == playerCell && field[2][1] == playerCell && field[2][2] == playerCell ||
                field[0][0] == playerCell && field[1][0] == playerCell && field[2][0] == playerCell ||
                field[0][1] == playerCell && field[1][1] == playerCell && field[2][1] == playerCell ||
                field[0][2] == playerCell && field[1][2] == playerCell && field[2][2] == playerCell ||
                field[0][0] == playerCell && field[1][1] == playerCell && field[2][2] == playerCell ||
                field[0][2] == playerCell && field[1][1] == playerCell && field[2][0] == playerCell;
        if (win) {
            System.out.println(playerCell + " wins");
        } else {
            round++;
            if (round < 9) {
                turnX = !turnX;
                makeTurns();
            } else {
                System.out.println("Draw");
            }
        }
    }
}