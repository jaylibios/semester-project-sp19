package edu.uh.tech.cis3368.semesterproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductComponentRepository extends CrudRepository<ProductComponent, Integer> {
    List<ProductComponent> findByproductByProductId(Product product);
    List<ProductComponent> findBycomponentByComponentId(Product product);
}
