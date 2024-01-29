///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package projecthms;

/**
 *
 * @author MM
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projecthms.Admin.admins;
import static projecthms.Admin.guests;
import static projecthms.Admin.receptionists;
import static projecthms.Admin.rooms;
import static projecthms.Admin.services;
import static projecthms.Admin.usersID;


public class Receptionist extends User implements Serializable{
    //Service service;
    static ArrayList<Room> Checkrooms=new ArrayList<>();
    static ArrayList<Service> Checkservice=new ArrayList<>();
    static ArrayList<User> CheckALLguests=new ArrayList<>();
    static int count=0;
    static ArrayList<Reservation> reservations=new ArrayList<>();
    static int reservationcount=0;
   static ArrayList<Integer> reservationsID=new ArrayList<>();
   
 public Receptionist(int UserId, String username, String Department, String Password) {
        super(UserId, username, Department, Password);
    }
     @Override
     public boolean LogIN(String username, String password) throws ClassNotFoundException{
        ArrayList<Receptionist> logarr=new ArrayList<>();
        try{
            FileInputStream LOg =new FileInputStream("Receptionistsgui.dat");
        ObjectInputStream LLog=new ObjectInputStream(LOg);
        logarr=(ArrayList<Receptionist>) LLog.readObject();
        for(Receptionist receptionist:logarr){
            if(username.equals(receptionist.username) && password.equals(receptionist.Password)){
            System.out.println("Welcome Back");
            
            return true;
        }
        }
        }catch(IOException e){
            System.out.println(e);
        }
            System.out.println("The Username Or Password Is Incorrect");
            //admin1.LogIN( username,  password);
            return false;
    }
     public void CreateReservation( Reservation reservation){
  
      reservations.add(reservation);
         System.out.println(reservationsID.size() + "first");
      reservationsID.add(reservation.reservationID);
      System.out.println(reservationsID.size() + "after");
     }

public void CancleReservation(int resid) {
    Iterator<Reservation> iterator = reservations.iterator();

    while (iterator.hasNext()) {
        Reservation reservation = iterator.next();
        if (reservation.getReservationID()==resid) {
            iterator.remove();
        }
    }
//    Iterator<Integer> iteratorr = reservationsID.iterator();
//
//    while (iteratorr.hasNext()) {
//        Integer reservationId = iteratorr.next();
//        if (reservationId == resid) {
//            iteratorr.remove();
//        }
//    }

}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

public ArrayList SearchRoom(String searchCategory) throws ClassNotFoundException{
        ArrayList <String> SCategory= new ArrayList<>();

        for(Room room:rooms){
            if(room.Category.equals(searchCategory)){
                SCategory.add(room.RoomNum);
            }
        }

       return SCategory;
    }
 
        

   public ArrayList<Room> searchAvailablecategoryofRooms(LocalDate desiredCheckInDate, LocalDate desiredCheckOutDate, String categ) {
   
       ArrayList<Room> tempAvailableRooms = new ArrayList<>(); 
    for (Room room : rooms) {
        boolean isAvailable = true;
        if (room.getCategory().equals(categ)) {
            for (Reservation reservation : reservations) {   
                if (reservation.getRoomID().equals(room.getRoomNum())) {
                    if (!(desiredCheckOutDate.isBefore(reservation.getCheckInDate()) ||
                            desiredCheckInDate.isAfter(reservation.getCheckOutDate()))) {
                        isAvailable = false; 
                        break;
                    }
                }
            }

            if (isAvailable) {
                tempAvailableRooms.add(room);
            }
        }
    }
    return tempAvailableRooms;
}
   public boolean searchAvailableRoomnum(LocalDate desiredCheckInDate, LocalDate desiredCheckOutDate, String roomnum) {
    for (Room room : rooms) {
        if (room.getRoomNum().equals(roomnum)) {
            boolean isAvailable = true;

            for (Reservation reservation : reservations) {
                if (reservation.getRoomID().equals(roomnum)) {
                    if (!(desiredCheckOutDate.isBefore(reservation.getCheckInDate()) ||
                            desiredCheckInDate.isAfter(reservation.getCheckOutDate()))) {
                        isAvailable = false; // Room is not available
                        break;
                    }
                }
            }

            return isAvailable; 
        }
    }

    return false; 
}
        
         static boolean saveAll(){
            try{   //FileOutputStream r = new FileOutputStream("Roomsgui.dat");
//                    FileOutputStream g = new FileOutputStream("Guestsgui.dat");
//                    FileOutputStream a = new FileOutputStream("Adminsgui.dat");
//                    FileOutputStream s = new FileOutputStream("Servicesgui.dat");
                    FileOutputStream ROUT=new FileOutputStream("Receptionistsgui.dat");
                     FileOutputStream ReOUT=new FileOutputStream("reservationsgui.dat");
                     FileOutputStream ReIDOUT=new FileOutputStream("reservationsIDgui.dat");
//                    ObjectOutputStream rout = new ObjectOutputStream(r);
//                    ObjectOutputStream gout = new ObjectOutputStream(g);
//                    ObjectOutputStream aout = new ObjectOutputStream(a);
//                    ObjectOutputStream sout = new ObjectOutputStream(s);
                    ObjectOutputStream Rout=new ObjectOutputStream(ROUT);
                    ObjectOutputStream Reout=new ObjectOutputStream(ReOUT);
                    ObjectOutputStream ReIDout=new ObjectOutputStream(ReIDOUT);
//                    rout.writeObject(rooms); 
//                    gout.writeObject(guests); 
//                    aout.writeObject(admins);
//                    sout.writeObject(services);
                    Rout.writeObject(receptionists);
                     Reout.writeObject(reservations);
                     ReIDout.writeObject(reservationsID);
                    System.out.println("Data Saved Successfully");
//                    r.close();
//                    g.close();
//                    a.close();
//                    s.close();
//                    ROUT.close();
//                    ROUT.close();
                    ReOUT.close();
                    ReIDOUT.close();
                    return true;
        } catch (IOException e) {
                    System.out.println("Error adding arraylist to file: " + e.getMessage());
                    System.out.println(e);
                    return false;
        }
        }
 public void Loaddata()throws ClassNotFoundException{
        try{
       //FileInputStream R=new FileInputStream("Roomsgui.dat");
//       FileInputStream S=new FileInputStream("Servicesgui.dat");
//       FileInputStream A=new FileInputStream("Adminsgui.dat");
//       FileInputStream G=new FileInputStream("Guestsgui.dat");
       FileInputStream RS=new FileInputStream("Receptionistsgui.dat");
       FileInputStream ReS=new FileInputStream("reservationsgui.dat");
       FileInputStream ReIDS=new FileInputStream("reservationsIDgui.dat");
       //ObjectInputStream RIN=new ObjectInputStream(R);
//       ObjectInputStream SIN=new ObjectInputStream(S);
//       ObjectInputStream AIN=new ObjectInputStream(A);
//       ObjectInputStream GIN=new ObjectInputStream(G);
       ObjectInputStream RSIN=new ObjectInputStream(RS);
       ObjectInputStream ReSIN=new ObjectInputStream(ReS);
       ObjectInputStream ReIDSIN=new ObjectInputStream(ReIDS);
      // rooms=(ArrayList<Room>) RIN.readObject();
//       services=(ArrayList<Service>) SIN.readObject();
//       admins=(ArrayList<Admin>) AIN.readObject();
//       guests=(ArrayList<Guest>) GIN.readObject();
       receptionists=(ArrayList<Receptionist>) RSIN.readObject();
       reservations=(ArrayList<Reservation>) ReSIN.readObject();
       reservationsID=(ArrayList<Integer>) ReIDSIN.readObject();
            System.out.println("The Data Load Successfully");
            //R.close();
//            S.close();
//            A.close();
//            G.close();
            RS.close();
            ReS.close();
            ReIDS.close();
        }catch(IOException e){
            System.out.println("Error While Loading"+e.getMessage());
            System.out.println(e);
        }
   }
    
//     static boolean saveAll(){
//             try{   FileOutputStream r = new FileOutputStream("Roomsgui.dat");
//                    FileOutputStream g = new FileOutputStream("Guestsgui.dat");
//                    FileOutputStream a = new FileOutputStream("Adminsgui.dat");
//                    FileOutputStream s = new FileOutputStream("Servicesgui.dat");
//                    FileOutputStream ROUT=new FileOutputStream("Receptionistsgui.dat");
//                     FileOutputStream ReOUT=new FileOutputStream("reservationsgui.dat");
//                     FileOutputStream ReIDOUT=new FileOutputStream("reservationsIDgui.dat");
//                    ObjectOutputStream rout = new ObjectOutputStream(r);
//                    ObjectOutputStream gout = new ObjectOutputStream(g);
//                    ObjectOutputStream aout = new ObjectOutputStream(a);
//                    ObjectOutputStream sout = new ObjectOutputStream(s);
//                    ObjectOutputStream Rout=new ObjectOutputStream(ROUT);
//                    ObjectOutputStream Reout=new ObjectOutputStream(ReOUT);
//                    ObjectOutputStream ReIDout=new ObjectOutputStream(ReIDOUT);
//                    rout.writeObject(rooms); 
//                    gout.writeObject(guests); 
//                    aout.writeObject(admins);
//                    sout.writeObject(services);
//                    Rout.writeObject(receptionists);
//                     Reout.writeObject(reservations);
//                     ReIDout.writeObject(reservationsID);
//                    System.out.println("Data Saved Successfully");
//                    r.close();
//                    g.close();
//                    a.close();
//                    s.close();
//                    ROUT.close();
//                    ROUT.close();
//                    ReOUT.close();
//                    ReIDOUT.close();
//                    return true;
//        } catch (IOException e) {
//                    System.out.println("Error adding arraylist to file: " + e.getMessage());
//                    System.out.println(e);
//                    return false;
//        }
//        }
// public void Loaddata()throws ClassNotFoundException{
//        try{
//       FileInputStream R=new FileInputStream("Roomsgui.dat");
//       FileInputStream S=new FileInputStream("Servicesgui.dat");
//       FileInputStream A=new FileInputStream("Adminsgui.dat");
//       FileInputStream G=new FileInputStream("Guestsgui.dat");
//       FileInputStream RS=new FileInputStream("Receptionistsgui.dat");
//       FileInputStream ReS=new FileInputStream("reservationsgui.dat");
//       FileInputStream ReIDS=new FileInputStream("reservationsIDgui.dat");
//       ObjectInputStream RIN=new ObjectInputStream(R);
//       ObjectInputStream SIN=new ObjectInputStream(S);
//       ObjectInputStream AIN=new ObjectInputStream(A);
//       ObjectInputStream GIN=new ObjectInputStream(G);
//       ObjectInputStream RSIN=new ObjectInputStream(RS);
//       ObjectInputStream ReSIN=new ObjectInputStream(ReS);
//       ObjectInputStream ReIDSIN=new ObjectInputStream(ReIDS);
//       rooms=(ArrayList<Room>) RIN.readObject();
//       services=(ArrayList<Service>) SIN.readObject();
//       admins=(ArrayList<Admin>) AIN.readObject();
//       guests=(ArrayList<Guest>) GIN.readObject();
//       receptionists=(ArrayList<Receptionist>) RSIN.readObject();
//       reservations=(ArrayList<Reservation>) ReSIN.readObject();
//       reservationsID=(ArrayList<Integer>) ReIDSIN.readObject();
//            System.out.println("The Data Load Successfully");
//            R.close();
//            S.close();
//            A.close();
//            G.close();
//            RS.close();
//            ReS.close();
//            ReIDS.close();
//        }catch(IOException e){
//            System.out.println("Error While Loading"+e.getMessage());
//            System.out.println(e);
//        }
//   }

   @Override
    public void AddUser(User user)throws ClassNotFoundException{
        if(user instanceof Admin){
             for(int i =0; i<admins.size(); i++){
                if(admins.get(i).UserId==((Admin) user).UserId){
                    System.out.println("Admin already exists");
                    return;
                }
            }
            Admin newadmin=(Admin) user;
            admins.add(newadmin);
            usersID.add(newadmin.UserId);
            System.out.println("The Admin Added Successfully");
    }   else if (user instanceof Guest) {
            Guest newGuest = (Guest) user;
            for(int i =0; i<guests.size(); i++){
                if(guests.get(i).UserId==user.UserId){
                    System.out.println("Guest already exists");
                    return;
                }
            }
            guests.add(newGuest);
            usersID.add(newGuest.UserId);
            System.out.println("The Guest Added By Admin");
        }
        else if(user instanceof Receptionist) {
            Receptionist newReceptionist = (Receptionist) user;
            for(int i =0; i<receptionists.size(); i++){
                if(receptionists.get(i).UserId==user.UserId){
                    System.out.println("Receptionist already exists");
                    return;
                }
            }
            receptionists.add(newReceptionist);
            usersID.add(newReceptionist.UserId);
            System.out.println("The Receptionist Added Successfully");
        }
    }
}
