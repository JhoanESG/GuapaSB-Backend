package com.example.salondebelleza_database;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.salondebelleza_database.entity.Tipo_usuario;
import com.example.salondebelleza_database.entity.Usuario;
import com.example.salondebelleza_database.repository.UsuarioRepository;
import com.example.salondebelleza_database.service.UsuarioService;
import com.example.salondebelleza_database.service.excepciones.AttributeException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testCrearUsuario() {
        // Mock de datos
        Usuario usuario = new Usuario("123", "contraseña", "email@example.com", Tipo_usuario.ADM);

        // Configuración del comportamiento del repositorio mock
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Llamada al servicio
        Usuario result = null;
        try {
            result = usuarioService.crearUsuario(usuario);
        } catch (AttributeException e) {
            throw new RuntimeException(e);
        }

        // Verificación
        assertNotNull(result);
        assertEquals("123", result.getId_usuario());
        assertEquals("contraseña", result.getContrasena());
        assertEquals("email@example.com", result.getEmail());
        assertEquals(Tipo_usuario.ADM, result.getTipo_usuario());
    }

    // Aquí seguirían más métodos de prueba para los otros métodos del servicio
    // actualizarUsuario, obtenerUsuario, eliminarUsuario, existeUsuario, listarUsuarios
}

