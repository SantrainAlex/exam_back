package barApp.barApp.services;

import barApp.barApp.dto.AuthResponseDTO;
import barApp.barApp.dto.BarmakerDto;
import barApp.barApp.models.Barmaker;
import barApp.barApp.models.Role;
import barApp.barApp.repositories.BarmakerRepository;
import barApp.barApp.repositories.RoleRepository;
import barApp.barApp.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarmakerService {

    private final BarmakerRepository barmakerRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTGenerator jwtGenerator;
    private final RoleRepository roleRepository;

    @Autowired
    public BarmakerService(AuthenticationManager authenticationManager, BarmakerRepository barmakerRepository, JWTGenerator jwtGenerator, RoleRepository roleRepository){
        this.authenticationManager = authenticationManager;
        this.barmakerRepository = barmakerRepository;
        this.jwtGenerator = jwtGenerator;
        this.roleRepository = roleRepository;
    }

    public Boolean createBarmaker(BarmakerDto barmakerDto){;

        PasswordEncoderService encoderService = new PasswordEncoderService();

        var barmaker = Barmaker.builder().email(barmakerDto.getEmail()).password(encoderService.encodePassword(barmakerDto.getPassword())).build();

        if(barmakerRepository.findByEmail(barmakerDto.getEmail()).isEmpty()){
            barmakerRepository.save(barmaker);
            return true;
        }
        return false;
    }


    public AuthResponseDTO login(BarmakerDto barmaker){
        PasswordEncoderService encoderService = new PasswordEncoderService();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        barmaker.getEmail(),
                        encoderService.encodePassword(barmaker.getPassword())));
        System.out.println(authentication.getName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);

    }

}
