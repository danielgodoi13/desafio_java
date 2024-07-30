package com.daniel.repository;

import com.daniel.model.ViewPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewPrincipalRepository extends JpaRepository<ViewPrincipal, Long> {
}
