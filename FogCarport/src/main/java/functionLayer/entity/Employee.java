/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.entity;

/**
 * This entity is used to handle all Employee information.
 * @author Snøvsen
 */
public class Employee {
    private String username;
    private String password;
    private String role;
    private int userID;

    public Employee(String username, String password, String role, int userID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getUserID() {
        return userID;
    }
}
