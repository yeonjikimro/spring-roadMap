package hello.jdbc.repository;

import java.lang.reflect.Member;

public interface MemberRespository {

    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void delete(String memberId);

}
