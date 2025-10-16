package dev.audrey.apinaruto.repository;

import dev.audrey.apinaruto.model.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NinjaRepository extends JpaRepository<Ninja, Long> {

}
