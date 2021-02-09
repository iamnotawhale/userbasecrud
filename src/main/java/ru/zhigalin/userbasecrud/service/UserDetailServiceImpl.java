package ru.zhigalin.userbasecrud.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhigalin.userbasecrud.dao.UserDao;
import ru.zhigalin.userbasecrud.model.Role;
import ru.zhigalin.userbasecrud.model.User;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByName(s);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getLogin()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
