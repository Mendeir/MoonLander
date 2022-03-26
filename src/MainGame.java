import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainGame {

    public static void main(String[] args) {

        Database newDatabase = new Database();
        //newDatabase.insertDatabase("lorene",1,69);
        //newDatabase.displayDatabase();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameWindow window = new GameWindow();
                window.createAndShowCanvas();
            }
        });
    }
}
