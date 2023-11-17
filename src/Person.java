public class Person {
    protected String name;
    protected int id; // matricula
    protected int maxHours; // limite de tempo semanal
    protected int usedHours; // tempo utilizado semananalmente

    public Person(String name, int id, int maxHours){
        this.name = name;
        this.id = id;
        this.maxHours = maxHours;
        usedHours = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getMaxHours() {
        return maxHours;
    }

    public int getUsedHours() {
        return usedHours;
    }

    public void addUsedHours(int toAdd) {
        usedHours = usedHours + toAdd;
    }

    @Override
    public String toString() {
        return "Nome: " + name + ", Matrícula: " + id;
        /* s += "\n Carga-horária máxima: " + maxHours;
        s += "\n Carga-horária atual: " + usedHours; */
    }
}
