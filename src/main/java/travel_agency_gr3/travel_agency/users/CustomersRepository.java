package travel_agency_gr3.travel_agency.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customer, Long> {
    boolean existsByUsername(String username);
}
