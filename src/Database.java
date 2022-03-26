import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    private ArrayList<UserScore> levelOneList;
    private ArrayList<UserScore> levelTwoList;
    private ArrayList<UserScore> levelThreeList;

    public Database() {
        levelOneList = new ArrayList<UserScore>();
        levelTwoList = new ArrayList<UserScore>();
        levelThreeList = new ArrayList<UserScore>();

        try {
            //Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores", "root", "levi022428");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from moon_lander");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void insertDatabase(String userName, int gameLevel, int gameScore) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores", "root", "levi022428");
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO moon_lander (user_name, game_level, game_score) VALUES (?, ?, ?)";

            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, userName);
            pstatement.setInt(2, gameLevel);
            pstatement.setInt(3, gameScore);

            pstatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void retrieveDatabase() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores", "root", "levi022428");
            Statement statement = connection.createStatement();
            String sql = "SELECT * from moon_lander ORDER BY game_score DESC";
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs;
            rs = pstatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("user_name");
                int game_level = rs.getInt("game_level");
                int score = rs.getInt("game_score");

               if (game_level == 1) {
                    levelOneList.add(new UserScore(score, name));
                }
                if (game_level == 2) {
                    levelTwoList.add(new UserScore(score, name));
                }
                if (game_level == 3){
                    levelThreeList.add(new UserScore(score, name));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserScore> getLevelOneList() {
        return levelOneList;
    }

    public ArrayList<UserScore> getLevelTwoList() {
        return levelTwoList;
    }

    public ArrayList<UserScore> getLevelThreeList() {
        return levelThreeList;
    }
}


