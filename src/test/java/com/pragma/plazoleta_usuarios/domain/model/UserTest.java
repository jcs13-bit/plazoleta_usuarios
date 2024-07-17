package com.pragma.plazoleta_usuarios.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Mock
    private User user;


    @BeforeEach
    public void setUp(){
        user = new User(1l,"user1","lastName1","1007","+52154263", LocalDate.now(),"email@gmail.com","password1",new Role(1L, "Test", "test role"));
    }


    @Test
    void testGetters() {
        assertEquals(1L, user.getId());
        assertEquals("user1", user.getName());
        assertEquals("lastName1", user.getLastName());
        assertEquals("1007", user.getDocNumber());
        assertEquals("+52154263", user.getCellphone());
        assertEquals(LocalDate.now(), user.getBirthDate());
        assertEquals("email@gmail.com", user.getEmail());
        assertEquals("password1", user.getPassword());
    }

    @Test
    void testSetters() {
        user.setName("user2");
        assertEquals("user2", user.getName());

        user.setLastName("lastName2");
        assertEquals("lastName2", user.getLastName());

        user.setDocNumber("1008");
        assertEquals("1008", user.getDocNumber());

        user.setCellphone("+52154264");
        assertEquals("+52154264", user.getCellphone());

        user.setBirthDate(LocalDate.now());
        assertEquals(LocalDate.now(), user.getBirthDate());

        user.setEmail("email2@gmail.com");
        assertEquals("email2@gmail.com", user.getEmail());

        user.setPassword("password2");
        assertEquals("password2", user.getPassword());

    }

}