package a2_1901040037.repositories;

import a2_1901040037.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static a2_1901040037.utils.DbConnect.getConnection;

public class StudentRepository implements CRUDRepository<Student> {

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM student";
        List<Student> studentList = new ArrayList<>();
        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);

                ResultSet rs = prepareStatement.executeQuery()
        ) {
            // loop through the result set
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String email = rs.getString("email");
                studentList.add(new Student(id, name, dob, address, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return studentList;
    }

    public void saveOne(Student student) {
        String INSERT_SQL = "INSERT INTO student values (?, ?, ?, ?, ?)";

        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
        ) {
            prepareStatement.setString(1,student.getId());
            prepareStatement.setString(2,student.getName());
            prepareStatement.setString(3,student.getDob());
            prepareStatement.setString(4,student.getAddress());
            prepareStatement.setString(5,student.getEmail());
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Student o) {

    }

    @Override
    public void update(Student o) {

    }

    public int count() {
        return CRUDRepository.super.count("student");
    }

    public Object[][] getAllStudentReport() {
        String sql = "SELECT * FROM student";
        Object[][] data = new Object[this.count("enrollment")][];
        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);

                ResultSet rs = prepareStatement.executeQuery()
        ) {
            int count = 0;
            // loop through the result set
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String email = rs.getString("email");
                data[count] = new Object[]{
                        id,
                        name,
                        dob,
                        address,
                        email
                };
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return data;
    }
}
