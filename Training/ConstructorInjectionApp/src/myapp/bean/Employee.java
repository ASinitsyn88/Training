package myapp.bean;

public class Employee {

    private int id;
    private String name;

    // Конструктор
    public Employee() {

        System.out.println("def cons");
    }

    // Конструктор
    public Employee(int id) {

        this.id = id;
    }

    // Конструктор
    public Employee(String name) {

        this.name = name;
    }

    // Конструктор
    public Employee(int id, String name) {

        this.id = id;
        this.name = name;
    }

    // Конструктор
    public Employee(String name, int id) {

        this.id = id * 2;
        this.name = name;
    }

    /**
     * Показать информацию о сотруднике
     */
    public void show() {

        System.out.println(id + " " + name);
    }
}
