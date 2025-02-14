package shop.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shop.data.DbFunctions;
import shop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JavaFxInterface extends Application {
    private TableView<Product> table;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Estoque");

        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantidade");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, Float> priceColumn = new TableColumn<>("Preço");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table = new TableView<>();
        table.getColumns().addAll(idColumn, nameColumn, quantityColumn, priceColumn);

        loadData();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(table);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showStockWindow() {
        Platform.runLater(() -> {
            JavaFxInterface stockWindow = new JavaFxInterface();
            Stage stage = new Stage();
            stockWindow.start(stage);
        });
    }

    public void loadData() {
        ObservableList<Product> data = FXCollections.observableArrayList();

        String sql = "SELECT id, product_name, quantity, price FROM products";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                data.add(new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getInt("quantity"), resultSet.getFloat("price")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        table.setItems(data);
    }
}
