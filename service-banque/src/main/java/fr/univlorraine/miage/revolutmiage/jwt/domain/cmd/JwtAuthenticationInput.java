package fr.univlorraine.miage.revolutmiage.jwt.domain.cmd;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class JwtAuthenticationInput {
    private String username;
    private String password;
}
