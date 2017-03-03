package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller
        implements Initializable {

    public ArrayList generateRandomNumberlist(){
        ArrayList<Integer> nums = new ArrayList<>();
        int i = 1;
        while (nums.size() < 20){
            nums.add(i);
            i++;
        }
        Collections.shuffle(nums);
        return nums;
    }


    @FXML //  fx:id="mainVBox"
    private VBox mainVBox; // Value injected by FXMLLoader

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert mainVBox != null : "fx:id=\"mainVBox\" was not injected: check your FXML file 'sample.fxml'.";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Bubble sort");
        bc.setCategoryGap(0);
        bc.setBarGap(0);
        bc.setMinSize(550,350);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Random data");
        ArrayList<Integer> nums = generateRandomNumberlist();
        for (int a: nums){
            series1.getData().add(new XYChart.Data(""+a, a));
        }

        bc.getData().addAll(series1);
        mainVBox.getChildren().add(bc);
    }
}
