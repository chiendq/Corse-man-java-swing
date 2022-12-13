package a2_1901040037.views.frames.enrollment;

public class InitialReportTableModel {
    private int enrollmentId;
    private String studentId;
    private String studentName;
    private String moduleCode;
    private String moduleName;

    public InitialReportTableModel(int enrollmentId, String studentId, String studentName, String moduleCode, String moduleName) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }
}
