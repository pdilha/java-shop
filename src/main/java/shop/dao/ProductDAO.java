package shop.dao;

import shop.data.DbFunctions;
import shop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> allProducts() {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getInt("quantity"), resultSet.getFloat("price"));
                products.add(product);
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return products;
    }

    public void addProduct(Product product) {

        String sql = "INSERT INTO products (product_name, quantity, price) VALUES (?, ?, ?)";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setFloat(3, product.getPrice());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateProduct(Product product) {

        String sql = "UPDATE products SET product_name = ?, quantity = ?, price = ? WHERE id = ?";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteProduct(int id) {

        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DbFunctions.connectToDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Product searchProductById(int id) {

        List<Product> products = allProducts();

        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> searchProductByName(String name) {

        List<Product> products = allProducts();
        List<Product> foundProducts = new ArrayList<>();

        String productRegex = name.trim().toLowerCase().replaceAll("\\s+", "\\\\s*");
        for (Product product : products) {
            if (product.getName().toLowerCase().matches(".*" + productRegex + ".*")) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }
}
