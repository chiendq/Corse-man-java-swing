package a2_1901040037.views.frames.enrollment;

public class EnrollmentCreation {
    private String studentId;
    private String moduleId;
    private float internalMark;
    private float examMark;

    private char finalGrade;

    public EnrollmentCreation(String studentId, String moduleId, float internalMark, float examMark) {
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.internalMark = internalMark;
        this.examMark = examMark;
        this.finalGrade = generateFinalGrade(internalMark, examMark);
    }

    private char generateFinalGrade(double internalMark, double examinationMark) {
        double aggregatedMark = (0.4 * internalMark) + (0.6 * examinationMark);
        if (aggregatedMark < 5) {
            return 'F';
        } else if (aggregatedMark >= 5 && aggregatedMark < 6) {
            return 'P';
        } else if (aggregatedMark > 6 && aggregatedMark <= 8) {
            return 'G';
        }
        return 'E';
    }

    public String getStudentId() {
        return studentId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public float getInternalMark() {
        return internalMark;
    }

    public float getExamMark() {
        return examMark;
    }

    public char getFinalGrade() {
        return finalGrade;
    }
}
