package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {


    //static , 현재는 싱글톤 이라서 hashmap 사용했지만 멀티스레드인경우 ConcurrentHashMap 사용해야함
    private static final Map<Long,Item> store = new HashMap<>();

    private static Long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
