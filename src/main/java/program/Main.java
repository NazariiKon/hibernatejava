package program;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.DbContextZibert;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int menu = 1;
        while (menu != 0) {
            System.out.println("Меню\n" +
                    "0. Вийти\n" +
                    "1. Створити категорію\n" +
                    "2. Показати категорії\n" +
                    "3. Видалити категорію\n" +
                    "4. Змінити категорію\n" +
                    "5. Створити новий продукт\n" +
                    "6. Показати продукти\n" +
                    "7. Видалити продукт");
            try {
                menu = Integer.parseInt(in.nextLine());
            } catch (Exception ex) {
                System.out.println("Невірно введенний формат!");
                break;
            }

            switch (menu) {
                case 1: {
                    insertCategory();
                    break;
                }
                case 2: {
                    selectCategory();
                    break;
                }
                case 3: {
                    deleteCategory();
                    break;
                }
                case 4: {
                    updateCategory();
                    break;
                }
                case 5: {
                    insertProduct();
                    break;
                }
                case 6: {
                    selectProduct();
                    break;
                }
                case 7: {
                    deleteProduct();
                    break;
                }
                case 8: {
                    updateProduct();
                    break;
                }
                default: {
                    System.out.println("Невірний пункт меню!");
                    break;
                }
            }
            }
    }

    private static void insertCategory() {
        Category category = new Category();
        System.out.println("Введіть категорію: ");
        String name = in.nextLine();
        category.setName(name);

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();

            session.save(category);

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
    }
    private static void selectCategory() {
        Session context = DbContextZibert.getSessionFactory().openSession();
        Query query = context.createQuery("FROM Category");
        List<Category> categories = query.list();
        for (Category category : categories)
            System.out.println("{ id = " + category.getId() + ", name: " + category.getName() + " }");
    }
    private static void deleteCategory() {
        System.out.println("Введіть id: ");
        int id = Integer.parseInt(in.nextLine());
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();
            Category category = session.get(Category.class, id);
            session.delete(category);

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
    }
    private static void updateCategory() {
        System.out.println("Введіть id: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.println("Введіть нове ім'я: ");
        String name = in.nextLine();

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();

            Category category = session.get(Category.class, id);
            category.setName(name);
            session.saveOrUpdate(category);

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
    }

    private static void insertProduct() {
        Product product = new Product();
        System.out.println("Введіть назву: ");
        String name = in.nextLine();
        product.setName(name);
        System.out.println("Введіть ціну: ");
        double price = Double.parseDouble(in.nextLine());
        product.setPrice(price);
        selectCategory();
        System.out.println("Введіть id категорії: ");
        int id = Integer.parseInt(in.nextLine());

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();
            Category category = session.get(Category.class, id);
            product.setCategory(category);
            session.save(product);

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
    }
    private static void selectProduct() {
        Session context = DbContextZibert.getSessionFactory().openSession();
        Query query = context.createQuery("FROM Product");
        List<Product> products = query.list();
        for (Product product : products) {
            System.out.println("{ id = " + product.getId() + ", " +
                    "name = " + product.getName() + ", " +
                    "category = " + product.getCategory().getName() + " }");
        }
    }
    private static void deleteProduct() {
        System.out.println("Введіть id: ");
        int id = Integer.parseInt(in.nextLine());
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);

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
    }
    private static void updateProduct() {
        System.out.println("Введіть id: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.println("Введіть нове ім'я: ");
        String name = in.nextLine();
        System.out.println("Введіть нову ціну: ");
        double price = Double.parseDouble(in.nextLine());
        System.out.println("Введіть нову категорію: ");
        int categoryId = Integer.parseInt(in.nextLine());

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;

        try {
            sessionFactory = DbContextZibert.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session open");
            tx = session.beginTransaction();

            Product product = session.get(Product.class, id);
            Category category = session.get(Category.class, categoryId);
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            session.saveOrUpdate(product);

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
    }
}
