/**
 * Lumbini Parnas
 * Human Computer Interaction
 * Project 4
 * This is the Main file of the program.
 *
 * */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage mainWindow;
    private StackPane root;
    private UserGUI userGUI;
    private Scene homepage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.root = new StackPane();
        this.homepage = new Scene(this.root, 1000, 625);
        this.mainWindow = primaryStage;

        this.userGUI = new UserGUI(this.mainWindow, this.root, this.homepage);
        this.userGUI.initWindow();

        primaryStage.setTitle("Linux Man Pages");
        primaryStage.setScene(homepage);

        this.mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}