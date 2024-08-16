package io.drezzy.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.drezzy.workshopmongo.domain.User;
import io.drezzy.workshopmongo.repository.UserRepository;

//O serviço acessa o reposítorio
@Service
public class UserService {
    
    /*o próprio spring vai buscar a depedência,
     a injeção de depedência automática*/
    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }
}
