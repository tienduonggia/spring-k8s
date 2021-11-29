package com.giatien.springk8s.controller;

import com.giatien.springk8s.common.exception.ResourceNotFoundException;
import com.giatien.springk8s.model.Fruit;
import com.giatien.springk8s.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FruitController {
    @Autowired
    private FruitRepository fruitRepository;

    @PostMapping("/fruits")
    public Fruit addFruit(@RequestBody Fruit fruit) {
        return fruitRepository.save(fruit);
    }


    @GetMapping("/fruits")
    public ResponseEntity<List<Fruit>> getAllFruit() {
        return ResponseEntity.ok(fruitRepository.findAll());
    }

    @GetMapping("fruits/{id}")
    public ResponseEntity<Fruit> findFruitById(@PathVariable(value = "id") Integer fruitId) {
        Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found" + fruitId));
        return ResponseEntity.ok().body(fruit);
    }

    @PutMapping("fruits/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable(value = "id") Integer fruitId,
                                                   @RequestBody Fruit fruitDetail) {
        Fruit fruit = fruitRepository.findById(fruitId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + fruitId));
        fruit.setName(fruitDetail.getName());
        final Fruit updateFruit = fruitRepository.save(fruit);
        return ResponseEntity.ok(updateFruit);

    }

    @DeleteMapping("fruits/{id}")
    public ResponseEntity<Void> deletFruit(@PathVariable(value = "id") Integer fruitId) {
        Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found" + fruitId));
        fruitRepository.delete(fruit);
        return ResponseEntity.ok().build();
    }
}
