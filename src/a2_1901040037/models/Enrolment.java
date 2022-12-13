package a2_1901040037.models;

import a2_1901040037.exceptions.InvalidValueException;
import a2_1901040037.models.module.Module;

import java.util.Arrays;

public class Enrolment {
    private Student student;
    private Module module;
    private double internalMark;
    private double examinationMark;
    private char finalGrade;

    public Enrolment(Student student, Module module) {
        this.student = student;
        this.module = module;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getInternalMark() {
        return internalMark;
    }

    public void setInternalMark(double internalMark) {
        validateValidMark(internalMark, "Internal Mark");
        this.internalMark = internalMark;
    }

    public double getExaminationMark() {
        return examinationMark;
    }

    public void setExaminationMark(double examinationMark) {
        validateValidMark(examinationMark, "Examination Mark");
        this.examinationMark = examinationMark;
    }

    public char getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(char finalGrade) {
        validateFinalGrade(finalGrade);
        this.finalGrade = finalGrade;
    }

    /**
     * @effect if mark is not valid
     * throw InvalidValueException
     */
    private void validateValidMark(double mark, String markType){
        if(mark < 0 || mark > 10){
            throw new InvalidValueException(String.valueOf(mark), markType);
        }
    }

    /**
     * @effect if final Grade is not valid
     * throw InvalidValueException
     */
    private void validateFinalGrade(char finalGrade){
        if(!Arrays.asList('E','G','P','F').contains(finalGrade)){
            throw new InvalidValueException(String.valueOf(finalGrade), "Final Grade");
        }
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "student=" + student +
                ", module=" + module +
                ", internalMark=" + internalMark +
                ", examinationMark=" + examinationMark +
                ", finalGrade=" + finalGrade +
                '}';
    }

    public String report() {
        return "Enrolment{" +
                "student=" + student +
                ", module=" + module +
                '}';
    }

}
