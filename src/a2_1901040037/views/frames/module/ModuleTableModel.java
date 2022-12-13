package a2_1901040037.views.frames.module;

public class ModuleTableModel {
    private int no;
    private int semester;
    private String moduleCode;
    private String moduleName;
    private String moduleType;
    private String moduleDepartmentName;

    private int credits;

    public ModuleTableModel(int no, int semester, String moduleCode, String moduleName, String moduleType, String moduleDepartmentName, int credits) {
        this.no = no;
        this.semester = semester;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.moduleDepartmentName = moduleDepartmentName;
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "ModuleTableModel{" +
                "no=" + no +
                ", semester=" + semester +
                ", moduleCode='" + moduleCode + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", moduleType='" + moduleType + '\'' +
                ", moduleDepartmentName='" + moduleDepartmentName + '\'' +
                ", credits=" + credits +
                '}';
    }

    public int getNo() {
        return no;
    }

    public int getSemester() {
        return semester;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public String getModuleDepartmentName() {
        return moduleDepartmentName;
    }

    public int getCredits() {
        return credits;
    }
}
