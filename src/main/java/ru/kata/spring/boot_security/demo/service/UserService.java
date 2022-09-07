package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User findByUsername (String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден",username));
        }
        user.getAuthorities().size();
        return user;
    }


    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long id){
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    private static Collection<? extends GrantedAuthority> getAuthorities(User user){
//        String[] userRoles=user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorityCollections = AuthorityUtils.createAuthorityList(userRoles);
//        return authorityCollections;
//    }

}
