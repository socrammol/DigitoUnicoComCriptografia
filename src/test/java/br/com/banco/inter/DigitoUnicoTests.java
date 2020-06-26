package br.com.banco.inter;


import br.com.banco.inter.controller.DigitoUnicoController;
import br.com.banco.inter.controller.UsuarioController;
import br.com.banco.inter.dto.DigitoUnicoDTO;
import br.com.banco.inter.dto.UsuarioDTO;
import br.com.banco.inter.service.DigitoUnicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DigitoUnicoTests extends BancoInterApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    DigitoUnicoController digitoUnicoController;
    @Autowired
    UsuarioController usuarioController;




    @Test
    public void calculaDigitoUnicoNoExistingUserWith404Return() throws Exception {
        DigitoUnicoDTO digito = new DigitoUnicoDTO("14322233",2,3L,0);
        mockMvc.perform(post("/digitounico/calcular")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(digito)))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void calculaDigitoUnicoWhithoutUser200Return() throws Exception {
        DigitoUnicoDTO digito = new DigitoUnicoDTO("14322233",2,null,0);
        mockMvc.perform(post("/digitounico/calcular")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(digito)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inteiro").value(14322233))
                .andExpect(MockMvcResultMatchers.jsonPath("$.multiplicador").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value(4));
    }
    @Test
    public void calculaDigitoUnicoExistingUserWith200Return() throws Exception {

        UsuarioDTO user = new UsuarioDTO("marcos","ma@ma.com");
        usuarioController.create(user);
        DigitoUnicoDTO digito = new DigitoUnicoDTO("14322233",2,1L,0);
        mockMvc.perform(post("/digitounico/calcular")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(digito)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inteiro").value(14322233))
                .andExpect(MockMvcResultMatchers.jsonPath("$.multiplicador").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idUsuario").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value(4));;
    }
    @Test
    public void calculaDigitoUnicoWhithoutUserWhithLetter200Return() throws Exception {
        DigitoUnicoDTO digito = new DigitoUnicoDTO("143a2223b3",2,null,0);
        mockMvc.perform(post("/digitounico/calcular")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(digito)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inteiro").value(14322233))
                .andExpect(MockMvcResultMatchers.jsonPath("$.multiplicador").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultado").value(4));
    }

}
