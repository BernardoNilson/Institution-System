import java.util.ArrayList;
import java.util.List;

/*
 * Teacher
 */
public class Teacher extends Person {
    private Degree degree;
    private List<Class> classes;
    
    public Teacher(String name, int id, Degree degree){
        super(name, id, 12);
        this.degree = degree;
        classes = new ArrayList<>();
    }

    // Getters
    public Degree getDegree() {
        return degree;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public boolean addClass(Class clazz) {
        return classes.add(clazz);
    }

    @Override
    public String toString() {
        return super.toString() + ", Formação: " + degree;
    }
}