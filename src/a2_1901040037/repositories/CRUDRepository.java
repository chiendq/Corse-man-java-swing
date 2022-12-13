package a2_1901040037.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static a2_1901040037.utils.DbConnect.getConnection;

public interface CRUDRepository<T> {
    List<T> getAll();

    void delete(T o);

    void update(T o);

    default int count(String tableName){
        String INSERT_SQL = "SELECT  COUNT(*) AS total from " + tableName;
        int count = 0;
        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
                ResultSet resultSet = prepareStatement.executeQuery()
        ) {
            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return count;
    }
}
