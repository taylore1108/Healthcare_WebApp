package com.team11.hhs;
import com.team11.hhs.DTO.UserDTO;
import com.team11.hhs.model.User;
import com.team11.hhs.repository.UserRepo;
import com.team11.hhs.service.UserService;
import com.team11.hhs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;
import com.team11.hhs.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTests {

    @Test
    public void testThatValidIdProducesA200(){

        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);

        User user = new User();
        user.setUsername("therealJaneDoe1");
        user.setPassword("password123");
        user.setFirstname("Jane");
        user.setLastname("Doe");

        Mockito
                .when(mockRepository.findById(1L))
                .thenReturn(Optional.of(user));
        UserServiceImpl userController = new UserServiceImpl(mockRepository);

        // CALL
        ResponseEntity<User> responseEntity = userController.readByID(1L);

        // ASSERTIONS
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Jane", responseEntity.getBody().getFirstname());
    }

    @Test
    public void testAddUser() {
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        UserDTO user = new UserDTO();
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        userService.saveUser(user);
        ArgumentCaptor<UserDTO> userCaptor = ArgumentCaptor.forClass(UserDTO.class);
        Mockito.verify(userService).saveUser(userCaptor.capture());
        assert(userCaptor.getValue().getUsername().equals("test"));
    }

    @Test
    public void invalidIdProduces404() {
        UserRepo mockRepository = Mockito.mock(UserRepo.class);
        UserServiceImpl userService = new UserServiceImpl(mockRepository);
        Mockito.when(mockRepository.findById(100000L)).thenReturn(Optional.empty());
        ResponseEntity<User> responseEntity = userService.readByID(100000L);
        assertEquals(404, responseEntity.getStatusCodeValue());
        assertNull( responseEntity.getBody());
    }

//    @Test
//    public void  validIdProduces200() {
//        UserRepo mockRepository = Mockito.mock(UserRepo.class);
//        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(new User()));
//        ResponseEntity<User> responseEntity = new UserServiceImpl(mockRepository).readByID(1L);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals("test", responseEntity.getBody().getFirstname());
//    }

    @Test
    public void testGetAllUsers() {
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        userService.findAllUsers();
        Mockito.verify(userService).findAllUsers();
    }




    @Test
    public void testValidLogin() {
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstname("test");
        user.setLastname("test");
        Mockito.when(userService.findByUsername("test")).thenReturn(user);
        assertEquals(userService.findByUsername("test"), user);
    }


//    @Test
//    public void testChangePassword() {
//        UserRepo mockRepository = Mockito.mock(UserRepo.class);
//        UserServiceImpl userService = new UserServiceImpl(mockRepository);
//
//        User savedUser = new User();
//        savedUser.setUsername("therealJohnDoe1");
//        savedUser.setPassword("password123");
//        savedUser.setFirstname("John");
//        savedUser.setLastname("Doe");
//
//
//        User user = new User();
//        user.setUsername("therealJohnDoe1");
//        user.setPassword("test2");
//        user.setFirstname("John");
//        user.setLastname("Doe");
//
//        user = userService.updateUser(user.getId(), savedUser);
//
//        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
//        Mockito.verify(userService).updatePassword(userCaptor.capture().getId(), "test");
////        assert(userCaptor.getValue().getUsername().equals("test"));
//
//        assertEquals("[therealJohnDoe1, test2, John, Doe]", user.printUser());
//    }
    @Test
    public void testToDeleteUser(){
        // SETUP
        UserRepo mockRepository = Mockito.mock(UserRepo.class);

        UserServiceImpl userController = new UserServiceImpl(mockRepository);

        // CALL
        userController.deleteUser(1L);

        // ASSERTIONS
        Mockito.verify(mockRepository).deleteById(1L);
    }
}
