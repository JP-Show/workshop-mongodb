package io.drezzy.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import dto.AuthorDTO;
import io.drezzy.workshopmongo.domain.Post;
import io.drezzy.workshopmongo.domain.User;
import io.drezzy.workshopmongo.repository.PostRepository;
import io.drezzy.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        
        Post post1 = new Post(null, dateFormat.parse("02/10/2019"), "LES'T ROLL TO THE BEACH", "Nobody wants to go to the beach", new AuthorDTO(maria));
        Post post2 = new Post(null, dateFormat.parse("02/10/2019"), "How to make a sandwich", "For a kid, it's easy", new AuthorDTO(alex));

        postRepository.saveAll(Arrays.asList(post1, post2));
    }
    
}
