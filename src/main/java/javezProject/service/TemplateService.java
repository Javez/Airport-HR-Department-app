package javezProject.service;

import javezProject.model.Template;
import java.util.List;

public interface TemplateService {
    List<Template> allTemplates();
    List<Template> someTemplate(String templateId);
    void add(Template template);
    void delete(Template template);
    void edit(Template template);
    Template getById(int id);
}
