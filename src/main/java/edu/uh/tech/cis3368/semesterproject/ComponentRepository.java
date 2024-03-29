package edu.uh.tech.cis3368.semesterproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends CrudRepository<Component, Integer> {
    List<Component> findWholesalePriceById(int id);
}
