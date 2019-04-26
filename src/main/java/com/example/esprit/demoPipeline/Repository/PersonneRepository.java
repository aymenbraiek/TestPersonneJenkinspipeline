package com.example.esprit.demoPipeline.Repository;

import com.example.esprit.demoPipeline.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne,Long> {
}
