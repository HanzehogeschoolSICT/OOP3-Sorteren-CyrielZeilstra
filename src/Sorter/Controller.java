package Sorter;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller implements Initializable {
    // Amount of data in the barchart
    int n = 35;

    ArrayList<Integer> nums;
    final NumberAxis yAxis = new NumberAxis();
    final CategoryAxis xAxis = new CategoryAxis();
    final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
    XYChart.Series<String, Number> series1 = new XYChart.Series();
    String algo = "Bubble sort";

    public Controller() {
        bc.setTitle("Algorithm : " + algo);
        bc.setCategoryGap(0);
        bc.setBarGap(0);
        bc.setAnimated(false);
        bc.setMinSize(550, 350);
        series1.setName("Random data");

        generateRandomNumberlist();

        for (Object a : nums) {
            series1.getData().add(new XYChart.Data(a.toString(), a));
        }
        bc.getData().add(series1);
    }

    public void log(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log.appendText("(" + dtf.format(now) + "): " + text + "\n");
    }

    public void generateRandomNumberlist() {
        ArrayList<Integer> nums = new ArrayList<>();
        int i = 1;
        while (nums.size() < n) {
            nums.add(i);
            i++;
        }
        Collections.shuffle(nums);
        this.nums = nums;
    }

    private void updateChart() {
        XYChart.Series<String, Number> seriesA = new XYChart.Series();
        for (int i = 0; i < nums.size(); i++) {
            seriesA.getData().add(new XYChart.Data(nums.get(i).toString(), nums.get(i)));
        }
        seriesA.setName("Random data");
        bc.getData().clear();
        bc.layout();
        bc.getData().add(seriesA);
    }

    private void oneBubbleStep() {
        int temp = 0;
        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = 1; j < (nums.size() - i); j++) {
                if (nums.get(j - 1) > nums.get(j)) {
                    temp = nums.get(j - 1);
                    nums.set(j - 1, nums.get(j));
                    nums.set(j, temp);
                }
            }
            break;
        }
    }



    public boolean isSorted() {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void runSortOnTimer() {
        String text = msTextField.getText();
        if (text.matches("[0-9]+") && text.length() > 2) {
            log("Sorting every : " + text + "ms.");
            Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            oneBubbleStep();
                            updateChart();
                            if (isSorted()){
                                log("List is sorted. Stopping loop.");
                                t.cancel();
                            }
                        }
                    });
                }
            }, 1000, Integer.parseInt(msTextField.getText()));
        } else {
            log("Please input a number higher than or equal to 100.");
        }
    }

    @FXML //  fx:id="mainVBox"
    private VBox mainVBox; // Value injected by FXMLLoader

    @FXML //  fx:id="mainVBox"
    private ComboBox algorithmSelect; // Value injected by FXMLLoader

    @FXML
    private javafx.scene.control.Button sortOnTimerBtn; // Value injected by FXMLLoader

    @FXML
    private TextArea log; // Value injected by FXMLLoader

    @FXML
    private TextField msTextField; // Value injected by FXMLLoader

    @FXML
    private javafx.scene.control.Button stepBtn; // Value injected by FXMLLoader

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert mainVBox != null : "fx:id=\"mainVBox\" was not injected: check your FXML file 'Sorter.fxml'.";
        assert sortOnTimerBtn != null : "fx:id=\"sortOnTimerBtn\" was not injected: check your FXML file 'Sorter.fxml'.";
        assert stepBtn != null : "fx:id=\"stepBtn\" was not injected: check your FXML file 'Sorter.fxml'.";
        assert msTextField != null : "fx:id=\"msTextField\" was not injected: check your FXML file 'Sorter.fxml'.";
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Sorter.fxml'.";
        assert algorithmSelect != null : "fx:id=\"algorithmSelect\" was not injected: check your FXML file 'Sorter.fxml'.";

        mainVBox.getChildren().add(bc);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Bubble sort",
                        "Insertion sort",
                        "Quick sort"
                );

        algorithmSelect.setItems(options);
        algorithmSelect.setValue(options.get(0));

        algorithmSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                log("Selected : " + algorithmSelect.getValue());
                algo = "" + algorithmSelect.getValue();
                bc.setTitle("Algorithm : " + algo);
            }
        });

        stepBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                oneBubbleStep();
                updateChart();
            }
        });

        sortOnTimerBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                runSortOnTimer();
            }
        });
    }
}
