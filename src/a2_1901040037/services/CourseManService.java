package a2_1901040037.services;

import a2_1901040037.repositories.EnrollmentRepository;
import a2_1901040037.repositories.ModuleRepository;
import a2_1901040037.repositories.StudentRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseManService {
    private StudentRepository studentRepository;

    private ModuleRepository moduleRepository;

    private EnrollmentRepository enrollmentRepository;

    public CourseManService() {
        this.studentRepository = new StudentRepository();
        this.moduleRepository = new ModuleRepository();
        this.enrollmentRepository = new EnrollmentRepository();
    }

    public String generateModuleCode(int semester){
        String code = moduleRepository.getLatestModuleCodeBySemester(semester);
        if(code == null){
            return "M" + (semester * 100 + 1);
        }
        int codeWithoutChar = Integer.parseInt(code.replace("M", ""));
        return "M" + (codeWithoutChar + 1);
    }
}
