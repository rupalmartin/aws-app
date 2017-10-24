package com.aws.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractService {
    protected Connection getConnection() throws ClassNotFoundException,
            SQLException {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://aws-app-281-db.ckg0faguue9u.us-east-1.rds.amazonaws.com:3306/aws-app-281-db";
        String user = "rupaldb";
        String password = "rmartin31";

        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        return conn;
    }
}
