package control;

import common.Alertutils;
import common.DeliverFileutils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import moudle.Grid;

import javafx.scene.control.*;
import moudle.GridDeliver;

import java.util.HashMap;
import java.util.Map;

import static common.AQICalculator.*;

public class GridmanDeliverController {
    @FXML
    private TableColumn txtUser;
    @FXML
    private TableColumn txtGrid;
    @FXML
    private TableColumn txtSite;
    @FXML
    private TableColumn txtLevel;
    @FXML
    private TableColumn txtInformation;

    @FXML
    private TextField SO2con;
    @FXML
    private TextField SO2level;
    @FXML
    private TextField COcon;
    @FXML
    private TextField Colevel;
    @FXML
    private TextField PM25con;
    @FXML
    private TextField PM25level;
    @FXML
    private TextField AQlevelField;
    @FXML
    private TableView<Grid> dataTable;

    private ObservableList<Grid> tableData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 配置表格列
        txtUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        txtGrid.setCellValueFactory(new PropertyValueFactory<>("grid"));
        txtSite.setCellValueFactory(new PropertyValueFactory<>("site"));
        txtLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        txtInformation.setCellValueFactory(new PropertyValueFactory<>("information"));

        dataTable.setItems(tableData);
    }

    public void initData(Grid selectedGrid) {
        if(selectedGrid == null) return;
        tableData.clear();
        tableData.add(selectedGrid);
        // 自动选择第一行
        dataTable.getSelectionModel().selectFirst();
    }

    @FXML
    private void DefLevel() throws NumberFormatException {
        try{
            int so2 = Integer.parseInt(SO2con.getText());
            int co = Integer.parseInt(COcon.getText());
            int pm25 = Integer.parseInt(PM25con.getText());

            int so2level=getSO2Level(so2);
            int colevel=getCOLevel(co);
            int pm25level=getPM25Level(pm25);
            int AQlevel=getLevelDescription(so2level,colevel,pm25level);

            SO2level.setText(String.valueOf(so2level));
            Colevel.setText(String.valueOf(colevel));
            PM25level.setText(String.valueOf(pm25level));
            AQlevelField.setText(String.valueOf(AQlevel));
        }catch (NumberFormatException e){
            Alertutils.showdialog("输入错误","请输入有效的数字");
        }
    }
    @FXML
    private void submitData() {
        try {
            GridDeliver deliverData = new GridDeliver(
                    dataTable.getSelectionModel().getSelectedItem().getSite(),
                    SO2con.getText(),
                    COcon.getText(),
                    PM25con.getText(),
                    SO2level.getText(),
                    Colevel.getText(),
                    PM25level.getText(),
                    AQlevelField.getText()
            );

            Map<String, GridDeliver> dataMap = new HashMap<>();
            dataMap.put(deliverData.getSite(), deliverData);  // 使用 site 字段作为 key

            DeliverFileutils.writeMapobject("src/deliver_data.txt", dataMap);

            Alertutils.showdialog("成功", "数据已保存!");
            clearFields();

        } catch (Exception e) {
            Alertutils.showdialog("错误", "保存失败: " + e.getMessage());
        }
    }
    //清空输入框
    private void clearFields() {
        SO2con.clear();
        SO2level.clear();
        COcon.clear();
        Colevel.clear();
        PM25con.clear();
        PM25level.clear();
        AQlevelField.clear();
    }

}
