package ru.job4j.dream.dataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.job4j.dream.model.Candidate;

import java.util.List;


public class CandidateDAO implements DAO<Candidate> {
    private static CandidateDAO instance;

    public static synchronized CandidateDAO getInstance() {
        if (instance == null) {
            instance = new CandidateDAO();
        }
        return instance;
    }
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
            .configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
    private final SessionFactory factory=new Configuration().configure().buildSessionFactory();

    private CandidateDAO() {
    }
    @Override
    public void create(  Candidate candidate) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(candidate);

            session.getTransaction().commit();
        }
    }

    @Override
    public Candidate read(final int id) {
        try (final Session session = factory.openSession()) {

            final Candidate result = session.get(Candidate.class, id);

            return result != null ? result :null;
        }
    }

    @Override
    public void update( final Candidate candidate) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(candidate);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete( final Candidate candidate) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(candidate);

            session.getTransaction().commit();
        }
    }
    @Override
    public List<Candidate> findAll( ) {
        List<Candidate>candidates;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            String sql = "select id, name from candidate";
            Query query = session.createSQLQuery(sql).addEntity(Candidate.class);
            candidates = query.list();
            session.getTransaction().commit();
        }
        return candidates;
    }

    public static void main(String[] args) {

    }
}
