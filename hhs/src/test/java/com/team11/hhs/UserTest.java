package com.team11.hhs;

import com.team11.hhs.controller.UserController;
import com.team11.hhs.model.User;
import com.team11.hhs.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class UserTest {
    @Test
    // make test for @GetMapping("/todo/{id}")
    public void testThatInvalidIdProducesA404(){
        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);

        Mockito.when(mockRepository.findById(100000L)).thenReturn(Optional.empty());
        UserController quoteController = new UserController(mockRepository);

        // CALL
        ResponseEntity<String> responseEntity = quoteController.readFirstName(100000L);

        // ASSERTIONS
        assertEquals(404, responseEntity.getStatusCodeValue());
        assertNull( responseEntity.getBody() );
    }

    @Test
    public void testThatValidIdProducesA200(){

        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);

        User idQuote = new User();
        idQuote.setFirstname("Quote phrase here");

        Mockito
                .when(mockRepository.findById(1L))
                .thenReturn(Optional.of(idQuote));
        UserController quoteController = new UserController(mockRepository);

        // CALL
        ResponseEntity<String> responseEntity = quoteController.readFirstName(1L);

        // ASSERTIONS
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Quote phrase here", responseEntity.getBody() );
    }

    @Test
    public void testToDeleteUser(){
        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);
        UserController quoteController = new UserController(mockRepository);

        // CALL
        quoteController.deleteUser(1L);

        // ASSERTIONS
        Mockito.verify(mockRepository).deleteById(1L);
    }

    @Test
    public void testToAddUser(){
        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);
        UserController quoteController = new UserController(mockRepository);

        User savedUser = new User();
        savedUser.setUsername("therealJohnDoe1");
        savedUser.setPassword("password123");
        savedUser.setFirstname("John");
        savedUser.setLastname("Doe");
        savedUser.setRole("P");

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.when(mockRepository.save(userArgumentCaptor.capture())).thenReturn(savedUser);
        // CALL
        User user = quoteController.addUser("therealJohnDoe1","password123","John","Doe","P");

        // ASSERTIONS
        assertEquals("[therealJohnDoe1, password123, John, Doe, P]", user.printUser());
    }

    @Test
    public void getUsersWithoutSearchParameterTest(){
        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);
        UserController quoteController = new UserController(mockRepository);

        List<User> allQuotes = List.of(new User());
        Mockito.when(mockRepository.findAll()).thenReturn(allQuotes);

        // CALL
        List<User> quotes = quoteController.getUsers(Optional.empty());

        // ASSERTIONS
        assertEquals(allQuotes, quotes);      // Optional.of("word") --> fail bc get another quotes list
        Mockito.verify(mockRepository).findAll();
        Mockito.verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void getContainingUserTest(){
        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);
        UserController quoteController = new UserController(mockRepository);

        List<User> containingQuotes = Collections.emptyList();
        Mockito.when(mockRepository.getContainingFirstName("blah")).thenReturn(containingQuotes);

        // CALL
        List<User> quotes = quoteController.getUsers(Optional.of("blah"));

        // ASSERTIONS
        assertEquals(containingQuotes, quotes);
        Mockito.verify(mockRepository).getContainingFirstName("blah");
    }
    // did not need to put in phrases, only check if "getContainingQuote" function was called
    // and that the list it returned was the same list that "getQuotes" returned
}
