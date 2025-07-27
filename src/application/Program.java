package application;

import domain.Player;
import domain.domainEnums.Type;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        String type1;

        System.out.print("Player 1 name: ");
        String nameplayer1 = sc.nextLine();

        while (true){
            System.out.print(" please choose one of these ( X / O) : ");
            type1= sc.nextLine().toUpperCase();
            if(type1.equals("X") || type1.equals("O") ){
                break;
            }
            else {
                System.out.println("You need to choose only between ( X ) and ( O )");
            }
        }
        System.out.print("Player 2 name: ");
        String nameplayer2 = sc.nextLine();
        String type2;
        if (type1.equals("X")) {
            type2 = "O";
        } else {
            type2 = "X";
        }

        Player player1 = new Player(nameplayer1, Type.valueOf(type1));
        Player player2 = new Player(nameplayer2, Type.valueOf(type2));
        GameMatch gameMatch = new GameMatch(player1, player2);
        gameMatch.startGame(sc);
    }
}
