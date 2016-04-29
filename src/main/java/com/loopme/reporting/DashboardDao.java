package com.loopme.reporting;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardDao {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://136.243.174.148:5432/dashboard", "postgres", "Xxyy2jMobxgyLzDq2wbovQbd");
    }


    private static Map<Long, String> loadItems(Connection connection, String query, String id, String name) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            System.out.println("Loading " + query);
            ResultSet rs = stmt.executeQuery(query);
            Map<Long, String> items = new HashMap<>();
            while (rs.next()) {
                items.put(rs.getLong(id), rs.getString(name));
            }
            System.out.println("Loaded " + items.size());
            return items;
        }
    }


    public static Dictionary loadDictionary() {
        try (Connection conn = getConnection()) {
            return new Dictionary(
                    loadItems(conn, "select id, name from line_items", "id", "name"),
                    loadItems(conn, "select id, name from campaigns", "id", "name"),
                    loadItems(conn, "select id, name from apps", "id", "name"),
                    loadItems(conn, "select id, name from creatives", "id", "name"),
                    loadItems(conn, "select iso2, name from countries", "iso2", "name"),
                    loadItems(conn, "select id, description from content_categories", "id", "description"),
                    loadItems(conn, "select id, name from genres", "id", "name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
