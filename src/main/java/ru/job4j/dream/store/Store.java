package ru.job4j.dream.store;

import ru.job4j.dream.dataBase.CandidateDAO;
import ru.job4j.dream.dataBase.PostDAO;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final Store INST = new Store();
    private static AtomicInteger POST_ID = new AtomicInteger(0);
    private static AtomicInteger CAN_ID = new AtomicInteger(0);
    private final PostDAO postDatabase = PostDAO.getInstance();
    private final CandidateDAO candidateDatabase = CandidateDAO.getInstance();

    private Store() {

    }

    public static Store instOf() {
        return INST;
    }

    public void postSave(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        postDatabase.create(post);
    }

    public void postUpdate(Post post) {

        postDatabase.update(post);
    }

    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CAN_ID.incrementAndGet());
        }
        candidateDatabase.create(candidate);
    }

    public List<Post> findAllPosts() {
        return postDatabase.findAll();
    }

    public List<Candidate> findAllCandidates() {
        return candidateDatabase.findAll();
    }

    public Post findById(int id) {
        Post post=null;
        for(Post p:this.findAllPosts()) {
            if(p.getId()==id){
                post=p;
            }
    }
        return post;
    }

    public Candidate findByIdCandidate(int id) {
        Candidate candidate=null;
        for(Candidate p:this.findAllCandidates()) {
            if(p.getId()==id){
                candidate=p;
            }
        }
        return candidate;

    }

}
