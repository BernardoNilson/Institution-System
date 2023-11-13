public class Person {
    protected String name;
    protected int id; // matricula
    protected int maxHours; // limite de tempo semanal

    public Person(String name, int id, int maxHours){
        this.name = name;
        this.id = id;
        this.maxHours = maxHours;
    }
}
