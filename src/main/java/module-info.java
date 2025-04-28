module pucgo.poobd.projetofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;


    opens pucgo.poobd.projetofinal to javafx.fxml;
    exports pucgo.poobd.projetofinal;
    exports pucgo.poobd.projetofinal.view;
    opens pucgo.poobd.projetofinal.view to javafx.fxml;
    exports pucgo.poobd.projetofinal.controller;
    opens pucgo.poobd.projetofinal.controller to javafx.fxml;
    opens pucgo.poobd.projetofinal.model to javafx.base;
    opens pucgo.poobd.projetofinal.dao to javafx.base;
}