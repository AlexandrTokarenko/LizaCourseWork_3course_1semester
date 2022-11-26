package com.example.coursework.controllers;

import com.example.coursework.entity.FPS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class MainWindowController {

    @FXML private Label textMatrix;
    private FPS fps = new FPS();


    public void setFPS(FPS fps) {
        this.fps = fps;
    }

    public void initialize() {

        fps = MenuController.getFps();
        showTable();
    }

    private void showTable() {

        BigDecimal[][] matrix1 = fps.getMatrix();

        StringBuilder res = new StringBuilder();
        res.append("B\\H");
        for (int i = 1; i < matrix1.length; i++) {
            if (i == 1) {
                res.append("      ");
            } else if (i == 10) {
                res.append("         ");
            }
            else if (i > 10) {
                res.append("        ");
            }
            else res.append("           ");
            res.append(i);
        }

        res.append("\n");

        for (int i = 1; i < matrix1.length; i++) {
            if (i < 10) {
                res.append(" ").append(i);
            } else res.append(i);
            for (int j = 1; j < matrix1[0].length; j++) {
                StringBuilder number = new StringBuilder();
                if (matrix1[i][j].equals(BigDecimal.ZERO)) {
                    number.append("0.00");
                } else {
                    String s = String.valueOf(matrix1[i][j]);
                    int ar = 0;
                    for (int k = 0; k < s.length(); k++) {
                        if (s.charAt(k) == ',' || s.charAt(k) == '.') {
                            ar++;
                            number.append(s.charAt(k));
                        } else if (ar != 0) {
                            ar++;
                            number.append(s.charAt(k));
                            if (ar == 3) break;
                        } else number.append(s.charAt(k));
                    }
                }

                if (matrix1[i][j].compareTo(BigDecimal.valueOf(10)) < 0) {
                    res.append("      ");
                    res.append(number);
                } else {
                    res.append("    ");
                    res.append(number);
                }
            }
            res.append("\n");
        }

        res.append("\nМаксимальний прибуток: %5.2f грн ".formatted(fps.getMaxProfit()));
        res.append("\nВерстатів: ").append(fps.getNumberOfMachines()).append("\nНакопичувачів: ").append(fps.getNumberOfDrives()).append("\n");
        System.out.println(res);

        textMatrix.setText(String.valueOf(res));
    }

    public void clickBack(ActionEvent actionEvent) {

        Stage primaryStage = (Stage) textMatrix.getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/coursework/hello-view.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ГВС");
            stage.setResizable(false);
            stage.show();
            primaryStage.hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
