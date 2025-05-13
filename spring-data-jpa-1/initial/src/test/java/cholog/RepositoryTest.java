package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class RepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void save() {
        customerRepository.save(new Customer("Jack", "Bauer"));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack");
    }

    @Test
    void findAll() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack", "Chloe");
    }

    @Test
    void findById() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        Customer customer = customerRepository.findById(5L).orElseThrow(IllegalArgumentException::new);
//        assertThatThrownBy(() -> customerRepository.findById(1L).orElseThrow(IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
//        assertThatThrownBy(() -> customerRepository.findById(2L).orElseThrow(IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
//        assertThatThrownBy(() -> customerRepository.findById(3L).orElseThrow(IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
//        assertThatThrownBy(() -> customerRepository.findById(4L).orElseThrow(IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThat(customer.getFirstName()).isEqualTo("Jack");
    }

    @Test
    void count() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        long count = customerRepository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void delete() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        customerRepository.deleteById(1L);
        assertThat(customerRepository.count()).isEqualTo(1);
    }
}
