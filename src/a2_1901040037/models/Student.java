package a2_1901040037.models;


import a2_1901040037.exceptions.InvalidValueException;

import java.util.Calendar;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Map.entry;

/**
 * @overview A general  Student is a student of a faculty of a university who enrol into course module
 * @attributes id    String
 * name  String
 * dob   LocalDate
 * address String
 * email String
 */
public class Student {
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static int currentStudent = 0;

    private static final String EMAIL_PATTERN = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    private String id;
    private String name;
    private String dob;
    private String address;
    private String email;

    public Student(String name, String dob, String address, String email) throws InvalidValueException {
        this.id = "S" + (CURRENT_YEAR + currentStudent);
        setName(name);
        setDob(dob);
        setAddress(address);
        setEmail(email);
        currentStudent++;
    }

    public Student(String id, String name, String dob, String address, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        validateId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
//        validateDateOfBirth(dob);
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    /**
     * @effect if id is not valid
     * throw InvalidValueException
     */
    private void validateId(String id) throws InvalidValueException {
        boolean validLetter = id.startsWith("S");
        boolean validLength = id.length() == 5;
        boolean validIndex = Integer.parseInt(id.substring(1, 4)) >= CURRENT_YEAR;
        if (validLetter && validLength && validIndex) {
            throw new InvalidValueException(id, "Id");
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

//    /**
//     * @effect if date of birth is not valid
//     * throw InvalidValueException
//     */
//    private void validateDateOfBirth(String dob) throws InvalidValueException {
//        if (dob.isAfter(LocalDate.now())) {
//            throw new InvalidValueException(dob.toString(), "LocalDate");
//        }
//    }

    /**
     * @effect if address is empty
     * throw InvalidValueException
     */
    private void validateAddress(String address) {
        if (address.isEmpty()) {
            throw new InvalidValueException("Empty address", "Address");
        }
    }

    /**
     * @effect if email is not valid
     * throw InvalidValueException
     */
    private void validateEmail(String email) {
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            throw new InvalidValueException(email, "Email");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}