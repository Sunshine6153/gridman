package control;

import common.ButtonHoverEffect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import common.Alertutils;
import module.Gridman;
import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.Map;

import static common.ManFileutils.readMapObject;

public class GridmanLoginController {
    @FXML
    private Button btnlogin;
    @FXML
    private TextField txtaccount;
    @FXML
    private TextField txtpassword;
    
    @FXML
    public void initialize() {
        new ButtonHoverEffect(btnlogin);
    }

    public void Gridmanlogin(ActionEvent event) throws IOException {
        File file = new File("gridman.txt");
        if (!file.exists()) {
            System.out.println("文件不存在，登录失败");
            return;
        }
        Map<String, Gridman> GridmanMap = readMapObject("gridman.txt");
        String account = txtaccount.getText();
        String password = txtpassword.getText();

        if (account == null || account.trim().isEmpty()) {
            Alertutils.showdialog("Warning", "账户不得为空");
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            Alertutils.showdialog("Warning", "密码不得为空");
            return;
        }

        if (GridmanMap.containsKey(account)) {
            Gridman gridman = GridmanMap.get(account);

            if (password.equals(gridman.getPassword())) {
                Alertutils.showdialog("login", "登录成功");
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL url = getClass().getResource("../view/gridmanreview.fxml");
                fxmlLoader.setLocation(url);
                Parent root = fxmlLoader.load();
                Scene scene = btnlogin.getScene();
                scene.setRoot(root);
                Stage stage = (Stage) scene.getWindow();
                stage.show();
            } else {
                Alertutils.showdialog("Warning", "密码错误");
            }
        } else {
            Alertutils.showdialog("Warning", "账户不存在");
        }
    }
}