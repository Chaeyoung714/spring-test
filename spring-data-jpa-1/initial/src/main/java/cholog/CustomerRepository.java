package cholog;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameIgnoreCase(String lastName);

    List<Customer> findByLastNameOrderByFirstNameDesc(String lastName);
}
