package InterviewScheduler.demo.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "RH";
    private static final Object lock = new Object();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Connection connection = null;
        try {
            // Obtain the connection to the database
            connection = session.getJdbcConnectionAccess().obtainConnection();
            // Query to get the maximum ID from the interview table
            synchronized (lock) {
                PreparedStatement statement = connection.prepareStatement("SELECT MAX(CAST(SUBSTRING(rh01, 3) AS UNSIGNED)) FROM interviews");
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt(1); // Get the maximum ID
                    // Return the custom ID formatted with a leading zero
                    return PREFIX + String.format("%02d", maxId + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    // Release the connection back to the connection pool
                    session.getJdbcConnectionAccess().releaseConnection(connection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // If no IDs exist in the table, return the first ID "RH01"
        return PREFIX + "01";
    }
}
