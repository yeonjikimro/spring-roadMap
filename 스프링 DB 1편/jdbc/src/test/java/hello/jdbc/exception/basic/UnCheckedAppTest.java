package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class UnCheckedAppTest {

    @Test
    void unchecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }


    /**
     * StackTrace 예시
     */
    @Test
    void printEx() {
        Controller controller = new Controller();
        try {
            controller.request();
        } catch (Exception e) {
            // 로그 출력 시 마지막 파라미터에 예외를 넣어주면 로그에 스택 트레이스 출력 가능
            // e.printStackTrace();
            log.info("ex", e);
        }
    }
    static class Controller {
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic()  {
            repository.call();
            networkClient.call();
        }

    }

    static class NetworkClient {
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }

    }

    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                // 이전 예외도 같이 가지고 온다
                throw new RuntimeSQLException(e);

            }
        }


        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }

    }


    static class RuntimeConnectException extends RuntimeException {
        public RuntimeConnectException(String message) {
            super (message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(Throwable cause) {
            super (cause);
        }
    }



}
