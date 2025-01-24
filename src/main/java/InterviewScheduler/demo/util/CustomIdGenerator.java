package InterviewScheduler.demo.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "RH";
        final Long[] nextId = {null}; // Use a single-element array to store the value

        // Use session.doWork() to get the next value from id_sequence
        session.doWork(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO id_sequence () VALUES ()", PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        nextId[0] = rs.getLong(1); // Assign the value to the array element
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error generating ID", e);
            }
        });

        if (nextId[0] == null) {
            throw new RuntimeException("Failed to generate ID from id_sequence");
        }

        // Format the numeric part with leading zeros (e.g., 01, 02, 03)
        String formattedId = String.format("%02d", nextId[0]);

        return prefix + formattedId;
    }
}