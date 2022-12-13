package a2_1901040037.views.frames.module;


public class ModuleCreation {
    private String code;
    private String name;
    private int semester;
    private String departmentName;
    private String moduleType;
    private int credits;

    public ModuleCreation(String code, String name, int semester, String departmentName, String moduleType, int credits) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.departmentName = departmentName;
        this.moduleType = moduleType;
        this.credits = credits;
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

    public String getModuleType() {
        return moduleType;
    }

    public int getCredits() {
        return credits;
    }
}
