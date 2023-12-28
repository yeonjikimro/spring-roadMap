package hello.jdbc.repository;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Member;

@Slf4j
//public class MemberRepositoryV3  implements MemberRepositoryEx{
public class MemberRepositoryV3 {
/*    @Override // exception 상위타입
    public Member save(Member member) throws Exception {
        return null;
    }*/

    //@Override
    public Member save(Member member) {
        return null;
    }

    //@Override
    public Member findById(String memberId) {
        return null;
    }

    //@Override
    public void update(String memberId, int money) {

    }

    //@Override
    public void delete(String memberId) {

    }
}
