package ru.job4j.dream.dataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.job4j.dream.model.Post;
import java.util.List;


public class PostDAO implements DAO<Post> {
    private static PostDAO instance;

    public static synchronized PostDAO getInstance() {
        if (instance == null) {
            instance = new PostDAO();
        }
        return instance;
    }
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
            .configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
    private final SessionFactory factory=new Configuration().configure().buildSessionFactory();

    private PostDAO() {
    }

    @Override
    public void create(  Post post) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(post);

            session.getTransaction().commit();
        }
    }

    @Override
    public Post read(final int id) {
        try (final Session session = factory.openSession()) {

            final Post result = session.get(Post.class, id);

            return result != null ? result :null;
        }
    }

    @Override
    public void update( final Post post) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(post);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete( final Post post) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(post);

            session.getTransaction().commit();
        }
    }

    public List<Post> findAll( ) {
        List<Post>posts;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            String sql = "select id, name from post";
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
             posts = query.list();
            session.getTransaction().commit();
        }
        return posts;
    }

    public static void main(String[] args) {

    }
}
