package lesson;

import lesson.persist.Buyer;
import lesson.persist.Product;
import lesson.persist.PurchasesList;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static EntityManagerFactory emFactory;
    public static EntityManager em;

    public static void main(String[] args) {
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        em = emFactory.createEntityManager();
//        dataInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true){
            try {
                System.out.println("Please, enter a command");
                input = reader.readLine();
                if (input.startsWith("\\")){
                    switch (input.split(" ",2)[0]){
                        case "\\end":
                            System.out.println("Shutting down");
                            return;
                        case "\\buyer":
                            showClient(Integer.parseInt(input.split(" ",2)[1]));
                            break;
                        case "\\product":
                            showProduct(Integer.parseInt(input.split(" ",2)[1]));
                            break;
                        case "\\delete":
                            if (input.split(" ",3)[1].equals("buyer")){
                                em.getTransaction().begin();
                                em.remove(em.find(Buyer.class,Integer.parseInt(input.split(" ",3)[2])));
                                em.getTransaction().commit();
                            } else if (input.split(" ",3)[1].equals("product")){
                                em.getTransaction().begin();
                                em.remove(em.find(Product.class,Integer.parseInt(input.split(" ",3)[2])));
                                em.getTransaction().commit();
                            } else {
                                System.out.println("There is error in command/");
                            }
                            break;
                        case "\\help":
                            System.out.println(
                                    "\\end - exit" + "\n" +
                                    "\\buyer # - show what bought buyer with id #" + "\n" +
                                    "\\product # - show who bought product with id #" + "\n" +
                                    "\\delete buyer|product # - delete buyer|produc with id #");
                    }
                } else {
                    System.out.println("Unknown command. Enter \"help\" to see list of commands.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void showClient(int id){
        Buyer buyer = em.find(Buyer.class,id);
        System.out.println(buyer);
        for (Product p: buyer.getProducts()) {
            System.out.println(p);
        }
    }
    public static void showProduct(int id){
        Product product = em.find(Product.class,id);
        System.out.println(product);
        for (Buyer b: product.getBuyers()) {
            System.out.println(b);
        }

    }

    public static void dataInput(){
        em.getTransaction().begin();
        em.persist(new Buyer("Billy","Johnson"));
        em.persist(new Buyer("Alex", "Washington"));
        em.persist(new Buyer("Sindy", "Smith"));
        em.persist(new Buyer("Tom", "Sawyer"));
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(new Product("Table",200));
        em.persist(new Product("Chair",100));
        em.persist(new Product("Lamp",80));
        em.persist(new Product("TV",400));
        em.persist(new Product("Plate",10));
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(new PurchasesList(1,1));
        em.persist(new PurchasesList(1,3));
        em.persist(new PurchasesList(1,5));
        em.persist(new PurchasesList(2,2));
        em.persist(new PurchasesList(3,3));
        em.persist(new PurchasesList(4,1));
        em.persist(new PurchasesList(4,4));
        em.getTransaction().commit();
    }

}
