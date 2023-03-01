package jdbc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbTest {

    public Connection connectionSetUp() throws SQLException {
        JdbcDriverSetUp driver = new JdbcDriverSetUp();
        driver.dbDriverSetUp();
        Connection connection = driver.getConnectionDb();
        return connection;
    }

    public Statement getStatement() throws SQLException {
        return connectionSetUp().createStatement();
    }

    @Test
    public void checkStatement() throws SQLException {
        assertNotNull(connectionSetUp());
        assertNotNull(getStatement());
    }

    @Test void verifyCityWithPopulation() throws SQLException {
        Statement statement = getStatement();
        ResultSet rs = statement.executeQuery("select city from geography where population < 1000000");
        List<String> list = new ArrayList<>();
        while (rs.next()){
            list.add(rs.getString("city"));
        }
        assertThat(list.stream().anyMatch(city -> city.equals("Makarska"))).as("Quary result doesn't contain search "
                + "city").isTrue();
    }

    @Test
    public void verifySights() throws SQLException {
        Statement statement = getStatement();
        ResultSet rsSights = statement.executeQuery("Select * from attractions left join geography using(id) where "
                + "population is not null");
        List<String> sightsList = new ArrayList<>(Arrays.asList("Golden Gates", "Imperial Palace","Alhambra"));
        List<String> dbList = new ArrayList<>();
        while (rsSights.next()){
            dbList.add(rsSights.getString("sights"));
        }
        assertThat(sightsList.containsAll(dbList)).isTrue();
    }

    @Test void verifyUpdatedTable() throws SQLException {
        Statement statement = getStatement();
        statement.executeUpdate("delete from geography where population order by population limit"
                + " 1;");
        statement.executeUpdate("delete from geography where city = 'Tokio'");
        ResultSet rs = statement.executeQuery("select count(*) from geography");
        rs.next();
        assertThat(rs.getInt("count(*)")).isLessThan(4);
    }
}

