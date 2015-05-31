package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.good.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
