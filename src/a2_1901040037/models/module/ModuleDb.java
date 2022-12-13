package a2_1901040037.models.module;

public class ModuleDb {
    private int id;
    private String code;
    private String name;
    private int semester;
    private String departmentName;
    private int credits;

    public ModuleDb(int id, String code, String name, int semester, String departmentName, int credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.departmentName = departmentName;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getCredits() {
        return credits;
    }
}
