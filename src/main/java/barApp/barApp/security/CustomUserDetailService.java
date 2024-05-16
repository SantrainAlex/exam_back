package barApp.barApp.security;

import barApp.barApp.models.Role;
import barApp.barApp.repositories.BarmakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private BarmakerRepository barmakerRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var barmaker = this.barmakerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email not found"));
        return new User(barmaker.getEmail(), barmaker.getPassword(), mapRolesToAuthorities(barmaker.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
