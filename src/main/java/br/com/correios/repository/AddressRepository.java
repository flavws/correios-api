package br.com.correios.repository;

import br.com.correios.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
