package com.example.coursework.controllers;

import com.example.coursework.entity.FPS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainWindowController {

    @FXML private Label textMatrix;
    private FPS fps = new FPS();


    public void setFPS(FPS fps) {
        this.fps = fps;
    }

    public void initialize() {

        textMatrix.setFont(Font.font ("Courier New", 10));
        fps = MenuController.getFps();
        showTable();
    }

    private void showTable() {

        BigDecimal[][] matrix1 = fps.getMatrix();

        StringBuilder res = new StringBuilder();
        res.append("B\\H");
        for (int i = 1; i < matrix1.length; i++) {
            res.append("%7d ".formatted(i));
        }

        res.append("\n");

        for (int i = 1; i < matrix1.length; i++) {
            res.append("%2d ".formatted(i));
            for (int j = 1; j < matrix1.length; j++) {
                res.append("%8.2f".formatted(matrix1[i][j]));
            }
            res.append("\n");
        }

        res.append("\nМаксимальний прибуток: %5.2f грн ".formatted(fps.getMaxProfit()));
        res.append("\nВерстатів: ").append(fps.getNumberOfMachines()).append("\nНакопичувачів: ").append(fps.getNumberOfDrives()).append("\n");
       // System.out.println(res);

        textMatrix.setText(String.valueOf(res));
    }

    private void showTable1() {

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
//                StringBuilder number = new StringBuilder();
//                if (matrix1[i][j].equals(BigDecimal.ZERO)) {
//                    number.append("0.00");
//                } else {
//                    String s = String.valueOf(matrix1[i][j]);
//                    int ar = 0;
//                    for (int k = 0; k < s.length(); k++) {
//                        if (s.charAt(k) == ',' || s.charAt(k) == '.') {
//                            ar++;
//                            number.append(s.charAt(k));
//                        } else if (ar != 0) {
//                            ar++;
//                            number.append(s.charAt(k));
//                            if (ar == 3) break;
//                        } else number.append(s.charAt(k));
//                    }
//                }

                DecimalFormat decimalFormat = new DecimalFormat("8.2");
                if(matrix1[i][j].equals(BigDecimal.ZERO)) {
//                    res.append("     ");
                    res.append(decimalFormat.format(BigDecimal.ZERO));
                } else res.append(matrix1[i][j]);
                    double a = matrix1[i][j].doubleValue();
                    if (a < 10) {
//                        res.append("     ");
                        res.append(decimalFormat.format(matrix1[i][j]));
                    } else if (a < 100) {
//                        res.append("    ");
                        res.append(decimalFormat.format(matrix1[i][j]));
                    } else {
//                        res.append("   ");
                        res.append(decimalFormat.format(matrix1[i][j]));
                    }
//                if (matrix1[i][j].compareTo(BigDecimal.valueOf(10)) < 0) {
//                    res.append("      ");
//                    res.append(number);
//                } else {
//                    res.append("    ");
//                    res.append(number);
//                }
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
