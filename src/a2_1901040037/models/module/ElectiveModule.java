package a2_1901040037.models.module;

import a2_1901040037.exceptions.InvalidValueException;

public class ElectiveModule extends Module {
    private String departmentName;

    private ElectiveModule() {
    }

    public ElectiveModule(String name, int semester, String departmentName) {
        super(name, semester);
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        setDepartmentName(departmentName);
        this.departmentName = departmentName;
    }

    /**
     * @effect if department name is empty
     * throw InvalidValueException
     */
    private void validateDepartmentName(String departmentName) {
        if (departmentName.isEmpty()) {
            throw new InvalidValueException("Empty name", "Department name");
        }
    }
    @Override
    public String toString() {
        return "ElectiveModule{" +
                "code='" + super.getCode() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", semester=" + super.getSemester() +
                ", credits=" + super.getCredits() +
                ", departmentName=" + this.getDepartmentName() +
                '}';
    }
}
