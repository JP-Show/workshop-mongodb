package io.drezzy.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.drezzy.workshopmongo.domain.Post;
import io.drezzy.workshopmongo.resources.utils.URL;
import io.drezzy.workshopmongo.services.PostService;

//O controlador REST acessa o serviço
@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Post> findbyId(@PathVariable String id){
        Post post = service.findById(id);
        return ResponseEntity.ok(post);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
    public ResponseEntity<List<Post>> findbyTitle(@RequestParam(value="text", defaultValue="") String text){
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
        @RequestParam(value="text", defaultValue="") String text,
        @RequestParam(value="minDate", defaultValue="") String minDate,
        @RequestParam(value="maxDate", defaultValue="") String maxDate)
{
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = service.fullSearch(text, min, max );
        return ResponseEntity.ok().body(list);
    }
}
