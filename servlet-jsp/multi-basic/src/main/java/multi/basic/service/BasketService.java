package multi.basic.service;

import multi.api.contract.BasketApi;
import multi.api.dto.basket.BasketResponse;
import multi.basic.mapping.BasketMapper;
import multi.basic.repository.BasketDao;
import multi.basic.repository.file.RepositoryBasket;
import multi.domain.Basket;

import java.util.List;

public class BasketService implements BasketApi {
    private BasketDao repositoryBasket;
    private BasketMapper basketMapper;

    public BasketService(BasketDao repositoryBasket, BasketMapper basketMapper) {
        this.repositoryBasket = repositoryBasket;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketResponse createBasket(long orderId, long productId, int count) {
        Basket basket = new Basket(orderId,productId,count);
        repositoryBasket.save(basket);
        return basketMapper.createBasketResponse(basket);
    }

    @Override
    public void deleteBasket(long id) {
        repositoryBasket.delete(id);
    }

    @Override
    public List<BasketResponse> getBasketByOrderId(long id) {
        return repositoryBasket.getBasketsByUser(id).stream().map(basket -> basketMapper.createBasketResponse(basket)).toList();
    }
}
