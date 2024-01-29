/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecthms;

/**
 *
 * @author MM
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import static projecthms.Admin.services;
public class Reservation implements Serializable{
       int reservationID;
       LocalDate checkInDate;
       LocalDate checkOutDate;
       static double totalRevenue;
       String receptionistName;
       String roomID;
       User user;
       String guest;
       String  service;
       int ReceptionistID;
       double Totalprice;
       int rating;
  
    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, int reservationID,String roomID, String guestid, String serviceid, String receptionistName,int ReceptionistID,double Totalprice, int rating) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationID = reservationID;
        this.roomID = roomID;
        this.guest = guestid;
        this.service = serviceid;
        this.receptionistName = receptionistName;
        this.ReceptionistID =ReceptionistID;
        this.Totalprice = Totalprice;
        this.rating =rating;

    }

    public int getReceptionistID() {
        return ReceptionistID;
    }

    public void setReceptionistID(int ReceptionistID) {
        this.ReceptionistID = ReceptionistID;
    }

    public double getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(double Totalprice) {
        this.Totalprice = Totalprice;
    }

    public int getReservationID() {
        return reservationID;
    }

    public String getReceptionistName() {
        return receptionistName;
    }

    public void setReceptionistName(String receptionistName) {
        this.receptionistName = receptionistName;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
public double calculateReservationPrice(String roomCategory,String mainservice, LocalDate checkInDate, LocalDate checkOutDate) {
    
    double roomPrice = getRoomCategoryPrice(roomCategory);
    double serviceprice = getServicePrice(mainservice);
    long numberOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    double totalPrice = (serviceprice + roomPrice) * numberOfDays;
    
    return totalPrice;
}
public double getRoomCategoryPrice(String roomCategory) {

    if ("Single".equalsIgnoreCase(roomCategory)) {
        return 100.0;  
    } else if ("Presidential Suite".equalsIgnoreCase(roomCategory)) {
        return 300.0;  
    } else if ("Deluxe".equalsIgnoreCase(roomCategory)) {
        return 150.0; 
    } else if ("King".equalsIgnoreCase(roomCategory)) {
        return 200.0;  
    } else if ("Triple".equalsIgnoreCase(roomCategory)) {
        return 120.0;  
    } else if ("Apartment".equalsIgnoreCase(roomCategory)) {
        return 250.0;  
    } else {
        
        return 0.0;    
    }
}
//public double getServicsePrice(String service) {
//   
//    for(Service servicee :services){
//        if (service.equalsIgnoreCase(servicee.Service)) {
//             return servicee.ServicePrice;
//        }
//
//    }
//    if ("NoService".equalsIgnoreCase(service)) {
//        return 0.0; 
//    }
//    else{
//        return 0.0;  
//    }
//}
public double getServicePrice(String service) {
   
    for(Service servicee :services){
        if (service.equalsIgnoreCase(servicee.Service)) {
             return servicee.ServicePrice;
        }

    }
    if ("NoService".equalsIgnoreCase(service)) {
        return 0.0; 
    }
    else{
        return 0.0;  
    }
}

    public int getReservationId() {
        return reservationID;
    }
    public Room getRoomnum() throws ClassNotFoundException{
        ArrayList<Room> getRoomNum=new ArrayList<>();
        ArrayList<Room> getRoomNumR=new ArrayList<>();
        try{ FileInputStream SRoomNum=new FileInputStream("Reservations.dat");
             ObjectInputStream SSRoomNum=new ObjectInputStream(SRoomNum);
             getRoomNum=(ArrayList<Room>) SSRoomNum.readObject();
             FileInputStream SRoomNumR=new FileInputStream("Rooms.dat");
             ObjectInputStream SSRoomNumR=new ObjectInputStream(SRoomNumR);
             getRoomNumR=(ArrayList<Room>) SSRoomNumR.readObject();
             for(Room room:getRoomNumR){
                 for(Room rroom:getRoomNum){
                     if(room.RoomNum!=rroom.RoomNum){
                         return room;
                     }
                 }
             }
        }catch(IOException e){
            System.out.println(e);
        }
   
           return null;
    }


    public LocalDate getCheckInDate() {
        return checkInDate;
    }


    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }


    public double getTotalRevenue() {
        return totalRevenue;
    }



    public String getRoomID() {
        return roomID;
    }

    void setRating(int rating) {
        
    }

    public int getRating() {
        return rating;
    }

   

  
}


