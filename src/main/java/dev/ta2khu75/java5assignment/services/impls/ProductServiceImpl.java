package dev.ta2khu75.java5assignment.services.impls;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Product getProductById(Long id) throws JsonProcessingException {
        Product product = redisService.get("product" + id, Product.class);
        if (product != null) {
            return product;
        }
        String key = "product" + id;
        product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        if (product != null)
            redisService.save(key, product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, MultipartFile image) throws IOException {
        repository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        if (image != null && !image.isEmpty()) {
            String imageUrl = azureStorageService.writeBlobFile(ContainerEnviroment.PRODUCT, image);
            product.setImageUrl(String.format("https://java5assignment.blob.core.windows.net/%s/%s",
                    ContainerEnviroment.PRODUCT, imageUrl));
        }
        return repository.save(product);
    }

    @Override
    public Product createProduct(Product product, MultipartFile image) throws IOException {
        String imageUrl = azureStorageService.writeBlobFile(ContainerEnviroment.PRODUCT, image);
        product.setImageUrl(String.format("https://java5assignment.blob.core.windows.net/%s/%s",
                ContainerEnviroment.PRODUCT, imageUrl));
        return repository.save(product);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) throws JsonProcessingException {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        Page<Product> products = null;// (Page<Product>)
                                      // redisService.getPage(String.format("all_product_size%d_page%d",size,page),
                                      // Product.class);
        if (products != null) {
            return products;
        }
        Page<Product> productList = repository.findAll(pageable);
        redisService.savePage(String.format("all_product_size%d_page%d", size, page), productList);
        return productList;
    }

    @Override
    public Page<Product> getProductNameByKeyword(Pageable pageable, String keyword) {
        return repository.findByNameContainingAndActiveTrue(keyword, pageable);
    }

    @Override
    public Page<Product> getProductByCategory(Pageable pageable, Category category) {
        return repository.findByCategory(pageable, category);
    }

    @Override
    public List<Product> getProductByKeywordAndCategory(String keyword) {
        return repository.findByNameContainingAndCategoryContainingAndActiveTrue(keyword, keyword);
    }

    @Override
    public Page<Product> getAllProductsActiveTrue(Pageable pageable) throws JsonProcessingException {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        Page<Product> products = (Page<Product>) redisService
                .getPage(String.format("product_page%d_size%d", page, size), Product.class);
        if (products != null) {
            return products;
        }
        Page<Product> productList = repository.findByActiveTrueOrderByCreateDateDesc(pageable);
        redisService.savePage(String.format("product_%d_%d", page, size), productList);
        return productList;
    }

    @Override
    public Page<Product> getProductByKeywordAndPriceGreater(Pageable pageable, String keyword, Long minPrice) {
        return repository.findByPriceGreaterThanEqualAndNameContainingAndActiveTrueOrderByNumberOfSalesDesc(pageable,
                minPrice, keyword);
    }

    @Override
    public Page<Product> getProductByKeywordAndPriceLess(Pageable pageable, String keyword, Long maxPrice) {
        return repository.findByPriceLessThanEqualAndNameContainingAndActiveTrueOrderByNumberOfSalesDesc(pageable,
                maxPrice, keyword);
    }

    @Override
    public Page<Product> getProductByKeywordAndPriceBetween(Pageable pageable, String keyword, Long minPrice,
            Long maxPrice) {
        return repository.findByPriceBetweenAndNameContainingAndActiveTrueOrderByNumberOfSalesDesc(pageable, minPrice,
                maxPrice, keyword);
    }

    @Override
    public Page<Product> getProductByCategoryAndPriceGreater(Pageable pageable, String keyword, Long minPrice) {
        return repository.findByPriceGreaterThanEqualAndCategoryAndActiveTrueOrderByNumberOfSalesDesc(pageable,
                minPrice,
                Category.valueOf(keyword));
    }

    @Override
    public Page<Product> getProductByCategoryAndPriceLess(Pageable pageable, String keyword, Long maxPrice) {
        return repository.findByPriceLessThanEqualAndCategoryAndActiveTrueOrderByNumberOfSalesDesc(pageable, maxPrice,
                Category.valueOf(keyword));
    }

    @Override
    public Page<Product> getProductByCategoryAndPriceBetween(Pageable pageable, String keyword, Long minPrice,
            Long maxPrice) {
        return repository.findByPriceBetweenAndCategoryAndActiveTrueOrderByNumberOfSalesDesc(pageable, minPrice,
                maxPrice, Category
                        .valueOf(keyword));
    }
}
