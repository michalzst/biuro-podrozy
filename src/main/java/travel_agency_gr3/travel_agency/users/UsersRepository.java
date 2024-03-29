package travel_agency_gr3.travel_agency.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository<T extends User>  extends JpaRepository<T, Long> {

    Optional<T> findByUsername(String userName);

    boolean existsByUsername(String username);
}
