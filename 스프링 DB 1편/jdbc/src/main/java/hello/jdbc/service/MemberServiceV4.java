package hello.jdbc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * 예외 누수 문제 해결
 * SQLException 제거
 *
 * MemberRepository 인터페이스 의존
 */
@Slf4j
public class MemberServiceV4 {

    private final MemberRepository memberRepository;

    public MemberServiceV4(MemberRepositoryV4 memberRepository) { this.memberRepository = memberRepository}

    @Transactional
    public void accountTransfer(String fromId, String toId, int money)  {
        bizLogic(fromId, toId, money);
    }


    private void bizLogic(String fromId, String toId, int money) {

    }

}
