package com.kastourik12.urlshortener.repositories;

import com.kastourik12.urlshortener.models.ERole;
import com.kastourik12.urlshortener.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole valueOf);
}
