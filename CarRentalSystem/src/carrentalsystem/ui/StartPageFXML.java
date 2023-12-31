package carrentalsystem.ui;

import carrentalsystem.dl.DirectorDAO;
import carrentalsystem.pojo.Director;
import carrentalsystem.utility.DAOConstants;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartPageFXML extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
////                try
////                {
////                    DirectorDAO directorDAO=new DirectorDAO();
////                    Director director=new Director("fn","ln","cn","em","s","c","pro","zip","pass");
////                    directorDAO.registerDirector(director);
////                    System.out.println("Director Added");
////                }catch(Exception ex)
////                {
////                    System.out.println(ex.getMessage());
////                }
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        Parent root=FXMLLoader.load(getClass().getResource("RegisterHomeWindow.fxml"));
        Scene scene = new Scene(root, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
        primaryStage.setTitle("Property Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
