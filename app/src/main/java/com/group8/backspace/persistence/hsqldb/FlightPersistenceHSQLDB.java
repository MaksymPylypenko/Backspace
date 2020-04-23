package com.group8.backspace.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group8.backspace.objects.Flight;
import com.group8.backspace.persistence.FlightPersistence;

import org.joda.time.DateTime;

public class FlightPersistenceHSQLDB implements FlightPersistence {
    private final String path;

    public FlightPersistenceHSQLDB(final String path) {
        this.path = path;
    }

    private Connection connection() throws SQLException {
            return DriverManager.getConnection("jdbc:hsqldb:file:" + path, "SA", "");
    }

    private Flight fromResultSet(final ResultSet rs) throws SQLException {
        final int flightID = rs.getInt("FLIGHTID");
        final String origin = rs.getString("ORIGINID");
        final String destination = rs.getString("DESTINATIONID");
        final long departure_unix = rs.getInt("DEPARTURE");
        final long arrival_unix = rs.getInt("ARRIVAL");

        final DateTime departure = new DateTime(departure_unix * 1000);
        final DateTime arrival = new DateTime(arrival_unix * 1000);

        return new Flight(flightID, origin, destination, departure, arrival);
    }

    @Override
    public List<Flight> getFlights() {
        final List<Flight> flights = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM flights");
            while (rs.next()) {
                final Flight flight = fromResultSet(rs);
                flights.add(flight);
            }
            rs.close();
            st.close();

            return flights;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Flight> getFlights(String origin, String destination) {
        final List<Flight> flights = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "SELECT * FROM flights WHERE originID = ? AND destinationID = ?");
            st.setString(1, origin);
            st.setString(2, destination);

            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Flight flight = fromResultSet(rs);
                flights.add(flight);
            }
            rs.close();
            st.close();

            return flights;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Flight getFlightByID(int flightID) {
        final List<Flight> flights = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "SELECT * FROM flights WHERE flightID = ?");
            st.setInt(1, flightID);

            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Flight flight = fromResultSet(rs);
                flights.add(flight);
            }
            rs.close();
            st.close();

            if (flights.size() == 1) {
                return flights.get(0);
            }
            return null;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
