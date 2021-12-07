package fr.univlorraine.miage.revolutmiage.utils.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface DefautRepository<C> extends JpaRepository<C, UUID> {
}
