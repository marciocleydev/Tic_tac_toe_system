package application;

import domain.Player;
import domain.domainEnums.Type;
import ui.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<GameMatch> matches = new ArrayList<>();
        int matchCount = 0;
        boolean changePlayers = true;
        Player player1 = null;
        Player player2 = null;

        while (true) {
            if(changePlayers){
                GameUI.clearScreen();
                player1 = selectPlayers(sc, 1);
                player2 = selectPlayers(sc, 2);

                if (player1.getPlayertype().equals(Type.X)) {
                    player2.setPlayertype(Type.O);
                } else {
                    player2.setPlayertype(Type.X);
                }
            }

            matches.add(matchCount,new GameMatch(player1, player2));
            matches.get(matchCount).startGame(sc);

            Character playMore;
            do {
                System.out.print("Do you want to continue playing: [ y / n ]: ");
                playMore = sc.next().toUpperCase().charAt(0);
                sc.nextLine();
            } while (!hasYesOrNo(playMore));

            Character wantChangeNames;
            if (playMore.equals('Y')) {
                do {
                    System.out.print("Do you want to change the names: [ y / n ]");
                    wantChangeNames = sc.next().toUpperCase().charAt(0);
                    sc.nextLine();
                }while (!hasYesOrNo(wantChangeNames));

                matches.remove((matchCount ));
                changePlayers = wantChangeNames.equals('Y');

            } else {
                break;
            }
        }
        sc.close();
    }

    public static Player selectPlayers(Scanner sc , int number){
        String type;
        System.out.print("\nPlayer " + number + " name: ");
        String nameplayer = sc.nextLine();

        if (number == 1){
            while (true) {
                System.out.print(" please choose one of these ( X / O) : ");
                type = sc.nextLine().toUpperCase();
                if (type.equals("X") || type.equals("O")) {
                    break;
                } else {
                    System.out.println("You need to choose only between ( X ) and ( O )");
                }
            }
        }
        else {
            type = "X";
        }
       return   new Player(nameplayer, Type.valueOf(type));
    }
    public static boolean hasYesOrNo(Character c){
        if (c.equals('Y') || c.equals('N')){
            return true;
        }
        else {
            System.out.println("                  You need to choose only between (y | n)");
            return false;
        }
    }

}
