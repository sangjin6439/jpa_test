package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    public final EntityManager em;

    public void save(Item item){ //값이 null이라는 것은 처음 저장한다는 의미를 가지고 있다. 그래서 persist함
        if(item.getId()==null){
            em.persist(item);
        } else{ // update와 같은 역할을 한다. 이미 DB에 값이 등록된 거임. 값이 있다면 바꾸는 느낌
            em.merge(item);
        }
    }

    public Item findOne(Long id){
       return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
