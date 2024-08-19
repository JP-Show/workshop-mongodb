package io.drezzy.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import dto.AuthorDTO;
import dto.CommentDTO;
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

        CommentDTO c1 = new CommentDTO("Nice trip dude", dateFormat.parse("12/04/2019"), new AuthorDTO(alex) );
        CommentDTO c2 = new CommentDTO( "HAVING FUN!", dateFormat.parse("22/05/2019"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Have a great day", dateFormat.parse("22/06/2019"), new AuthorDTO(alex));
        
        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));
        
        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
    
}
