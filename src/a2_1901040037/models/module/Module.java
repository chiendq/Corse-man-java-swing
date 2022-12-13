package a2_1901040037.models.module;

import a2_1901040037.exceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;
/**
 * @overview A general  Module is a course module of each semester
 * @attributes
 * code    String
 * name  String
 * semester int
 * credits int
 */
public abstract class Module {
    private String code;
    private String name;
    private int semester;

    private static int credits;

    private static Map<Integer, Integer> codeMap = new HashMap<>();

    public Module() {

    }

    public Module(String name, int semester) {
        this.code = generateCode(semester);
        setName(name);
        setSemester(semester);
        credits++;
    }

    public static String generateCode(int semester) {
        if (codeMap.containsKey(semester)) {
            int currentIndex = codeMap.get(semester) + 1;
            codeMap.put(semester, currentIndex);
            return "M" + (semester * 100 + currentIndex);
        } else {
            codeMap.put(semester, 1);
            return "M" + (semester * 100 + 1);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        validateCode(code);
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        validateSemester(semester);
        this.semester = semester;
    }

    public static int getCredits() {
        return credits;
    }


    /**
     * @effect if code is not valid
     * throw InvalidValueException
     */
    private void validateCode(String code) throws InvalidValueException {
        boolean validLetter = code.startsWith("M");
        boolean validLength = code.length() == 3;
        if(validLetter && validLength ){
            throw new InvalidValueException(code, "Code");
        }
    }

    /**
     * @effect if name is empty
     * throw InvalidValueException
     */
    private void validateName(String name) {
        if (name.isEmpty()) {
            throw new InvalidValueException("Empty name", "Name");
        }
    }

    /**
     * @effect if semester is not valid
     * throw InvalidValueException
     */
    private void validateSemester(int semester) {
        if (semester <= 0) {
            throw new InvalidValueException("Empty name", "Name");
        }
    }

    @Override
    public String toString() {
        return "Module{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", credits=" + credits +
                '}';
    }
}
