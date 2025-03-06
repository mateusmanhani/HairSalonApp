package models;

public class User {
    protected Integer userId;
    protected String fullName;
    protected String username;
    protected String password;

    public User(Integer userId, String fullName, String username, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public User (Integer userId, String fullName){
        this.userId = userId;
        this. fullName = fullName;
    }
    // Getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}