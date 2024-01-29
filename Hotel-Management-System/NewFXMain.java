

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projecthms;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.logging.Logger;
import static projecthms.Admin.rooms;
import static projecthms.Admin.admins;
import static projecthms.Admin.receptionists;
import static projecthms.Admin.guests;
import static projecthms.Admin.LogedIn;
import static projecthms.Admin.usersID;
import javafx.collections.ObservableList;
//import static javaapplication22.Admin.rooms;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.ParseException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Date;
import java.util.Scanner;
import static projecthms.Admin.services;
import static projecthms.Receptionist.reservations;
import static projecthms.Receptionist.reservationsID;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.util.Duration;

/**
 *
 * @author DELL_PC
 */
public class NewFXMain extends Application implements Serializable {

    private Stage primaryStage;
    private Boolean currentAdmin = false;
    private Boolean currentReceptionist = false;
    ArrayList<Receptionist> LogedInr = new ArrayList<>();
   // ArrayList<Reservation> reservations = new ArrayList();
    static ArrayList<Admin> toop = new ArrayList<>();
    int IDN = usersID.size();
    static final int REFRESH_INTERVAL = 50;
    static int CUID;
    static String CUN;
    boolean stillin = false;
    int IDNrec = reservationsID.size();
    ArrayList<Guest> LoggedInr = new ArrayList<>();
    static ArrayList<Receptionist>toop1=new ArrayList<>();
    //private static int UID=0;
//    private int UAID=admins.size();
//    private int URID=receptionists.size();
//    private int UGID=guests.size();
// Class-level variable to store the current student
    //private static ArrayList<Student> studentsList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            FileInputStream Uutop = new FileInputStream("C:/Users/MM/Documents/NetBeansProjects/projectHMS/Adminsgui.dat");
            ObjectInputStream uutoop = new ObjectInputStream(Uutop);
            toop = (ArrayList<Admin>) uutoop.readObject();
            uutoop.close();
            Uutop.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (EOFException exx) {
            System.out.println("End of File reached");
        } catch (IOException | ClassNotFoundException exx) {
            exx.printStackTrace();
        }
        try{
        FileInputStream Uutop1=new FileInputStream("C:/Users/MM/Documents/NetBeansProjects/projectHMS/Receptionistsgui.dat");
                ObjectInputStream uutoop1=new ObjectInputStream(Uutop1);
                toop1=(ArrayList<Receptionist>) uutoop1.readObject();
                Uutop1.close();
                uutoop1.close();
            }catch (FileNotFoundException ex) {
             System.out.println(ex);           
            }catch (EOFException ex) {
                 System.out.println("End of File reached");
            }catch (IOException |ClassNotFoundException ex) {
                 ex.printStackTrace();
            }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
        try {
            this.primaryStage = primaryStage;
            boolean isFirstRun = toop.isEmpty() || toop.get(0).Department.equalsIgnoreCase(null);
            if (isFirstRun) {
                showregform();
            } else {
                showSelectionForm();
            }
        } catch (ClassNotFoundException exx) {
            exx.printStackTrace();
        }
//        try {
//                System.out.println("press 1 to start server");
//                Scanner s=new Scanner(System.in);
//                int in=s.nextInt();
//                if(in==1){
//                  creatServer();
//                }
//                else{
//                    System.out.println("no thing to do");
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
    }
//     public void creatServer()throws IOException{
//        Server server=new Server();
//        server.receveConnection();
//    }
//      public void creatClientAdmin()throws IOException{
//        ClientAdmin clientadmin=new ClientAdmin(8080);
//        clientadmin.recevedData();
//    }

    private void showADDUSERForm() throws ClassNotFoundException {
        GridPane AdduserForm = createAddUserForm();
        Scene scene = new Scene(AdduserForm, 500, 400);
        primaryStage.setTitle("Add User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showReceptionistForm() throws ClassNotFoundException {
        GridPane ReceptionistForm = createReceptionistForm();
        Scene scene = new Scene(ReceptionistForm, 1200, 700);
        primaryStage.setTitle("receptionist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showReceptionistFormLoginForm() {
        GridPane ReceptionistloginForm = createReceptionistLoginForm();
        Scene scene = new Scene(ReceptionistloginForm, 400, 300);
        primaryStage.setTitle("Admin Login Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private ArrayList<String> getServiceNames() {
        ArrayList<String> serviceNames = new ArrayList<>();
        for (Service service : services) {
            serviceNames.add(service.getService());
        }
        return serviceNames;
    }

    private GridPane createReceptionistForm() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label Checkin = new Label("checkin");
        DatePicker checkindate = new DatePicker();
        checkindate.setValue(LocalDate.now());
        checkindate.setOnAction(event -> {
            LocalDate selectedDate = checkindate.getValue();
            System.out.println("Selected Date: " + selectedDate);
        });
        Label Checkout = new Label("checkout");
        DatePicker checkoutdate = new DatePicker();
        checkoutdate.setValue(LocalDate.now());
        checkoutdate.setOnAction(event -> {
            LocalDate selectedDate = checkoutdate.getValue();
            System.out.println("Selected Date: " + selectedDate);
        });

        Label GuestId = new Label("Guest ID");
        TextField GuestTF = new TextField();

        Label Roomnum = new Label("Room Number");
        TextField RoomNumberTF = new TextField();
        ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
        categoryChoiceBox.getItems().addAll(getServiceNames());
//        ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
//      categoryChoiceBox.getItems().addAll("Breakfast", "FullBoard", "HalfBoard", "NoService");

        Label Servicename = new Label("Choose service");
        // TextField ServiceTF = new TextField();

        Button clearfield = new Button("Clear Fields");

        clearfield.setOnAction(e -> {

            checkindate.setValue(LocalDate.now());
            checkoutdate.setValue(LocalDate.now());
            RoomNumberTF.clear();

            GuestTF.clear();
            //ServiceTF.clear();
            Alert adDR = new Alert(AlertType.INFORMATION);
            adDR.setHeaderText(null);
            adDR.setContentText("Fields cleared");
            ButtonType btDR = new ButtonType("OK");
            Optional<ButtonType> btDRR = adDR.showAndWait();
            if (btDRR.isPresent()) {
                System.out.println("Ok");
            }
        });

        Button searchButton = new Button("search room");
        Button BackBT = new Button("back");
        TextArea resultofsearchTextArea = new TextArea();
        resultofsearchTextArea.setEditable(false);

        TableView<Reservation> reservationTable = new TableView<>();

        TableColumn<Reservation, String> receptionistnamecoloun = new TableColumn<>("Receptionist Name");
        receptionistnamecoloun.setCellValueFactory(new PropertyValueFactory<>("receptionistName"));

        TableColumn<Reservation, String> reservationInfoColumn = new TableColumn<>("Reservation Info");
        reservationInfoColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));

        TableColumn<Reservation, String> roomNumberColumn = new TableColumn<>("Room Number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        TableColumn<Reservation, String> guestNameColumn = new TableColumn<>("Guest Name");
        guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("guest"));

        TableColumn<Reservation, String> serviceInfoColumn = new TableColumn<>("Service Info");
        serviceInfoColumn.setCellValueFactory(new PropertyValueFactory<>("service"));
        TableColumn<Reservation, LocalDate> CheckInColumn = new TableColumn<>("Checkin Info");
        CheckInColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));

        TableColumn<Reservation, LocalDate> CheckOutColumn = new TableColumn<>("Checkout Info");
        CheckOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        TableColumn<Reservation, Double> TotalPriceColumn = new TableColumn<>("Total Price");
        TotalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Totalprice"));

        reservationTable.getColumns().addAll(receptionistnamecoloun, reservationInfoColumn, roomNumberColumn, guestNameColumn, serviceInfoColumn, CheckInColumn, CheckOutColumn, TotalPriceColumn);

        reservationTable.getItems().addAll(reservations);

        Button createres = new Button("create Reservation");
        createres.setOnAction(e -> {

            //String receptname = LogedInr.get(0).username;
            //int reservationInfo = IDNrec;
            String roomNumber = RoomNumberTF.getText();
            String guestID = GuestTF.getText();

            String serviceInfo = (String) categoryChoiceBox.getValue();
            if (serviceInfo == null) {

                serviceInfo = "NoService";
            } else if (serviceInfo.isEmpty()) {

                serviceInfo = "NoService";
            }
            String roomCategory = "";

            for (Room room : rooms) {
                if (room.getRoomNum().equalsIgnoreCase(roomNumber)) {
                    roomCategory = room.getCategory();
                }

            }
            boolean searchResult = LogedInr.get(0).searchAvailableRoomnum(checkindate.getValue(), checkoutdate.getValue(), roomNumber);

            if (searchResult == true) {

                for (int i = 0; i < LogedInr.size(); i++) {
                    
                    if (CUID == LogedInr.get(i).UserId) {
                
                        Reservation reservation = new Reservation(checkindate.getValue(), checkoutdate.getValue(), reservationsID.size(), roomNumber, guestID, serviceInfo, LogedInr.get(i).username, CUID, 5.0, 0);
                        double pricetotal = reservation.calculateReservationPrice(roomCategory, serviceInfo, checkindate.getValue(), checkoutdate.getValue());
                       
                        reservation.setTotalprice(pricetotal);
                       
                        LogedInr.get(i).CreateReservation(reservation);
                    
                      
                        reservationTable.getItems().add(reservation);
                        LogedInr.get(i).saveAll();
                    }

                }

                checkindate.setValue(LocalDate.now());
                checkoutdate.setValue(LocalDate.now());
                RoomNumberTF.clear();
                GuestTF.clear();
//            ServiceTF.clear();

                Alert adDR = new Alert(AlertType.INFORMATION);
                adDR.setHeaderText(null);
                adDR.setContentText("Reservation Created Succsefully");
                ButtonType btDR = new ButtonType("OK");
                Optional<ButtonType> btDRR = adDR.showAndWait();
                if (btDRR.isPresent()) {
                    System.out.println("Ok");
                }
            }
            if (searchResult == false) {
                Alert adDR = new Alert(AlertType.INFORMATION);
                adDR.setHeaderText(null);
                adDR.setContentText("Reservation creation Failed , room is already taken or not available");
                ButtonType btDR = new ButtonType("OK");
                Optional<ButtonType> btDRR = adDR.showAndWait();
                if (btDRR.isPresent()) {
                    System.out.println("Ok");
                }
            }

        });

        Button addADDITIONALSERVERCES = new Button(" Add Additional Services to Reservation");
        addADDITIONALSERVERCES.setOnAction(e -> {

        });

        TextField canclresid = new TextField();
        canclresid.setPromptText("enter reservation id to cancle");
        Button cancelres = new Button("cancel Reservation");
        cancelres.setOnAction(e -> {
            int resid = Integer.parseInt(canclresid.getText());

            for (int i = 0; i < LogedInr.size(); i++) {
                if (CUID == LogedInr.get(i).UserId) {
                    LogedInr.get(i).CancleReservation(resid);
                    LogedInr.get(i).saveAll();

                }
            }

            reservationTable.getItems().clear();
            reservationTable.getItems().addAll(reservations);
            Alert adDR = new Alert(AlertType.INFORMATION);
            adDR.setHeaderText(null);
            adDR.setContentText("Reservation removed Succsefully");
            ButtonType btDR = new ButtonType("OK");
            Optional<ButtonType> btDRR = adDR.showAndWait();
            if (btDRR.isPresent()) {
                System.out.println("Ok");
            }
        });

        BackBT.setOnAction(e -> {
            try {
                for (int i = 0; i < LogedInr.size(); i++) {
                    if (CUID == LogedInr.get(i).UserId) {
                        LogedInr.remove(LogedInr.get(i));
                    }
                }
                showSelectionForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });

        try {
            Image roomPhoto1 = new Image(new File("C:/Users/MM/Downloads/download (2).jpg").toURI().toURL().toExternalForm());
        Image roomPhoto2 = new Image(new File("C:/Users/MM/Downloads/download (3).jpg").toURI().toURL().toExternalForm());
        Image roomPhoto3 = new Image(new File("C:/Users/MM/Downloads/presidental.jpg").toURI().toURL().toExternalForm());
        Image roomPhoto4 = new Image(new File("C:/Users/MM/Downloads/delux.jpg").toURI().toURL().toExternalForm());
        Image roomPhoto5 = new Image(new File("C:/Users/MM/Downloads/king.jpg").toURI().toURL().toExternalForm());
        Image roomPhoto6 = new Image(new File("C:/Users/MM/Downloads/apartment.jpg").toURI().toURL().toExternalForm());

            ImageView imageView1 = new ImageView(roomPhoto1);
            ImageView imageView2 = new ImageView(roomPhoto2);
            ImageView imageView3 = new ImageView(roomPhoto3);
            ImageView imageView4 = new ImageView(roomPhoto4);
            ImageView imageView5 = new ImageView(roomPhoto5);
            ImageView imageView6 = new ImageView(roomPhoto6);

            double imageWidth = 100;
            double imageHeight = 100;
            imageView1.setFitWidth(imageWidth);
            imageView1.setFitHeight(imageHeight);

            imageView2.setFitWidth(imageWidth);
            imageView2.setFitHeight(imageHeight);

            imageView3.setFitWidth(imageWidth);
            imageView3.setFitHeight(imageHeight);

            imageView4.setFitWidth(imageWidth);
            imageView4.setFitHeight(imageHeight);

            imageView5.setFitWidth(imageWidth);
            imageView5.setFitHeight(imageHeight);

            imageView6.setFitWidth(imageWidth);
            imageView6.setFitHeight(imageHeight);

            grid.add(imageView1, 4, 0);
            grid.add(imageView2, 5, 0);
            grid.add(imageView3, 4, 2);
            grid.add(imageView4, 5, 2);
            grid.add(imageView5, 4, 4);
            grid.add(imageView6, 5, 4);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Button button1 = new Button("Single");
        button1.setOnAction(e -> {
            String selectedCategory = button1.getText();
            if (selectedCategory != null) {
                ArrayList<Room> searchResult = new ArrayList<>();
                for (int i = 0; i < LogedInr.size(); i++) {
                    searchResult = LogedInr.get(i).searchAvailablecategoryofRooms(checkindate.getValue(), checkoutdate.getValue(), selectedCategory);
                }
                StringBuilder resultStringBuilder = new StringBuilder("Search Results:\n");
                for (Room room : searchResult) {
                    resultStringBuilder.append("Room Number: ").append(room.getRoomNum()).append("\n");
                }
                resultofsearchTextArea.appendText(resultStringBuilder.toString());
            }
        });

        Button button2 = new Button("Triple");
        button2.setOnAction(e -> {

            String selectedCategory = button2.getText();
            if (selectedCategory != null) {
                ArrayList<Room> searchResult = new ArrayList<>();
                for (int i = 0; i < LogedInr.size(); i++) {
                    searchResult = LogedInr.get(i).searchAvailablecategoryofRooms(checkindate.getValue(), checkoutdate.getValue(), selectedCategory);
                }
                StringBuilder resultStringBuilder = new StringBuilder("Search Results:\n");
                for (Room room : searchResult) {
                    resultStringBuilder.append("Room Number: ").append(room.getRoomNum()).append("\n");
                }
                resultofsearchTextArea.appendText(resultStringBuilder.toString());
            }
        });

        Button button3 = new Button("Presidential Suite");
        button3.setOnAction(e -> {
            String selectedCategory = button3.getText();
            if (selectedCategory != null) {
                ArrayList<Room> searchResult = new ArrayList<>();
                for (int i = 0; i < LogedInr.size(); i++) {
                    searchResult = LogedInr.get(i).searchAvailablecategoryofRooms(checkindate.getValue(), checkoutdate.getValue(), selectedCategory);
                }
                StringBuilder resultStringBuilder = new StringBuilder("Search Results:\n");
                for (Room room : searchResult) {
                    resultStringBuilder.append("Room Number: ").append(room.getRoomNum()).append("\n");
                }
                resultofsearchTextArea.appendText(resultStringBuilder.toString());
            }
        });

        Button button4 = new Button("Deluxe");
        button4.setOnAction(e -> {
            String selectedCategory = button4.getText();
            if (selectedCategory != null) {
                ArrayList<Room> searchResult = new ArrayList<>();
                for (int i = 0; i < LogedInr.size(); i++) {
                    searchResult = LogedInr.get(i).searchAvailablecategoryofRooms(checkindate.getValue(), checkoutdate.getValue(), selectedCategory);
                }
                StringBuilder resultStringBuilder = new StringBuilder("Search Results:\n");
                for (Room room : searchResult) {
                    resultStringBuilder.append("Room Number: ").append(room.getRoomNum()).append("\n");
                }
                resultofsearchTextArea.appendText(resultStringBuilder.toString());
            }
        });

        Button button5 = new Button("King");
        button5.setOnAction(e -> {
            String selectedCategory = button5.getText();
            if (selectedCategory != null) {
                ArrayList<Room> searchResult = new ArrayList<>();
                for (int i = 0; i < LogedInr.size(); i++) {
                    searchResult = LogedInr.get(i).searchAvailablecategoryofRooms(checkindate.getValue(), checkoutdate.getValue(), selectedCategory);
                }
                StringBuilder resultStringBuilder = new StringBuilder("Search Results:\n");
                for (Room room : searchResult) {
                    resultStringBuilder.append("Room Number: ").append(room.getRoomNum()).append("\n");
                }
                resultofsearchTextArea.appendText(resultStringBuilder.toString());
            }
        });

        Button button6 = new Button("Apartment");
        button6.setOnAction(e -> {
            String selectedCategory = button6.getText();
            if (selectedCategory != null) {
                ArrayList<Room> searchResult = new ArrayList<>();
                for (int i = 0; i < LogedInr.size(); i++) {
                    searchResult = LogedInr.get(i).searchAvailablecategoryofRooms(checkindate.getValue(), checkoutdate.getValue(), selectedCategory);
                }
                StringBuilder resultStringBuilder = new StringBuilder("Search Results:\n");
                for (Room room : searchResult) {
                    resultStringBuilder.append("Room Number: ").append(room.getRoomNum()).append("\n");
                }
                resultofsearchTextArea.appendText(resultStringBuilder.toString());
            }
        });

        Label categareatext = new Label("Available rooms of specified category");
        grid.add(reservationTable, 7, 0);
        grid.add(GuestId, 0, 0);
        grid.add(GuestTF, 1, 0);
        grid.add(Roomnum, 0, 1);
        grid.add(RoomNumberTF, 1, 1);
        //grid.add(Servicename, 0, 2);
        grid.add(categoryChoiceBox, 0, 2);
        grid.add(Checkin, 0, 3);
        grid.add(Checkout, 0, 4);
        grid.add(checkindate, 1, 3);
        grid.add(checkoutdate, 1, 4);
        grid.add(createres, 0, 5);
        grid.add(clearfield, 0, 6);
        grid.add(canclresid, 4, 6);
        grid.add(cancelres, 5, 6);
        grid.add(resultofsearchTextArea, 7, 4);
        grid.add(BackBT, 6, 6);
        grid.add(categareatext, 7, 3);
        grid.add(button1, 4, 1);
        grid.add(button2, 5, 1);
        grid.add(button3, 4, 3);
        grid.add(button4, 5, 3);
        grid.add(button5, 4, 5);
        grid.add(button6, 5, 5);

        return grid;
    }

    private void showregform() throws ClassNotFoundException {
        GridPane RF = createregestrationform();
        Scene scene = new Scene(RF, 500, 400);
        primaryStage.setTitle("registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createregestrationform() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter UserName");
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        Label DepartmentLabel = new Label("Department:");
        ChoiceBox DepartChoiceBoxUD = new ChoiceBox();
        DepartChoiceBoxUD.getItems().addAll("Admin");
        Button Register = new Button("Register");
        Button eraseAllButton = new Button("Erase All");
        Button LogIn = new Button("Login");
        LogIn.setVisible(false);
        Register.setOnAction(e -> {
            if ("Admin".equals(DepartChoiceBoxUD.getValue())) {
                Alert userid = new Alert(AlertType.INFORMATION);
                userid.setHeaderText(null);
                userid.setContentText("Your ID = " + usersID.size());
                userid.getButtonTypes().setAll(ButtonType.OK);
                Optional<ButtonType> Id = userid.showAndWait();
                if (Id.isPresent()) {
                    System.out.println("ok");
                }
                Admin admin = new Admin(usersID.size(), usernameField.getText(), (String) DepartChoiceBoxUD.getValue(), passwordField.getText());
                admins.add(admin);
                usersID.add(admin.UserId);
                try {
                    admin.saveAll();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert Reg = new Alert(AlertType.INFORMATION);
                Reg.setHeaderText(null);
                Reg.setContentText("Registered Successfully Registered as Admin");
                Reg.getButtonTypes().setAll(ButtonType.OK);
                Optional<ButtonType> reg = Reg.showAndWait();
                if (reg.isPresent()) {
                    System.out.println("ok");
                    LogIn.setVisible(true);
                }
            }
        });
        eraseAllButton.setOnAction(e -> {
            usernameField.clear();
            passwordField.clear();
        });
        LogIn.setOnAction(e -> {
            try {
                showSelectionForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(DepartmentLabel, 0, 2);
        grid.add(DepartChoiceBoxUD, 1, 2);
        grid.add(Register, 0, 3);
        grid.add(eraseAllButton, 1, 3);
        grid.add(LogIn, 2, 3);
        return grid;
    }

    private GridPane createAddUserForm() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

//        Label NameLabel = new Label("Name:");
//        TextField NameField = new TextField();
//        Label lastNameLabel = new Label("Last Name:");
//        TextField lastNameField = new TextField();
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

//        Label UID=new Label("User ID:");
//        TextField ID=new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label DepartmentLabel = new Label("Department:");
        ChoiceBox DepartChoiceBoxUD = new ChoiceBox();
        DepartChoiceBoxUD.getItems().addAll("Admin", "Receptionist", "Guest");

        Button addUserButton = new Button("Add User");
        Button eraseAllButton = new Button("Erase All");
        Button Back = new Button("Back");

        addUserButton.setOnAction(e -> {
            if ("Admin".equals(DepartChoiceBoxUD.getValue())) {
                // int ID=UAID++;
                User user = new Admin(usersID.size(), usernameField.getText(), (String) DepartChoiceBoxUD.getValue(), passwordField.getText());
                try {
                    Alert userid = new Alert(AlertType.INFORMATION);
                    userid.setHeaderText(null);
                    userid.setContentText("Your ID = " + usersID.size());
                    userid.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> Id = userid.showAndWait();
                    if (Id.isPresent()) {
                        System.out.println("ok");
                    }
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            LogedIn.get(i).AddUser(user);
                            LogedIn.get(i).saveAll();
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("Receptionist".equals(DepartChoiceBoxUD.getValue())) {
                //int IDR=URID++;
                //System.out.println(URID);
                //System.out.println(receptionists.size());
                User user = new Receptionist(usersID.size(), usernameField.getText(), (String) DepartChoiceBoxUD.getValue(), passwordField.getText());
                try {
                    Alert userid = new Alert(AlertType.INFORMATION);
                    userid.setHeaderText(null);
                    userid.setContentText("Your ID = " + usersID.size());
                    userid.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> Id = userid.showAndWait();
                    if (Id.isPresent()) {
                        System.out.println("ok");
                    }
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            LogedIn.get(i).AddUser(user);
                            LogedIn.get(i).saveAll();
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println(URID);
            } else if ("Guest".equals(DepartChoiceBoxUD.getValue())) {
                // int IDG=UGID++;
                User user = new Guest(usersID.size(), usernameField.getText(), (String) DepartChoiceBoxUD.getValue(), passwordField.getText());
                try {
                    Alert userid = new Alert(AlertType.INFORMATION);
                    userid.setHeaderText(null);
                    userid.setContentText("Your ID = " + usersID.size());
                    userid.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> Id = userid.showAndWait();
                    if (Id.isPresent()) {
                        System.out.println("ok");
                    }
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            LogedIn.get(i).AddUser(user);
                            LogedIn.get(i).saveAll();
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //RadioButton selectedGenderRadioButton = (RadioButton) genderToggleGroup.getSelectedToggle();
            //String selectedGender = (selectedGenderRadioButton != null) ? selectedGenderRadioButton.getText() : "";
//            currentAdmin = new Admin(
//                    NameField.getText(),
//                    NameField.getText(),
//                    usernameField.getText(),
//                    passwordField.getText(),
//                    phoneNumberField.getText(),
//                    Double.parseDouble(ageField.getText()),
//                    selectedGender,
//                    levelComboBox.getValue()
//            );
//           // Admin.studentsList.add(currentStudent);

            //showLoginForm();
        });

        eraseAllButton.setOnAction(e -> {
            usernameField.clear();
            passwordField.clear();
            //DepartmentField.clear();
//            firstNameField.clear();
//            lastNameField.clear();
//            usernameField.clear();
//            passwordField.clear();
//            phoneNumberField.clear();
//            ageField.clear();
//            genderToggleGroup.selectToggle(null);  // Clear the selected gender
//            levelComboBox.getSelectionModel().clearSelection();  // Clear the selected level
        });

        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(DepartmentLabel, 0, 3);
        grid.add(DepartChoiceBoxUD, 1, 3);
        grid.add(addUserButton, 0, 5);
        grid.add(eraseAllButton, 1, 5);
        grid.add(Back, 2, 5);
        return grid;
    }

    private void showAdminForm() throws ClassNotFoundException {
        GridPane AdminForm = creatAdminForm();
        Scene scene = new Scene(AdminForm, 1200, 700);
        primaryStage.setTitle("Admin " + CUN);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showWelcomBackForm() throws ClassNotFoundException {
        Alert ad = new Alert(
                AlertType.INFORMATION);
        ad.setTitle("Welcome");
        ad.setHeaderText("LogIn Completed successful");
        //ad.setContentText("You can open program");
        ad.showAndWait();
    }

    private void showAddRoom() throws ClassNotFoundException {
        GridPane AddRoom = creatAddRoom();
        Scene scene = new Scene(AddRoom, 500, 500);
        primaryStage.setTitle("ADD Room");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddser() throws ClassNotFoundException {
        GridPane AddService = creatAddService();
        Scene scene = new Scene(AddService, 500, 500);
        primaryStage.setTitle("ADD Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private int sprice = 0;

    private GridPane creatAddService() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label LID = new Label("Service ID:");
        TextField ServiceIDField = new TextField();
        ServiceIDField.setPromptText("Enter Service ID");
        Label Service = new Label("Service Category:");
        TextField ServiceField = new TextField();
        ServiceField.setPromptText("Enter Service");
        Label Serviceprice = new Label("Service Price:");
        TextField SPrice = new TextField();
        SPrice.setPromptText("Enter Service Price");
        Button ADDService = new Button("ADD Service");
        Button Back = new Button("Back");
        ADDService.setOnAction(e -> {
            try {
                String SID = ServiceIDField.getText();
                String Servicee = ServiceField.getText();
                SPrice.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, eventt -> {
                    if (!eventt.getCharacter().matches("\\d+")) {
                        eventt.consume();
                    }
                });
                SPrice.textProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        // Parse the text into an integer
                        sprice = Integer.parseInt(newValue);
                        System.out.println("Price Value: " + sprice);
                        // You can now use 'intValue' as needed
                    } catch (NumberFormatException exx) {
                        // Handle the case where the text cannot be parsed into an integer
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                });
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (!LogedIn.get(i).checkservicesID(SID) && !LogedIn.get(0).checkservices(Servicee)) {
                            Service service = new Service(SID, Servicee, sprice);
                            LogedIn.get(i).ADDService(SID, Servicee, sprice);
                            LogedIn.get(i).saveAll();
                            Alert addsf = new Alert(AlertType.INFORMATION);
                            addsf.setHeaderText(null);
                            addsf.setContentText("Service Added successfully");
                            addsf.getButtonTypes().setAll(ButtonType.OK);
                            Optional<ButtonType> rcaddsf = addsf.showAndWait();
                            if (rcaddsf.isPresent()) {
                                System.out.println("Ok");
                            }
                        } else if (LogedIn.get(i).checkservicesID(SID)) {
                            Alert addd = new Alert(AlertType.ERROR);
                            addd.setHeaderText(null);
                            addd.setContentText("Cannot Add the Service The ID Is Already Token");
                            ButtonType btTt = new ButtonType("OK");
                            addd.getButtonTypes().setAll(btTt);
                            Optional<ButtonType> rcd = addd.showAndWait();
                            if (rcd.isPresent() && rcd.get() == btTt) {
                                System.out.println("Ok");
                            }
                        } else {
                            Alert adddds = new Alert(AlertType.ERROR);
                            adddds.setHeaderText(null);
                            adddds.setContentText("Cannot Add the Service The Service Is Already Exists");
                            ButtonType btT = new ButtonType("OK");
                            adddds.getButtonTypes().setAll(btT);
                            Optional<ButtonType> rcds = adddds.showAndWait();
                            if (rcds.isPresent() && rcds.get() == btT) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(LID, 0, 0);
        grid.add(ServiceIDField, 1, 0);
        grid.add(Service, 0, 1);
        grid.add(ServiceField, 1, 1);
        grid.add(Serviceprice, 0, 2);
        grid.add(SPrice, 1, 2);
        grid.add(ADDService, 1, 3);
        grid.add(Back, 0, 3);
        return grid;
    }

    private GridPane creatAddRoom() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label Roomn = new Label("Enter Room Number");
        TextField RoomNFiled = new TextField();
        RoomNFiled.setPromptText("Enter Room Number");
        Label RoomC = new Label("Select Room Category");
        ChoiceBox categoryChoiceBoxxx = new ChoiceBox();
        categoryChoiceBoxxx.getItems().addAll("Single", "Presidential Suite", "Deluxe", "King", "Triple", "Apartment");
        Button AddRoom = new Button("ADD Room");
        Button Back = new Button("Back");
        AddRoom.setOnAction(e -> {
            try {
                String roomntoadd = RoomNFiled.getText();
                String cattoadd = (String) categoryChoiceBoxxx.getValue();
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (LogedIn.get(i).checkrooms(roomntoadd) && !cattoadd.isEmpty()) {
                            System.out.println(RoomNFiled.getText());
                            try {
                                LogedIn.get(i).ADDRoom(RoomNFiled.getText(), cattoadd);
                                LogedIn.get(i).saveAll();
                                System.out.println(LogedIn.get(i).username);
                            } catch (ClassNotFoundException ex) {
                                System.out.println(ex);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Alert adDR = new Alert(AlertType.INFORMATION);
                            adDR.setHeaderText(null);
                            adDR.setContentText("Room Added Succsefully");
                            ButtonType btDR = new ButtonType("OK");
                            Optional<ButtonType> btDRR = adDR.showAndWait();
                            if (btDRR.isPresent()) {
                                System.out.println("Ok");
                            }
                        } else {
                            Alert adDRR = new Alert(AlertType.ERROR);
                            adDRR.setHeaderText(null);
                            adDRR.setContentText("Room Number is Already Exists");
                            ButtonType btDRR = new ButtonType("OK");
                            Optional<ButtonType> bt = adDRR.showAndWait();
                            if (bt.isPresent()) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(Roomn, 0, 0);
        grid.add(RoomNFiled, 1, 0);
        grid.add(RoomC, 0, 1);
        grid.add(categoryChoiceBoxxx, 1, 1);
        grid.add(AddRoom, 0, 2);
        grid.add(Back, 0, 3);
        return grid;
    }

    private void showRemoveRoom() throws ClassNotFoundException {
        GridPane RemoveRoom = creatRemoveRoom();
        Scene scene = new Scene(RemoveRoom, 600, 700);
        primaryStage.setTitle("Remove Room");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane creatRemoveRoom() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label Roomn = new Label("Enter the Room Number");
        Label RoomC = new Label("Select Room Category");
        TextField RoomNFiled = new TextField();
        RoomNFiled.setPromptText("Enter Room Number");
        Button Back = new Button("Back");
        Button RemoveR = new Button("Remove Room");
        ChoiceBox categoryChoiceBoxx = new ChoiceBox();
        categoryChoiceBoxx.getItems().addAll("Single", "Presidential Suite", "Deluxe", "King", "Triple", "Apartment");
        RemoveR.setOnAction(e -> {
            try {
                String roomNummm = RoomNFiled.getText();
                String cattorem = (String) categoryChoiceBoxx.getValue();
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (LogedIn.get(i).checkroomC(roomNummm, cattorem)) {
                            Alert ad = new Alert(AlertType.CONFIRMATION);
                            ad.setHeaderText(null);
                            ad.setContentText("Are you sure?");
                            ad.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            Optional<ButtonType> r = ad.showAndWait();
                            if (r.isPresent() && r.get() == ButtonType.YES) {
                                LogedIn.get(i).RemoveRoom(roomNummm, cattorem);
                                LogedIn.get(i).saveAll();
                                System.out.println(LogedIn.get(i).username);
                            }
                            Alert adRM = new Alert(AlertType.INFORMATION);
                            adRM.setHeaderText(null);
                            adRM.setContentText("Room Removed Succsefully");
                            adRM.getButtonTypes().setAll(ButtonType.OK);
                            Optional<ButtonType> rc = adRM.showAndWait();
                            if (rc.isPresent() && rc.get() == ButtonType.OK) {
                                System.out.println("Ok");
                            }
                        } else {
                            Alert addd = new Alert(AlertType.ERROR);
                            addd.setHeaderText(null);
                            addd.setContentText("Cannot Remove the Room The Room Number is Not Matched With The Room Category");
                            ButtonType bt = new ButtonType("OK");
                            addd.getButtonTypes().setAll(bt, ButtonType.CANCEL);
                            Optional<ButtonType> rc = addd.showAndWait();
                            if (rc.isPresent() && rc.get() == bt) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(Roomn, 0, 0);
        grid.add(RoomNFiled, 1, 0);
        grid.add(RoomC, 0, 1);
        grid.add(categoryChoiceBoxx, 1, 1);
        grid.add(RemoveR, 0, 2);
        grid.add(Back, 0, 3);
        return grid;
    }

    private void showremoveser() throws ClassNotFoundException {
        GridPane RemoveService = createremser();
        Scene scene = new Scene(RemoveService, 500, 500);
        primaryStage.setTitle("Remove Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createremser() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label Service = new Label("Enter The Service To Remove");
        TextField Servicee = new TextField();
        Servicee.setPromptText("Enter Service");
        Button Removes = new Button("Remove");
        Button Back = new Button("Back");
        Removes.setOnAction(e -> {
            String STR = Servicee.getText();
            try {
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (LogedIn.get(i).checkservices(STR)) {
                            Alert rems = new Alert(AlertType.CONFIRMATION);
                            rems.setHeaderText(null);
                            rems.setContentText("Are you sure?");
                            rems.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            Optional<ButtonType> rem = rems.showAndWait();
                            if (rem.isPresent() && rem.get() == ButtonType.YES) {
                                LogedIn.get(i).RemoveService(STR);
                                LogedIn.get(i).saveAll();
                                Alert S = new Alert(AlertType.INFORMATION);
                                S.setHeaderText(null);
                                S.setContentText("The Service Removed Successfully");
                                S.getButtonTypes().setAll(ButtonType.OK);
                                Optional<ButtonType> SS = S.showAndWait();
                                if (SS.isPresent()) {
                                    System.out.println("Ok");
                                }
                            }
                        } else {
                            Alert remsE = new Alert(AlertType.ERROR);
                            remsE.setHeaderText(null);
                            remsE.setContentText("Can't Remove The Service The Service Doesn't Exists");
                            remsE.getButtonTypes().setAll(ButtonType.OK);
                            Optional<ButtonType> remE = remsE.showAndWait();
                            if (remE.isPresent() && remE.get() == ButtonType.OK) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(Service, 0, 0);
        grid.add(Servicee, 0, 1);
        grid.add(Removes, 0, 2);
        grid.add(Back, 0, 3);
        return grid;
    }

    private void showremU() throws ClassNotFoundException {
        GridPane RemUser = createRemUser();
        Scene scene = new Scene(RemUser, 500, 500);
        primaryStage.setTitle("Remove User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showEditUN() throws ClassNotFoundException {
        GridPane EUN = createEditUN();
        Scene scene = new Scene(EUN, 500, 500);
        primaryStage.setTitle("Edit UserName");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showEDTUP() throws ClassNotFoundException {
        GridPane EUP = createEditUP();
        Scene scene = new Scene(EUP, 500, 500);
        primaryStage.setTitle("Edite User Password");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showEDTD() throws ClassNotFoundException {
        GridPane EUD = createEUD();
        Scene scene = new Scene(EUD, 500, 500);
        primaryStage.setTitle("Edite User Department");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private int UIDTOED = 0;

    private GridPane createEUD() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label UIDTED = new Label("User ID:");
        TextField UIDTEDTF = new TextField();
        UIDTEDTF.setPromptText("Enter User ID");
        Label OLDD = new Label("Old Department:");
        Label NEWD = new Label("New Department:");
        ChoiceBox OLDDTE = new ChoiceBox();
        OLDDTE.getItems().addAll("Admin", "Receptionist", "Guest");
        ChoiceBox NEWDTE = new ChoiceBox();
        NEWDTE.getItems().addAll("Admin", "Receptionist", "Guest");
        Button editd = new Button("Edit");
        Button Back = new Button("Back");
        editd.setOnAction(e -> {
            UIDTEDTF.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
                if (!event.getCharacter().matches("\\d+")) {
                    event.consume();
                }
            });
            UIDTEDTF.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    // Parse the text into an integer
                    UIDTOED = Integer.parseInt(newValue);
                    System.out.println("Price Value: " + UIDTOED);
                    // You can now use 'intValue' as needed
                } catch (NumberFormatException exx) {
                    // Handle the case where the text cannot be parsed into an integer
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            });
            try {
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (LogedIn.get(i).checkdepforGui(UIDTOED, (String) OLDDTE.getValue())) {
                            Alert editD = new Alert(AlertType.CONFIRMATION);
                            editD.setHeaderText(null);
                            editD.setContentText("Are you sure?");
                            editD.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            Optional<ButtonType> edtD = editD.showAndWait();
                            if (edtD.isPresent() && edtD.get() == ButtonType.YES) {
                                LogedIn.get(i).changeDepartment(UIDTOED, (String) OLDDTE.getValue(), (String) NEWDTE.getValue());
                                LogedIn.get(i).saveAll();
                            }
                            Alert EU = new Alert(AlertType.INFORMATION);
                            EU.setHeaderText(null);
                            EU.setContentText("The Department Edited Successfully");
                            EU.getButtonTypes().setAll(ButtonType.OK);
                            Optional<ButtonType> EUU = EU.showAndWait();
                            if (EUU.isPresent()) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(UIDTED, 0, 0);
        grid.add(UIDTEDTF, 1, 0);
        grid.add(OLDD, 0, 1);
        grid.add(OLDDTE, 1, 1);
        grid.add(NEWD, 0, 2);
        grid.add(NEWDTE, 1, 2);
        grid.add(editd, 0, 3);
        grid.add(Back, 1, 3);
        return grid;
    }
    private int IDEP = 0;

    private GridPane createEditUP() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label UIDTOEP = new Label("User ID");
        TextField IDTOEP = new TextField();
        IDTOEP.setPromptText("Enter User Id");
        Label DEUP = new Label("Department");
        ChoiceBox DCBEUP = new ChoiceBox();
        DCBEUP.getItems().addAll("Admin", "Receptionist", "Guest");
        Label OLDP = new Label("OLD Password");
        PasswordField OLDUP = new PasswordField();
        OLDUP.setPromptText("Enter Old Password");
        Label newP = new Label("NEW Password");
        PasswordField NEWP = new PasswordField();
        NEWP.setPromptText("Enter New Password");
        Button EditP = new Button("Edit");
        Button Back = new Button("Back");
        EditP.setOnAction(e -> {
            IDTOEP.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
                if (!event.getCharacter().matches("\\d+")) {
                    event.consume();
                }
            });
            IDTOEP.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    // Parse the text into an integer
                    IDEP = Integer.parseInt(newValue);
                    System.out.println("Price Value: " + IDEP);
                    // You can now use 'intValue' as needed
                } catch (NumberFormatException exx) {
                    // Handle the case where the text cannot be parsed into an integer
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            });
            try {
                if (LogedIn.get(0).checkpassforGui(IDEP, (String) DCBEUP.getValue(), OLDUP.getText())) {
                    Alert edtu = new Alert(AlertType.CONFIRMATION);
                    edtu.setHeaderText(null);
                    edtu.setContentText("Are you sure?");
                    edtu.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    Optional<ButtonType> edtuu = edtu.showAndWait();
                    if (edtuu.isPresent() && edtuu.get() == ButtonType.YES) {
                        for (int i = 0; i < LogedIn.size(); i++) {
                            if (CUID == LogedIn.get(i).UserId) {
                                LogedIn.get(i).EditUserpassword(IDEP, (String) DCBEUP.getValue(), OLDUP.getText(), NEWP.getText());
                                LogedIn.get(i).saveAll();
                            }
                        }
                        Alert EU = new Alert(AlertType.INFORMATION);
                        EU.setHeaderText(null);
                        EU.setContentText("The Password Edited Successfully");
                        EU.getButtonTypes().setAll(ButtonType.OK);
                        Optional<ButtonType> EUU = EU.showAndWait();
                        if (EUU.isPresent()) {
                            System.out.println("Ok");
                        }
                    }
                } else {
                    Alert edtuE = new Alert(AlertType.ERROR);
                    edtuE.setHeaderText(null);
                    edtuE.setContentText("Can't Edite The User Password");
                    edtuE.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> edtEu = edtuE.showAndWait();
                    if (edtEu.isPresent() && edtEu.get() == ButtonType.OK) {
                        System.out.println("Ok");
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(UIDTOEP, 0, 0);
        grid.add(IDTOEP, 1, 0);
        grid.add(OLDP, 0, 1);
        grid.add(OLDUP, 1, 1);
        grid.add(newP, 0, 2);
        grid.add(NEWP, 1, 2);
        grid.add(DEUP, 0, 3);
        grid.add(DCBEUP, 1, 3);
        grid.add(EditP, 0, 4);
        grid.add(Back, 1, 4);
        return grid;
    }
    private int IDEN = 0;

    private GridPane createEditUN() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label UidtE = new Label("User ID:");
        TextField UIDtoE = new TextField();
        UIDtoE.setPromptText("Enter User Id");
        ChoiceBox DepartChoiceBoxxUDR = new ChoiceBox();
        DepartChoiceBoxxUDR.getItems().addAll("Admin", "Receptionist", "Guest");
        Label dep = new Label("Department");
        Label Nname = new Label("New UserName:");
        TextField Nn = new TextField();
        Nn.setPromptText("Enter New Username");
        Button Edite = new Button("Edit UserName");
        Button Back = new Button("Back");
        Edite.setOnAction(e -> {
            String nuname = Nn.getText();
            //String UIDDte=UIDtoE.getText();
            String departmentstat = (String) DepartChoiceBoxxUDR.getValue();
            UIDtoE.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
                if (!event.getCharacter().matches("\\d+")) {
                    event.consume();
                }
            });
            UIDtoE.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    // Parse the text into an integer
                    IDEN = Integer.parseInt(newValue);
                    System.out.println("Price Value: " + IDEN);
                    // You can now use 'intValue' as needed
                } catch (NumberFormatException exx) {
                    // Handle the case where the text cannot be parsed into an integer
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            });
            try {
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (LogedIn.get(i).SearchUseruserid(IDEN, departmentstat) != null) {
                            Alert edtu = new Alert(AlertType.CONFIRMATION);
                            edtu.setHeaderText(null);
                            edtu.setContentText("Are you sure?");
                            edtu.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            Optional<ButtonType> edtuu = edtu.showAndWait();
                            if (edtuu.isPresent() && edtuu.get() == ButtonType.YES) {
                                LogedIn.get(i).EditUserUserName(IDEN, departmentstat, nuname);
                                LogedIn.get(i).saveAll();
                                Alert EU = new Alert(AlertType.INFORMATION);
                                EU.setHeaderText(null);
                                EU.setContentText("The UserName Edited Successfully");
                                EU.getButtonTypes().setAll(ButtonType.OK);
                                Optional<ButtonType> EUU = EU.showAndWait();
                                if (EUU.isPresent()) {
                                    System.out.println("Ok");
                                }
                            }
                        } else {
                            Alert edtuE = new Alert(AlertType.ERROR);
                            edtuE.setHeaderText(null);
                            edtuE.setContentText("Can't Remove The UserName");
                            edtuE.getButtonTypes().setAll(ButtonType.OK);
                            Optional<ButtonType> edtEu = edtuE.showAndWait();
                            if (edtEu.isPresent() && edtEu.get() == ButtonType.OK) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(UidtE, 0, 0);
        grid.add(UIDtoE, 1, 0);
        grid.add(Nname, 0, 1);
        grid.add(Nn, 1, 1);
        grid.add(dep, 0, 2);
        grid.add(DepartChoiceBoxxUDR, 1, 2);
        grid.add(Edite, 0, 3);
        grid.add(Back, 1, 3);
        return grid;
    }
    private int IDRU = 0;

    private GridPane createRemUser() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label username = new Label("UserName:");
        TextField usern = new TextField();
        usern.setPromptText("Enter Username");
        Label Uid = new Label("User ID:");
        TextField UID = new TextField();
        UID.setPromptText("Enter User Id");
        ChoiceBox DepartChoiceBoxUDR = new ChoiceBox();
        DepartChoiceBoxUDR.getItems().addAll("Admin", "Receptionist", "Guest");
        Label dep = new Label("Department");
        //TextField Depart=new TextField();
        Button removeU = new Button("Remove");
        Button Back = new Button("Back");
        removeU.setOnAction(e -> {
            UID.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
                if (!event.getCharacter().matches("\\d+")) {
                    event.consume();
                }
            });
            UID.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    // Parse the text into an integer
                    IDRU = Integer.parseInt(newValue);
                    System.out.println("ID: " + IDRU);
                    // You can now use 'intValue' as needed
                } catch (NumberFormatException exx) {
                    // Handle the case where the text cannot be parsed into an integer
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            });
            try {
                String uname = usern.getText();
                String UIDD = UID.getText();
                String department = (String) DepartChoiceBoxUDR.getValue();
                if (LogedIn.get(0).SearchUseruserid(IDRU, department) != null) {
                    Alert remu = new Alert(AlertType.CONFIRMATION);
                    remu.setHeaderText(null);
                    remu.setContentText("Are you sure?");
                    remu.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    Optional<ButtonType> remuu = remu.showAndWait();
                    if (remuu.isPresent() && remuu.get() == ButtonType.YES && LogedIn.get(0).getAdminCount() != 0) {
                        for (int i = 0; i < LogedIn.size(); i++) {
                            if (CUID == LogedIn.get(i).UserId) {
                                LogedIn.get(i).RemoveUser(IDRU, department);
                                LogedIn.get(i).saveAll();
                            }
                        }
                        if (LogedIn.get(0).getAdminCount() == 0) {
                            Alert exitAlert = new Alert(AlertType.INFORMATION);
                            exitAlert.setHeaderText(null);
                            exitAlert.setContentText("Last admin removed. Closing the application.");
                            exitAlert.getButtonTypes().setAll(ButtonType.OK);
                            Optional<ButtonType> result = exitAlert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                Platform.exit();  // Close the application
                            }
                        }
                        Alert U = new Alert(AlertType.INFORMATION);
                        U.setHeaderText(null);
                        U.setContentText("The User Removed Successfully");
                        U.getButtonTypes().setAll(ButtonType.OK);
                        Optional<ButtonType> UU = U.showAndWait();
                        if (UU.isPresent()) {
                            System.out.println("Ok");
                        }
                    }
                } else {
                    Alert remuE = new Alert(AlertType.ERROR);
                    remuE.setHeaderText(null);
                    remuE.setContentText("Can't Remove The User The User Doesn't Exists");
                    remuE.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> remEu = remuE.showAndWait();
                    if (remEu.isPresent() && remEu.get() == ButtonType.OK) {
                        System.out.println("Ok");
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(username, 0, 0);
        grid.add(usern, 1, 0);
        grid.add(Uid, 0, 1);
        grid.add(UID, 1, 1);
        grid.add(dep, 0, 2);
        grid.add(DepartChoiceBoxUDR, 1, 2);
        grid.add(removeU, 0, 3);
        grid.add(Back, 1, 3);
        return grid;
    }

    private GridPane creatAdminForm() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label Room = new Label("Manage Room");
        Label Service = new Label("Manage Service");
        Label User = new Label("Manage User");
        Button AddroomB = new Button("Add Room");
        Button RemoveRoomB = new Button("Remove Room");
        Button EditRoomB = new Button("Edit Room");
        Button SearchRoomB = new Button("Search Room");
        Button AddService = new Button("Add Service");
        Button RemoveService = new Button("Remove Service");
        Button EditService = new Button("Edit Service");
        Button SearchService = new Button("Search Service");
        Button AddUser = new Button("Add User");
        Button RemoveUser = new Button("Remove User");
        Button EditeUsername = new Button("Edit User Name");
        Button EditUserPass = new Button("Edit User Password");
        Button ChangeUserDep = new Button("Change User Department");
        Button SearchUserByID = new Button("Search User By ID");
        Button SearchUserbyUN = new Button("Search User By User Name");
        Button LogOut = new Button("Log Out");
        //////////////////////////////////////////////////////////////////////////
        Button numofroom = new Button("number of reserved room");
        numofroom.setOnAction(e -> {
            try {
                shownumofreservedroom();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button mostroom = new Button("most reserved room");
        mostroom.setOnAction(e -> {
            try {
                shownumofreservedroomm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button mostoomB = new Button("most revenue room");
        mostoomB.setOnAction(e -> {
            try {
                showmostrevenue();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button numofreqser = new Button("number of requested service");
        numofreqser.setOnAction(e -> {
            try {
                shownumofreqservice();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Button Mrequestedservice = new Button("most requested service");
        Mrequestedservice.setOnAction(e -> {
            try {
                showmostreqserv();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button Mrevenueservice = new Button("most revenue service");
        Mrevenueservice.setOnAction(e -> {
            try {
                showmostrevserv();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ////////////////////////////////////////////////////////////////////////////
        AddroomB.setOnAction(e -> {
            try {
                showAddRoom();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        RemoveRoomB.setOnAction(e -> {
            try {
                showRemoveRoom();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        SearchRoomB.setOnAction(e -> {
            showrooms();
        });
        EditRoomB.setOnAction(e -> {
            showEditeRoom();
        });
        AddService.setOnAction(e -> {
            try {
                showAddser();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        RemoveService.setOnAction(e -> {
            try {
                showremoveser();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        EditService.setOnAction(e -> {
            showEditeS();
        });
        SearchService.setOnAction(e -> {
            showServices();
        });
        AddUser.setOnAction(e -> {
            try {
                showADDUSERForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        RemoveUser.setOnAction(e -> {
            try {
                showremU();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        EditeUsername.setOnAction(e -> {
            try {
                showEditUN();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        EditUserPass.setOnAction(e -> {
            try {
                showEDTUP();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        ChangeUserDep.setOnAction(e -> {
            try {
                showEDTD();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        SearchUserByID.setOnAction(e -> {
            try {
                showSUBID();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        SearchUserbyUN.setOnAction(e -> {
            try {
                showSUBN();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        LogOut.setOnAction(e -> {
            stillin = false;
            try {
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        Admin admintolout = LogedIn.get(i);
                        LogedIn.remove(LogedIn.get(i));
                        if (!LogedIn.isEmpty()) {
                            admintolout.saveLoggedIn();
                        } else {
                            LogedIn = new ArrayList<>();
                            admintolout.saveLoggedIn();
                            System.out.println("no users are Logged In");
                        }
                    }
                }
                showSelectionForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(Room, 0, 0);
        grid.add(Service, 0, 1);
        grid.add(User, 0, 2);
        grid.add(AddroomB, 1, 0);
        grid.add(RemoveRoomB, 2, 0);
        grid.add(EditRoomB, 3, 0);
        grid.add(SearchRoomB, 4, 0);
        grid.add(AddService, 1, 1);
        grid.add(RemoveService, 2, 1);
        grid.add(EditService, 3, 1);
        grid.add(SearchService, 4, 1);
        grid.add(AddUser, 1, 2);
        grid.add(RemoveUser, 2, 2);
        grid.add(EditeUsername, 3, 2);
        grid.add(EditUserPass, 4, 2);
        grid.add(ChangeUserDep, 5, 2);
        grid.add(SearchUserByID, 6, 2);
        grid.add(SearchUserbyUN, 7, 2);
        grid.add(LogOut, 10, 20);
        /////////////////////////////////////
        grid.add(numofreqser, 5, 1);
        grid.add(Mrequestedservice, 6, 1);
        grid.add(Mrevenueservice, 7, 1);
        grid.add(numofroom, 5, 0);
        grid.add(mostroom, 6, 0);
        grid.add(mostoomB, 7, 0);
        return grid;
    }

    private GridPane createEditS() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label ServiceID = new Label("Service ID");
        Label ServiceNM = new Label("Service");
        TextField SN = new TextField();
        SN.setPromptText("Enter Service");
        TextField SID = new TextField();
        SID.setPromptText("Enter Service ID");
        Button Edit = new Button("Edit");
        Button BackE = new Button("Back");
        Edit.setOnAction(e -> {
            String EnterdSID = ServiceID.getText();
            String EnterdS = ServiceNM.getText();
            try {
                for (int i = 0; i < LogedIn.size(); i++) {
                    if (CUID == LogedIn.get(i).UserId) {
                        if (LogedIn.get(i).checkservicesID(SID.getText())) {
                            Alert ad = new Alert(AlertType.CONFIRMATION);
                            ad.setHeaderText(null);
                            ad.setContentText("Are you sure?");
                            ButtonType bt = new ButtonType("Yes");
                            ad.getButtonTypes().setAll(bt, ButtonType.CANCEL);
                            Optional<ButtonType> r = ad.showAndWait();
                            if (r.get() == bt) {
                                System.out.println("Yes pressed");
                                LogedIn.get(i).EditeService(SID.getText(), SN.getText());
                                LogedIn.get(i).saveAll();
                                Alert adED = new Alert(AlertType.INFORMATION);
                                adED.setTitle("Edit");
                                adED.setHeaderText("Edit Completed successful");
                                adED.setContentText("You can Go Back To Admin Page");
                                adED.show();
                            } else {
                                System.out.println("Cancel pressed");
                            }
                        } else {
                            Alert adSE = new Alert(AlertType.ERROR);
                            adSE.setHeaderText(null);
                            adSE.setContentText("Cannot Edit the Service The Service ID Is Doesn't Exists");
                            ButtonType btt = new ButtonType("OK");
                            adSE.getButtonTypes().setAll(btt, ButtonType.CANCEL);
                            Optional<ButtonType> rcSE = adSE.showAndWait();
                            if (rcSE.isPresent() && rcSE.get() == btt) {
                                System.out.println("Ok");
                            }
                        }
                    }
                }
//                if(LogedIn.get(0).checkservicesID(SID.getText())){
//                    Alert ad = new Alert(AlertType.CONFIRMATION);
//                    ad.setHeaderText(null);
//                    ad.setContentText("Are you sure?");
//                    ButtonType bt = new ButtonType("Yes");
//                    ad.getButtonTypes().setAll(bt,ButtonType.CANCEL);
//                    Optional<ButtonType> r = ad.showAndWait();
//                    if(r.get()==bt){
//                    System.out.println("Yes pressed");
//                    for(int i=0;i<LogedIn.size();i++){
//                         if(CUID==LogedIn.get(i).UserId){
//                             LogedIn.get(i).EditeService(SID.getText(), SN.getText());
//                             LogedIn.get(i).saveAll();
//                         }
//                     }
//                    Alert adED = new Alert(AlertType.INFORMATION);
//                    adED.setTitle("Edit");
//                    adED.setHeaderText("Edit Completed successful");
//                    adED.setContentText("You can Go Back To Admin Page");
//                    adED.show();
//                    }
//                    else
//                    System.out.println("Cancel pressed");
//                }else{
//                            Alert adSE= new Alert(AlertType.ERROR);
//                            adSE.setHeaderText(null);
//                            adSE.setContentText("Cannot Edit the Service The Service ID Is Doesn't Exists");
//                            ButtonType btt = new ButtonType("OK");
//                            adSE.getButtonTypes().setAll(btt,ButtonType.CANCEL);
//                            Optional<ButtonType> rcSE = adSE.showAndWait();
//                            if(rcSE.isPresent() && rcSE.get() == btt){
//                            System.out.println("Ok");
//                            }
//                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        BackE.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(ServiceID, 0, 0);
        grid.add(SID, 1, 0);
        grid.add(ServiceNM, 0, 1);
        grid.add(SN, 1, 1);
        grid.add(Edit, 1, 2);
        grid.add(BackE, 2, 2);
        return grid;
    }

    private void showEditeS() {
        GridPane Editservice = createEditS();
        Scene scene = new Scene(Editservice, 400, 300);
        primaryStage.setTitle("Edit Services");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createShowServices() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label searchl = new Label("Search Service");
        //Label serviceName=new Label("Service");
        TextField ServiceN = new TextField();
        ServiceN.setPromptText("Enter Service");
        TextArea resultArea = new TextArea();
        Button Searchs = new Button("Search");
        Button BackS = new Button("Back");
        Searchs.setOnAction(e -> {
            String EnterdService = ServiceN.getText();
            try {
                if (EnterdService != null) {
                    ArrayList<String> searchResult = new ArrayList<>();
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            searchResult = LogedIn.get(i).SearchService(EnterdService);
                            resultArea.setText("Search Results:\n" + String.join("\n", "Service ID: " + searchResult));
                        }
                    }
                } else {
                    resultArea.setText("Please Enter a Service to search.");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        BackS.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(searchl, 0, 0);
        //grid.add(serviceName,1,0);
        grid.add(ServiceN, 1, 0);
        grid.add(resultArea, 0, 3);
        grid.add(Searchs, 1, 1);
        grid.add(BackS, 2, 1);
        return grid;
    }

    private void showServices() {
        GridPane searchservice = createShowServices();
        Scene scene = new Scene(searchservice, 800, 500);
        primaryStage.setTitle("Services");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showSUBID() throws ClassNotFoundException {
        GridPane SUID = cretaeSUBID();
        Scene scene = new Scene(SUID, 800, 500);
        primaryStage.setTitle("Search User By ID");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showSUBN() throws ClassNotFoundException {
        GridPane SUBN = createshowUSBN();
        Scene scene = new Scene(SUBN, 800, 500);
        primaryStage.setTitle("Search User By Name");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    int IDSID = 0;

    private GridPane cretaeSUBID() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label Search = new Label("User ID:");
        TextField ID = new TextField();
        ID.setPromptText("Enter User ID");
        Label Dep = new Label("Department:");
        ChoiceBox DepartChoiceBoxUDSID = new ChoiceBox();
        DepartChoiceBoxUDSID.getItems().addAll("Admin", "Receptionist", "Guest");
        TextArea resultAreaSUID = new TextArea();
        resultAreaSUID.setEditable(false);
        Button SearchU = new Button("Search");
        Button Back = new Button("Back");
        SearchU.setOnAction(e -> {
            ID.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
                if (!event.getCharacter().matches("\\d+")) {
                    event.consume();
                }
            });
            ID.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    // Parse the text into an integer
                    IDSID = Integer.parseInt(newValue);
                    System.out.println("ID: " + IDSID);
                    // You can now use 'intValue' as needed
                } catch (NumberFormatException exx) {
                    // Handle the case where the text cannot be parsed into an integer
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            });
            String selectedDep = (String) DepartChoiceBoxUDSID.getValue();
            try {
                User user;
                if (!ID.getText().isEmpty() && selectedDep != null) {
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            user = LogedIn.get(i).SearchUseruserid(IDSID, selectedDep);
                            if (user != null) {
                                resultAreaSUID.setText("Search Results:\n" + "User " + user.toString());
                            } else {
                                resultAreaSUID.setText("User not found.");
                            }
                        }
                    }
                } else {
                    resultAreaSUID.setText("Please Enter The Id And select a Department to search.");
                }

            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(Search, 0, 0);
        grid.add(ID, 1, 0);
        grid.add(Dep, 0, 1);
        grid.add(DepartChoiceBoxUDSID, 1, 1);
        grid.add(SearchU, 2, 0);
        grid.add(Back, 2, 1);
        grid.add(resultAreaSUID, 0, 3);
        return grid;
    }

    private GridPane createshowUSBN() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label Searchh = new Label("User Name:");
        TextField NAME = new TextField();
        NAME.setPromptText("Enter User Name");
        Label Depp = new Label("Department:");
        ChoiceBox DepartChoiceBoxUDSN = new ChoiceBox();
        DepartChoiceBoxUDSN.getItems().addAll("Admin", "Receptionist", "Guest");
        TextArea resultAreaSUN = new TextArea();
        resultAreaSUN.setEditable(false);
        Button SearchUBN = new Button("Search");
        Button Back = new Button("Back");
        SearchUBN.setOnAction(e -> {
            String SDTSBN = (String) DepartChoiceBoxUDSN.getValue();
            String UNAME = NAME.getText();
            if (UNAME != null && DepartChoiceBoxUDSN != null) {
                //ArrayList<User> Result=new ArrayList<>();
                try {
                    if (SDTSBN.equalsIgnoreCase("Admin")) {
                        ArrayList<Admin> Result = new ArrayList<>();
                        for (int i = 0; i < LogedIn.size(); i++) {
                            if (CUID == LogedIn.get(i).UserId) {
                                Result = LogedIn.get(i).SearchUserusername(UNAME, SDTSBN);
                            }
                        }
                        resultAreaSUN.setText("Search Results:\n" + "User " + Result);
                    } else if (SDTSBN.equalsIgnoreCase("Receptionist")) {
                        ArrayList<Receptionist> Result = new ArrayList<>();
                        for (int i = 0; i < LogedIn.size(); i++) {
                            if (CUID == LogedIn.get(i).UserId) {
                                Result = LogedIn.get(i).SearchUserusername(UNAME, SDTSBN);
                            }
                        }
                        resultAreaSUN.setText("Search Results:\n" + "User " + Result);
                    } else if (SDTSBN.equalsIgnoreCase("Guest")) {
                        ArrayList<Guest> Result = new ArrayList<>();
                        for (int i = 0; i < LogedIn.size(); i++) {
                            if (CUID == LogedIn.get(i).UserId) {
                                Result = LogedIn.get(i).SearchUserusername(UNAME, SDTSBN);
                            }
                        }
                        resultAreaSUN.setText("Search Results:\n" + "User " + Result);
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
            } else {
                resultAreaSUN.setText("Please Enter The Username And select a Department to search.");
            }
        });
        Back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(Searchh, 0, 0);
        grid.add(NAME, 1, 0);
        grid.add(SearchUBN, 2, 0);
        grid.add(Depp, 0, 1);
        grid.add(DepartChoiceBoxUDSN, 1, 1);
        grid.add(resultAreaSUN, 0, 3);
        grid.add(Back, 2, 1);
        return grid;
    }

    private GridPane createshowrooms() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label searchLabel = new Label("Search Room by Category:");
        ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
        categoryChoiceBox.getItems().addAll("Single", "Presidential Suite", "Deluxe", "King", "Triple", "Apartment");
        Button searchButton = new Button("Search");
        Button BackBT = new Button("Back");
        TextArea resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        // Handle search button click event
        searchButton.setOnAction(event -> {
            String selectedCategory = (String) categoryChoiceBox.getValue();
            if (selectedCategory != null) {
                ArrayList<String> searchResult = new ArrayList<>();
                try {
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            searchResult = LogedIn.get(i).SearchRoom(selectedCategory);
                        }
                    }
                    resultTextArea.setText("Search Results:\n" + String.join("\n", "Room Number: " + searchResult));
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
            } else {
                resultTextArea.setText("Please select a category to search.");
            }
        });
        BackBT.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(searchLabel, 0, 0);
        grid.add(categoryChoiceBox, 1, 0);
        grid.add(searchButton, 2, 0);
        grid.add(resultTextArea, 0, 3);
        grid.add(BackBT, 2, 1);
        return grid;
    }

    private void showrooms() {
        GridPane searchRoom = createshowrooms();
        Scene scene = new Scene(searchRoom, 800, 400);
        primaryStage.setTitle("Search Rooms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAdminLoginForm() {
        GridPane AdminloginForm = createAdminLoginForm();
        Scene scene = new Scene(AdminloginForm, 400, 300);
        primaryStage.setTitle("Admin Login Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createEditeRoom() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label roomNumLabel = new Label("Enter Room Number:");
        TextField roomNumTextField = new TextField();
        roomNumTextField.setPromptText("Enter Room Number");
        Label categoryLabel = new Label("Select New Category:");
        Button BackBT = new Button("Back");
        ChoiceBox categoryChoiceBox = new ChoiceBox();
        categoryChoiceBox.getItems().addAll("Single", "Presidential Suite", "Deluxe", "King", "Triple", "Apartment");
        //TextField categoryTextField = new TextField();
        Button editButton = new Button("Edit Room");
        editButton.setOnAction(event -> {
            try {
                String roomNum = roomNumTextField.getText();
                String newCategory = (String) categoryChoiceBox.getValue();
                if (!roomNum.isEmpty() && !newCategory.isEmpty()) {
                    for (int i = 0; i < LogedIn.size(); i++) {
                        if (CUID == LogedIn.get(i).UserId) {
                            LogedIn.get(i).EditRoom(roomNum, newCategory);
                            LogedIn.get(i).saveAll();
                        }
                    }
                    Alert addroom = new Alert(AlertType.INFORMATION);
                    addroom.setHeaderText(null);
                    addroom.setContentText("Room Category Edited Successfully");
                    addroom.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> addrB = addroom.showAndWait();
                    if (addrB.isPresent()) {
                        System.out.println("Ok");
                    }
                } else {
                    Alert addroome = new Alert(AlertType.ERROR);
                    addroome.setHeaderText(null);
                    addroome.setContentText("please Enter Both Room Number and New Category");
                    addroome.getButtonTypes().setAll(ButtonType.OK);
                    Optional<ButtonType> adder = addroome.showAndWait();
                    if (adder.isPresent()) {
                        System.out.println("ok");
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        BackBT.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        grid.add(roomNumLabel, 0, 0);
        grid.add(roomNumTextField, 1, 0);
        grid.add(categoryLabel, 0, 2);
        grid.add(categoryChoiceBox, 1, 2);
        grid.add(editButton, 0, 3);
        grid.add(BackBT, 0, 4);
        return grid;
    }

    private void showEditeRoom() {
        GridPane EditRooms = createEditeRoom();
        Scene scene = new Scene(EditRooms, 700, 600);
        primaryStage.setTitle("Edit Rooms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createSelectionForm() throws ClassNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Button Admin = new Button("Admin");
        Button Receptionist = new Button("Receptionist");
        Button Guest = new Button("Guest");
        try {
            Image adminImage = new Image("file:///C://Users//DELL_PC//Documents//OOP//JavaApplication22//Admin.jpg");
            Image receptionist = new Image("file:///C://Users//DELL_PC//Documents//OOP//JavaApplication22//Reseptionistt.png");
            Image guest = new Image("file:///C://Users//DELL_PC//Documents//OOP//JavaApplication22//tourist.png");
            ImageView adminImageView = new ImageView(adminImage);
            ImageView guestview = new ImageView(guest);
            ImageView receptionistview = new ImageView(receptionist);
            adminImageView.setFitHeight(50);
            adminImageView.setFitWidth(50);
            receptionistview.setFitHeight(70);
            receptionistview.setFitWidth(85);
            guestview.setFitHeight(50);
            guestview.setFitWidth(60);
            grid.add(adminImageView, 0, 0);
            grid.add(receptionistview, 1, 0);
            grid.add(guestview, 2, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Admin.setOnAction(e -> {
            showAdminLoginForm();
        });
        Receptionist.setOnAction(e -> {
            showReceptionistFormLoginForm();
        });
        Guest.setOnAction(e -> {
            try {
                showGuestLoginForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(Admin, 0, 1);
        grid.add(Receptionist, 1, 1);
        grid.add(Guest, 2, 1);
        return grid;
    }

    private void showSelectionForm() throws ClassNotFoundException {
        GridPane SelectionForm = createSelectionForm();
        Scene scene = new Scene(SelectionForm, 400, 300);
        primaryStage.setTitle("Users");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//private GridPane createReceptionistLoginForm() {
//        GridPane grid = new GridPane();
//        grid.setAlignment(javafx.geometry.Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//        Label usernameLabel = new Label("Username:");
//        TextField usernameField = new TextField();
//        Label passwordLabel = new Label("Password:");
//        PasswordField passwordField = new PasswordField();
//        Button loginButton = new Button("Login");
//        //Admin admin1=new Admin(0,"0","Admin","0");
//        User user = new Receptionist(0, "0", "0", "0");
//        //admin1=admin1.SearchUserusername(usernameField.getText(),"Admin");
//        loginButton.setOnAction(e -> {
//            String enteredUsername = usernameField.getText();
//            String enteredPassword = passwordField.getText();
//            Receptionist receptionist = new Receptionist(user.getUserId(enteredUsername, enteredPassword), enteredUsername, "Receptionist", enteredPassword);
//            try {
//                if (currentReceptionist = receptionist.LogIN(enteredUsername, enteredPassword) == true) {
//                    LogedInr.add(receptionist);
//                    receptionist.Loaddata();
//                    showWelcomBackForm();
//                    showReceptionistForm();
//                } else {
//                    Alert ad = new Alert(
//                            AlertType.ERROR);
//                    ad.setHeaderText(null);
//                    ad.setContentText("Invalid Username or Password");
//                    ButtonType bt = new ButtonType("Try Again");
//                    ad.getButtonTypes().setAll(bt,
//                            ButtonType.CANCEL);
//                    Optional<ButtonType> r = ad.showAndWait();
//                    if (r.get() == bt) {
//                        //System.out.println("Skip pressed");
//                        //showLoginForm();
//                    } else {
//                        System.out.println("Cancel pressed");
//                    }
//                }
//            } catch (ClassNotFoundException ex) {
//                System.out.println(ex);
//            }
//            //showStudentForm(currentAdmin);
//        });
//        grid.add(usernameLabel, 0, 0);
//        grid.add(usernameField, 1, 0);
//        grid.add(passwordLabel, 0, 1);
//        grid.add(passwordField, 1, 1);
//        grid.add(loginButton, 1, 2);
//        return grid;
//    }
     private GridPane createReceptionistLoginForm(){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        Button loginButton = new Button("Login");
        Button Back=new Button("Back");
        
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            
     
               
            try{
                Receptionist receptionist=new Receptionist(0,"0","0","0");
                for(Receptionist receptionist1:toop1){
                    if(receptionist1.username.equalsIgnoreCase(enteredUsername)&&receptionist1.Password.equalsIgnoreCase(enteredPassword)){
                        receptionist=receptionist1;
                    }
                }
            if(currentReceptionist=receptionist.LogIN(enteredUsername,enteredPassword)==true){
//                if (!LogedInr.contains(receptionist)) {
//                LogedInr.add(receptionist);
//                receptionist.Loaddata();
//                CUID = receptionist.UserId;
//                System.out.println(CUID);
                LogedInr.add(receptionist);
                receptionist.Loaddata();
                CUID=receptionist.UserId;
                System.out.println(CUID);
                
              
                showWelcomBackForm();
                showReceptionistForm();
               
                 Timeline timeline = new Timeline( new KeyFrame(Duration.millis(200), ec -> {
            try {
                LogedInr.get(0).Loaddata();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
                })
                );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
            }

            else{
                Alert ad = new Alert(
             AlertType.ERROR);
                ad.setHeaderText(null);
                ad.setContentText("Invalid Username or Password");
                ButtonType bt = new ButtonType("Try Again");
                ad.getButtonTypes().setAll(bt,
             ButtonType.CANCEL);
                Optional<ButtonType> r = ad.showAndWait();
                if(r.get()==bt){
               
                }
                else
                System.out.println("Cancel pressed");
            }
            }catch(ClassNotFoundException ex){
                System.out.println(ex);
            }
           
        });
        Back.setOnAction(e->{
            try {
                showSelectionForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(Back,1,3);
        return grid;
    }

    private GridPane createAdminLoginForm() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        Button loginButton = new Button("Login");
        Button Back = new Button("Back");
        //Admin admin1=new Admin(0,"0","Admin","0");
        //User user=new Admin(0,"0","0","0");
        //admin1=admin1.SearchUserusername(usernameField.getText(),"Admin");
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            //Admin admin=new Admin(0,"0","0","0");
            try {
                Admin admin = new Admin(0, "0", "0", "0");
                for (Admin admin1 : toop) {
                    if (admin1.username.equalsIgnoreCase(enteredUsername) && admin1.Password.equalsIgnoreCase(enteredPassword)) {
                        admin = admin1;
                    }
                }
                if (currentAdmin = admin.LogIN(enteredUsername, enteredPassword) == true) {
                    //LogedIn.add(admin);
                    //creatClientAdmin();
                    admin.Loaddata();
                    LogedIn.add(admin);
                    System.out.println(admin.username);
                    System.out.println(LogedIn.size());
                    admin.saveLoggedIn();
                    CUID = admin.UserId;
                    CUN = admin.username;
                    stillin = true;
//                for(int i=0;i<LogedIn.size();i++){
//                    if(LogedIn.get(i).UserId==CUID){
//                        LogedIn.get(i).saveAlltoserver();
//                    }
//                }
                    showWelcomBackForm();
                    showAdminForm();
                    //  if(stillin==true){
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), ec -> {
                        try {
                            if (stillin == true) {
                                for (int i = 0; i < LogedIn.size(); i++) {
                                    if (CUID == LogedIn.get(i).UserId) {
                                        LogedIn.get(i).Loaddata();
                                    }
                                }
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    })
                    );
                    timeline.setCycleCount(Animation.INDEFINITE);
                    timeline.play();
                    // }
                } else {
                    Alert ad = new Alert(AlertType.ERROR);
                    ad.setHeaderText(null);
                    ad.setContentText("Invalid Username or Password");
                    ButtonType bt = new ButtonType("Try Again");
                    ad.getButtonTypes().setAll(bt, ButtonType.CANCEL);
                    Optional<ButtonType> r = ad.showAndWait();
                    if (r.get() == bt) {
                        //System.out.println("Skip pressed");
                        //showLoginForm();
                    } else {
                        System.out.println("Cancel pressed");
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            //showStudentForm(currentAdmin);
            //showStudentForm(currentAdmin);

            //showStudentForm(currentAdmin);
            //showStudentForm(currentAdmin);
        });
        Back.setOnAction(e -> {
            try {
                showSelectionForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(Back, 1, 3);
        return grid;
    }
//    private void showStudentForm(Student currentStudent) {
//        GridPane studentForm = createStudentForm(currentStudent);
//        Scene scene = new Scene(studentForm, 400, 300);
//
//        primaryStage.setTitle("Student Form");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

//    private GridPane createStudentForm(Student currentStudent) {
//        GridPane grid = new GridPane();
//        grid.setAlignment(javafx.geometry.Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        Label nameLabel = new Label("Name: " + currentStudent.firstName + " " + currentStudent.lastName);
//        Label usernameLabel = new Label("Username: " + currentStudent.username);
//        Label ageLabel = new Label("Age: " + currentStudent.age);
//        Label genderLabel = new Label("Gender: " + currentStudent.gender);
//        Label levelLabel = new Label("Level: " + currentStudent.level);
//
//        Button viewCoursesButton = new Button("View Courses");
//        //viewCoursesButton.setOnAction(e -> showViewCoursesForm());
//
//        grid.add(nameLabel, 0, 0);
//        grid.add(usernameLabel, 0, 1);
//        grid.add(ageLabel, 0, 2);
//        grid.add(genderLabel, 0, 3);
//        grid.add(levelLabel, 0, 4);
//        grid.add(viewCoursesButton, 0, 5);
//
//        return grid;
//    }
//    @Override
//    public void start(Stage primaryStage) {
//        TextField username=new TextField("Enter username");
//        TextField password=new TextField("Enter password");
//        Button btnA = new Button();
//        Button btnR =new Button();
//        Button btnG=new Button();
//        btnA.setText("LogIN Admin");
//        btnR.setText("LogIN Receptionist");
//        btnG.setText("LogIN Guest");
//        //string usernameL=username.getText();
//        Admin admin1=new Admin(0,"0","Admin","0");
//        btnA.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    //System.out.println("Hello World!");
//                    admin1.LogIN(username.getText(),password.getText());
//                } catch (ClassNotFoundException ex) {
//                    System.out.println(ex);
//                }
//            }
//        });
//        
//        Grid root = new StackPane();
//        root.getChildren().add(btnA);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }    
    private void shownumofreservedroom() throws ClassNotFoundException {
        GridPane rr = createnumofreservedroom();
        Scene scene = new Scene(rr, 500, 500);
        primaryStage.setTitle("data of rooms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private GridPane createnumofreservedroom() {
        GridPane reserved = new GridPane();
        reserved.add(new Label("Enter Start Date"), 0, 0);
        reserved.add(new Label("Enter End Date"), 0, 1);

        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        reserved.add(startDatePicker, 2, 0);
        reserved.add(endDatePicker, 2, 1);
        reserved.setVgap(15);
        reserved.setHgap(15);
        reserved.setPadding(new Insets(30));

        Button back = new Button("Back");
        Button checkbutton = new Button("Get num of reserved room");
        reserved.add(back, 1, 3);
        reserved.add(checkbutton, 0, 3);

        checkbutton.setOnAction(e -> {
            try {

                LocalDate startDate = startDatePicker.getValue();
                LocalDate endDate = endDatePicker.getValue();

                int numofreserved = LogedIn.get(0).NumberOfReservation(startDate, endDate);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Number of Reserved Rooms");
                alert.setHeaderText(null);
                alert.setContentText("Number of reserved rooms between " + startDate + " and " + endDate + " is " + numofreserved);
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        back.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return reserved;
    }

    private void shownumofreservedroomm() throws ClassNotFoundException {
        GridPane rr = createmostreservedroom();
        Scene scene = new Scene(rr, 500, 500);
        primaryStage.setTitle("data of rooms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createmostreservedroom() {
        GridPane reserved = new GridPane();
        reserved.add(new Label("Enter Start Date"), 0, 0);
        reserved.add(new Label("Enter End Date"), 0, 1);

        // Use DatePicker instead of TextField for date input
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        reserved.add(startDatePicker, 2, 0);
        reserved.add(endDatePicker, 2, 1);
        reserved.setVgap(15);
        reserved.setHgap(15);
        reserved.setPadding(new Insets(30));
        Button bkk = new Button("Back");
        Button checkbutton = new Button("get most  reserved room");
        reserved.add(bkk, 1, 3);
        reserved.add(checkbutton, 0, 3);
        checkbutton.setOnAction(e -> {
            try {

                LocalDate startDate = startDatePicker.getValue();
                LocalDate endDate = endDatePicker.getValue();

                String mostReserved = LogedIn.get(0).getMostReservedRoom(startDate, endDate);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("most reserved  Rooms");
                alert.setHeaderText(null);
                alert.setContentText("most reserved  between " + startDate + " and " + endDate + " is " + mostReserved);
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        bkk.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return reserved;

    }

    private void showmostrevenue() throws ClassNotFoundException {
        GridPane ss = createmostrevenueroom();
        Scene scene = new Scene(ss, 500, 500);
        primaryStage.setTitle("data of rooms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createmostrevenueroom() {
        GridPane mrev = new GridPane();
        mrev.add(new Label("Enter Start Date"), 0, 0);
        mrev.add(new Label("Enter End Date"), 0, 1);

        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        mrev.add(startDatePicker, 2, 0);
        mrev.add(endDatePicker, 2, 1);
        mrev.setVgap(15);
        mrev.setHgap(15);
        mrev.setPadding(new Insets(30));
        Button bkbutton = new Button("Back");
        Button chck = new Button("get most revenue room ");
        mrev.add(bkbutton, 1, 3);
        mrev.add(chck, 0, 3);
        chck.setOnAction(e -> {
            try {

                LocalDate startDate = startDatePicker.getValue();
                LocalDate endDate = endDatePicker.getValue();

                String mostrevenued = LogedIn.get(0).getMostRevenuedRoom(startDate, endDate);
                //hena lama function el search be el id 3ashan yetal3 el category 

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("revenue of Rooms");
                alert.setHeaderText(null);
                alert.setContentText("revenue of  rooms between " + startDate + " and " + endDate + " is " + mostrevenued);
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });
        bkbutton.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return mrev;

    }

    private void shownumofreqservice() throws ClassNotFoundException {
        GridPane ss = createnumofreq();
        Scene scene = new Scene(ss, 500, 500);
        primaryStage.setTitle("data of service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createnumofreq() {
        GridPane req = new GridPane();
        req.add(new Label("Enter Start Date"), 0, 0);
        req.add(new Label("Enter End Date"), 0, 1);
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        req.add(startDatePicker, 2, 0);
        req.add(endDatePicker, 2, 1);
        req.setVgap(15);
        req.setHgap(15);
        req.setPadding(new Insets(30));

        Button backbutton = new Button("Back");
        Button check = new Button("Get num of requests");
        req.add(backbutton, 1, 3);
        req.add(check, 0, 3);

        check.setOnAction(e -> {
            LocalDate localStartDate = startDatePicker.getValue();
            LocalDate localEndDate = endDatePicker.getValue();

            Date startDate = java.sql.Date.valueOf(localStartDate);
            Date endDate = java.sql.Date.valueOf(localEndDate);

            int numberOfRequests = LogedIn.get(0).NumberOfrequests(startDate, endDate);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Number of Requests");
            alert.setHeaderText(null);
            alert.setContentText("Number of requests between " + localStartDate + " and " + localEndDate + "is " + numberOfRequests);
            alert.showAndWait();
        });

        backbutton.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return req;
    }

    private void showmostreqserv() throws ClassNotFoundException {
        GridPane most = createmostreqser();
        Scene scene = new Scene(most, 500, 500);
        primaryStage.setTitle("data of service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createmostreqser() {
        GridPane mostreq = new GridPane();
        mostreq.add(new Label("Enter Start Date(yyyy-mm-dd)"), 0, 0);
        mostreq.add(new Label("Enter End Date(yyyy-mm-dd)"), 0, 1);
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        mostreq.add(startDatePicker, 2, 0);
        mostreq.add(endDatePicker, 2, 1);
        mostreq.setVgap(15);
        mostreq.setHgap(15);
        mostreq.setPadding(new Insets(30));
        Button bb = new Button("Back");
        Button ck = new Button("get most requested service");
        mostreq.add(bb, 1, 3);
        mostreq.add(ck, 0, 3);
        ck.setOnAction(e -> {
            try {

                LocalDate localStartDate = startDatePicker.getValue();
                LocalDate localEndDate = endDatePicker.getValue();

                Date startDate = java.sql.Date.valueOf(localStartDate);
                Date endDate = java.sql.Date.valueOf(localEndDate);

                String mostRequested = LogedIn.get(0).getMostRequestedService(startDate, endDate);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Most Requested Service");
                alert.setHeaderText(null);
                alert.setContentText("Most requested service between " + localStartDate + " and " + localEndDate + "is " + mostRequested);
                alert.showAndWait();
            } catch (ParseException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        });
        bb.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return mostreq;

    }

    private void showmostrevserv() throws ClassNotFoundException {
        GridPane mostrev = createmostrevenueser();
        Scene scene = new Scene(mostrev, 500, 500);
        primaryStage.setTitle("data of service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createmostrevenueser() {
        GridPane mostrevenue = new GridPane();
        mostrevenue.add(new Label("Enter Start Date"), 0, 0);
        mostrevenue.add(new Label("Enter End Date"), 0, 1);

        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        mostrevenue.add(startDatePicker, 2, 0);
        mostrevenue.add(endDatePicker, 2, 1);
        mostrevenue.setVgap(15);
        mostrevenue.setHgap(15);
        mostrevenue.setPadding(new Insets(30));

        Button backb = new Button("Back");
        Button chk = new Button("Get most revenue service");
        mostrevenue.add(backb, 1, 3);
        mostrevenue.add(chk, 0, 3);

        chk.setOnAction(e -> {
            try {

                LocalDate localStartDate = startDatePicker.getValue();
                LocalDate localEndDate = endDatePicker.getValue();

                Date startDate = java.sql.Date.valueOf(localStartDate);
                Date endDate = java.sql.Date.valueOf(localEndDate);

                String mostRequested = LogedIn.get(0).getMostRequestedService(startDate, endDate);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Most revenue Service");
                alert.setHeaderText(null);
                alert.setContentText("Most revenue service between " + localStartDate + " and " + localEndDate + " is " + mostRequested);
                alert.showAndWait();
            } catch (ParseException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });

        backb.setOnAction(e -> {
            try {
                showAdminForm();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return mostrevenue;
    }

    private void showGuestLoginForm() throws ClassNotFoundException {
        GridPane GuestLoginForm = createGuestLoginForm();
        Scene scene = new Scene(GuestLoginForm, 400, 300);
        primaryStage.setTitle("Guest login form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showGuestForm() throws ClassNotFoundException {
        GridPane GuestForm = createGuestForm();
        Scene scene = new Scene(GuestForm, 800, 600);
        primaryStage.setTitle("Guest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createRequestService() {
        GridPane requestService = new GridPane();
        requestService.setVgap(15);
        requestService.setHgap(15);
        requestService.setPadding(new Insets(30));

        Label requestLabel = new Label("Select Request Type:");
        requestService.add(requestLabel, 0, 0);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton foodRequest = new RadioButton("Food Request");
        RadioButton cleaningRequest = new RadioButton("Cleaning Request");
        RadioButton maintenanceRequest = new RadioButton("Maintenance Request");

        foodRequest.setToggleGroup(toggleGroup);
        cleaningRequest.setToggleGroup(toggleGroup);
        maintenanceRequest.setToggleGroup(toggleGroup);

        requestService.add(foodRequest, 0, 1);
        requestService.add(cleaningRequest, 0, 2);
        requestService.add(maintenanceRequest, 0, 3);
        Button back = new Button("Back");
        requestService.add(back, 1, 4);
        back.setOnAction(e -> {
            try {
                showGuestForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        Button requestButton = new Button("Request");
        requestService.add(requestButton, 0, 4);

        requestButton.setOnAction(e -> {

            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

            if (selectedRadioButton != null) {

                String requestType = selectedRadioButton.getText();
                //double cost = calculatePriceForType(requestType);
                LoggedInr.get(0).requestService(requestType);

                showConfirmationAlert(requestType + " request has been submitted.");

            }
        });

        return requestService;

    }

    private void showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showrequest() {
        GridPane mm = createRequestService();
        Scene scene = new Scene(mm, 500, 500);
        primaryStage.setTitle("Request");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private GridPane createGuestLoginForm() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        Button loginButton = new Button("Login");
        Button Back = new Button("Back");
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            int userid = LoggedInr.size();
            Guest guest = new Guest(userid, enteredUsername, "Guest", enteredPassword);
            try {
                if (guest.LogIN(enteredUsername, enteredPassword) == true) {
                    LoggedInr.add(guest);
                    guest.Loaddata();
                    System.out.println(guest.UserId);
                    showWelcomBackForm();
                    showGuestForm();
                } else {
                    Alert ad = new Alert(
                            AlertType.ERROR);
                    ad.setHeaderText(null);
                    ad.setContentText("Invalid Username or Password");
                    ButtonType bt = new ButtonType("Try Again");
                    ad.getButtonTypes().setAll(bt,
                            ButtonType.CANCEL);
                    Optional<ButtonType> r = ad.showAndWait();
                    if (r.get() == bt) {
                        //System.out.println("Skip pressed");
                        //showLoginForm();
                    } else {
                        System.out.println("Cancel pressed");
                    }
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });
        Back.setOnAction(e -> {
            try {
                showSelectionForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);;
            }
        });
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(Back, 2, 2);
        return grid;
    }

    public GridPane createGuestForm() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 30, 30, 30));

        TableView<Reservation> reservationTable = new TableView<>();
        TableColumn<Reservation, String> reservationInfoColumn = new TableColumn<>("Reservation Info");
        reservationInfoColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));

        TableColumn<Reservation, String> roomNumberColumn = new TableColumn<>("Room Number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        // Add more columns based on your Reservation class
        reservationTable.getColumns().addAll(reservationInfoColumn, roomNumberColumn);

        Button viewDetailsButton = new Button("View Reservation Details");
        viewDetailsButton.setOnAction(e -> {
            Reservation selectedReservation = reservationTable.getSelectionModel().getSelectedItem();
            if (selectedReservation != null) {
                showReservationDetails(selectedReservation);
            } else {
                showAlert("No reservation selected", "Please select a reservation to view details.");
            }
        });

        Button rateBookingButton = new Button("Rate Booking");
        rateBookingButton.setOnAction(e -> {
            Reservation selectedReservation = reservationTable.getSelectionModel().getSelectedItem();
            if (selectedReservation != null) {
                showRateBookingDialog(selectedReservation);
            } else {
                showAlert("No reservation selected", "Please select a reservation to rate the booking.");
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e -> {
            try {
                showGuestLoginForm();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        });

        Button viewAmenitiesButton = new Button("View Hotel Amenities");
        viewAmenitiesButton.setOnAction(e -> showHotelAmenities());

        Button requestService = new Button("Request Service");
        requestService.setOnAction(e -> showrequest());

        grid.add(viewDetailsButton, 0, 0);
        grid.add(rateBookingButton, 0, 1);
        grid.add(back, 0, 4);
        grid.add(viewAmenitiesButton, 0, 3);
        grid.add(requestService, 0, 2);

        grid.add(reservationTable, 2, 0, 3, 5); // Span the table across 3 columns and 5 rows

        loadReservationHistory(reservationTable);

        return grid;
    }

    private void showHotelAmenities() {
        // Implement the logic to display hotel amenities (you can customize this part based on your needs)
        // For demonstration purposes, let's show a simple alert with a message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Hotel Amenities");
        alert.setHeaderText("List of Hotel Amenities");

        String amenities = "1. Free Wi-Fi\n" + "2. Fitness Center\n" + "3. Swimming Pool\n" + "4. Restaurant\n" + "5. Room Service";

        alert.setContentText(amenities);
        alert.showAndWait();
    }

    // ... (existing code)
    private void loadReservationHistory(TableView<Reservation> reservationTable) {
        ObservableList<Reservation> reservationData = FXCollections.observableArrayList(reservations);
        reservationTable.setItems(reservationData);
    }

    private void showReservationDetails(Reservation reservation) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reservation Details");
        alert.setHeaderText("Details for Reservation ID: " + reservation.getReservationID());

        // Assuming Reservation class has appropriate getters for details
        String details = "Room Number: " + reservation.getRoomID() + "\n"
                + "Guest Name: " + reservation.getGuest() + "\n"
                + "Service Info: " + reservation.getService() + "\n"
                + "Check-in Date: " + reservation.getCheckInDate() + "\n"
                + "Check-out Date: " + reservation.getCheckOutDate();

        alert.setContentText(details);
        alert.showAndWait();
    }

    private void showRateBookingDialog(Reservation reservation) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Rate Booking");
        dialog.setHeaderText("Rate your booking for Reservation ID: " + reservation.getReservationID());

        ButtonType rateButtonType = new ButtonType("Rate", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(rateButtonType, ButtonType.CANCEL);

        Label ratingLabel = new Label("Rating:");
        Spinner<Integer> ratingSpinner = new Spinner<>(0, 5, 0);

        GridPane grid = new GridPane();
        grid.add(ratingLabel, 0, 0);
        grid.add(ratingSpinner, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == rateButtonType) {
                return ratingSpinner.getValue();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(rating -> {

            reservation.setRating(rating);
            System.out.println("Rating submitted: " + rating);
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

