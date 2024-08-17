package io.drezzy.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserDTO;
import io.drezzy.workshopmongo.domain.User;
import io.drezzy.workshopmongo.repository.UserRepository;
import io.drezzy.workshopmongo.services.exception.ObjectNotFoundException;

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

    public User findById(String id){
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public User insert(User user){
        return repo.insert(user);
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }

    public User update(User updatedUser){
        Optional<User> currentUser = repo.findById(updatedUser.getId());
        updateData(currentUser.get(), updatedUser);
        return repo.save(currentUser.get());

    }
    

    private void updateData(User currentUser, User updatedUser) {
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail());
        
        }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
