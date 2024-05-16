package barApp.barApp.controllers;

import barApp.barApp.dto.AuthResponseDTO;
import barApp.barApp.dto.BarmakerDto;
import barApp.barApp.models.Barmaker;
import barApp.barApp.repositories.BarmakerRepository;
import barApp.barApp.services.BarmakerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class BarmakerController {

    @Autowired
    private BarmakerService barmakerService;

    @Autowired
    private BarmakerRepository barmakerRepository;




    @PostMapping("/signUp")
    public ResponseEntity<String> createBarmaker(@RequestBody BarmakerDto barmakerDto) {
        if(barmakerRepository.existsByEmail(barmakerDto.getEmail())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Barmaker created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody BarmakerDto barmaker) {
        return new ResponseEntity<>(barmakerService.login(barmaker), HttpStatus.OK);
    }

}
