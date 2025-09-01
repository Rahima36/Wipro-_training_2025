package com.wipro.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.wipro.product_service.model.Product;
import com.wipro.product_service.repository.ProductRepository;

@Service
public class ProductService {
  private final ProductRepository repo;
  public ProductService(ProductRepository repo){ this.repo=repo; }

  public Product save(Product p){ return repo.save(p); }
  public List<Product> all(){ return repo.findAll(); }
  public Optional<Product> get(Long id){ return repo.findById(id); }
  public void delete(Long id){ repo.deleteById(id); }
}
