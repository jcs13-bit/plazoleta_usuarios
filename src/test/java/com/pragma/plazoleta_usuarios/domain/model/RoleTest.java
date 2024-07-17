package com.pragma.plazoleta_usuarios.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Mock
    private Role role;


    @BeforeEach
    public void setUp(){
        role = new Role(1L, "Test", "test role");
    }


    @Test
    void testGetters() {
        assertEquals(1L, role.getId());
        assertEquals("Test", role.getName());
        assertEquals("test role", role.getDescription());
    }

    @Test
    void testSetters() {
        role.setName("Admin");
        role.setDescription("Admin role");

        assertEquals("Admin", role.getName());
        assertEquals("Admin role", role.getDescription());

    }

}