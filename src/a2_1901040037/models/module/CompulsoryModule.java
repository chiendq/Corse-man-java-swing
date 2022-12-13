package a2_1901040037.models.module;

public class CompulsoryModule extends Module {

    private CompulsoryModule() {

    }


    public CompulsoryModule(String name, int semester) {
        super(name, semester);
    }

    @Override
    public String toString() {
        return "CompulsoryModule{" +
                "code='" + super.getCode() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", semester=" + super.getSemester() +
                ", credits=" + super.getCredits() +
                '}';
    }
}