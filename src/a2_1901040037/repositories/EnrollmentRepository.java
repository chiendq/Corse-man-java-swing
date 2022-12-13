package a2_1901040037.repositories;

import a2_1901040037.models.Enrolment;
import a2_1901040037.views.frames.enrollment.EnrollmentCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static a2_1901040037.utils.DbConnect.getConnection;

public class EnrollmentRepository implements CRUDRepository<Enrolment> {
    @Override
    public List<Enrolment> getAll() {
        return null;
    }

    @Override
    public void delete(Enrolment o) {

    }

    @Override
    public void update(Enrolment o) {

    }


    public void saveOne(EnrollmentCreation enrollment) {
        String sql = """
                INSERT INTO enrollment (student_id, module_code, internal_mark, exam_mark, final_grade)
                VALUES (?, ?, ?, ?, ?);
                """;
        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ) {
            prepareStatement.setString(1, enrollment.getStudentId());
            prepareStatement.setString(2, enrollment.getModuleId());
            prepareStatement.setFloat(3, enrollment.getInternalMark());
            prepareStatement.setFloat(4, enrollment.getExamMark());
            prepareStatement.setString(5, String.valueOf(enrollment.getFinalGrade()));
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public Object[][] getAllInitialReport() {
        String sql = """
                SELECT e.id "enrollment_id",
                       s.id AS "student_id",
                       s.name AS "student_name",
                       m.code AS "module_code",
                       m.name AS "module_name"
                FROM enrollment e
                JOIN student s ON e.student_id = s.id
                JOIN module m on e.module_code = m.code
                """;
        Object[][] data = new Object[this.count("enrollment")][];
        int count = 0;
        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);

                ResultSet rs = prepareStatement.executeQuery()
        ) {
            // loop through the result set
            while (rs.next()) {
                int enrollmentId = rs.getInt("enrollment_id");
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String moduleCode = rs.getString("module_code");
                String moduleName = rs.getString("module_name");
                data[count] = new Object[]{
                        enrollmentId,
                        studentId,
                        studentName,
                        moduleCode,
                        moduleName
                };
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return data;
    }

    public Object[][] getAllAssessmentReport() {
        String sql = """
                SELECT
                    e.id AS "enrollment_id",
                    e.internal_mark AS "internal_mark",
                    e.exam_mark AS "exam_mark",
                    e.final_grade AS "final_grade",
                    s.id AS "student_id",
                    m.code AS "module_code",
                    m.name AS "module_name"
                FROM enrollment e
                LEFT JOIN student s ON e.student_id = s.id
                LEFT JOIN module m on e.module_code = m.code
                """;
        Object[][] data = new Object[this.count("enrollment")][];
        int count = 0;
        try (
                Connection connection = DbConnect.getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);

                ResultSet rs = prepareStatement.executeQuery()
        ) {
            // loop through the result set
            while (rs.next()) {
                int enrollmentId = rs.getInt("enrollment_id");
                String studentId = rs.getString("student_id");
                String moduleCode = rs.getString("module_code");
                double internalMark = rs.getDouble("internal_mark");
                double examMark = rs.getDouble("exam_mark");
                String finalGrade = rs.getString("final_grade");
                data[count] = new Object[]{
                        enrollmentId,
                        studentId,
                        moduleCode,
                        internalMark,
                        examMark,
                        finalGrade
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
