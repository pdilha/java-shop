package shop.service;

import shop.dao.TradeDAO;
import shop.model.Product;
import shop.model.Trade;
import shop.model.TradeType;

import java.util.HashMap;
import java.util.Map;

public class TradeService {
    ProductService productService = new ProductService();
    TradeDAO tradeDAO = new TradeDAO();

    public void sellProductService(int id, int quantity) {
        if (!productService.verifyProductDisponibilityService(id, quantity)) {
            throw new IllegalArgumentException("Quantidade indisponível.");
        }
        Product product = productService.searchProductByIdService(id);
        product.setQuantity(product.getQuantity() - quantity);
        productService.updateProductService(product);

        Map<Integer, Integer> itemsSold = new HashMap<>();
        itemsSold.put(id, quantity);

        float totalSaleValue = (float) totalValueService(itemsSold);

        Trade trade = new Trade();
        trade.setTradeType(TradeType.EXIT);
        trade.setQuantity(quantity);
        trade.setPrice(totalSaleValue);

        tradeDAO.addTrade(trade);

        System.out.println("Venda feita com sucesso.");
    }

    public void buyProductService(int id, int quantity) {
        Product product = productService.searchProductByIdService(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        product.setQuantity(product.getQuantity() + quantity);
        productService.updateProductService(product);

        Trade trade = new Trade();
        trade.setTradeType(TradeType.ENTRY);
        trade.setQuantity(quantity);
        trade.setPrice(product.getPrice());

        tradeDAO.addTrade(trade);

        System.out.println("Compra feita com sucesso.");
    }

    public double totalValueService(Map<Integer, Integer> itemsSold) {
        float total = 0;

        for (Map.Entry<Integer, Integer> entry : itemsSold.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = productService.searchProductByIdService(productId);
            if (product == null) {
                throw new IllegalArgumentException("Produto não encontrado.");
            }

            float itemPrice = product.getPrice() * quantity;
            total += itemPrice;
        }

        return total;
    }
}
