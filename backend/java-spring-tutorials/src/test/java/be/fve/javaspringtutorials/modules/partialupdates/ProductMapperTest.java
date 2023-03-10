package be.fve.javaspringtutorials.modules.partialupdates;

import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper mapper;

    @Test
    void should_update_all_entities_in_product_except_id() {
        ProductDTO update = new ProductDTO(3L, "Updated name", 2, JsonNullable.of("Updated description"), JsonNullable.of("UpdateCompany"));
        Product destination = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");
        Product expected = new Product(1L, "Updated name", 2, "Updated description", "UpdateCompany");

        mapper.update(update, destination);

        assertEquals(expected.getId(), destination.getId());
        assertEquals(expected.getDescription(), destination.getDescription());
        assertEquals(expected.getManufacturer(), destination.getManufacturer());
        assertEquals(expected.getName(), destination.getName());
        assertEquals(expected.getQuantity(), destination.getQuantity());
    }

    @Test
    void should_update_only_nullable_fields_in_product() {
        ProductDTO update = new ProductDTO(1L, null, null, JsonNullable.of(null), JsonNullable.of(null));
        Product destination = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");
        Product expected = new Product(1L, "RTX3080", 0, null, null);

        mapper.update(update, destination);

        assertEquals(expected.getId(), destination.getId());
        assertEquals(expected.getDescription(), destination.getDescription());
        assertEquals(expected.getManufacturer(), destination.getManufacturer());
        assertEquals(expected.getName(), destination.getName());
        assertEquals(expected.getQuantity(), destination.getQuantity());
    }

    @Test
    void should_not_update_any_field_in_product_2() {
        ProductDTO update = new ProductDTO(null, null, null, null, null);
        Product destination = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");
        Product expected = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");


        mapper.update(update, destination);

        assertEquals(expected.getId(), destination.getId());
        assertEquals(expected.getDescription(), destination.getDescription());
        assertEquals(expected.getManufacturer(), destination.getManufacturer());
        assertEquals(expected.getName(), destination.getName());
        assertEquals(expected.getQuantity(), destination.getQuantity());
    }

    @Test
    void should_not_update_any_field_in_product() {
        ProductDTO update = new ProductDTO(null, null, null, JsonNullable.undefined(), JsonNullable.undefined());
        Product destination = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");
        Product expected = new Product(1L, "RTX3080", 0, "Great GPU", "NVIDIA");

        mapper.update(update, destination);

        assertEquals(expected.getId(), destination.getId());
        assertEquals(expected.getDescription(), destination.getDescription());
        assertEquals(expected.getManufacturer(), destination.getManufacturer());
        assertEquals(expected.getName(), destination.getName());
        assertEquals(expected.getQuantity(), destination.getQuantity());
    }

}