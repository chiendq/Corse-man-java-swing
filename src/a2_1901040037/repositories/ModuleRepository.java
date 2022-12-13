package a2_1901040037.repositories;
import a2_1901040037.models.module.ModuleDb;
import a2_1901040037.views.frames.module.ModuleCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static a2_1901040037.utils.DbConnect.getConnection;

public class ModuleRepository implements CRUDRepository<ModuleDb> {
    @Override
    public List<ModuleDb> getAll() {
        String sql = "SELECT * FROM module";
        List<ModuleDb> moduleList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);

                ResultSet rs = prepareStatement.executeQuery()
        ) {
            // loop through the result set
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                int semester = rs.getInt("semester");
                int id = rs.getInt("id");
                String departmentName = rs.getString("department_name");
                String moduleType = rs.getString("module_type");
                int credits = rs.getInt("credits");
                moduleList.add(new ModuleDb(id,code,name, semester,departmentName,credits));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return moduleList;
    }

    @Override
    public void delete(ModuleDb o) {

    }

    @Override
    public void update(ModuleDb o) {

    }

    public void saveOne(ModuleCreation module) {
        String sql = """
                INSERT INTO module (code, name, semester, department_name, module_type)
                VALUES (?, ?, ?, ?, ?);
                """;
        try (
                Connection connection = getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ) {
            prepareStatement.setString(1, module.getCode());
            prepareStatement.setString(2, module.getName());
            prepareStatement.setInt(3, module.getSemester());
            prepareStatement.setString(4, module.getDepartmentName());
            prepareStatement.setString(5, module.getModuleType());
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }


    public Object[][] getAllModuleReport() {
        String sql = """
                SELECT * FROM module
                """;
        Object[][] data = new Object[this.count("module")][];
        try (
                Connection connection = getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);

                ResultSet rs = prepareStatement.executeQuery()
        ) {
            int count = 0;
            // loop through the result set
            while (rs.next()) {
                int moduleNo = rs.getInt("id");
                int semester = rs.getInt("semester");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String moduleType = rs.getString("module_type");
                int credits = rs.getInt("credits");
                String moduleDepartmentName = rs.getString("department_name");
                data[count] = new Object[]{
                        moduleNo,
                        code,
                        name,
                        moduleType,
                        semester,
                        credits,
                        moduleDepartmentName
                };
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return data;
    }

    public String getLatestModuleCodeBySemester(int semester) {
        String SQL = """
                SELECT MAX(m.code) AS 'latest_module_code'
                FROM module AS m
                where m.code like ?
                """;

        try (
                Connection connection = getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(SQL);

        ) {
            prepareStatement.setString(1, "M" + semester + "%");
            ResultSet rs = prepareStatement.executeQuery();

            if(rs.next()){
                return rs.getString("latest_module_code");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return null;
    }
}
