package javezProject.dao;

import javezProject.model.Template;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TemplateDAOImp implements TemplateDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Template> allTemplates() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Template";
        List<Template> templates = session.createQuery(hql, Template.class).list();
        return templates;
    }

    @Override
    public List<Template> someTemplate(String templateId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Template where id = " + templateId;
        List<Template> templates = session.createQuery(hql, Template.class).list();
        return templates;
    }

    @Override
    public void add(Template template) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(template);
    }

    @Override
    public void delete(Template template) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(template);
    }

    @Override
    public void edit(Template template) {
        Session session = sessionFactory.getCurrentSession();
        session.update(template);
    }


    @Override
    public Template getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Template.class, id);
    }

}
