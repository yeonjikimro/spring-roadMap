package hello.hellospring.domain;

import jakarta.persistence.*;

// ORM
@Entity
public class Member {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY) // DB가 알아서 id 생성
    private Long id;


    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
