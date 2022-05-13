package javezProject.service;

import javezProject.dao.TemplateDAO;
import javezProject.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemplateServiceImp implements TemplateService {
    private TemplateDAO templateDAO;

    @Autowired
    public void setTemplateDAO(TemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }

    @Override
    @Transactional
    public List<Template> allTemplates() {
        return templateDAO.allTemplates();
    }

    @Override
    @Transactional
    public List<Template> someTemplate(String templateId) {
        return templateDAO.someTemplate(templateId);
    }

    @Override
    @Transactional
    public void add(Template template) {
        templateDAO.add(template);
    }

    @Override
    @Transactional
    public void delete(Template template) {
        templateDAO.delete(template);
    }

    @Override
    @Transactional
    public void edit(Template template) {
        templateDAO.edit(template);
    }

    @Override
    @Transactional
    public Template getById(int id) {
        return templateDAO.getById(id);
    }

}
