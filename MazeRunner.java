import java.util.Scanner;

public class MazeRunner {
    static Maze myMap = new Maze();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        intro();
        int count = 0;
        while (!myMap.didIWin()) {
            String solveMaze = userMove();
            Move(solveMaze);
            navigatePit(solveMaze);
            myMap.printMap();
            myMap.isThereAPit(solveMaze);
            count += 1;
            movesMessage(count);
            if (count >= 100) {
                break;
            }
        }
    }

    public static void intro() {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position: ");
        myMap.printMap();
    }

    public static void navigatePit(String solveMaze) {
        System.out.print("is there a pit ahead?: ");
        boolean b = myMap.isThereAPit(solveMaze);
        System.out.println(b);
        if (b) {
            System.out.print("Watch out! There's a pit ahead, jump it? ('yes' or 'no'): ");
            String entry = input.next();
            while (!(entry.equalsIgnoreCase("Yes") || entry.equalsIgnoreCase("No"))) {
                System.out.println("Not valid!!, Please enter the specified character ('yes' or 'no'): ");
                entry = input.next();
            }
            if (entry.equalsIgnoreCase("yes")) {
                myMap.jumpOverPit(solveMaze);
            }
            else if (entry.equalsIgnoreCase("no")) {
                solveMaze = userMove();
                Move(solveMaze);
            }
        }
        else {
            System.out.println("There are no pit ahead!");
        }
    }


    public static String userMove() {
        System.out.print("Where would you like move? (R, L, U, D): ");
        char entry = input.next().charAt(0);
        String move = "";
        move = move + entry;
        move = move.toUpperCase();
        System.out.println(move);
        while (!(move.equals("R") || move.equals("L") || move.equals("U") || move.equals("D"))) {
            System.out.println("Not valid!!, Please enter the specified character (R, L, U, D)");
            System.out.print("Where would you like move? (R, L, U, D): ");
            entry = input.next().charAt(0);
            move = "" + entry;
            move = move.toUpperCase();
        }
        return move;
    }

    public static void Move(String move) {
        if (move.equals("R") && myMap.canIMoveRight()) {
            myMap.moveRight();
        }
        else if (move.equals("L") && myMap.canIMoveLeft()) {
            myMap.moveLeft();
        }
        else if (move.equals("U") && myMap.canIMoveUp()) {
            myMap.moveUp();
        }
        else if (move.equals("D") && myMap.canIMoveDown()) {
            myMap.moveDown();
        }
        else {
            System.out.println("Sorry, you've hit a wall");
        }
    }


    public static void movesMessage(int moves) {
        if (moves == 50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        }
        else if (moves == 75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        }
        else if (moves == 90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        }
        else if (moves == 100) {
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
        }
    }
}

