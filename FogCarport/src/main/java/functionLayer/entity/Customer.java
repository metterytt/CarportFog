
package functionLayer.entity;


public class Customer {
 
 private int ID;
 private String email;
  private String password;
 private String name;
 private String lastname;
 private String phoneNumber;
 private String role;

    public Customer(int ID, String email, String password, String name, String lastname, String phoneNumber, String role) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public String getRole() {
        return role;
    }
 
 
    
}
