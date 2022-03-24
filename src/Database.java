import java.sql.*;

public class Database {

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    public Database() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores", "root", "levi022428");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from moon_lander");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void insertDatabase (String userName, int gameLevel, int gameScore){
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores", "root", "levi022428");
                Statement statement = connection.createStatement();

                String sql = "INSERT INTO moon_lander (user_name, game_level, game_score) VALUES (?, ?, ?)";

                PreparedStatement pstatement = connection.prepareStatement(sql);
                pstatement.setString(1, userName);
                pstatement.setInt(2, gameLevel);
                pstatement.setInt(3, gameScore);

                pstatement.executeUpdate();


            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void displayDatabase (){

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores", "root", "levi022428");
                Statement statement = connection.createStatement();

                String sql = "SELECT * from moon_lander ORDER BY game_score DESC";
                PreparedStatement pstatement = connection.prepareStatement(sql);
                ResultSet rs;
                rs = pstatement.executeQuery();

                while (rs.next()) {
                     int id = rs.getInt("user_id");
                     String name = rs.getString("user_name");
                     int score = rs.getInt("game_score");

                     System.out.println("Name: " + name + "  " + "Score: " + score);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


