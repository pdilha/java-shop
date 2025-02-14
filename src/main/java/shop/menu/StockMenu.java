package shop.menu;

import shop.model.Product;
import shop.service.ProductService;
import shop.view.JavaFxInterface;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StockMenu {
    private Scanner input;
    private ProductService productService;

    public StockMenu(Scanner input) {
        this.input = input;
        this.productService = new ProductService();
    }

    public void showStockMenu() throws IOException {
        while (true) {
            System.out.println("---ESTOQUE---\n");

            System.out.println("1- Ver produtos no estoque \n2- Procurar produto no estoque \n3- Adicionar produtos ao estoque \n4- Remover produtos do estoque \n5- Atualizar produtos do estoque \n6- Voltar para o início");

            System.out.print("\nOpção desejada: ");
            int stockMenuOption = input.nextInt();
            input.nextLine();
            System.out.println();

            if (stockMenuOption == 1) {
                JavaFxInterface.showStockWindow();

            } else if (stockMenuOption == 2) {
                System.out.print("\nBuscar produto pelo nome: ");
                String searchName = input.nextLine();

                List<Product> foundProducts = productService.searchProductByNameService(searchName);

                if (!foundProducts.isEmpty()) {
                    System.out.println("\nProdutos encontrados:");
                    for (Product product : foundProducts) {
                        System.out.println("- \"" + product.getName() + "\"" + "| Quantidade: " + product.getQuantity() + "| Preço: R$" + product.getPrice() + "\n");

                    }
                } else {
                    System.out.println("Nenhum produto encontrado.");

                }
            } else if (stockMenuOption == 3) {

                System.out.print("Nome do produto a ser adicionado: ");
                String newProductName = input.nextLine();

                System.out.print("Quantidade do produto a ser adicionada: ");
                int newProductQuantity = input.nextInt();
                input.nextLine();

                System.out.print("Preço do produto a ser adicionado: ");
                float newProductPrice = input.nextFloat();
                input.nextLine();

                Product newProduct = new Product(newProductName, newProductQuantity, newProductPrice);

                productService.addProductService(newProduct);

                System.out.println("\nProduto adicionado com sucesso.\n");

            } else if (stockMenuOption == 4) {

                JavaFxInterface.showStockWindow();
                System.out.println("ID do produto a ser deletado: ");
                int productDeleteId = input.nextInt();
                input.nextLine();

                productService.deleteProductService(productDeleteId);

                System.out.print("Produto deletado com sucesso.\n");

            } else if (stockMenuOption == 5) {

                JavaFxInterface.showStockWindow();
                System.out.println("ID do produto a ser atualizado: ");
                int productId = input.nextInt();
                input.nextLine();

                Product product = productService.searchProductByIdService(productId);

                if (product!= null) {
                    System.out.println("Produto: " + product.getName());
                    System.out.println("Alteração desejada: \n1- Nome \n2- Quantidade \n3- Preço");
                    System.out.print("Opção desejada: ");
                    int updateOption = input.nextInt();
                    input.nextLine();

                    if (updateOption == 1) {
                        System.out.print("Novo nome do produto: ");
                        String newName = input.nextLine();

                        Product updatedProduct = new Product(product.getId(), newName, product.getQuantity(), product.getPrice());

                        productService.updateProductService(updatedProduct);

                        System.out.println("Produto alterado com sucesso.\n");
                    } else if (updateOption == 2) {
                        System.out.print("Nova quantidade do produto: ");
                        int newQuantity = input.nextInt();
                        input.nextLine();

                        Product updatedProduct = new Product(product.getId(), product.getName(), newQuantity, product.getPrice());

                        productService.updateProductService(updatedProduct);

                        System.out.println("Produto alterado com sucesso.\n");
                    } else if (updateOption == 3) {
                        System.out.print("Novo preço do produto: ");
                        float newPrice = input.nextFloat();
                        input.nextLine();

                        Product updatedProduct = new Product(product.getId(), product.getName(), product.getQuantity(), newPrice);

                        productService.updateProductService(updatedProduct);

                        System.out.println("Produto alterado com sucesso.\n");

                    }
                } else {
                    System.out.println("Produto não encontrado.");

                }
            } else if (stockMenuOption == 6) {
                break;

            }
        }
    }
}
