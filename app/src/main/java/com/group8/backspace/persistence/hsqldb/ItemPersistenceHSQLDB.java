package com.group8.backspace.persistence.hsqldb;
import com.group8.backspace.persistence.ItemPersistence;
import com.group8.backspace.objects.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemPersistenceHSQLDB implements ItemPersistence {

    private final String path;
    public ItemPersistenceHSQLDB(final String path) {
        this.path = path;
    }


    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path, "SA", "");
    }

    @Override
    public Item getItem(String name) {
        Item price = null;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "SELECT * FROM ITEMS WHERE item = ?");
            st.setString(1, name);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                price = fromResultSet(rs);
            }
            rs.close();
            st.close();
            if (price==null) {
                throw new NullPointerException();
            }
            else {
                return price;
            }
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Item fromResultSet(final ResultSet rs) throws SQLException {
        String item = rs.getString("item");
        String type = rs.getString("type");
        int price = rs.getInt("price");
        return new Item(item,type,price);
    }
}
