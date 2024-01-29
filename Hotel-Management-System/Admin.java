/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecthms;
//s
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
//import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.*;
import static java.lang.System.exit;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projecthms.Receptionist.reservations;
//import static javaapplication22.Server.connection;

/**
 *
 * @author DELL_PC
 */
public class Admin extends User implements Serializable{
    public Admin(int UserId, String username, String Department, String Password) {
        super(UserId, username, Department, Password);
    }
    //private int AID;
   static ArrayList<Room> rooms =new ArrayList<>();
   static ArrayList<Service> services=new ArrayList<>();
   static ArrayList<Guest> guests=new ArrayList<>();
   static ArrayList<Admin> admins=new ArrayList<>();
   static ArrayList<Receptionist> receptionists=new ArrayList<>();  
   static ArrayList<Integer> usersID=new ArrayList<>();
   static ArrayList<Admin>LogedIn=new ArrayList<>();
//   private static String SERVER_ADDRESS = "127.0.0.1";
//   private static final int SERVER_PORT = 8080;
   private static final long serialVersionUID = 4522128246106554370L;
    @Override
     public boolean LogIN(String username, String password) throws ClassNotFoundException{
        ArrayList<Admin> logarr=new ArrayList<>();
        try{
            FileInputStream LOg =new FileInputStream("Adminsgui.dat");
        ObjectInputStream LLog=new ObjectInputStream(LOg);
        logarr=(ArrayList<Admin>) LLog.readObject();
        for(Admin admin:logarr){
            if(username.equals(admin.username) && password.equals(admin.Password)){
            System.out.println("Welcome Back");
            return true;
        }
        }
        }catch(IOException e){
            System.out.println(e);
        }
            System.out.println("The Username Or Password Is Incorrect");
            return false;
    }
    public void Loaddata()throws ClassNotFoundException{
        try{
       FileInputStream R=new FileInputStream("Roomsgui.dat");
       FileInputStream S=new FileInputStream("Servicesgui.dat");
       FileInputStream A=new FileInputStream("Adminsgui.dat");
       FileInputStream G=new FileInputStream("Guestsgui.dat");
       FileInputStream RS=new FileInputStream("Receptionistsgui.dat");
       FileInputStream U=new FileInputStream("Users.dat");
       FileInputStream L=new FileInputStream("LoggedInAdmins.dat");
       
       ObjectInputStream RIN=new ObjectInputStream(R);
       ObjectInputStream SIN=new ObjectInputStream(S);
       ObjectInputStream AIN=new ObjectInputStream(A);
       ObjectInputStream GIN=new ObjectInputStream(G);
       ObjectInputStream RSIN=new ObjectInputStream(RS);
       ObjectInputStream UIN=new ObjectInputStream(U);
       ObjectInputStream LIN=new ObjectInputStream(L);
       rooms=(ArrayList<Room>) RIN.readObject();
       services=(ArrayList<Service>) SIN.readObject();
       admins=(ArrayList<Admin>) AIN.readObject();
       guests=(ArrayList<Guest>) GIN.readObject();
       receptionists=(ArrayList<Receptionist>) RSIN.readObject();
       usersID=(ArrayList<Integer>) UIN.readObject();
       LogedIn=(ArrayList<Admin>) LIN.readObject();
            System.out.println("The Data Load Successfully");
            R.close();
            RIN.close();
            S.close();
            SIN.close();
            A.close();
            AIN.close();
            G.close();
            GIN.close();
            RS.close();
            RSIN.close();
            U.close();
            UIN.close();
            L.close();
            LIN.close();
        }catch(IOException e){
            System.out.println("Error While Loading"+e.getMessage());
            System.out.println(e);
        }
   }
        static boolean saveAll() throws FileNotFoundException{
             try{   FileOutputStream r = new FileOutputStream("Roomsgui.dat");
                    FileOutputStream g = new FileOutputStream("Guestsgui.dat");
                    FileOutputStream a = new FileOutputStream("Adminsgui.dat");
                    FileOutputStream s = new FileOutputStream("Servicesgui.dat");
                    FileOutputStream ROUT=new FileOutputStream("Receptionistsgui.dat");
                    FileOutputStream UOUT=new FileOutputStream("Users.dat");
                    ObjectOutputStream rout = new ObjectOutputStream(r);
                    ObjectOutputStream gout = new ObjectOutputStream(g);
                    ObjectOutputStream aout = new ObjectOutputStream(a);
                    ObjectOutputStream sout = new ObjectOutputStream(s);
                    ObjectOutputStream Rout=new ObjectOutputStream(ROUT);
                    ObjectOutputStream uout=new ObjectOutputStream(UOUT);
                    rout.writeObject(rooms); 
                    gout.writeObject(guests); 
                    aout.writeObject(admins);
                    sout.writeObject(services);
                    Rout.writeObject(receptionists);
                    uout.writeObject(usersID);
                    System.out.println("Data Saved Successfully to files");
                    r.close();
                    rout.close();
                    g.close();
                    gout.close();
                    a.close();
                    aout.close();
                    s.close();
                    sout.close();
                    ROUT.close();
                    Rout.close();
                    UOUT.close();
                    uout.close();
                    return true;
        } catch (IOException e) {
                    System.out.println("Error adding arraylist to file: " + e.getMessage());
                    return false;
        }
        }
        public boolean saveLoggedIn() throws FileNotFoundException{
            try{
          FileOutputStream LOUT=new FileOutputStream("LoggedInAdmins.dat");
          ObjectOutputStream lout=new ObjectOutputStream(LOUT);
          lout.writeObject(LogedIn);
          LOUT.close();
          lout.close();
                System.out.println("LoggedIn Admins Saved Successfully");
                return true;
            }catch(FileNotFoundException ex){
                System.out.println(ex);
            }catch(IOException ex){
            System.out.println(ex);
        }
            return false;
        }
        
//         public void Loaddatafromserver()throws ClassNotFoundException{
//            Thread t=new Thread(){
//                public void run(){
//                     try{
//            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
//                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//                    rooms=(ArrayList<Room>) in.readObject();
//                    guests=(ArrayList<Guest>) in.readObject();
//                    admins=(ArrayList<Admin>) in.readObject();
//                    services=(ArrayList<Service>) in.readObject();
//                    receptionists=(ArrayList<Receptionist>) in.readObject();
//                    usersID=(ArrayList<Integer>) in.readObject();
//            }catch(IOException e){
//                System.out.println(e);
//            }       catch (ClassNotFoundException ex) {
//                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//            }
//            };t.start();
//        }
        private void getAndDisplayNumOfReservationsPerReceptionist(int txt) {
    ArrayList<Integer> reservationsCount = new ArrayList<>();

    
    for (int i = 0; i < receptionists.size(); i++) {
        reservationsCount.add(0);
    }

    for (Reservation reservation : reservations) {
        int receptionistId = reservation.getReceptionistID();
        reservationsCount.set(receptionistId, reservationsCount.get(receptionistId) + 1);
    }

    
}
        public void ADDRoom(String RoomNum,String Category) throws ClassNotFoundException{
        Room room=new Room(RoomNum,Category);
        for(int i=0; i<rooms.size(); i++){
        if(room.RoomNum.equals(rooms.get(i).RoomNum)){
            System.out.println("Room already exists");
            return;
            }
        }
       rooms.add(room);
    }
        public boolean checkrooms(String RoomNumber)throws ClassNotFoundException{
            if(!rooms.isEmpty()){
            for(Room room:rooms){
                if(RoomNumber.equals(room.RoomNum)){
                    return false;
                }
            }
            return true;
            }else{
            return true;
            }
        }
        public boolean checkroomC(String RoomNum,String roomC)throws ClassNotFoundException{
            if(!rooms.isEmpty()){
               for(Room room:rooms){
                if(RoomNum.equals(room.RoomNum )&& roomC.equals(room.Category)){
                    return true;
                }
               }
               return false;
            }
            else{
                   return false; 
                }
        }
    public void RemoveRoom(String RoomNum,String RoomCat) throws ClassNotFoundException{
//        ArrayList<Room> BRemove =new ArrayList<>();
//        ArrayList<Room> ARemove=new ArrayList<>();
//        try{ FileInputStream BS=new FileInputStream("Rooms.dat");
//        ObjectInputStream BSO=new ObjectInputStream(BS);
//        BRemove = (ArrayList<Room>) BSO.readObject();
        if(checkroomC(RoomNum,RoomCat)){
        for(Room room:rooms){
            if(RoomNum.equals(room.RoomNum)){
                rooms.remove(room);
                System.out.println("Room Removed Successfully");
                return;
            }
        }
        System.out.println("Error The Room Doesn't Exists");
        }
    }
    public void EditRoom(String RoomNum,String newCategory)throws ClassNotFoundException{
        for(Room room:rooms){
            if(RoomNum.equals(room.RoomNum)){
                rooms.remove(room);
                room.Category=newCategory;
                rooms.add(room);
            }
        }
    }
    public ArrayList SearchRoom(String searchCategory) throws ClassNotFoundException{
        ArrayList <String> SCategory= new ArrayList<>();
        for(Room room:rooms){
            if(searchCategory.equals(room.Category)){
                SCategory.add(room.RoomNum);
            }
        }
       return SCategory;
    }
    public double roomtotalprice(String roomC){
        double basePrice;
        double serviceChargeRate=0.12;
        double extraTaxRate=0.14;
        double totalPrice;
        if("Single".equalsIgnoreCase(roomC)){
            basePrice=150;
            double serviceCharge = basePrice * serviceChargeRate;
            double extraTax = basePrice * extraTaxRate;
            totalPrice = basePrice + serviceCharge + extraTax;
            return totalPrice;
        }else if("Deluxe".equalsIgnoreCase(roomC)){
            basePrice=250;
            double serviceCharge = basePrice * serviceChargeRate;
            double extraTax = basePrice * extraTaxRate;
            totalPrice = basePrice + serviceCharge + extraTax;
            return totalPrice;
        }else if("Presidential Suite".equalsIgnoreCase(roomC)){
            basePrice=700;
            double serviceCharge = basePrice * serviceChargeRate;
            double extraTax = basePrice * extraTaxRate;
            totalPrice = basePrice + serviceCharge + extraTax;
            return totalPrice;
        }else if("King".equalsIgnoreCase(roomC)){
            basePrice=500;
            double serviceCharge = basePrice * serviceChargeRate;
            double extraTax = basePrice * extraTaxRate;
            totalPrice = basePrice + serviceCharge + extraTax;
            return totalPrice;
        }else if("Triple".equalsIgnoreCase(roomC)){
            basePrice=370;
            double serviceCharge = basePrice * serviceChargeRate;
            double extraTax = basePrice * extraTaxRate;
            totalPrice = basePrice + serviceCharge + extraTax;
            return totalPrice;
        }else if("Apartment".equalsIgnoreCase(roomC)){
            basePrice=400;
            double serviceCharge = basePrice * serviceChargeRate;
            double extraTax = basePrice * extraTaxRate;
            totalPrice = basePrice + serviceCharge + extraTax;
            return totalPrice;
        }
        return -1;
    }
    public void AvailableRooms(String roomcategory) throws ClassNotFoundException{
        ArrayList<Room> AvailableRooms=new ArrayList<>();
        ArrayList<Room> NotAvailableRooms=new ArrayList<>();
        ArrayList<Room> getAllRooms=new ArrayList<>();
        try{ FileInputStream SRoomNum=new FileInputStream("Reservations.dat");
             ObjectInputStream SSRoomNum=new ObjectInputStream(SRoomNum);
             NotAvailableRooms=(ArrayList<Room>) SSRoomNum.readObject();
             FileInputStream SRoomNumR=new FileInputStream("Rooms.dat");
             ObjectInputStream SSRoomNumR=new ObjectInputStream(SRoomNumR);
             getAllRooms=(ArrayList<Room>) SSRoomNumR.readObject();
             for(Room room:getAllRooms){
                 for(Room rroom:NotAvailableRooms){
                     if(room.RoomNum!=rroom.RoomNum){
                         AvailableRooms.add(room);
                     }
                 }
             }
            FileOutputStream AvailRooms=new FileOutputStream("AvailableRooms.dat");
            ObjectOutputStream AvailRoomsout=new ObjectOutputStream(AvailRooms);
            for(Room AVroom:AvailableRooms)
            AvailRoomsout.writeObject(AVroom);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void ADDService(String ServiceID,String Service,int Serviceprice)throws ClassNotFoundException{
        Service service=new Service(ServiceID,Service,Serviceprice);
        for(int i=0; i<services.size(); i++){
        if(service.ServiceID.equals(services.get(i).ServiceID )){
            System.out.println("Service already exists");
            return;
            }
        }
        services.add(service);
    }
    public boolean checkservicesID(String serviceID)throws ClassNotFoundException{
            for(int i=0;i<services.size();i++){
                if(serviceID.equals(services.get(i).ServiceID)){
                    return true;
                }
            }
            return false;
        }
    public boolean checkservices(String service)throws ClassNotFoundException{
            for(int i=0;i<services.size();i++){
                if(service.equals(services.get(i).Service)){
                    return true;
                }
            }
            return false;
        }
    public double checkservicePrice(String ServiceID)throws ClassNotFoundException{
            for(int i=0;i<services.size();i++){
                if(ServiceID.equals(services.get(i).ServiceID)){
                    return services.get(i).ServicePrice;
                }
            }
            return -1;
    }
    public void RemoveService(String Servicetorem)throws ClassNotFoundException{
        for(Service service:services){
            if(Servicetorem.equals(service.Service)){
                services.remove(service);
                System.out.println("The Service Removed Successfully");
                return;
            }
        }
        System.out.println("Error The Service Doesn't Exists");
    }
    public void EditeService(String ServiceID,String newService)throws ClassNotFoundException{
        for(Service service:services){
            if(ServiceID.equals(service.ServiceID)){
                services.remove(service);
                service.Service=newService;
                services.add(service);
                System.out.println("Service Edited Successfully");
                return;
            }
        }
        System.out.println("Error While Editing The Service");
    }
    public ArrayList SearchService(String Service) throws ClassNotFoundException{
        ArrayList <String> Sservice= new ArrayList<>();
        for(Service sservice:services){
            if(Service.equals(sservice.Service)){
                Sservice.add(sservice.ServiceID);
            }
        }
       return Sservice;
    }
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
    public void EditUserUserName(int userID,String Department,String newusername)throws ClassNotFoundException{
        User user = SearchUseruserid(userID,Department);
        if(user != null){
        if(user instanceof Admin){
            for(int i=0;i<admins.size();i++){
            if(userID==user.UserId){
                admins.remove(user);
                user.username=newusername;
                admins.add((Admin)user);
            }
            }
            System.out.println("User Edited Successfully");
        }
        else if(user instanceof Guest){
        for(int i=0;i<guests.size();i++){
            if(userID==user.UserId){
                guests.remove(user);
                user.username=newusername;
                guests.add((Guest)user);
            }
        }
            System.out.println("User Edited Successfully");
        }
        else if(user instanceof Receptionist){
        for(int i=0;i<receptionists.size();i++){
               if(userID==user.UserId){
                receptionists.remove(user);
                user.username=newusername;
                receptionists.add((Receptionist)user);
            }
        }
        }
        else System.out.println("User not found");
        }
    }
    public boolean checkpassforGui(int UserID,String Dep,String oldpass)throws ClassNotFoundException{
        User usertocheck = SearchUseruserid(UserID,Dep);
        if(usertocheck!=null){
            if(usertocheck instanceof Admin){
                for(int i=0;i<admins.size();i++){
                    if(oldpass.equals(usertocheck.Password)){
                        return true;
                    }
                }
                return false;
            }else if(usertocheck instanceof Receptionist){
                for(int j=0;j<receptionists.size();j++){
                    if(oldpass.equals(usertocheck.Password)){
                        return true;
                    }
                }
                return false;
            }else if(usertocheck instanceof Guest){
                for(int l=0;l<guests.size();l++){
                    if(oldpass.equals(usertocheck.Password)){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
    public void EditUserpassword(int UserID,String Department,String oldpassword,String newpassword)throws ClassNotFoundException{
        User user = SearchUseruserid(UserID,Department);
        if(user != null){
        if(user instanceof Admin){
        for(int i=0;i<admins.size();i++){
                if(oldpassword.equals(user.Password)){
                    admins.remove(user);
                user.Password=newpassword;
                admins.add((Admin)user);
            }
        }
        }
        else if(user instanceof Guest){
        for(int i=0;i<guests.size();i++){
                if(oldpassword.equals(user.Password)){
                guests.remove(user);
                user.Password=newpassword;
                guests.add((Guest)user);
            }
        }
        }
        else if(user instanceof Receptionist){
        for(int i=0;i<receptionists.size();i++){
                if(oldpassword.equals(user.Password)){
                receptionists.remove(user);
                user.Password=newpassword;
                receptionists.add((Receptionist)user);
            }
        }
        }
        else System.out.println("User not found");
        }
    }
     public boolean checkdepforGui(int UserID,String OldDep)throws ClassNotFoundException{
        User usertocheck = SearchUseruserid(UserID,OldDep);
        if(usertocheck!=null){
            if(usertocheck instanceof Admin){
                for(int i=0;i<admins.size();i++){
                    if(UserID==usertocheck.UserId){
                        return true;
                    }
                }
                return false;
            }else if(usertocheck instanceof Receptionist){
                for(int j=0;j<receptionists.size();j++){
                    if(UserID==usertocheck.UserId){
                        return true;
                    }
                }
                return false;
            }else if(usertocheck instanceof Guest){
                for(int l=0;l<guests.size();l++){
                    if(UserID==usertocheck.UserId){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
    public void changeDepartment(int userID,String oldDepartment,String newDepartment)throws ClassNotFoundException{
        User user = SearchUseruserid(userID,oldDepartment);
        if(user != null){
        if(user instanceof Admin){   
        for(int i=0;i<admins.size();i++){
            if(userID==user.UserId){
                admins.remove(user);
                user.Department=newDepartment;
                admins.add((Admin)user);
            }
        }
            System.out.println("User Edited Successfully");
        }
        else if(user instanceof Admin){
        for(int i=0;i<receptionists.size();i++){
           if(userID==user.UserId){
               receptionists.remove(user);
                user.Department=newDepartment;
                receptionists.add((Receptionist)user);
            }
        }
            System.out.println("User Edited Successfully");
                }
        }
         else System.out.println("User not found");
    }
    public void RemoveUser(int userID,String Department)throws ClassNotFoundException{
          User user = SearchUseruserid(userID,Department);
        if(user != null){
        if(user instanceof Admin){
            admins.remove(user);
            System.out.println("Admin Removed Successfully");
        }else if(user instanceof Receptionist){
            receptionists.remove(user);
            System.out.println("Receptionist Removed Successfully");
                }
        }else if(user instanceof Guest){  
            guests.remove(user);
            System.out.println("Guest Removed Successfully");
        }
        else {
            System.out.println("User not found");
        }       
    }
    public User SearchUseruserid(int userID,String Department)throws ClassNotFoundException{
        if("Admin".equals(Department)){   
        for(User user:admins){
           if(userID==user.UserId){
                return user;
            }
        }
        }
        else if("Receptionist".equals(Department)){
        for(User user:receptionists){
           if(userID==user.UserId){
                return user;
            }
        }
        }else if("Guest".equals(Department)){
        for(User user:guests){
           if(userID==user.UserId){
                return user;
            }
        }
        }
        System.out.println("User with id "+ userID+" doesn't exist");
        return null;
    }
      public ArrayList SearchUserusername(String username,String Department)throws ClassNotFoundException{
          ArrayList<User> USBN=new ArrayList<>();
          if("Admin".equals(Department)){   
        for(User user:admins){
           if(username.equals(user.username)){
                USBN.add(user);
            }
        }
        return USBN;
        }
        else if("Receptionist".equals(Department)){
        for(User user:receptionists){
           if(username.equals(user.username)){
                USBN.add(user);
            }
        }
        return USBN;
        }else if("Guest".equals(Department)){
        for(User user:guests){
           if(username.equals(user.username)){
                USBN.add(user);
            }
        }
        return USBN;
        }
        System.out.println("User with username "+ username+" doesn't exist");
        return null;
    }
      public int NumberOfrequests(Date start,Date end)
    {
        
          int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Services.dat"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the comma as a separator
                String[] parts = line.split(",");
                
                // Assuming that the date is at index 2 (change it if needed)
                String dateString = parts[2].trim();

                try {
                    // Parse the date from the string
                    Date requestDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

                    // Check if the request date is between the specified start and end dates
                    if (requestDate.after(start) && requestDate.before(end)) {
                        count++;
                    }
                } catch (ParseException e) {
                    System.out.println("Error parsing date: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading request file: " + e.getMessage());
        }

        return count;
    }
     
     
     


      public String getMostRequestedService(Date start,Date end) throws ParseException {
        Map<String, Integer> serviceCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Services.dat"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String service = parts[1].trim();
                
                 String dateString = parts[2].trim();

                Date requestDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

                    // Check if the request date is between the specified start and end dates
                    if (requestDate.after(start) && requestDate.before(end)) {
                serviceCountMap.put(service, serviceCountMap.getOrDefault(service, 0) + 1);
                    }
            }
        } catch (IOException e) {
            System.out.println("Error reading request file: " + e.getMessage());
        }

        // Find the most requested room
        String mostRequestedService = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : serviceCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostRequestedService = entry.getKey();
            }
        }

        return mostRequestedService;
      }
      public String getMostRevenueService(Date start,Date end) throws ParseException {
        Map<String, Double> serviceRevenueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Services.dat"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the comma as a separator
                String[] parts = line.split(",");

                // Assuming that the room name is at index 1 and cost is at index 3 (change if needed)
                String service = parts[1].trim();
                double cost = Double.parseDouble(parts[3].trim());
                String dateString = parts[2].trim();

                Date requestDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

                    // Check if the request date is between the specified start and end dates
                    if (requestDate.after(start) && requestDate.before(end)) {

                // Update the revenue for the room
                serviceRevenueMap.put(service, serviceRevenueMap.getOrDefault(service, 0.0) + cost);
                    }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading/calculating revenue: " + e.getMessage());
        }

        // Find the room with the highest revenue
        String mostRevenueService = null;
        double maxRevenue = 0.0;

        for (Map.Entry<String, Double> entry : serviceRevenueMap.entrySet()) {
            if (entry.getValue() > maxRevenue) {
                maxRevenue = entry.getValue();
                mostRevenueService = entry.getKey();
            }
        }

        return mostRevenueService;
    }
      public int NumberOfReservation(LocalDate start, LocalDate end) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("reservation.dat"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the comma as a separator
                String[] parts = line.split(",");

                // Assuming that the date is at index 2 (change it if needed)
                String dateString = parts[3].trim();

                try {
                    // Parse the date from the string using LocalDate
                    LocalDate reservationDate = LocalDate.parse(dateString);

                    // Check if the reservation date is between the specified start and end dates
                    if (!reservationDate.isBefore(start) && !reservationDate.isAfter(end)) {
                        count++;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing date: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading reservation file: " + e.getMessage());
        }

        return count;
    }
      
       public String getMostReservedRoom(LocalDate start, LocalDate end) {
        Map<String, Integer> roomCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("reservation.dat"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the comma as a separator
                String[] parts = line.split(",");

                // Assuming that the room name is at index 1 (change it if needed)
                String reservation = parts[2].trim();
                 String dateString = parts[3].trim();
                    LocalDate reservationDate = LocalDate.parse(dateString);

                    // Check if the reservation date is between the specified start and end dates
                    if (!reservationDate.isBefore(start) && !reservationDate.isAfter(end)) {
                roomCountMap.put(reservation, roomCountMap.getOrDefault(reservation, 0) + 1);
                    }
            }
        } catch (IOException e) {
            System.out.println("Error reading request file: " + e.getMessage());
        }

        // Find the most requested room
        String mostReserevedRoom = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : roomCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostReserevedRoom = entry.getKey();
            }
        }

        return mostReserevedRoom;
      }
       
 
       public String getMostRevenuedRoom(LocalDate start, LocalDate end) {
        Map<String, Double> roomRevenueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("reservation.dat"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the comma as a separator
                String[] parts = line.split(",");

                // Assuming that the room name is at index 1 and cost is at index 3 (change if needed)
                String room = parts[2].trim();
                double cost = Double.parseDouble(parts[5].trim());
                String dateString = parts[3].trim();
                    LocalDate reservationDate = LocalDate.parse(dateString);

                    // Check if the reservation date is between the specified start and end dates
                    if (!reservationDate.isBefore(start) && !reservationDate.isAfter(end)) {
                // Update the revenue for the room
                roomRevenueMap.put(room, roomRevenueMap.getOrDefault(room, 0.0) + cost);
                    }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading/calculating revenue: " + e.getMessage());
        }

        // Find the room with the highest revenue
        String mostRevenueRoom = null;
        double maxRevenue = 0.0;

        for (Map.Entry<String, Double> entry : roomRevenueMap.entrySet()) {
            if (entry.getValue() > maxRevenue) {
                maxRevenue = entry.getValue();
                mostRevenueRoom = entry.getKey();
            }
        }

        return mostRevenueRoom;
    }
      
    public void DisplayRooms() throws ClassNotFoundException{
       System.out.println("List of Rooms: ");
        for(Room room:rooms){
            System.out.println( room.toString());
        }
    }
    public void DisplayServices(){
        System.out.println("List of Services: ");
        for(Service service:services){
            System.out.println(service.toString());
        }
    }
    public void DisplayUsers(){
       System.out.println("List of Users: ");
        for(User admin:admins){
            System.out.println(admin.toString());
            }
        for(User guest:guests){
            System.out.println(guest.toString());
            }
        for(User receptionist:receptionists){
            System.out.println(receptionist.toString());
        }
        }
  
    static public ArrayList<Room> loadRooms() throws ClassNotFoundException{
                   ArrayList<Room> LRooms=new ArrayList<>();
     try{
           FileInputStream i=new FileInputStream("Rooms.dat");
           ObjectInputStream in=new ObjectInputStream(i);
           LRooms =(ArrayList<Room>) in.readObject();
           in.close();
           i.close();
       }catch(IOException | ClassNotFoundException e){
           System.out.println(e);
       }
     return LRooms;
    } 
    static public ArrayList<Service> loadServices()throws ClassNotFoundException{
        ArrayList<Service> LServices=new ArrayList<>();
        try{
            FileInputStream LS=new FileInputStream("Services.dat");
            ObjectInputStream LSIN=new ObjectInputStream(LS);
            LServices=(ArrayList<Service>) LSIN.readObject();
            LS.close();
            LSIN.close();
        }catch(IOException e){
            System.out.println(e);
        }
        return LServices;
    }
    static public ArrayList<User> loadAdmins()throws ClassNotFoundException{
        ArrayList<User> LAdmins=new ArrayList<>();
     try{
           FileInputStream i=new FileInputStream("Admins.dat");
           ObjectInputStream in=new ObjectInputStream(i);
           LAdmins =(ArrayList<User>) in.readObject();
           in.close();
           i.close();
       }catch(IOException | ClassNotFoundException e){
           System.out.println(e);
       }
     return LAdmins;
    } static public ArrayList<User> loadGuests()throws ClassNotFoundException{
        ArrayList<User> LGuests=new ArrayList<>();
     try{
           FileInputStream i=new FileInputStream("Guests.dat");
           ObjectInputStream in=new ObjectInputStream(i);
           LGuests =(ArrayList<User>) in.readObject();
           in.close();
           i.close();
       }catch(IOException | ClassNotFoundException e){
           System.out.println(e);
       }
     return LGuests;
    }
    static public ArrayList<User> LoadReceptionist()throws ClassNotFoundException{
        ArrayList<User> LReceptionists=new ArrayList<>();
        try{
            FileInputStream LRS=new FileInputStream("Receptionists.dat");
            ObjectInputStream LLRRS=new ObjectInputStream(LRS);
            LReceptionists=(ArrayList<User>) LLRRS.readObject();
            LRS.close();
            LLRRS.close();
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return LReceptionists;
    }

    int getAdminCount() {
        int count=0;
        for(int i=0;i<admins.size();i++){
            count++;
        }
        return count;
  
    
    
    }
}
