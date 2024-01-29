
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecthms;

import static  projecthms.Admin.usersID;

/**
 *
 * @author DELL_PC
 */
public class ProjectHMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException{
        Admin admin1=new Admin(0,"0","Admin","0");
        admin1.Loaddata();
//        admin1.ADDRoom("1", "Single");
//        admin1.saveAll();
        //admin1.DisplayRooms();
////        Receptionist rec5=new Receptionist(3,"Karim","Receptionist","113200");
////        admin1.AddUser(rec5);
////        admin1.saveAll();
        //admin1.DisplayUsers();
////        admin1.RemoveUser("Karim", "Admin");
////        admin1.saveAll();
//        Admin admin2=new Admin(2,"Ismail","Admin","4321");
//        admin1.AddUser(admin2);
//        admin1.saveAll();
//                admin1.DisplayUsers();

//        admin1.RemoveUser("Ismail","Admin");
//        admin1.saveAll();
//        admin1.DisplayUsers();
//        Admin admin3=new Admin(0,"Youssef","Admin","225004");
//        admin1.AddUser(admin3);
//        admin1.saveAll();
//admin1.RemoveUser(0, "Admin");
//admin1.saveAll();
System.out.println(usersID);
                admin1.DisplayUsers();
                //admin1.DisplayRooms();
//        admin1.Loaddata();
//        int userID1=1;
//        int userID2=2;
//        int userID3=3;
//        int userID4=4;
//        String username1="Youssef Eldemerdash";
//        String username2="Ismail Ahmed";
//        String username3="Rawan Mohamed";
//        String username4="Moatasem";
//        String Dep1="Admin";
//        String Dep2="sssss";
//        String Dep3="Admin";
//        String Dep4="Guest";
//        String Pass1="0123";
//        String Pass2="4567";
//        String Pass3="891011";
//        String Pass4="12131415";
//        Admin user=new Admin(userID1,username1,Dep1,Pass1);
//        Admin user2= new Admin(userID3,username3,Dep3,Pass3);
//        Guest user3= new Guest(userID4,username4,Dep4,Pass4);
        //User user4= new User(userID4,username4,Dep4,Pass4);
//        admin1.AddUser(user);
//        admin1.saveAll();
//        admin1.AddUser(user2);
//        admin1.saveAll();
//        admin1.AddUser(user3);
//        admin1.saveAll();
        //admin1.AddUser(user4);
        //admin1.DisplayUsers();
//        int ServiceID1=1;
//        int ServiceID2=2;
//        int ServiceID3=3;
//        String Service1="Wifi";
//        String Service2="FullBoard";
//        String Service3="DryClean";
//        Service service1=new Service(ServiceID1,Service1);
//        Service service2=new Service(ServiceID2,Service2);
//        Service service3=new Service(ServiceID3,Service3);
//        admin1.ADDAdditionalService(service1);
//        admin1.ADDAdditionalService(service2);
//        admin1.ADDAdditionalService(service3);
//        admin1.DisplayServices();
//        System.out.println(admin1.SearchService("FullBoard"));
        //admin1.EditeService(2, "wifi");
        //admin1.DisplayServices();
        //admin1.RemoveService("Wifi");
        //admin1.DisplayServices();
//          int RoomNum1=1;
//          int RoomNum2=2;
//          int RoomNum3=3;
//          int RoomNum4=4;
//          int RoomNum5=5;
//          String Category1="Single";
//          String Category2="Deulex";
//          String Category3="Sweet";
//          String category4="Deulex";
//          String Category5="Single";
//          Room room1=new Room(RoomNum1,Category1);
//          Room room2=new Room(RoomNum2,Category2);
//          Room room3=new Room(RoomNum3,Category3);
//          Room room4=new Room(RoomNum4,category4);
//          Room room5=new Room(RoomNum5,Category5);
//          admin1.ADDRoom(room1);
//          admin1.ADDRoom(room2);
//          admin1.ADDRoom(room3);
//          admin1.ADDRoom(room4);
//          admin1.ADDRoom(room5);
          //admin1.DisplayRooms();
          //admin1.saveAll();
//        System.out.println("enter the room to remove: ");
//        RoomNum=s.nextInt();
//        String newCategory=s.next();
          //admin1.RemoveRoom(4);
          //admin1.RemoveRoom(5);
//        admin1.EditeRoom(RoomNum, newCategory);
//        System.out.println(admin1.SearchRoom(newCategory));
//        admin1.DisplayRooms();
           //System.out.println(admin1.SearchRoom("Single"));
        //System.out.println(admin1.loadRooms());
        //admin1.RemoveRoom(1);
        //admin1.RemoveRoom(2);
//        admin1.DisplayRooms();
//        Service s1=new Service(1,"WIFI");
//        admin1.ADDAdditionalService(s1);
//        admin1.saveAll();
          //Guest g1=new Guest(0,"Ali","Guest","12345");
        //admin1.SearchRoom("Single");
        //int r = admin1.SearchRoom("Single").indexOf(0);
        //Reservation rrR = new Reservation(1,g1,admin1.SearchRoom("Single").indexOf(0),admin1.SearchService("WIFI").indexOf(0),new Date(2023,11,11),new Date(2023,11,16));
        //admin1.ADDRoom(1, "Single");
        //admin1.saveAll();
        //admin1.ADDRoom(2,"DEulex");
        ///admin1.saveAll();
        //System.out.println(admin1.loadRooms());
        //admin1.AddUser();
        //admin1.saveAll();
        //admin1.ADDAdditionalService(5, "Dry Clean");
        //admin1.saveAll();
        //System.out.println(admin1.loadServices());
        //admin1.AddUser(2, "Moatasem", "Receptionist", "12123");
        //admin1.saveAll();
        //System.out.println(admin1.LoadReceptionist());
        //admin1.AddUser(1, "Youssef", "Admin", "1234");
        //System.out.println(admin1.loadAdmins());
        //admin1.EditUserUserName(1,"Admin","Youssef");
        //admin1.saveAll();
        //System.out.println(admin1.loadAdmins());
    }
    
    
}
