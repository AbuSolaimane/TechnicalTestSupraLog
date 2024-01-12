package fr.supralog.technicalTest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.supralog.technicalTest.domain.UserEntity;
import fr.supralog.technicalTest.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	public CustomUserDetailsService(final UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(username);
		
		if (userEntity == null)
			throw new UsernameNotFoundException("the usename " + username + "doesn't exist");
		
		return new CustomUserDetails(userEntity);
	}

}
