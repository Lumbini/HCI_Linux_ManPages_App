/**
 * Lumbini Parnas
 * Human Computer Interaction
 * Project 4
 * This file handles the GUI elements
 *
 * */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javafx.scene.Scene;
import javafx.scene.paint.Color;



public class UserGUI{

    //Info about each topic
    String info;

    // stage and root variables
    private Stage mainWindow;
    private StackPane mainLayout;
    private StackPane optionLayout;


    //Initialize Buttons
    Button userCommands;
    Button systemCalls;
    Button libraryFunctions;
    Button devices;
    Button files;
    Button superUser;
    Button home;
    Button allCommands;
    Button searchButt;

    //Array of Linux Man Pages
    private ArrayList<Page> linuxPages;
    ReadXML linuxXML;

    //Scenes handled but this App
    Scene homePage;
    Scene options;

    //Labels
    Label title;
    Label empty;

    //Search Field
    TextField search;
    TextArea showPage;

    //Layouts
    HBox header;
    HBox headerLeft;
    HBox headerRight;
    VBox leftMenu;
    HBox rightMenu;
    HBox centerMenu;
    BorderPane baseLayout;

    //ListView for viewing Commands
    ListView<String> commandList;
    ArrayList<String> commandsToAdd;


    public UserGUI(Stage mainWindow, StackPane mainlayout, Scene homePage){
        this.mainWindow = mainWindow;
        this.mainLayout = mainlayout;
        this.homePage = homePage;
        mainWindow.setResizable(false);

        this.linuxXML = new ReadXML();
        linuxXML.initPage();
        linuxPages = linuxXML.getManpages();

        //setup stylesheet and id of root
        this.mainLayout.getStylesheets().add("styleMe.css");
        this.mainLayout.setId("background");
        this.createComponents();
    }

    public void createComponents(){
        commandList = new ListView<String>();
        commandsToAdd = new ArrayList<String>();
        header = new HBox(20);
        leftMenu = new VBox(33);
        rightMenu = new HBox(20);
        centerMenu = new HBox();
        title = new Label("Linux - Manual");
        empty = new Label("      ");
        search = new TextField();
        showPage = new TextArea();
        showPage.setFont(new Font("Trebuchet MS", 14));
        showPage.setEditable(false);
        baseLayout = new BorderPane();
        headerLeft = new HBox();
        headerRight = new HBox();
    }


    public void initWindow(){

        //Initialize Buttons in the Window
        initButtons();

        //ListView for options
        commandList.setOpacity(0.75);


        //Search Option
        search.setPrefWidth(200);
        search.setBackground(new Background(new BackgroundFill(Color.web("#b3b3b3"), new CornerRadii(3), null)));

        //Edit Top Menu
        title.setId("title");
        empty.setId("title");
        title.setTextFill(Color.web("#408000"));
        header.setAlignment(Pos.TOP_CENTER);
        header.setPadding(new Insets(40, 10, 20, 40));
        header.getChildren().add(title);
        baseLayout.setTop(header);

        //Edit left Menu
        leftMenu.setAlignment(Pos.TOP_LEFT);
        leftMenu.setPadding(new Insets(40, 10, 20, 170));
        leftMenu.getChildren().addAll(userCommands, systemCalls, libraryFunctions, devices, files, superUser, allCommands);
        baseLayout.setLeft(leftMenu);

        //Edit Right Menu
        rightMenu.setAlignment(Pos.TOP_RIGHT);
        rightMenu.setPadding(new Insets(40, 170, 20, 20));
        rightMenu.getChildren().addAll(search, searchButt);
        baseLayout.setRight(rightMenu);

        //Edit Centre Menu
        centerMenu.setAlignment(Pos.TOP_CENTER);
        centerMenu.setPadding(new Insets(40, 30, 95, 10));

        //Add base layout to main layout
        mainLayout.getChildren().add(baseLayout);
    }

    public void initOptions() {

        //Change Background
        mainLayout.setId("pageBackground");

        //Edit top menu
        header.getChildren().clear();
        header.setAlignment(Pos.TOP_LEFT);
        title.setId("optionsTitle");
        header.getChildren().addAll(title, empty, search, searchButt, home);

        //Edit left Menu
        leftMenu.setPadding(new Insets(10, 10, 20, 40));

        //Set up Page display
        showPage.setPrefWidth(550);
        showPage.setPrefHeight(500);
        showPage.setWrapText(true);
        showPage.setOpacity(0.75);
        showPage.setBackground(new Background(new BackgroundFill(Color.web("#b3b3b3"), new CornerRadii(3), null)));

        //Edit right menu
        rightMenu.getChildren().clear();
        rightMenu.setPadding(new Insets(10, 40, 95, 10));
        rightMenu.getChildren().add(showPage);

        //Set up List to display
        commandList.setPrefWidth(250);
        commandList.setOnMouseClicked(e -> cellClicked(commandList.getSelectionModel().getSelectedItem()));

        //Edit Centre Menu
        centerMenu.getChildren().clear();
        centerMenu.setPadding(new Insets(10, 10, 95, 5));
        centerMenu.getChildren().add(commandList);
        baseLayout.setCenter(centerMenu);
    }

    public void initButtons() {

        //Create Buttons
        userCommands = new Button("User Commands");
        systemCalls = new Button("System Calls");
        libraryFunctions = new Button("Library Functions");
        devices = new Button("Device Commands");
        files = new Button("Files");
        superUser = new Button("Superuser/System Admin");
        allCommands = new Button("All Commands");
        home = new Button("Home");
        searchButt = new Button("Search");

        //Set Button widths
        userCommands.setPrefWidth(180);
        systemCalls.setPrefWidth(180);
        libraryFunctions.setPrefWidth(180);
        devices.setPrefWidth(180);
        files.setPrefWidth(180);
        superUser.setPrefWidth(180);
        allCommands.setPrefWidth(180);
        searchButt.setPrefWidth(100);
        home.setPrefWidth(100);

        //Assign button actions
        userCommands.setOnAction(e -> {
            info = "User Command: " + "\n\nGeneral Commands used by the User.\n" +
                    "\nPlease select a command on the left to view details";
            buttonClicked("User", "USER COMMANDS", info);
        });
        systemCalls.setOnAction(e ->{
            info = "System Calls: " + "\n\nCommands used to request a service from the kernel. " +
                    "The functions which change the execution mode of the program from user mode " +
                    "to kernel mode are known as system calls.\n" + "\nPlease select a command on the left to view details";
            buttonClicked("System", "SYSTEM CALLS", info);
        });
        libraryFunctions.setOnAction(e -> {
            info = "Library Functions: " + "\n\nThe functions which are a part of standard C library " +
                    "are known as Library functions.  \n" + "\nPlease select a command on the left to view details";
            buttonClicked("Library","LIBRARY FUNCTIONS", info);
        });
        devices.setOnAction(e -> {
            info = "Devices: " + "\n\nCommands used to interact with devices connected to the system. \n" +
                    "\nPlease select a command on the left to view details";
            buttonClicked("Devices","DEVICES", info);
        });
        files.setOnAction(e -> {
            info = "Files: " + "\n\nCommands used to manage files and file systems. \n" +
                    "\nPlease select a command on the left to view details";
            buttonClicked("Files","FILES", info);
        });
        superUser.setOnAction(e -> {
            info = "Super User/System Admin: " + "\n\nCommands used by System Administrators and Super users - Advance commands.\n"
                    + "\nPlease select a command on the left to view details";
            buttonClicked("SuperUser", "SUPER USER/ADMIN", info);
        });
        allCommands.setOnAction(e -> {
            info = "All Commands: " + "\n\nUser Commands: General Commands used by the User.\n" +
                    "\nSystem Calls: Commands used to request a service from the kernel \n" +
                    "\nLibrary Functions: The functions which are a part of standard C library are known as Library functions.  \n" +
                    "\nDevices: Commands used to interact with devices connected to the system. \n" +
                    "\nFiles: Commands used to manage files and file systems. \n" +
                    "\nSuper User/System Admin: Commands used by System Administrators and Super users - Advance commands. \n" +
                    "\nPlease select a command on the left to view details";
            buttonClicked("All","ALL COMMANDS", info);
        });
        home.setOnAction(e -> buttonClicked("Home","Home", info));
    }

    //Button Clicked Handler
    public void buttonClicked(String buttonName, String menuName, String info){
        if (buttonName.equals("Home")){
            mainLayout.getChildren().clear();
            this.mainLayout.setId("background");
            this.createComponents();
            this.initWindow();
        }
        else{
            initOptions();
            showPage.setText(info);
            populateList(buttonName, menuName);
        }

    }

    //Cell Clicked Handler
    public void cellClicked(String name){
        mainLayout.setId("pageBackground");
        if (!commandsToAdd.contains(name)){
            showPage.setText(info);
        }

        for (Page pages : linuxPages) {
            if (pages.compareName(name)){
                showPage.setText(pages.toString());
            }
        }

    }

    //Populate the list view
    public void populateList(String buttonName, String menuName) {
        commandList.getItems().clear();
        commandList.getItems().add(menuName);
        commandsToAdd.clear();
        for (Page pages : linuxPages) {
            if (pages.compareType(buttonName) || buttonName.equals("All")) {
                commandList.getItems().add(pages.getName());
                commandsToAdd.add(pages.getName());
            }
        }
    }
}
