package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

//Service를 인터페이스를 사용하는 경우는 거의 없고 로직 수정을 하는게 대부분이지만, 이 강의에서는
//버전을 높여가며 수정이 여러차례 이루어지기 때문에 인터페이스를 도입했다.

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);
}
