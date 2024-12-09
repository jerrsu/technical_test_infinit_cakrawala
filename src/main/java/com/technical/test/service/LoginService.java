package com.technical.test.service;

import com.technical.test.model.Account;
import com.technical.test.model.Role;
import com.technical.test.repository.AccountRepository;
import com.technical.test.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jerry
 */

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Role role = roleRepository.findById(account.getRoleId()).get();
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
                AuthorityUtils.createAuthorityList(role.getName()));
    }
}
