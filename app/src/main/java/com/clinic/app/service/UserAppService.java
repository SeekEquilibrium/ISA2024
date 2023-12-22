package com.clinic.app.service;

import com.clinic.app.model.Employee;
import com.clinic.app.model.UserApp;
import com.clinic.app.repository.UserAppRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAppService implements UserDetailsService {
    private final UserAppRepository repository;

    public UserAppService(UserAppRepository repository){
        this.repository=repository;
    }

    public UserApp  findbyEmail(String email) {
        return repository.findByEmail(email);
    }

    public void register(Employee employee){
        repository.save(employee);
  ;  }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = repository.findByEmail(username);
        if(user == null){
            throw  new RuntimeException("User not found");
        } else {
            return user;
        }
    }
}
