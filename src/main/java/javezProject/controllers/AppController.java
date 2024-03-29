package javezProject.controllers;

import com.lowagie.text.DocumentException;
import javezProject.model.Department;
import javezProject.model.Employee;
import javezProject.model.Template;
import javezProject.model.User;
import javezProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private PdfService pdfService;



    @GetMapping("/")
    public String start() {

        return "/start";
    }

    @GetMapping("/login")
    public String authentication() {
        return "/login";
    }

    @GetMapping("/login-admin")
    public String authenticationAdmin() {
        return "/login-admin";
    }

    @PostMapping("/home-admin")
    public ModelAndView authenticationAdmin(@RequestParam("nickname") String nickname,
                                            @RequestParam("password") String password) {

        Boolean checkIfExist = userService.findUser(nickname, password);
        String loginRole = userService.getUserRole(nickname, password);
        String needRole = "admin";

        if(checkIfExist == true && loginRole.equals(needRole)) {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/home-admin");
            modelAndView.addObject("nickname", nickname);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/login-admin");
            return modelAndView;
        }
    }

    @PostMapping("/home-admin/all")
    public ModelAndView authenticationAdmin(@RequestParam("nickname") String nickname) {

            List<User> userList = userService.allUsers();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/home-admin");
            modelAndView.addObject("nickname", nickname);
            modelAndView.addObject("userList", userList);
            return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@RequestParam("nickname") String nickname) {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-user");
        modelAndView.addObject("nicknameState", nickname);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/create-user-confirm")
    public ModelAndView confirmCreateUser(@RequestParam("nicknameState") String nicknameState,
                                            @RequestParam("sname") String sname,
                                          @RequestParam("fname") String fname,
                                          @RequestParam("nickname") String nickname,
                                          @RequestParam("email") String email,
                                          @RequestParam("phoneNumber") String phoneNumber,
                                          @RequestParam("homeAddress") String homeAddress,
                                          @RequestParam("role") String role,
                                          @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setFname(fname);
        user.setSname(sname);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHomeAddress(homeAddress);
        user.setRole(role);
        user.setPassword(password);

        userService.add(user);

        modelAndView.setViewName("update-confirm-user");
        modelAndView.addObject("nickname", nicknameState);
        return modelAndView;

    }

    @PostMapping("/edit-user/{id}")
    public ModelAndView editUser(@RequestParam("nickname") String nickname,
                                     @RequestParam("userId") int userId) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(userId);
        modelAndView.setViewName("/edit-user");
        modelAndView.addObject("nicknameState", nickname);
        modelAndView.addObject("userId", user.getId());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit-user-confirm/{id}")
    public ModelAndView confirmEditEmployee(@RequestParam("nicknameState") String nicknameState,
                                            @RequestParam("userId") int userId,
                                            @RequestParam("sname") String sname,
                                            @RequestParam("fname") String fname,
                                            @RequestParam("nickname") String nickname,
                                            @RequestParam("email") String email,
                                            @RequestParam("phoneNumber") String phoneNumber,
                                            @RequestParam("homeAddress") String homeAddress,
                                            @RequestParam("role") String role,
                                            @RequestParam("password") String password) {
//        @Validated
//        BindingResult bindingResult
//        bindingResult.hasErrors()
//        Department oldDep = departmentService.getById(oldDepId);
//        oldDep.deleteEmployee(employeeId);

        User user = new User();
        user.setId(userId);
        user.setFname(fname);
        user.setSname(sname);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHomeAddress(homeAddress);
        user.setRole(role);
        user.setPassword(password);

        userService.edit(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-confirm-user");
        modelAndView.addObject("nickname", nicknameState);
        return modelAndView;
    }

    @PostMapping("/delete-user/{id}")
    public ModelAndView deleteUser(@RequestParam("nickname") String nickname,
                                       @RequestParam("userId") int userId) {

        userService.delete(userService.getById(userId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-confirm-user");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;
    }


    @PostMapping("/home")
    public ModelAndView authentication(@RequestParam("nickname") String nickname,
                                       @RequestParam("password") String password) {

        Boolean checkIfExist = userService.findUser(nickname, password);
        String loginRole = userService.getUserRole(nickname, password);
        String needRole = "user";

        if(checkIfExist == true && loginRole.equals(needRole)) {

            String title = "Оберіть відділ для отримання даних";
            List<Department> depList = departmentService.allDepartments();

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/home");
            modelAndView.addObject("title", title);
            modelAndView.addObject("nickname", nickname);
            modelAndView.addObject("departmentList", depList);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/login");
            return modelAndView;
        }
    }

    @PostMapping("/home/all-departments")
    public ModelAndView homeAllEmployees(@RequestParam("nickname") String nickname){

        List<Department> departmentList = departmentService.allDepartments();
        List<Employee> employeeList = employeeService.allEmployees();
        String title = "Ви знаходитесь у розділі усіх Відділів";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        modelAndView.addObject("title", title);
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("departmentList", departmentList);
        return modelAndView;
    }

    @PostMapping("/home/some-department")
    public ModelAndView homeSomeEmployee(@RequestParam("nickname") String nickname,
                                         @RequestParam("departmentName") String departmentName,
                                         @RequestParam("departmentId") String departmentId){

        List<Department> departmentList = departmentService.allDepartments();
        List<Employee> employeeList = employeeService.someEmployees(departmentId);
        String title = "Ви знаходитесь у розділі: " + departmentName;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        modelAndView.addObject("title", title);
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("departmentList", departmentList);
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView createEmployee(@RequestParam("nickname") String nickname) {
        Employee employee = new Employee();
        List<Department> departments = departmentService.allDepartments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-employee");
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }

    @PostMapping("/create-employee-confirm")
    public ModelAndView confirmCreateEmployee(@RequestParam("nickname") String nickname,
                                              @RequestParam("createRecordDate") Date createRecordDate,
                                              @RequestParam("sName") String sName,
                                              @RequestParam("fName") String fName,
                                              @RequestParam("tName") String tName,
                                              @RequestParam("departmentId") int departmentId,
                                              @RequestParam("identityNumber") String identityNumber,
                                              @RequestParam("passportNumb") String passportNumb,
                                              @RequestParam("sex") String sex,
                                              @RequestParam("birthday") Date birthday,
                                              @RequestParam("country") String country,
                                              @RequestParam("universityName") String universityName,
                                              @RequestParam("diplomNumb") String diplomNumb,
                                              @RequestParam("endUniversityYear") Date endUniversityYear,
                                              @RequestParam("speciality") String speciality,
                                              @RequestParam("educationForm") String educationForm,
                                              @RequestParam("stateRegisterHomeAddress") String stateRegisterHomeAddress,
                                              @RequestParam("actualHomeAddress") String actualHomeAddress,
                                              @RequestParam("workExperience") String workExperience,
                                              @RequestParam("position") String position,
                                              @RequestParam("status") String status) {

        ModelAndView modelAndView = new ModelAndView();
        Department department;
        Employee employee = new Employee();
        department = departmentService.getById(departmentId);
        employee.setCreateRecordDate(createRecordDate);
        employee.setsName(sName);
        employee.setfName(fName);
        employee.settName(tName);
        employee.setDepartment(department);
        employee.setIdentityNumber(identityNumber);
        employee.setPassportNumb(passportNumb);
        employee.setSex(sex);
        employee.setBirthday(birthday);
        employee.setCountry(country);
        employee.setUniversityName(universityName);
        employee.setDiplomNumb(diplomNumb);
        employee.setEndUniversityYear(endUniversityYear);
        employee.setSpeciality(speciality);
        employee.setEducationForm(educationForm);
        employee.setStateRegisterHomeAddress(stateRegisterHomeAddress);
        employee.setActualHomeAddress(actualHomeAddress);
        employee.setWorkExperience(workExperience);
        employee.setPosition(position);
        employee.setStatus(status);

        employeeService.add(employee);

        modelAndView.setViewName("update-confirm-employee");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;

    }

    @PostMapping("/edit-employee/{id}")
    public ModelAndView editEmployee(@RequestParam("nickname") String nickname,
                                     @RequestParam("employeeId") int employeeId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Department> departments = departmentService.allDepartments();
        Employee employee = employeeService.getById(employeeId);
        int oldDepId = employee.getDepartment().getId();
        modelAndView.setViewName("/edit-employee");
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("employeeId", employee.getId());
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("departmentList", departments);
        modelAndView.addObject("oldDepId", oldDepId);
        return modelAndView;
    }

    @PostMapping("/edit-employee-confirm/{id}")
    public ModelAndView confirmEditEmployee(@RequestParam("nickname") String nickname,
                                            @RequestParam("employeeId") int employeeId,
                                            @RequestParam("createRecordDate") Date createRecordDate,
                                            @RequestParam("sName") String sName,
                                            @RequestParam("fName") String fName,
                                            @RequestParam("tName") String tName,
                                            @RequestParam("departmentId") int departmentId,
                                            @RequestParam("identityNumber") String identityNumber,
                                            @RequestParam("passportNumb") String passportNumb,
                                            @RequestParam("sex") String sex,
                                            @RequestParam("birthday") Date birthday,
                                            @RequestParam("country") String country,
                                            @RequestParam("universityName") String universityName,
                                            @RequestParam("diplomNumb") String diplomNumb,
                                            @RequestParam("endUniversityYear") Date endUniversityYear,
                                            @RequestParam("speciality") String speciality,
                                            @RequestParam("educationForm") String educationForm,
                                            @RequestParam("stateRegisterHomeAddress") String stateRegisterHomeAddress,
                                            @RequestParam("actualHomeAddress") String actualHomeAddress,
                                            @RequestParam("workExperience") String workExperience,
                                            @RequestParam("position") String position,
                                            @RequestParam("status") String status) {
//        @Validated
//        BindingResult bindingResult
//        bindingResult.hasErrors()
//        Department oldDep = departmentService.getById(oldDepId);
//        oldDep.deleteEmployee(employeeId);

        Employee employee = new Employee();
        employee.setId(employeeId);
        Department dep = departmentService.getById(departmentId);
        employee.setCreateRecordDate(createRecordDate);
        employee.setsName(sName);
        employee.setfName(fName);
        employee.settName(tName);
        employee.setDepartment(dep);
        employee.setIdentityNumber(identityNumber);
        employee.setPassportNumb(passportNumb);
        employee.setSex(sex);
        employee.setBirthday(birthday);
        employee.setCountry(country);
        employee.setUniversityName(universityName);
        employee.setDiplomNumb(diplomNumb);
        employee.setEndUniversityYear(endUniversityYear);
        employee.setSpeciality(speciality);
        employee.setEducationForm(educationForm);
        employee.setStateRegisterHomeAddress(stateRegisterHomeAddress);
        employee.setActualHomeAddress(actualHomeAddress);
        employee.setWorkExperience(workExperience);
        employee.setPosition(position);
        employee.setStatus(status);
        employeeService.edit(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-confirm-employee");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;
    }

    @PostMapping("/delete-employee/{id}")
    public ModelAndView deleteEmployee(@RequestParam("nickname") String nickname,
                                       @RequestParam("employeeId") int employeeId) {

        employeeService.delete(employeeService.getById(employeeId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-confirm-employee");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;
    }

    @PostMapping("/create-department")
    public ModelAndView createDepartment(@RequestParam("nickname") String nickname) {

        Department department = new Department();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-department");
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("department", department);
        return modelAndView;
    }

    @PostMapping("/create-department-confirm")
    public ModelAndView confirmCreateDepartment(@RequestParam("nickname") String nickname,
                                                @RequestParam("name") String name,
                                                @RequestParam("discription") String discription) {

        ModelAndView modelAndView = new ModelAndView();

        Department department = new Department();
        department.setName(name);
        department.setDiscription(discription);

        departmentService.add(department);
        modelAndView.setViewName("update-confirm-department");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;

    }

    @PostMapping("/home/templates")
    public ModelAndView homeTemplates(@RequestParam("nickname") String nickname) {

        String title = "Оберіть тип шаблонів для отримання даних";
        List<Template> templateList = templateService.allTemplates();
        List<Department> departmentList = departmentService.allDepartments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home-template");
        modelAndView.addObject("title", title);
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("departmentList", departmentList);

        return modelAndView;
    }


    @PostMapping("/home/all-templates")
    public ModelAndView homeAllTemplates(@RequestParam("nickname") String nickname){

        List<Department> departmentList = departmentService.allDepartments();
        List<Template> templateList = templateService.allTemplates();
        String title = "Ви знаходитесь у розділі усіх Шаблонів";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home-template");
        modelAndView.addObject("title", title);
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("templateList", templateList);
        modelAndView.addObject("departmentList", departmentList);
        return modelAndView;
    }

    @PostMapping("/home/some-template")
    public ModelAndView homeSomeTemplate(@RequestParam("nickname") String nickname,
                                         @RequestParam("departmentName") String departmentName,
                                         @RequestParam("departmentId") String departmentId){

        List<Department> departmentList = departmentService.allDepartments();
        List<Template> templateList = templateService.someTemplate(departmentId);
        String title = "Ви знаходитесь у розділі: " + departmentName;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home-template");
        modelAndView.addObject("title", title);
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("templateList", templateList);
        modelAndView.addObject("departmentList", departmentList);
        return modelAndView;
    }


    @PostMapping("/create-template")
    public ModelAndView createTemplate(@RequestParam("nickname") String nickname) {
        Template template = new Template();
        List<Department> departments = departmentService.allDepartments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-template");
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("template", template);
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }

    @PostMapping("/create-template-confirm")
    public ModelAndView confirmCreateTemplate(@RequestParam("nickname") String nickname,
                                              @RequestParam("companyName") String companyName,
                                              @RequestParam("infoCompany") String infoCompany,
                                              @RequestParam("locationCity") String locationCity,
                                              @RequestParam("positionName") String positionName,
                                              @RequestParam("salary") String salary,
                                              @RequestParam("departmentId") int departmentId,
                                              @RequestParam("jobDiscription") String jobDiscription,
                                              @RequestParam("requirements1") String requirements1,
                                              @RequestParam("requirements2") String requirements2,
                                              @RequestParam("requirements3") String requirements3,
                                              @RequestParam("requirements4") String requirements4,
                                              @RequestParam("requirements5") String requirements5,
                                              @RequestParam("task1") String task1,
                                              @RequestParam("task2") String task2,
                                              @RequestParam("task3") String task3,
                                              @RequestParam("task4") String task4,
                                              @RequestParam("task5") String task5,
                                              @RequestParam("workingConditions1") String workingConditions1,
                                              @RequestParam("workingConditions2") String workingConditions2,
                                              @RequestParam("workingConditions3") String workingConditions3,
                                              @RequestParam("workingConditions4") String workingConditions4) {

        ModelAndView modelAndView = new ModelAndView();
        Department department2;
        Template template = new Template();
        department2 = departmentService.getById(departmentId);
        template.setPositionName(positionName);
        template.setSalary(salary);
        template.setCompanyName(companyName);
        template.setInfoCompany(infoCompany);
        template.setDepartment(department2);
        template.setLocationCity(locationCity);
        template.setJobDiscription(jobDiscription);
        template.setRequirements1(requirements1);
        template.setRequirements2(requirements2);
        template.setRequirements3(requirements3);
        template.setRequirements4(requirements4);
        template.setRequirements5(requirements5);
        template.setTask1(task1);
        template.setTask2(task2);
        template.setTask3(task3);
        template.setTask4(task4);
        template.setTask5(task5);
        template.setWorkingConditions1(workingConditions1);
        template.setWorkingConditions2(workingConditions2);
        template.setWorkingConditions3(workingConditions3);
        template.setWorkingConditions4(workingConditions4);


        templateService.add(template);

        modelAndView.setViewName("update-confirm-template");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;
    }


    @PostMapping("/edit-template/{id}")
    public ModelAndView editTemplate(@RequestParam("nickname") String nickname,
                                     @RequestParam("templateId") int templateId, @PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Department> departments1 = departmentService.allDepartments();
        Template template = templateService.getById(templateId);
        int oldDepId = template.getDepartment().getId();
        modelAndView.setViewName("/edit-template");
        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("templateId", template.getId());
        modelAndView.addObject("template", template);
        modelAndView.addObject("departmentList", departments1);
        modelAndView.addObject("oldDepId", oldDepId);
        return modelAndView;
    }

    @PostMapping("/edit-template-confirm/{id}")
    public ModelAndView confirmEditTemplate(@RequestParam("nickname") String nickname,
                                            @RequestParam("templateId") int templateId,
                                            @RequestParam("positionName") String positionName,
                                            @RequestParam("salary") String salary,
                                            @RequestParam("companyName") String companyName,
                                            @RequestParam("infoCompany") String infoCompany,
                                            @RequestParam("departmentId") int departmentId,
                                            @RequestParam("locationCity") String locationCity,
                                            @RequestParam("jobDiscription") String jobDiscription,
                                            @RequestParam("requirements1") String requirements1,
                                            @RequestParam("requirements2") String requirements2,
                                            @RequestParam("requirements3") String requirements3,
                                            @RequestParam("requirements4") String requirements4,
                                            @RequestParam("requirements5") String requirements5,
                                            @RequestParam("task1") String task1,
                                            @RequestParam("task2") String task2,
                                            @RequestParam("task3") String task3,
                                            @RequestParam("task4") String task4,
                                            @RequestParam("task5") String task5,
                                            @RequestParam("workingConditions1") String workingConditions1,
                                            @RequestParam("workingConditions2") String workingConditions2,
                                            @RequestParam("workingConditions3") String workingConditions3,
                                            @RequestParam("workingConditions4") String workingConditions4, @PathVariable String id) {


        Template template = new Template();
        template.setId(templateId);
        Department department;
        department = departmentService.getById(departmentId);
        template.setPositionName(positionName);
        template.setSalary(salary);
        template.setCompanyName(companyName);
        template.setInfoCompany(infoCompany);
        template.setDepartment(department);
        template.setLocationCity(locationCity);
        template.setJobDiscription(jobDiscription);
        template.setRequirements1(requirements1);
        template.setRequirements2(requirements2);
        template.setRequirements3(requirements3);
        template.setRequirements4(requirements4);
        template.setRequirements5(requirements5);
        template.setTask1(task1);
        template.setTask2(task2);
        template.setTask3(task3);
        template.setTask4(task4);
        template.setTask5(task5);
        template.setWorkingConditions1(workingConditions1);
        template.setWorkingConditions2(workingConditions2);
        template.setWorkingConditions3(workingConditions3);
        template.setWorkingConditions4(workingConditions4);
        templateService.edit(template);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-confirm-template");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;
    }

    @PostMapping("/delete-template/{id}")
    public ModelAndView deleteTemplate(@RequestParam("nickname") String nickname,
                                       @RequestParam("templateId") int templateId, @PathVariable String id) {

        templateService.delete(templateService.getById(templateId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-confirm-tempalte");
        modelAndView.addObject("nickname", nickname);
        return modelAndView;
    }

    @PostMapping("/open-template")
    public ModelAndView openTemplate(@RequestParam("nickname") String nickname,
                                    @RequestParam("templateId") int templateId) {
        Template template = templateService.getById(templateId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("open-template");

        modelAndView.addObject("nickname", nickname);
        modelAndView.addObject("template", template);

        return modelAndView;
    }


    @PostMapping("/print-template")
    public void download(HttpServletResponse response,
                         @RequestParam("templateId") int templateId) throws IOException, DocumentException {
        Template template = templateService.getById(templateId);

        String htmlInvoice = pdfService.buildHtmlFromTemplate(template);


        ByteArrayOutputStream bos = pdfService.generatePdf(htmlInvoice);
        byte[] pdfReport = bos.toByteArray();

        String mimeType =  "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "template.pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }


}

