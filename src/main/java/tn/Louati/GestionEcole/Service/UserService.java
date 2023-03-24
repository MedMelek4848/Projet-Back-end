package tn.Louati.GestionEcole.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Louati.GestionEcole.Repository.RoleRepository;
import tn.Louati.GestionEcole.Repository.UserRepository;
import tn.Louati.GestionEcole.model.Role;
import tn.Louati.GestionEcole.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private RoleRepository roleRepository;
    
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long iduser) {
        return userRepository.findById(iduser).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(roleId).orElseThrow();
        user.addRole(role);
        userRepository.save(user);
    }
    
 }

