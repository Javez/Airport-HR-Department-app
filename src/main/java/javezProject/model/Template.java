package javezProject.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

@Entity
@Table(name = "templates", schema = "zhulyanydb")
public class Template {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="department_id", nullable=false)
    private Department department;
    @Column(name = "positionName")
    private String positionName;
    @Column
    private String salary;
    @Column
    private String companyName;
    @Column
    private String infoCompany;
    @Column
    private String locationCity;
    @Column
    private String jobDiscription;
    @Column
    private String requirements1;
    @Column
    private String requirements2;
    @Column
    private String requirements3;
    @Column
    private String requirements4;
    @Column
    private String requirements5;
    @Column
    private String task1;
    @Column
    private String task2;
    @Column
    private String task3;
    @Column
    private String task4;
    @Column
    private String task5;
    @Column
    private String workingConditions1;
    @Column
    private String workingConditions2;
    @Column
    private String workingConditions3;
    @Column
    private String workingConditions4;

    @Autowired
    public Template() {}

    public Template(int id, Department department, String positionName, String salary, String companyName, String infoCompany, String locationCity, String jobDiscription, String requirements1, String requirements2, String requirements3, String requirements4, String requirements5, String task1, String task2, String task3, String task4, String task5, String workingConditions1, String workingConditions2, String workingConditions3, String workingConditions4) {
        this.id = id;
        this.department = department;
        this.positionName = positionName;
        this.salary = salary;
        this.companyName = companyName;
        this.infoCompany = infoCompany;
        this.locationCity = locationCity;
        this.jobDiscription = jobDiscription;
        this.requirements1 = requirements1;
        this.requirements2 = requirements2;
        this.requirements3 = requirements3;
        this.requirements4 = requirements4;
        this.requirements5 = requirements5;
        this.task1 = task1;
        this.task2 = task2;
        this.task3 = task3;
        this.task4 = task4;
        this.task5 = task5;
        this.workingConditions1 = workingConditions1;
        this.workingConditions2 = workingConditions2;
        this.workingConditions3 = workingConditions3;
        this.workingConditions4 = workingConditions4;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInfoCompany() {
        return infoCompany;
    }

    public void setInfoCompany(String infoCompany) {
        this.infoCompany = infoCompany;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getJobDiscription() {
        return jobDiscription;
    }

    public void setJobDiscription(String jobDiscription) {
        this.jobDiscription = jobDiscription;
    }

    public String getRequirements1() {
        return requirements1;
    }

    public void setRequirements1(String requirements1) {
        this.requirements1 = requirements1;
    }

    public String getRequirements2() {
        return requirements2;
    }

    public void setRequirements2(String requirements2) {
        this.requirements2 = requirements2;
    }

    public String getRequirements3() {
        return requirements3;
    }

    public void setRequirements3(String requirements3) {
        this.requirements3 = requirements3;
    }

    public String getRequirements4() {
        return requirements4;
    }

    public void setRequirements4(String requirements4) {
        this.requirements4 = requirements4;
    }

    public String getRequirements5() {
        return requirements5;
    }

    public void setRequirements5(String requirements5) {
        this.requirements5 = requirements5;
    }

    public String getTask1() {
        return task1;
    }

    public void setTask1(String task1) {
        this.task1 = task1;
    }

    public String getTask2() {
        return task2;
    }

    public void setTask2(String task2) {
        this.task2 = task2;
    }

    public String getTask3() {
        return task3;
    }

    public void setTask3(String task3) {
        this.task3 = task3;
    }

    public String getTask4() {
        return task4;
    }

    public void setTask4(String task4) {
        this.task4 = task4;
    }

    public String getTask5() {
        return task5;
    }

    public void setTask5(String task5) {
        this.task5 = task5;
    }

    public String getWorkingConditions1() {
        return workingConditions1;
    }

    public void setWorkingConditions1(String workingConditions1) {
        this.workingConditions1 = workingConditions1;
    }

    public String getWorkingConditions2() {
        return workingConditions2;
    }

    public void setWorkingConditions2(String workingConditions2) {
        this.workingConditions2 = workingConditions2;
    }

    public String getWorkingConditions3() {
        return workingConditions3;
    }

    public void setWorkingConditions3(String workingConditions3) {
        this.workingConditions3 = workingConditions3;
    }

    public String getWorkingConditions4() {
        return workingConditions4;
    }

    public void setWorkingConditions4(String workingConditions4) {
        this.workingConditions4 = workingConditions4;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", department=" + department +
                ", positionName='" + positionName + '\'' +
                ", salary='" + salary + '\'' +
                ", companyName='" + companyName + '\'' +
                ", infoCompany='" + infoCompany + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", jobDiscription='" + jobDiscription + '\'' +
                ", requirements1='" + requirements1 + '\'' +
                ", requirements2='" + requirements2 + '\'' +
                ", requirements3='" + requirements3 + '\'' +
                ", requirements4='" + requirements4 + '\'' +
                ", requirements5='" + requirements5 + '\'' +
                ", task1='" + task1 + '\'' +
                ", task2='" + task2 + '\'' +
                ", task3='" + task3 + '\'' +
                ", task4='" + task4 + '\'' +
                ", task5='" + task5 + '\'' +
                ", workingConditions1='" + workingConditions1 + '\'' +
                ", workingConditions2='" + workingConditions2 + '\'' +
                ", workingConditions3='" + workingConditions3 + '\'' +
                ", workingConditions4='" + workingConditions4 + '\'' +
                '}';
    }
}

