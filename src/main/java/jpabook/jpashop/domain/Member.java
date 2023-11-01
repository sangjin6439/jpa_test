package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name= "name_id")
    private Long id;

    @Column(name ="MEMBER_NAME")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> order= new ArrayList<>();

    @Embedded
    private Address address;


}
