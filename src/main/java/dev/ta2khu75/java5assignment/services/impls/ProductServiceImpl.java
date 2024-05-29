package dev.ta2khu75.java5assignment.services.impls;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.ta2khu75.java5assignment.enviroments.ContainerEnviroment;
import dev.ta2khu75.java5assignment.exceptions.NotFoundException;
import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.repositories.ProductRepository;
import dev.ta2khu75.java5assignment.services.AzureStorageService;
import dev.ta2khu75.java5assignment.services.ProductService;
import dev.ta2khu75.java5assignment.services.RedisService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository repository;
    AzureStorageService azureStorageService;
    RedisService redisService;

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Product getProductById(Long id) throws JsonProcessingException, ClassNotFoundException {
        Product productt=redisService.get("product"+id, Product.class);
        if(productt!=null){
            return productt;
        }
        String key="product"+id;
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        redisService.save(key, product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, MultipartFile image) throws IOException {
        Product productExisting = repository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        if (image != null && !image.isEmpty()) {
            String imageUrl = azureStorageService.writeBlobFile(ContainerEnviroment.PRODUCT, image);
            product.setImageUrl(String.format("https://java5assignment.blob.core.windows.net/%s/%s",
                    ContainerEnviroment.PRODUCT, imageUrl));
        }
        productExisting.setName(product.getName());
        productExisting.setActive(product.isActive());
        productExisting.setPrice(product.getPrice());
        productExisting.setDescription(product.getDescription());
        productExisting.setNumberOfSales(product.getNumberOfSales());
        productExisting.setQuantity(product.getQuantity());
        productExisting.setCategory(product.getCategory());
        return repository.save(productExisting);
    }

    @Override
    public Product createProduct(Product product, MultipartFile image) throws IOException {
        String imageUrl = azureStorageService.writeBlobFile(ContainerEnviroment.PRODUCT, image);
        product.setImageUrl(String.format("https://java5assignment.blob.core.windows.net/%s/%s",
                ContainerEnviroment.PRODUCT, imageUrl));
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() throws JsonMappingException, JsonProcessingException, ClassNotFoundException {
        List<Product> products = (List<Product>) redisService.getList("all_product", Product.class);
        if(products!=null){
            return products;
        }
        List<Product> productList = repository.findAll();
        redisService.saveList("all_product", productList);
        return productList;
    }

    @Override
    public List<Product> getProductNameByKeyword(String keyword) {
        return repository.findByNameContainingAndActiveTrue(keyword);
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> getProductByKeywordAndCategory(String keyword) {
        return repository.findByNameContainingAndCategoryContainingAndActiveTrue(keyword, keyword);
    }

    @Override
    public List<Product> getAllProductsActiveTrue() throws JsonProcessingException {
        List<Product> products = (List<Product>) redisService.getList("all_product_active", Product.class);
        if(products!=null){
            return products;
        }
        List<Product> productList = repository.findByActiveTrue();
        redisService.saveList("all_product_active", productList);
        return productList;
    }
}
