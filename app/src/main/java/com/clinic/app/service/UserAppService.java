package com.clinic.app.service;

import com.clinic.app.model.Employee;
import com.clinic.app.model.UserApp;
import com.clinic.app.model.Email;
import com.clinic.app.repository.UserAppRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAppService implements UserDetailsService {
    private final UserAppRepository repository;
    private final EmailSender emailSender;

    public UserAppService(UserAppRepository repository, EmailSender emailSender){

        this.repository=repository;
        this.emailSender=emailSender;
    }

    public UserApp  findbyEmail(String email) {
        return repository.findByEmail(email);
    }

    public void register(Employee employee){
        repository.save(employee);
        String link = "http://localhost:4200/verify/" + employee.getEmail();
        String content = "<h1>Thanks you for registering!</h1><p>To verify your account, please click on <a href=" + link + ">this link</a>.</p>";
        emailSender.send(new Email(employee.getEmail(), "Verify your registration", content));
    }
    public void verifyAccount(UserApp user){
        user.setVerified(true);
        repository.save(user);
    }

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
