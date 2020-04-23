package com.group8.backspace.persistence.hsqldb;

import com.group8.backspace.objects.Location;
import com.group8.backspace.persistence.PlanetPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlanetPersistenceHSQLDB implements PlanetPersistence {
    private final String path;

    public PlanetPersistenceHSQLDB(final String path) {
        this.path = path;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path, "SA", "");
    }

    @Override
    public List<Location> getPlanets() {
        final List<Location> planets = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM LOCATIONS");
            while (rs.next()) {
                final Location loc = fromResultSet(rs);
                planets.add(loc);
            }
            rs.close();
            st.close();

            return planets;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Location getPlanetByName(String locationName) {
        final List<Location> planets = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "SELECT * FROM locations WHERE LOCATIONID = ?");
            st.setString(1, locationName);

            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Location loc = fromResultSet(rs);
                planets.add(loc);
            }
            rs.close();
            st.close();

            if (planets.size() == 1) {
                return planets.get(0);
            }
            return null;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
}

    private Location fromResultSet(final ResultSet rs) throws SQLException {
        final String planetName = rs.getString("LOCATIONID");
//        String image = rs.getString("IMAGESRC");
        String years = rs.getString("YEAR");
        String min = rs.getString("MINTEMP");
        String max = rs.getString("MAXTEMP");
        String pop = rs.getString("POPULATION");
        String dist = rs.getString("DISTANCE");
        String desc = rs.getString("DESCRIPTION");
        String shortDesc = rs.getString("SHORTDESC");

        Location newLocation = new Location(planetName);
//        newLocation.setImgSrc(image);
        newLocation.setYears(years);
        newLocation.setMinTemp(min);
        newLocation.setMaxTemp(max);
        newLocation.setPopulation(pop);
        newLocation.setDistance(dist);
        newLocation.setDescription(desc);
        newLocation.setShortDesc(shortDesc);

        return newLocation;
    }

}
