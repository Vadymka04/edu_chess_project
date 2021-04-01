import java.util.ArrayList;
import java.util.HashMap;

public class ChessMain {
    public static void main(String[] args) {
//        Player whitePlayer = new Player("Beth Harmon", "beth.harmon@gmail.com", true, 2000, 20);
//        Player blackPlayer = new Player("Vasily Borgov", "vasily.borgov@yandex.ru", false, 2500, 45);
        ChessMain main = new ChessMain();
        ArrayList<Player> players = main.createPlayers();
        for(Player player: players){
            System.out.println(player);
        }
        HashMap<String, Piece> pieceHashMap = main.createdPieces();
        System.out.println(pieceHashMap);
        main.play(players, pieceHashMap);

//        System.out.println(whitePlayer);
//        System.out.println(blackPlayer);




//        try {
//            whitePlayer.setRank(4000);
//        }
//        catch (IllegalArgumentException err){
//            System.out.println("please update rank to valid");
//        }
    }

    public ArrayList<Player> createPlayers(){
        Player whitePlayer = new Player("Beth Harmon", "beth.harmon@gmail.com", true, 2000, 20);
        Player blackPlayer = new Player("Vasily Borgov", "vasily.borgov@yandex.ru", false, 2500, 45);
        ArrayList<Player> players = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);
        return players;
    }

    public HashMap<String, Piece> createdPieces(){
        King whiteKing = new King(new Spot("H", 7), "white_king", true);
        King blackKing = new King(new Spot("D", 8), "black_king", false);
        Rook whiteRook1 = new Rook(new Spot("A", 7), "white_rook1", true);
        Rook whiteRook2 = new Rook(new Spot("G", 7), "white_rook2", true);
        Knight blackKnight1 = new Knight(new Spot("D", 6), "blackKnight1", false);

        HashMap<String, Piece> pieceHashMap = new HashMap<>();
        pieceHashMap.put(whiteKing.getId(),whiteKing);
        pieceHashMap.put(blackKing.getId(),blackKing);
        pieceHashMap.put(whiteRook1.getId(),whiteRook1);
        pieceHashMap.put(whiteRook2.getId(),whiteRook2);
        pieceHashMap.put(blackKnight1.getId(),blackKnight1);
        return pieceHashMap;
    }

    public void play(ArrayList<Player> players, HashMap<String, Piece> hashMap){
        //move1
        try {
            players.get(0).movePiece(hashMap.get("white_rook1"), new Spot("A", 8));
        }catch (IllegalArgumentException error){
            System.out.println("The move is incorrect, please try again!");
        }
        players.get(1).movePiece(hashMap.get("blackKnight1"), new Spot("C", 8));
    }
}
