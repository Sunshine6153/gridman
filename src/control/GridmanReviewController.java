package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import common.Alertutils;
import common.GridFileutils;
import module.Grid;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GridmanReviewController {
    @FXML
    private TableView<Grid> tableView;
    @FXML
    private TableColumn<Grid, String> collevel;
    @FXML
    private TableColumn<Grid, String> colsite;
    @FXML
    private Button deliverBtn;

    private ObservableList<Grid> tableList = FXCollections.observableArrayList();
    private Map<String, Grid> gridMap = new HashMap<>();

    @FXML
    public void initialize() {
        // 初始化动画效果
        setupButtonAnimation();
        setupTableRowAnimation();

        // 配置表格列
        collevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colsite.setCellValueFactory(new PropertyValueFactory<>("site"));

        // 加载数据
        loadGridData();
    }

    private void setupButtonAnimation() {
        // 悬停时放大到110%
        ScaleTransition grow = new ScaleTransition(Duration.millis(200), deliverBtn);
        grow.setToX(1.1);
        grow.setToY(1.1);

        // 离开时恢复到100%
        ScaleTransition shrink = new ScaleTransition(Duration.millis(200), deliverBtn);
        shrink.setToX(1.0);
        shrink.setToY(1.0);

        deliverBtn.setOnMouseEntered(e -> {
            shrink.stop();
            grow.playFromStart();
        });

        deliverBtn.setOnMouseExited(e -> {
            grow.stop();
            shrink.playFromStart();
        });
    }


    private void setupTableRowAnimation() {
        tableView.setRowFactory(tv -> {
            TableRow<Grid> row = new TableRow<>();

            // 悬停时放大到103%
            ScaleTransition rowGrow = new ScaleTransition(Duration.millis(150), row);
            rowGrow.setToX(1.03);
            rowGrow.setToY(1.03);

            // 离开时恢复到100%
            ScaleTransition rowShrink = new ScaleTransition(Duration.millis(150), row);
            rowShrink.setToX(1.0);
            rowShrink.setToY(1.0);

            row.setOnMouseEntered(e -> {
                if (!row.isEmpty()) {
                    rowShrink.stop();
                    rowGrow.playFromStart();
                }
            });

            row.setOnMouseExited(e -> {
                if (!row.isEmpty()) {
                    rowGrow.stop();
                    rowShrink.playFromStart();
                }
            });

            return row;
        });
    }

    private void loadGridData() {
        gridMap = GridFileutils.readMapObject("grid.txt");
        if (gridMap == null) {
            gridMap = new HashMap<>();
        }
        tableList.addAll(gridMap.values());
        tableView.setItems(tableList);
    }

    public void todeliver() {
        try {
            Grid selectedGrid = tableView.getSelectionModel().getSelectedItem();
            if (selectedGrid == null) {
                Alertutils.showdialog("提示", "请先选择一条记录");
                return;
            }

            // 加载目标FXML文件
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/gridmandeliver.fxml"));
            Parent root = fxmlLoader.load();
            GridmanDeliverController deliverController = fxmlLoader.getController();
            deliverController.initData(selectedGrid);

            // 创建并显示对话框
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("检测数据提交 - " + selectedGrid.getSite());
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

        } catch (IOException e) {
            Alertutils.showdialog("错误", "加载界面失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}