
package functionLayer.entity;


public class Customer {
 
 private int ID;
 private String email;
 private String password;
 private String name;
 private String lastname;
 private String phoneNumber;

    public Customer(int ID, String email, String password, String name, String lastname, String phoneNumber) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int ID, String email, String name, String lastname, String phoneNumber) {
        this.ID = ID;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }
    
    public Customer(String email, String password, String name, String lastname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }
    
    public int getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }
    
     public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
