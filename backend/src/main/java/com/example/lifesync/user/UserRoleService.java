package com.example.lifesync.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserRole> findAll() {
        return authorityRepository.findAll();
    }

    public UserRole findByAuthority(String name) {
        return authorityRepository.findByRole(name);
    }

    public UserRole save(UserRole authority) {
        return authorityRepository.save(authority);
    }

}
