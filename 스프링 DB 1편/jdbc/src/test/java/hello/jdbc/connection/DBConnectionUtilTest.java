package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtilTest {

   @Test
    void connection() throws SQLException {
       Connection connection = DBConnectionUtil.getConnection();
       Assertions.assertThat(connection).isNotNull();
   }
}
