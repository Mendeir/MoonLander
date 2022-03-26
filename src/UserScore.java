public class UserScore {
    private int userScore;
    private String userName;

    public UserScore(int userScore, String userName) {
        this.userScore = userScore;
        this.userName = userName;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return this.userName + " " + this.userScore;
    }
}
