import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;


public class MySqlContainerContainerTest {

	@Test
	public void testMySQLContainer() {
		try (MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")) {
			mysql.start();

			String jdbcUrl = mysql.getJdbcUrl();
			String username = mysql.getUsername();
			String password = mysql.getPassword();


		}
	}
}
