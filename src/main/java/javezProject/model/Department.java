package javezProject.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments", schema = "zhulyanydb")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    @OneToMany(mappedBy = "department")
    private List<Template> templates;
    @Column(name = "name")
    private String name;
    @Column(name = "discription")
    private String discription;

    @Autowired
    public Department() {
    }

    public Department(int id, List<Employee> employees, List<Template> templates, String name, String discription) {
        this.id = id;
        this.employees = employees;
        this.templates = templates;
        this.name = name;
        this.discription = discription;
    }


    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

//    public void deleteEmployee(int employeeId) {
//        employees.remove(employeeId);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employeeList) {
        this.employees = employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", employees=" + employees +
                ", templates=" + templates +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }
}
