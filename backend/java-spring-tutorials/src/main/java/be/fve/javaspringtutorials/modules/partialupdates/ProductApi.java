package be.fve.javaspringtutorials.modules.partialupdates;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    private final ProductService service;

    public ProductApi(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/addproduct")
    public Product addProduct() {
        return service.save();
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/product/{id}")
    public ProductDTO update(@PathVariable(name = "id") Long productId, @RequestBody ProductDTO update) {
        return service.update(productId, update);
    }
}