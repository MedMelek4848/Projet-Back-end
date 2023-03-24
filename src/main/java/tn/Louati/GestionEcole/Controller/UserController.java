package tn.Louati.GestionEcole.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.RoleService;
import tn.Louati.GestionEcole.Service.UserService;
import tn.Louati.GestionEcole.model.Role;
import tn.Louati.GestionEcole.model.User;

@RestController

@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{iduser}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "iduser") Long iduser) {
        User user = userService.getUserById(iduser);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Validated @RequestBody User user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/{iduser}/roles/{idrole}")
    public ResponseEntity<User> addRoleToUser(@PathVariable Long iduser, @PathVariable Long idrole) {
        User user = userService.getUserById(iduser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Role role = roleService.getRoleById(idrole);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        userService.addRoleToUser(iduser, idrole);
        return ResponseEntity.ok(user);
    }
    // d'autres méthodes pour la mise à jour et la suppression des utilisateurs, etc.
}
