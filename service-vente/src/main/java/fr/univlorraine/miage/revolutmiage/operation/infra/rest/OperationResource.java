package fr.univlorraine.miage.revolutmiage.operation.infra.rest;

import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.valideroperation.ValiderOperationInput;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payer")
public class OperationResource extends DefaultResource {

    private final Validator validator;

    @Value("${app.url.servicebanque}")
    private String banqueServiceOperationUrl;
    @Value("${app.properties.categorie}")
    private String categorie;
    @Value("${app.properties.libelle}")
    private String libelle;
    @Value("${app.properties.pays}")
    private String pays;
    @Value("${app.properties.ibanCommerce}")
    private String ibanCommerce;

    @PostMapping
    public ResponseEntity<?> validerPaiement(@RequestBody final ValiderOperationInput input) {
        input.setCategorie(categorie)
                .setLibelle(libelle)
                .setPays(pays)
                .setIbanCommerce(ibanCommerce);

        final Set<ConstraintViolation<ValiderOperationInput>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("payer", violations);
        }

        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<ValiderOperationInput> request = new HttpEntity<>(input);

        try {
            return restTemplate.exchange(banqueServiceOperationUrl, HttpMethod.POST, request, ValiderOperationInput.class);
        } catch (final HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }
}
