package br.com.correios.repository;

import br.com.correios.model.AddressStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressStatusRepository extends JpaRepository<AddressStatus, Integer> {
}
