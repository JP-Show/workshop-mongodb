package io.drezzy.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.drezzy.workshopmongo.domain.Post;
import io.drezzy.workshopmongo.repository.PostRepository;
import io.drezzy.workshopmongo.services.exception.ObjectNotFoundException;

//O serviço acessa o reposítorio
@Service
public class PostService {
    
    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public List<Post> findByTitle (String text){
        return repo.findByTitleContainingIgnoreCase(text);
    } 
}
