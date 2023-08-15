package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); // optional findbyid가 null 일 경우도 있으니
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
