package shop.service;

import shop.dao.ProductDAO;
import shop.model.Product;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();


    public void addProductService(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ficar vazio.");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a zero.");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("O preço deve ser maior ou igual a zero.");
        }
        productDAO.addProduct(product);
    }

    public void updateProductService(Product product) {
        if (product.getId() <= 0) {
            throw new IllegalArgumentException("ID do produto inválido.");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto inválido.");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantidade do produto inválida.");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Preço do produto inválido.");
        }
        productDAO.updateProduct(product);
    }

    public void deleteProductService(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        productDAO.deleteProduct(id);
    }

    public Product searchProductByIdService(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return productDAO.searchProductById(id);
    }
    
    public List<Product> searchProductByNameService(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        return productDAO.searchProductByName(name);
    }

    public boolean verifyProductDisponibilityService(int id, int quantityNeeded) {
        Product product = searchProductByIdService(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        return product.getQuantity() >= quantityNeeded;
    }
}
