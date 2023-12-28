package hello.jdbc.repository;

import java.lang.reflect.Member;

public interface MemberRepositoryEx {
    /*
    Member save(Member member) throws Exception; // exception 하위타입 가능(ex SqlException)
    * */

    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void delete(String memberId);
}
