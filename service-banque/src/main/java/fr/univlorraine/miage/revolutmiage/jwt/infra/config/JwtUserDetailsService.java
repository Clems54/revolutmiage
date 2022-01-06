package fr.univlorraine.miage.revolutmiage.jwt.infra.config;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UtilisateurCatalog utilisateurCatalog;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<Utilisateur> optionalUtilisateur = utilisateurCatalog.findByNumeroPasseport(username);

        if (optionalUtilisateur.isPresent()) {
            final Utilisateur currentUtilisateur = optionalUtilisateur.get();
            return new User(username, currentUtilisateur.getSecret(),
                    new ArrayList<>() {{
                        add(new SimpleGrantedAuthority("ROLE_USER"));
                    }});
        } else {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le passeport : " + username);
        }
    }
}
