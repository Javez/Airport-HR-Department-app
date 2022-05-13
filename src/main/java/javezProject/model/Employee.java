package javezProject.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employees", schema = "zhulyanydb")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="department_id", nullable=false)
    private Department department;
    @Column(name = "createRecordDate")
    private Date createRecordDate;
    @Column(name = "identityNumber")
    private String identityNumber;
    @Column(name = "passportNumb")
    private String passportNumb;
    @Column(name = "sex")
    private String sex;
    @Column(name = "sName")
    private String sName;
    @Column(name = "fName")
    private String fName;
    @Column(name = "tName")
    private String tName;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "country")
    private String country;
    @Column(name = "universityName")
    private String universityName;
    @Column(name = "diplomNumb")
    private String diplomNumb;
    @Column(name = "endUniversityYear")
    private Date endUniversityYear;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "educationForm")
    private String educationForm;
    @Column(name = "stateRegisterHomeAddress")
    private String stateRegisterHomeAddress;
    @Column(name = "actualHomeAddress")
    private String actualHomeAddress;
    @Column(name = "workExperience")
    private String workExperience;
    @Column(name = "position")
    private String position;
    @Column(name = "status")
    private String status;

    @Autowired
    public Employee() {}

    public Employee(int id, Department department,
                    Date createRecordDate, String identityNumber,
                    String passportNumb, String sex,
                    String sName, String fName,
                    String tName, Date birthday,
                    String country, String universityName,
                    String diplomNumb, Date endUniversityYear,
                    String speciality, String educationForm,
                    String stateRegisterHomeAddress, String actualHomeAddress,
                    String workExperience, String position, String status) {
        this.id = id;
        this.department = department;
        this.createRecordDate = createRecordDate;
        this.identityNumber = identityNumber;
        this.passportNumb = passportNumb;
        this.sex = sex;
        this.sName = sName;
        this.fName = fName;
        this.tName = tName;
        this.birthday = birthday;
        this.country = country;
        this.universityName = universityName;
        this.diplomNumb = diplomNumb;
        this.endUniversityYear = endUniversityYear;
        this.speciality = speciality;
        this.educationForm = educationForm;
        this.stateRegisterHomeAddress = stateRegisterHomeAddress;
        this.actualHomeAddress = actualHomeAddress;
        this.workExperience = workExperience;
        this.position = position;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public String getDepartmentName() { return department.getName(); }

    public void setDepartmentName(String depName) { this.department.setName(depName); }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getCreateRecordDate() {
        return createRecordDate;
    }

    public void setCreateRecordDate(Date createRecordDate) {
        this.createRecordDate = createRecordDate;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPassportNumb() {
        return passportNumb;
    }

    public void setPassportNumb(String passportNumb) {
        this.passportNumb = passportNumb;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDiplomNumb() {
        return diplomNumb;
    }

    public void setDiplomNumb(String diplomNumb) {
        this.diplomNumb = diplomNumb;
    }

    public Date getEndUniversityYear() {
        return endUniversityYear;
    }

    public void setEndUniversityYear(Date endUniversityYear) {
        this.endUniversityYear = endUniversityYear;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(String educationForm) {
        this.educationForm = educationForm;
    }

    public String getStateRegisterHomeAddress() {
        return stateRegisterHomeAddress;
    }

    public void setStateRegisterHomeAddress(String stateRegisterHomeAddress) {
        this.stateRegisterHomeAddress = stateRegisterHomeAddress;
    }

    public String getActualHomeAddress() {
        return actualHomeAddress;
    }

    public void setActualHomeAddress(String actualHomeAddress) {
        this.actualHomeAddress = actualHomeAddress;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", departments=" + department +
                ", createRecordDate='" + createRecordDate + '\'' +
                ", identityNumber=" + identityNumber +
                ", passportNumb='" + passportNumb + '\'' +
                ", sex='" + sex + '\'' +
                ", sName='" + sName + '\'' +
                ", fName='" + fName + '\'' +
                ", tName='" + tName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", country='" + country + '\'' +
                ", universityName='" + universityName + '\'' +
                ", diplomNumb='" + diplomNumb + '\'' +
                ", endUniversityYear='" + endUniversityYear + '\'' +
                ", speciality='" + speciality + '\'' +
                ", educationForm='" + educationForm + '\'' +
                ", stateRegisterHomeAddress='" + stateRegisterHomeAddress + '\'' +
                ", actualHomeAddress='" + actualHomeAddress + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
