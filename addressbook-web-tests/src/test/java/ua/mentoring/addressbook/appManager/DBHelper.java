package ua.mentoring.addressbook.appManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.mentoring.addressbook.model.GroupData;
import ua.mentoring.addressbook.model.Groups;

import java.util.List;

public class DBHelper {

  private final SessionFactory sessionFactory;

  public DBHelper() {
    // A Session Factory is set up once for as application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() //configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }


}
