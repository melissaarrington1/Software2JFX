package sample.model;

/**
 * Model class for a Login user
 */
public class User {
    private int userId;
    private String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * gets user id
     * @return Returns user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * sets user id
     * @param userId user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * get user name
     * @return Returns username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets user name
     * @param userName username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return userName;
    }
}
