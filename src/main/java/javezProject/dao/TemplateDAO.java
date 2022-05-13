package javezProject.dao;

import javezProject.model.Template;
import java.util.List;

public interface TemplateDAO {
    List<Template> allTemplates();
    List<Template> someTemplate(String templateId);
    void add(Template template);
    void delete(Template template);
    void edit(Template template);
    Template getById(int id);
}
