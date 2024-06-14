package com.book.library.tests;


import com.book.library.parton.Parton;

import com.book.library.parton.PartonController;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PartonTest {
    @Autowired
    PartonController partonController;

    // creating , getting and deleting by id
    @Test
    public void testPartonCreation() {
        Parton parton = new Parton();
        parton.setName("feras");
        parton.setContactInfo("2000");
        ResponseEntity<Parton> createdParton= partonController.save(parton);
        assertEquals(HttpStatus.CREATED,  createdParton.getStatusCode());
        ResponseEntity<Parton> getPartonById = partonController.getById(createdParton.getBody().getId());
        assertEquals(getPartonById.getBody().getId(),createdParton.getBody().getId());
        ResponseEntity<Void> deleteParton = partonController.delete(getPartonById.getBody().getId());
        assertThrows(ResponseStatusException.class, () -> {
            partonController.getById(createdParton.getBody().getId());
        });
    }

    // creating with blank name
    @Test
    public void testPartonCreationWithBlankName() {
        Parton parton = new Parton();
        parton.setName("");
        parton.setContactInfo("2000");
        assertThrows(ConstraintViolationException.class, () -> {
            partonController.save(parton);
        });
    }


    // creating with null name
    @Test
    public void testPartonCreationWithNullName() {
        Parton parton = new Parton();
        parton.setName(null);
        parton.setContactInfo("2000");
        assertThrows(ConstraintViolationException.class, () -> {
            partonController.save(parton);
        });
    }

// test parton udpate
    @Test
    public void testPartonUpdate() {
        Parton parton = new Parton();
        parton.setName("feras1000");
        parton.setContactInfo("2000");
        ResponseEntity<Parton> updatedParton= partonController.update(2L,parton);
        assertEquals(HttpStatus.OK,  updatedParton.getStatusCode());
        ResponseEntity<Parton> getPartonById = partonController.getById(updatedParton.getBody().getId());
        assertEquals(getPartonById.getBody().getName(),updatedParton.getBody().getName());
    }
}
