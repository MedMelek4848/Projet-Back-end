package tn.Louati.GestionEcole.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Louati.GestionEcole.Repository.RoleRepository;
import tn.Louati.GestionEcole.model.Role;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long idrole) {
        return roleRepository.findById(idrole).orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // d'autres méthodes pour la mise à jour et la suppression des rôles, etc.
}
