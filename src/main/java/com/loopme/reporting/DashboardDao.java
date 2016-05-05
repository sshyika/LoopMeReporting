package com.loopme.reporting;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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


    private static Map<String, String> loadItems(Connection connection, String query, String id, String name, boolean keyIsString) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            System.out.println("Loading " + query);
            ResultSet rs = stmt.executeQuery(query);
            Map<String, String> items = new LinkedHashMap<>();
            while (rs.next()) {
                if (keyIsString) {
                    items.put(rs.getString(id), rs.getString(name));
                } else {
                    items.put(String.valueOf(rs.getLong(id)), rs.getString(name));
                }
            }
            System.out.println("Loaded " + items.size());
            return items;
        }
    }


    public static Dictionary loadDictionary() {
        try (Connection conn = getConnection()) {
            return new Dictionary(
                    loadItems(conn, "select id, name from line_items", "id", "name", false),
                    loadItems(conn, "select id, name from campaigns", "id", "name", false),
                    loadItems(conn, "select id, name from apps", "id", "name", false),
                    loadItems(conn, "select id, name from creatives", "id", "name", false),
                    loadItems(conn, "select iso2, name from countries order by name asc", "iso2", "name", true),
                    loadItems(conn, "select id, description from content_categories", "id", "description", false),
                    loadItems(conn, "select id, name from genres", "id", "name", false)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
