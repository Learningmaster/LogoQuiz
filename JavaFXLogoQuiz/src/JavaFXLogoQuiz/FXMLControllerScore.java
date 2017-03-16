/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLControllerScore implements Initializable {

    @FXML
    private Button displayScore;

    @FXML
    private Label score;

    @FXML
    private HBox scoreBox;

    @FXML
    private TextField scoreField;

    @FXML
    private TableView<HiScore> tableView;

    @FXML
    private TableColumn<HiScore, String> value;

    @FXML
    private TableColumn<HiScore, String> scoreRange;

    public int index;

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayScore.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                formatTextField();
                addTable();
            }
        });
    }

    public void formatTextField() {
        DecimalFormat myFormatter = new DecimalFormat("##");
        String output = myFormatter.format(index);
        System.out.print(index);
        scoreField.setText(output);
    }

    public void addTable() {
        ObservableList<HiScore> data = FXCollections.observableArrayList(
                new HiScore("<5", "Disappointing, try again (insert reset)"),
                new HiScore("5-10", "Ok"),
                new HiScore("10+", "Great!")
        );

        value.setCellValueFactory(new PropertyValueFactory<HiScore, String>("value"));
        scoreRange.setCellValueFactory(new PropertyValueFactory<HiScore, String>("scoreRange"));

        tableView.setItems(data);
        
        tableView.setRowFactory(tv -> new TableRow<HiScore>() {
            
            @Override
            protected void updateItem(HiScore item, boolean empty) {
                super.updateItem(item, empty);

                String color = "";
                if (index < 5) {
                    color = "-fx-background-color: red";
                } else if (index >= 5 && index <= 10) {
                    color = "-fx-background-color: orange";
                } else if (index > 10) {
                    color = "-fx-background-color: green";
                }
                setStyle(color);
            }
    });
    }

    public class HiScore {

        public SimpleStringProperty ScoreRange;
        public SimpleStringProperty Value;

        public HiScore(String scoreRange, String value) {
            this.ScoreRange = new SimpleStringProperty(scoreRange);
            this.Value = new SimpleStringProperty(value);
        }

        public String getScoreRange() {
            return ScoreRange.get();
        }

        public String getValue() {
            return Value.get();
        }

        public void setScoreRange(String scoreRange) {
            ScoreRange.set(scoreRange);
        }

        public void setValue(String value) {
            Value.set(value);
        }
    }
}