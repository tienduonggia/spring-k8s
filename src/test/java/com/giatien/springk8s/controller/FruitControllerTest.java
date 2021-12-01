package com.giatien.springk8s.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giatien.springk8s.model.Fruit;
import com.giatien.springk8s.repository.FruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@MockBean(JpaMetamodelMappingContext.class)
class FruitControllerTest {


    @MockBean
    private FruitRepository fruitRepository;

    @Autowired
    private MockMvc mvc;

    private List<Fruit> fruitList;

    private Fruit fruit_1;
    private Fruit fruit_2;

    @BeforeEach
    public void setUp() {
        fruit_1 = Fruit.builder()
                .name("Fruit 1")
                .season("Test").build();
        fruit_2 = Fruit.builder()
                .name("Fruit 2")
                .season("Test").build();
        fruitList = new ArrayList<>();
        fruitList.add(fruit_1);
        fruitList.add(fruit_2);
    }

    @DisplayName("Create new fruits and return status 200")
    @Test
    void addFruit() throws Exception {
        given(fruitRepository.save(any(Fruit.class))).willReturn(fruit_1);
        mvc.perform(post("/api/fruits")
                .content(asJsonString(fruit_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is("Fruit 1")));
    }

    //Convert to JSON
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Find all and return status 200")
    @Test
    void getAllFruit() throws Exception {
        given(fruitRepository.findAll()).willReturn(fruitList);
        mvc.perform(get("/api/fruits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @DisplayName("Find by id and return status 200")
    @Test
    void findFruitById() throws Exception {
        given(fruitRepository.findById(1))
                .willReturn(Optional.of(fruit_1));
        mvc.perform(get("/api/fruits/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));


    }

    @Test
    @DisplayName("Update by id exits and return status 200")
    void updateFruit() throws Exception {
        given(fruitRepository.findById(1)).willReturn(Optional.of(fruit_1));
        given(fruitRepository.save(any(Fruit.class))).willReturn(fruit_2);
        mvc.perform(put("/api/fruits/{id}",1)
                .content(asJsonString(fruit_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is("Fruit 2")));

    }

    @Test
    @DisplayName("Delete by id exits and return status 200")
    void deletFruit() throws Exception {
        given(fruitRepository.findById(1)).willReturn(Optional.of(fruit_1));
        Mockito.doNothing().when(fruitRepository).deleteById(any(Integer.class));
        mvc.perform(delete("/api/fruits/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}