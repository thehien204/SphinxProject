package com.sphinx.project.config.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sphinx.project.model.User;
import com.sphinx.project.repo.UserRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserRepo userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		User users = new User();
		if (user == null) {
			return null;
		}
		else {
				users.setUsername(user.getUsername());
//				users.setAuthorityNames(user.getAuthorityNames());
				users.setPassword(encoder.encode(user.getPassword()));
		}
		return users;
	}	
}
