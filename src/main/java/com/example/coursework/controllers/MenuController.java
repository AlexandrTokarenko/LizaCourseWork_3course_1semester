package com.example.coursework.controllers;

import com.example.coursework.entity.FPS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController {
    @FXML private TextField fieldLamb;
    @FXML private Label exception;
    @FXML private TextField fieldTcp;
    @FXML private TextField fieldD;
    @FXML private TextField fieldVv;
    @FXML private TextField fieldVn;
    @FXML private TextField fieldK;

    private static FPS fps;

    public static FPS getFps() {
        return fps;
    }

    public void calculate(ActionEvent actionEvent) {

        double lamb = 0;
        double tcp = 0;
        double d = 0;
        double vv = 0;
        double vn = 0;
        int k = 0;

        try {
            lamb = Double.parseDouble(fieldLamb.getText());
            tcp = Double.parseDouble(fieldTcp.getText());
            d = Double.parseDouble(fieldD.getText());
            vv = Double.parseDouble(fieldVv.getText());
            vn = Double.parseDouble(fieldVn.getText());
            k = Integer.parseInt(fieldK.getText());

        } catch (NumberFormatException e) {
            exception.setText("Дані введено не вірно");
            exception.setMaxWidth(Double.MAX_VALUE);
            exception.setAlignment(Pos.CENTER);
        }

        if (checkData(lamb,tcp,d,vv,vn,k)) {

            FPS fps1 = new FPS(lamb,tcp,d,vv,vn,k);
            fps = fps1;
            Stage primaryStage = (Stage) fieldTcp.getScene().getWindow();
            try{
//                MainWindowController mainWindowController = new MainWindowController(f)
//                mainWindowController.setFPS(fps);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/coursework/main.fxml"));
                Parent root = (Parent)loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("ГВС");
//                MainWindowController mainWindowController = loader.getController();
//                mainWindowController.setFPS(fps);
//                loader.setController(mainWindowController);
                stage.show();
                primaryStage.hide();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private boolean checkData(double lamb, double tcp, double d, double vv, double vh, int k) {

        return lamb > 0 && tcp > 0 && d > 0 && vv > 0 && vh > 0 && k > 0;
    }

}
