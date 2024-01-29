/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecthms;

/**
 *
 * @author DELL_PC
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Room implements Serializable{
    String RoomNum;
    String Category;
    public Room(String RoomNum, String Category) {
        this.RoomNum = RoomNum;
        this.Category = Category;
    }
    public String getRoomNum() {
        return RoomNum;
    }
    
    public String getCategory() {
        return Category;
    }

    @Override
    public String toString() {
        return  "Room Number : " + RoomNum + ", Category : " + Category ;
    }
}