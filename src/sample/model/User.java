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
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * sets user id
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * get user name
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets user name
     * @param userName
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
