package dev.ta2khu75.java5assignment.services.impls;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.observability.RedisObservation;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    RedisTemplate redisTemplate;
    ObjectMapper objectMapper;
    RedisService redisService;

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Product getProductById(Long id) throws JsonProcessingException, ClassNotFoundException {
        Product productt=redisService.get("product"+id, Product.class);
        System.out.println(productt+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String objectJson = (String) redisTemplate.opsForValue().get("product"+id);
        if (objectJson != null) {
            return objectMapper.readValue(objectJson, Product.class);
        }
        String key="product"+id;
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        objectJson=objectMapper.writeValueAsString(product);
        redisTemplate.opsForValue().set(key, objectJson);
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
        productExisting.setDescription(product.getDescription());
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
        System.out.println(products);
        String jsonObject=(String) redisTemplate.opsForValue().get("all_product");
        if (jsonObject!= null){
            List<Product> productList = objectMapper.readValue(jsonObject, new TypeReference<List<Product>>() {});
            return productList;
        }
        List<Product> productList = repository.findAll();
        jsonObject=objectMapper.writeValueAsString(productList);
        redisTemplate.opsForValue().set("all_product", jsonObject);
        return productList;
    }

    @Override
    public List<Product> getProductNameByKeyword(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> getProductByKeywordAndCategory(String keyword) {
        return repository.findByNameContainingAndCategoryContaining(keyword, keyword);
    }
}
