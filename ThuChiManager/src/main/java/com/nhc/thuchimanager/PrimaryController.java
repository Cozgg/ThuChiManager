package com.nhc.thuchimanager;

import com.nhc.pojo.ChiTieu;
import com.nhc.pojo.DanhMuc;
import com.nhc.pojo.LoaiChiTieu;
import com.nhc.services.ChiTieuItDecorator;
import com.nhc.services.ChiTieuNhieuDecorator;
import com.nhc.services.ChiTieuServices;
import com.nhc.services.DanhMucServices;
import com.nhc.services.IChiTieu;
import com.nhc.services.LoaiChiTieuServices;
import com.nhc.services.Show;
import com.nhc.services.ShowLoaiChiTieuBanThanStrategy;
import com.nhc.services.ShowLoaiChiTieuGiaDinhStrategy;
import com.nhc.services.ShowStrategy;
import com.nhc.services.SimpleChiTieu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable{

    @FXML TableView tbThuChi;
    private static final ChiTieuServices chiTieuServices = new ChiTieuServices();
    private static final DanhMucServices danhMucServices = new DanhMucServices();
    private static final LoaiChiTieuServices loaiCTServices = new LoaiChiTieuServices();
    
    
    @FXML TextField txtTenThuChi;
    @FXML TextField txtNoiDung;
    @FXML TextField txtSoTien;
    @FXML ComboBox<LoaiChiTieu> cbLoaiThuchi;
    @FXML ComboBox<DanhMuc> cbLoaiDanhMuc;
    @FXML ComboBox<Show> cbHienThi;
    
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.tbThuChi.setItems(FXCollections.observableList(chiTieuServices.list()));
            this.cbLoaiDanhMuc.setItems(FXCollections.observableList(danhMucServices.list()));
            this.cbLoaiThuchi.setItems(FXCollections.observableList(loaiCTServices.list()));
            this.loadRowColors();
            this.cbHienThi.setItems(FXCollections.observableArrayList(Show.values()));
            this.loadColumns();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadColumns(){
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn colName = new TableColumn("Name");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn colDate = new TableColumn("Date");
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        
        TableColumn colMoney = new TableColumn("Money");
        colMoney.setCellValueFactory(new PropertyValueFactory("money"));
        
        TableColumn colNote = new TableColumn("Note");
        colNote.setCellValueFactory(new PropertyValueFactory("note"));
        TableColumn colDanhMuc = new TableColumn("Danh muc");
        colDanhMuc.setCellValueFactory(new PropertyValueFactory("danhMuc"));
        TableColumn colLoaiChiTieu = new TableColumn("Loai chi tieu");
        colLoaiChiTieu.setCellValueFactory(new PropertyValueFactory("loaiCT"));
        
        this.tbThuChi.getColumns().clear();
        this.tbThuChi.getColumns().addAll(colId, colName, colDate, colMoney, colNote, colDanhMuc, colLoaiChiTieu);
    }
    
    public void handdleAddChiTieu(ActionEvent e) throws SQLException{
        ChiTieu ct = new ChiTieu();
        
        ct.setName(this.txtTenThuChi.getText());
        ct.setDate(LocalDate.now());
        ct.setMoney(Double.parseDouble(this.txtSoTien.getText()));
        ct.setNote(this.txtNoiDung.getText());
        ct.setDanhMuc(this.cbLoaiDanhMuc.getSelectionModel().getSelectedItem());
        ct.setLoaiCT(this.cbLoaiThuchi.getSelectionModel().getSelectedItem());
        
        if(chiTieuServices.addChiTieu(ct)){
            System.out.println("Them thanh cong");
            this.tbThuChi.setItems(FXCollections.observableList(chiTieuServices.list()));
        }
        else{
            System.out.println("Them that bai");
        }
    }
    
    public void handleChangeShow(ActionEvent e) throws SQLException{
        if(this.cbHienThi.getSelectionModel().getSelectedItem() == Show.FULL){
            this.tbThuChi.setItems(FXCollections.observableList(chiTieuServices.list()));
        }
        if(this.cbHienThi.getSelectionModel().getSelectedItem() == Show.CHITIEUBANTHAN){
            ShowStrategy showChiTieuBanThan = new ShowLoaiChiTieuBanThanStrategy();
            
            this.tbThuChi.setItems(FXCollections.observableList(showChiTieuBanThan.render()));
        }
        if(this.cbHienThi.getSelectionModel().getSelectedItem() == Show.CHITIEUGIADINH){
            ShowStrategy showChiTieuGiaDinh = new ShowLoaiChiTieuGiaDinhStrategy();
            this.tbThuChi.setItems(FXCollections.observableList(showChiTieuGiaDinh.render()));
        }
    }
    
    private void loadRowColors() {
        this.tbThuChi.setRowFactory(tableView -> {
            // Tạo một đối tượng TableRow mới
            TableRow<ChiTieu> row = new TableRow<>();

            // Lắng nghe sự thay đổi của item (dữ liệu) trong hàng
            row.itemProperty().addListener((obs, previousItem, currentItem) -> {
                // Nếu hàng có dữ liệu (không phải hàng trống)
                if (currentItem != null) {
                    IChiTieu chiTieuStyler = new SimpleChiTieu();
                    
                    // Áp dụng decorator dựa trên điều kiện
                    if (currentItem.getMoney() > 100000) {
                        chiTieuStyler = new ChiTieuNhieuDecorator(chiTieuStyler); // Tô màu đỏ
                    } else {
                        chiTieuStyler = new ChiTieuItDecorator(chiTieuStyler); // Tô màu xanh
                    }
                    
                    // Set style cho hàng
                    row.setStyle(chiTieuStyler.getStyle());
                } else {
                    // Nếu là hàng trống, xóa style để tránh bị lỗi khi cuộn
                    row.setStyle("");
                }
            });

            return row;
        });
    }

}
