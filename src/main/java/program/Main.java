package program;

import entities.Author;
import entities.Book;
import entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.DbContextZibert;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session context = DbContextZibert.getSessionFactory().openSession();
//        System.out.println("Hello world!");
//        Role role = new Role();
//        role.setName("admin");
//        context.save(role);
//        Query query = context.createQuery("FROM Role");
//        List<Role> roles = query.list();
//        for (Role role : roles)
//            System.out.println(role);
//
//        Author peter = new Author();
//        peter.setFullName("Петро Могилев");
//        Author ivan = new Author();
//        ivan.setFullName("Іван Кабан");
//        Book garik = new Book();
//        garik.setName("Гарі Потер");
//        garik.setAuthor(peter);
//
//        Book rusalka = new Book();
//        rusalka.setName("Русалка на прогулці");
//        rusalka.setAuthor(ivan);
//
//        Book slavik = new Book();
//        slavik.setName("Походи Славіка");
//        slavik.setAuthor(ivan);

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();


//            session.delete(book);
//            session.save(peter);
//            session.save(ivan);
//
//            session.save(garik);
//            session.save(rusalka);
//            session.save(slavik);

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (!sessionFactory.isClosed()) {
                System.out.println("Closing SessionFactory");
                sessionFactory.close();
            }
        }

        context.close();
    }
}
