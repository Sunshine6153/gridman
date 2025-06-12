package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import common.Alertutils;
import common.GridFileutils;
import moudle.Grid;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GridmanReviewController {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Grid,String> collevel;
    @FXML
    private TableColumn<Grid,String> colsite;

    private ObservableList tableList= FXCollections.observableArrayList();
    private Map gridMap=new HashMap<>();

    @FXML
    public void  initialize(){
        //绑定实体类的属性和表格对应显示的列
        collevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colsite.setCellValueFactory(new PropertyValueFactory<>("site"));
        gridMap = GridFileutils.readMapObject("grid.txt");
        if(gridMap == null) {
            gridMap = new HashMap<>();
        }
        tableList.addAll(gridMap.values());
        tableView.setItems(tableList);
    }
    public void todeliver() throws IOException {
        Grid selectedGrid = (Grid) tableView.getSelectionModel().getSelectedItem();
        if (selectedGrid == null) {
            Alertutils.showdialog("提示", "请先选择一条记录");
            return;}
        else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL url = getClass().getResource("../view/gridmandeliver.fxml");
            fxmlLoader.setLocation(url);
            Parent root = fxmlLoader.load();
            //获得目标GridmanDeliverController并传递数据
            GridmanDeliverController deliverController = fxmlLoader.getController();
            if (deliverController == null) {
                Alertutils.showdialog("错误", "初始化失败");
                return;
            }
            deliverController.initData(selectedGrid);
            //显示dialog
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL); // 设置为模态窗口
            dialog.setTitle("检测数据提交 - " + selectedGrid.getSite());
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
        }
    }
}
