
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.constant.ConstantDescs.NULL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import static projecthms.Admin.admins;
import static projecthms.Admin.guests;
import static projecthms.Admin.receptionists;
import static projecthms.Admin.rooms;
import static projecthms.Admin.services;
import static projecthms.Receptionist.reservations;
import static projecthms.Receptionist.reservationsID;

public class Guest extends User{
//ArrayList<Reservation> reservations;
   //static ArrayList<Guest> guests = new ArrayList<>();

    @Override
       public boolean LogIN(String username, String password) throws ClassNotFoundException{
        ArrayList<Guest> logGarr=new ArrayList<>();
        try{
            FileInputStream LOOGG =new FileInputStream("Guestsgui.dat");
        ObjectInputStream LLOGG=new ObjectInputStream(LOOGG);
        logGarr=(ArrayList<Guest>) LLOGG.readObject();
        for(Guest guest:logGarr){
            if(username.equals(guest.username) && password.equals(guest.Password)){
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

    // Constructor for Guest class
    public Guest(int UserId, String username, String Department, String Password) {
        super(UserId, username, Department, Password);
    }
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
    // Implement the abstract method from the superclass
    @Override
    public void AddUser(User user) throws ClassNotFoundException {
        System.out.println("Guest cannot add other users.");
    }
    static boolean saveAll(){
             try{   FileOutputStream r = new FileOutputStream("Roomsgui.dat");
                    FileOutputStream g = new FileOutputStream("Guestsgui.dat");
                    FileOutputStream a = new FileOutputStream("Adminsgui.dat");
                    FileOutputStream s = new FileOutputStream("Servicesgui.dat");
                    FileOutputStream ROUT=new FileOutputStream("Receptionistsgui.dat");
                     FileOutputStream ReOUT=new FileOutputStream("reservationsgui.dat");
                     FileOutputStream ReIDOUT=new FileOutputStream("reservationsIDgui.dat");
                    ObjectOutputStream rout = new ObjectOutputStream(r);
                    ObjectOutputStream gout = new ObjectOutputStream(g);
                    ObjectOutputStream aout = new ObjectOutputStream(a);
                    ObjectOutputStream sout = new ObjectOutputStream(s);
                    ObjectOutputStream Rout=new ObjectOutputStream(ROUT);
                    ObjectOutputStream Reout=new ObjectOutputStream(ReOUT);
                    ObjectOutputStream ReIDout=new ObjectOutputStream(ReIDOUT);
                    rout.writeObject(rooms); 
                    gout.writeObject(guests); 
                    aout.writeObject(admins);
                    sout.writeObject(services);
                    Rout.writeObject(receptionists);
                     Reout.writeObject(reservations);
                     ReIDout.writeObject(reservationsID);
                    System.out.println("Data Saved Successfully");
                    r.close();
                    g.close();
                    a.close();
                    s.close();
                    ROUT.close();
                    ROUT.close();
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
       FileInputStream R=new FileInputStream("Roomsgui.dat");
       FileInputStream S=new FileInputStream("Servicesgui.dat");
       FileInputStream A=new FileInputStream("Adminsgui.dat");
       FileInputStream G=new FileInputStream("Guestsgui.dat");
       FileInputStream RS=new FileInputStream("Receptionistsgui.dat");
       FileInputStream ReS=new FileInputStream("reservationsgui.dat");
       FileInputStream ReIDS=new FileInputStream("reservationsIDgui.dat");
       ObjectInputStream RIN=new ObjectInputStream(R);
       ObjectInputStream SIN=new ObjectInputStream(S);
       ObjectInputStream AIN=new ObjectInputStream(A);
       ObjectInputStream GIN=new ObjectInputStream(G);
       ObjectInputStream RSIN=new ObjectInputStream(RS);
       ObjectInputStream ReSIN=new ObjectInputStream(ReS);
       ObjectInputStream ReIDSIN=new ObjectInputStream(ReIDS);
       rooms=(ArrayList<Room>) RIN.readObject();
       services=(ArrayList<Service>) SIN.readObject();
       admins=(ArrayList<Admin>) AIN.readObject();
       guests=(ArrayList<Guest>) GIN.readObject();
       receptionists=(ArrayList<Receptionist>) RSIN.readObject();
       reservations=(ArrayList<Reservation>) ReSIN.readObject();
       reservationsID=(ArrayList<Integer>) ReIDSIN.readObject();
            System.out.println("The Data Load Successfully");
            R.close();
            S.close();
            A.close();
            G.close();
            RS.close();
            ReS.close();
            ReIDS.close();
        }catch(IOException e){
            System.out.println("Error While Loading"+e.getMessage());
            System.out.println(e);
        }
   }
//    public void Loaddata()throws ClassNotFoundException{
//        try{
//       FileInputStream R=new FileInputStream("Roomsgui.dat");
//       FileInputStream S=new FileInputStream("Servicesgui.dat");
//       FileInputStream A=new FileInputStream("Adminsgui.dat");
//       FileInputStream G=new FileInputStream("Guestsgui.dat");
//       FileInputStream RS=new FileInputStream("Receptionistsgui.dat");
//       ObjectInputStream RIN=new ObjectInputStream(R);
//       ObjectInputStream SIN=new ObjectInputStream(S);
//       ObjectInputStream AIN=new ObjectInputStream(A);
//       ObjectInputStream GIN=new ObjectInputStream(G);
//       ObjectInputStream RSIN=new ObjectInputStream(RS);
//       rooms=(ArrayList<Room>) RIN.readObject();
//       services=(ArrayList<Service>) SIN.readObject();
//       admins=(ArrayList<Admin>) AIN.readObject();
//       guests=(ArrayList<Guest>) GIN.readObject();
//       receptionists=(ArrayList<Receptionist>) RSIN.readObject();
//      
//            System.out.println("The Data Load Successfully");
//            R.close();
//            S.close();
//            A.close();
//            G.close();
//            RS.close();
//        }catch(IOException e){
//            System.out.println("Error While Loading"+e.getMessage());
//            System.out.println(e);
//        }
//   }
    public void viewReservationHistory() {
        System.out.println("Reservation History for Guest: " + getUsername());
        for (Reservation reservation : reservations) {
            System.out.println(reservation.toString());
        }
    }
    public void viewReservationDetails(int reservationId) {
        Reservation selectedReservation = findReservationById(reservationId);
        if (selectedReservation != null) {
            System.out.println("Details for Reservation ID " + reservationId + ":");
            System.out.println(selectedReservation.toString()); 
        } else {
            System.out.println("Reservation not found.");
        }
    }
    public void rateReservation(int reservationId, int rating) {
        Reservation selectedReservation = findReservationById(reservationId);
        if (selectedReservation != null) {
            selectedReservation.setRating(rating);
            System.out.println("Booking rated successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }
    private Reservation findReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID()==(reservationId)) {
                return reservation;
            }
        }
        return null;
    }
//    public void loadReservationData() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Reservationsgui.dat"))) {
//            ArrayList<Reservation> reservationData = (ArrayList<Reservation>) ois.readObject();
//            this.reservations.addAll(reservationData);
//            System.out.println("Reservation Data Loaded Successfully");
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error While Loading Reservation Data: " + e.getMessage());
//        }
//    }
    
    public void requestService(String s )
    {
        
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Services.dat",true))) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dataToWrite = super.UserId + "," + s + "," + currentDate; 
            outputStream.writeUTF(dataToWrite);
            
            System.out.println("Serviced equested successfully.");
        } catch (IOException e) {
            System.out.println("Error saving guest data: " + e.getMessage());
        }
    }
 
    
    
}

