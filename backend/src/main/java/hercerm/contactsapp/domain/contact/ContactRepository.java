package hercerm.contactsapp.domain.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c ORDER BY c.firstName, c.lastName")
    Page<Contact> findAllOrdered(Pageable pageable);

    Optional<Contact> findContactByEmail(String email);
    Optional<Contact> findContactByPhoneNumber(String phoneNumber);

    // Learn more about paginating database results:
    // https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-seven-pagination/
    @Query("SELECT c FROM Contact c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "ORDER BY c.firstName, c.lastName")
    Page<Contact> findPaginatedBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);
}
