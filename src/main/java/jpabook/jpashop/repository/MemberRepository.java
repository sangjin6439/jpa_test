package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

   @PersistenceContext
   public EntityManager em;

   public void save(Member member){
       em.persist(member);
   }

   public Member findOne(Long id){
       return em.find(Member.class, id);
   }

    public List<Member> findAll() { //쿼리 날리니까 매개변수 없어도 됨
       return em.createQuery("select m from Member m", Member.class)
               .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

    }

}
