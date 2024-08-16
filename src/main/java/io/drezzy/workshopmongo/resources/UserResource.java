package io.drezzy.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.drezzy.workshopmongo.domain.User;


@RestController
@RequestMapping(value = "/users")
public class UserResource {

    //@GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll (){
        User maria = new User("1", "Maria", "maria@example.com");
        User daniel = new User("2", "Daniel", "daniel@example.com");
        List<User> users = new ArrayList<User>();
        users.addAll(Arrays.asList(maria, daniel));
        return ResponseEntity.ok(users);
    }
}
