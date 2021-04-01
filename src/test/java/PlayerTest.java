import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class PlayerTest {

    private static ArrayList<Player> players;
    private static HashMap<String, Piece> pieceHashMap;

    @BeforeAll
    public static void setUp(){
        ChessMain main = new ChessMain();
        players = main.createPlayers();
        pieceHashMap = main.createdPieces();
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Running before each");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("Running after each");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("Running after all test");
    }

    @Test
    public void validMoveTest(){
        players.get(0).movePiece(pieceHashMap.get("white_king"), new Spot("h", 8));
        Assertions.assertEquals(8, pieceHashMap.get("white_king").getSpot().getY());
    }

    @Test
    public void invalidMovePieceColorTest(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            players.get(0).movePiece(pieceHashMap.get("black_king"), new Spot("h", 8));
        });
    }

    @Test
    public void invalidMoveBadSpotTest(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            players.get(0).movePiece(pieceHashMap.get("white_king"), new Spot("z", 8));
        });
    }

    @Test
    public void playerCreateSuccesTest(){
        Player whitePlayer = new Player("Beth Harmon", "beth.harmon@gmail.com", true, 2000, 20);
        Assertions.assertEquals("Beth Harmon", whitePlayer.getName());
        Assertions.assertEquals("beth.harmon@gmail.com", whitePlayer.getEmail());
        Assertions.assertTrue(whitePlayer.isWhite());
        Assertions.assertEquals(2000, whitePlayer.getRank());
        Assertions.assertEquals(20, whitePlayer.getAge());
    }

    @ParameterizedTest
    @MethodSource("nameError")
    public void playerCreationIncorrectNameTest(String name){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Player whitePlayer = new Player(name, "beth.harmon@gmail.com", true, 2000, 20);
        });
    }

    @ParameterizedTest
    @MethodSource("emailError")
    public void playerCreationIncorrectEmailTest(String email){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Player whitePlayer = new Player("Berth", email, true, 2000, 20);
        });
    }

     public static Stream<String> nameError(){
        return Stream.of("", "   ", null);
     }

    public static Stream<String> emailError(){
        return Stream.of("", "   ", null, "beth.harmongmail.com", "Berth", "123");
    }

}
