package control;

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
import javafx.scene.input.MouseEvent;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

import static common.ManFileutils.readMapObject;

public class GridmanLoginController {
    @FXML
    private Button btnlogin;
    @FXML
    private TextField txtaccount;
    @FXML
    private TextField txtpassword;

    private ScaleTransition scaleIn;
    private ScaleTransition scaleOut;

    @FXML
    public void initialize() {
        // 初始化放大动画 (悬停时)
        scaleIn = new ScaleTransition(Duration.millis(200), btnlogin);
        scaleIn.setToX(1.1);  // 放大到110%
        scaleIn.setToY(1.1);

        // 初始化缩小动画 (离开时)
        scaleOut = new ScaleTransition(Duration.millis(200), btnlogin);
        scaleOut.setToX(1.0);  // 恢复到100%
        scaleOut.setToY(1.0);

        // 设置按钮的鼠标悬停效果 - 仅放大
        btnlogin.setOnMouseEntered(this::handleButtonHover);

        // 设置按钮的鼠标离开效果 - 仅恢复
        btnlogin.setOnMouseExited(this::handleButtonExit);
    }

    // 处理悬停效果 - 仅放大
    private void handleButtonHover(MouseEvent event) {
        scaleOut.stop();
        scaleIn.play();
    }

    // 处理离开效果 - 仅恢复
    private void handleButtonExit(MouseEvent event) {
        scaleIn.stop();
        scaleOut.play();
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