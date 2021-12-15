package fr.univlorraine.miage.revolutmiage.jwt.infra.rest;

import fr.univlorraine.miage.revolutmiage.jwt.infra.config.JwtTokenUtil;
import fr.univlorraine.miage.revolutmiage.jwt.infra.config.JwtUserDetailsService;
import fr.univlorraine.miage.revolutmiage.jwt.domain.cmd.JwtAuthenticationInput;
import fr.univlorraine.miage.revolutmiage.jwt.infra.dto.JwtAuthenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("authenticate")
public class JwtAuthenticationResource {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final JwtAuthenticationInput input) throws Exception {

        authenticate(input.getUsername(), input.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(input.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationDTO(token));
    }

    private void authenticate(final String username, final String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (final DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (final BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
