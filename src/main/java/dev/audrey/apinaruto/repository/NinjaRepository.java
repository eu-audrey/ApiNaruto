package dev.audrey.apinaruto.repository;

import dev.audrey.apinaruto.model.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<Ninja, Long> {

}
