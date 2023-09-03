package multi.api.dto.basket;

import multi.api.dto.product.ProductResponse;

import java.util.List;

public class OrderDto {
    private long id;
    private List<BasketResponse> basket;
    private List<ProductResponse> products;
    private double cost;
    private long userId;

    public OrderDto(long id, List<BasketResponse> basket, List<ProductResponse> products, long userId) {
        this.id = id;
        this.basket = basket;
        this.products = products;
        calculatePrice(basket,products);
        this.cost = cost(basket);
        this.userId = userId;
    }

    private void calculatePrice(List<BasketResponse> basket, List<ProductResponse> products) {
        for (BasketResponse basketResponse :  basket) {
            for (ProductResponse productResponse : products) {
                if(basketResponse.getProductId() == productResponse.getId()) {
                    basketResponse.setPrice(basketResponse.getCount() * productResponse.getPrice());
                }
            }
        }
    }

    private double cost(List<BasketResponse> basket) {
        return basket.stream().mapToDouble(BasketResponse::getPrice).sum();
    }
    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<BasketResponse> getBasket() {
        return basket;
    }

    public void setBasket(List<BasketResponse> basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", basket=" + basket +
                ", products=" + products +
                ", cost=" + cost +
                ", userId=" + userId +
                '}';
    }
}
