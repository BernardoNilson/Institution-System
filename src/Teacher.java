import java.util.ArrayList;
import java.util.List;

/*
 * Teacher
 */
public class Teacher extends Person {
    private List<Class> classes;
    
    public Teacher(String name, int id){
        super(name, id, 12);
        classes = new ArrayList<>();
    }

    // Getters
    public List<Class> getClasses() {
        return classes;
    }

    public boolean addClass(Class clazz) {
        return classes.add(clazz);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}