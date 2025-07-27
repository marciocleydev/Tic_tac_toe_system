package ui;

import application.GameMatch;
import domain.Piece;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class GameUI {


    public static void printScreen(GameMatch gameMatch){
        int count = 0;
        int underScore = 0;
        Piece[][] picies = gameMatch.getBoard().getPicies();
        for (int i = 0; i < 3; i++){
            int barCount = 0;
            underScore ++;
            for (int j = 0; j < 3; j++){
                count++;
                barCount++;
                if (picies[i][j] != null){
                    System.out.print("  " +picies[i][j] + "  ");
                }
                else {
                    System.out.print("  " +count + "  ");
                }
                if (barCount < 3){
                    System.out.print("|");
                }
            }
            if (underScore < 3){
                System.out.println("\n----------------");
            }
        }
        if (!gameMatch.getDraw()){
            if (!gameMatch.isGameOver()){
                input(gameMatch);
            }
            else {
                winnerOutPut(gameMatch);
            }
        }
        else {
            draw();
        }
    }
    public static int selectPositonToMove(Scanner sc){
        System.out.print("Where do you want to play: ");
        return sc.nextInt();
    }
    private static void input(GameMatch gameMatch){
        System.out.println();
        System.out.println("Player 1:"+gameMatch.getPlayers().get(0));
        System.out.println("Player 2:"+gameMatch.getPlayers().get(1));
        System.out.println();
        System.out.println("Current Player: " + gameMatch.getCurrentPlayer());
    }
    private static void winnerOutPut(GameMatch gameMatch){
        System.out.println("\nCongratulations: ");
        System.out.println("Winner: " + gameMatch.getCurrentPlayer().getName() + "( "+ gameMatch.getCurrentPlayer().getPlayertype() + " )");
    }
    private static void draw(){
        System.out.println();
        System.out.println("\n * EMPATE *");
    }
    public static void clearScreen(){
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }
}
