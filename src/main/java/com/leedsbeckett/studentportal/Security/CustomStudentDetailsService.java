package com.leedsbeckett.studentportal.Service;

import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Repository.StudentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomStudentDetailsService implements UserDetailsService {
    private StudentRepository studentRepository;

    public CustomStudentDetailsService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);

        if (student != null) {

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));

            return new org.springframework.security.core.userdetails.User(
                    student.getEmail(),
                    student.getPassword(),authorities
            );
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
