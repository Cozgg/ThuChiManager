module com.nhc.thuchimanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;

    opens com.nhc.thuchimanager to javafx.fxml;
    exports com.nhc.thuchimanager;
    exports com.nhc.pojo;
}
