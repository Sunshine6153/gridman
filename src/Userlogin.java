import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
public class Userlogin extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader();
        URL url=getClass().getResource("view/gridmanlogin.fxml");
        fxmlLoader.setLocation(url);
        Parent root=fxmlLoader.load();
        stage.setTitle("东软环保公众监督平台—网格员端");
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}