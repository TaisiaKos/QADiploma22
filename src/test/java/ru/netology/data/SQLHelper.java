package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.use"), System.getProperty("db.password");
    }

    @SneakyThrows
    public static DataHelper.OperationStatus getPaymentGate() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var status = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.OperationStatus(status);
    }

    @SneakyThrows
    public static DataHelper.OperationStatus getCreditGate() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var status = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.OperationStatus(status);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
