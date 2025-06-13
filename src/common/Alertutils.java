package common;

import javafx.scene.control.Alert;

public class Alertutils {
    public static void showdialog(String title,String content){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
