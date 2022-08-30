package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepositoryV2 implements ItemRepository {

    private final SpringDataJpaItemRepository repository;

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = repository.findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    //실무에서는 findAll()처럼 안하고 동적쿼리를 사용한다. 가끔 조건이 두개정도 밖에 없으때만 이렇게 사용한다.
    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        if (StringUtils.hasText(itemName) && maxPrice != null){
//          return repository.findByItemNameLikeAndPriceLessThanEqual(itemName,maxPrice); 아래와 같은 동작 수행
            return repository.findItems(itemName, maxPrice);
        }else if (StringUtils.hasText(itemName)){
            return repository.findByItemNameLike(itemName);
        }else if (maxPrice != null){
            return repository.findByPriceLessThanEqual(maxPrice);
        }else {
            return repository.findAll();
        }
    }
}
