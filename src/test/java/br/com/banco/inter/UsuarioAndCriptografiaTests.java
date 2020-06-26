package br.com.banco.inter;

import br.com.banco.inter.controller.CriptografiaController;
import br.com.banco.inter.controller.DigitoUnicoController;
import br.com.banco.inter.controller.UsuarioController;
import br.com.banco.inter.dto.Chaves;
import br.com.banco.inter.dto.CriptografiaDTO;
import br.com.banco.inter.dto.UsuarioDTO;
import br.com.banco.inter.model.UsuarioModel;
import br.com.banco.inter.service.CriptografiService;
import br.com.banco.inter.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioAndCriptografiaTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    DigitoUnicoController digitoUnicoController;
    @Autowired
    UsuarioController usuarioController;
    @Autowired
    CriptografiaController criptografiaController;
    @Autowired
    CriptografiService criptografiService;
    @Autowired
    UsuarioService usuarioService;

    @Test
    @Order(1)
    public void CreateUsuario200Return() throws Exception {
        UsuarioDTO user = new UsuarioDTO("marcos","ma@ma.com");
        mockMvc.perform(post("/usuario/criar")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    public void UsuarioWithNonNameAndEmail404Return() throws Exception {
        UsuarioDTO user = new UsuarioDTO();
        mockMvc.perform(post("/usuario/criar")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Order(3)
    @Test
    public void GetUsuario200Return() throws Exception {
               mockMvc.perform(get("/usuario/buscar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("marcos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ma@ma.com"));
    }
    @Order(4)
    @Test
    public void UpdateUsuario200Return() throws Exception {
        UsuarioDTO user = new UsuarioDTO();
       mockMvc.perform(get("/usuario/buscar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("marcos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ma@ma.com"));

        user.setEmail("b@b.com");
        user.setNome("mol");

        mockMvc.perform(put("/usuario/alterar/{id}", 1)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());


        mockMvc.perform(get("/usuario/buscar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("mol"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("b@b.com"));
    }

    @Test
    @Order(5)
    public void DeleteUsuario() throws Exception {

        mockMvc.perform(get("/usuario/buscar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("mol"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("b@b.com"));

        mockMvc.perform(delete("/usuario/delete/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/usuario/buscar/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    @Test
    @Order(6)
    public void criaChaveRsa() throws Exception {
        mockMvc.perform(get("/criptografia")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @Order(7)
    public void criptografaDescriptografaNome() throws Exception {
        Chaves chave = criptografiService.geraChave();
        CriptografiaDTO criptografiaDTO = new CriptografiaDTO();
        criptografiaDTO.setPrivateKey(chave.getPrivatekey());
        criptografiaDTO.setPublicKey(chave.getPublickey());
        criptografiaDTO.setId(2L);
        mockMvc.perform(post("/criptografia")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(criptografiaDTO)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/criptografia/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(criptografiaDTO)))
                .andExpect(status().isOk());

    }



}
