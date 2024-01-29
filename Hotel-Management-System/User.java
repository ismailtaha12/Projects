package projecthms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import static projecthms.Admin.admins;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL_PC
 */
public abstract class User implements Serializable{
    int UserId;
    String username;
    String Department;
    String Password;

    public User(int UserId, String username, String Department,String Password) {
        this.UserId = UserId;
        this.username = username;
        this.Department = Department;
        this.Password=Password;
    }
    public int getUserId(String username,String Password) {
        if(Department.equals("Admin")){
            for(Admin admin:admins){
            if(username.equals(admin.username)&&Password.equals(admin.Password)){
                return UserId;
            }
            }
        }
        return -1;
    }
//    public void loadusers()throws ClassNotFoundException, IOException{
//        try{
//               FileInputStream UU=new FileInputStream("Users.dat");
//               ObjectInputStream UUIN=new ObjectInputStream(UU);
//               users=(ArrayList<User>) UUIN.readObject();
//               System.out.println("Users Load Successfully");
//               UU.close();
//               UUIN.close();
//        }catch(FileNotFoundException exx){
//            System.out.println(exx);
//        }
//    }
    public String getUsername() {
        return username;
    }
    public String getDepartment() {
        return Department;
    }
    public String getPassword() {
        return Password;
    }
    public abstract void AddUser(User user)throws ClassNotFoundException;
    
    public abstract boolean LogIN(String username,String password)throws ClassNotFoundException;
    @Override
    public String toString() {
        return "Id : " + UserId + ", username : " + username + ", Department : " + Department ;
    }
    
}
