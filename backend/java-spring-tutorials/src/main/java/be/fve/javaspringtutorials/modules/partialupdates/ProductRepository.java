package be.fve.javaspringtutorials.modules.partialupdates;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}