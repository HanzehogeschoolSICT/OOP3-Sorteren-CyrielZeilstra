package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

public class Controller
        implements Initializable {
    int n = 25;

    public ArrayList generateRandomNumberlist() {
        ArrayList<Integer> nums = new ArrayList<>();
        int i = 1;
        while (nums.size() < n) {
            nums.add(i);
            i++;
        }
        Collections.shuffle(nums);
        return nums;
    }

    private void drawChart(ArrayList nus) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

        bc.setTitle("Bubble sort");
        bc.setCategoryGap(0);
        bc.setBarGap(0);
        bc.setMinSize(550, 350);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Random data");

        for (Object a : nus) {
            series1.getData().add(new XYChart.Data("" + a, a));
        }

        bc.getData().addAll(series1);
        mainVBox.getChildren().add(bc);
    }


    @FXML //  fx:id="mainVBox"
    private VBox mainVBox; // Value injected by FXMLLoader

    @FXML
    private javafx.scene.control.Button sortBtn; // Value injected by FXMLLoader

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert mainVBox != null : "fx:id=\"mainVBox\" was not injected: check your FXML file 'sample.fxml'.";
        assert sortBtn != null : "fx:id=\"sortBtn\" was not injected: check your FXML file 'sample.fxml'.";

        // Generate a random list and add them to the chart.
        ArrayList<Integer> nums = generateRandomNumberlist();
        drawChart(nums);

        sortBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                int temp = 0;
                for (int i = 0; i < nums.size() - 1; i++) {
                    for (int j = 1; j < (nums.size() - i); j++) {
                        if (nums.get(j - 1) > nums.get(j)) {
                            //swap the elements!
                            temp = nums.get(j - 1);
                            nums.set(j - 1, nums.get(j));
                            nums.set(j, temp);
                        }
                    }
                }
                drawChart(nums);
            }
        });
    }
}
