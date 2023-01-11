package be.fve.javaspringtutorials.modules.partialupdates;

import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repo;

    public Product save() {
        ProductDTO update = new ProductDTO(3L, "Updated name", 2, JsonNullable.of("Updated description"), JsonNullable.of("UpdateCompany"));
        Product destination = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");
        Product expected = new Product();
        expected.setName("Updated name");
        expected.setQuantity(2);
        expected.setDescription("Updated description");
        expected.setManufacturer("UpdateCompany");

        return repo.save(expected);
    }

    public ProductService(ProductMapper mapper, ProductRepository repo) {
        this.mapper = mapper;
        this.repo = repo;


    }

    public ProductDTO update(long productId, ProductDTO update) {
        Product product = repo.findById(productId).<RuntimeException>orElseThrow(() -> {
            throw new RuntimeException("Product with id <" + productId + "> not found.");
        });

        mapper.update(update, product);
        repo.save(product);

        return mapper.map(product);
    }

}