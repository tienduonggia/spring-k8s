package com.giatien.springk8s.repository;


import com.giatien.springk8s.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<Fruit,Integer> {
}
