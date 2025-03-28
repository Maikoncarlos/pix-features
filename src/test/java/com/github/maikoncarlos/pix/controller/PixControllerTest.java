package com.github.maikoncarlos.pix.controller;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixUpdateRequestDTO;
import com.github.maikoncarlos.pix.exception.PixByIdNotFoundException;
import com.github.maikoncarlos.pix.exception.PixExistsByIdException;
import com.github.maikoncarlos.pix.fatory.PixFactory;
import com.github.maikoncarlos.pix.fatory.PixRequestDTOFactory;
import com.github.maikoncarlos.pix.fatory.PixResponseDTOFactory;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.service.PixService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.maikoncarlos.pix.util.MocksUtil.ID;
import static com.github.maikoncarlos.pix.util.MocksUtil.ID_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PixControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    PixService pixService;

    @MockitoBean
    IPixMapper pixMapper;

    @Autowired
    private JacksonTester<PixRequestDTO> requestDTO;

    @Autowired
    private JacksonTester<PixUpdateRequestDTO> requestUpdateDTO;


    @Nested
    class TestCreated {

        @Test
        @DisplayName("Teste de Retornar status 200 Ok e criar chave pix")
        void testSuccess() throws Exception {
            when (pixMapper.toResponseDTO (PixFactory.responseSuccess ())).thenReturn (PixResponseDTOFactory.success ());
            when (pixService.save (PixRequestDTOFactory.success ())).thenReturn (PixFactory.responseSuccess ());

            final var response = mvc.perform (post ("/v1/pix")
                            .contentType (MediaType.APPLICATION_JSON)
                            .content (requestDTO.write (PixRequestDTOFactory.success ()).getJson ()))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.OK.value (), response.getStatus ());
        }

        @Test
        @DisplayName("Teste de Retornar status 422 UNPROCESSABLE_ENTITY pois está com keyType inválido")
        void testException() throws Exception {

            final var response = mvc.perform (post ("/v1/pix")
                            .contentType (MediaType.APPLICATION_JSON)
                            .content (requestDTO.write (PixRequestDTOFactory.errorKeyType ("xxx")).getJson ()))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.UNPROCESSABLE_ENTITY.value (), response.getStatus ());
        }

        @Test
        @DisplayName("Teste de Retornar status 422 UNPROCESSABLE_ENTITY pois está com accountType inválido")
        void testException_1() throws Exception {

            final var response = mvc.perform (post ("/v1/pix")
                            .contentType (MediaType.APPLICATION_JSON)
                            .content (requestDTO.write (PixRequestDTOFactory.errorAccountType ("xxx")).getJson ()))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.UNPROCESSABLE_ENTITY.value (), response.getStatus ());
        }

    }

    @Nested
    class TestFindById {

        @Test
        @DisplayName("Teste de Retornar status 200 Ok e buscar chave pix por id")
        void testSuccess() throws Exception {
            when (pixMapper.toResponseDTO (PixFactory.responseSuccess ())).thenReturn (PixResponseDTOFactory.success ());

            final var response = mvc.perform (get ("/v1/pix/" + ID)
                            .contentType (MediaType.APPLICATION_JSON))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.OK.value (), response.getStatus ());
        }

        @Test
        @DisplayName("Teste de Retornar status 404 NOT_FOUND pois está com id inválido")
        void testException() throws Exception {
            when (pixService.findById (ID_ERROR.toString ())).thenThrow (new PixByIdNotFoundException (ID_ERROR.toString ()));

            final var response = mvc.perform (get ("/v1/pix/" + ID_ERROR)
                            .contentType (MediaType.APPLICATION_JSON))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.NOT_FOUND.value (), response.getStatus ());
        }

    }

    @Nested
    class TestDisableById {

        @Test
        @DisplayName("Teste de Retornar status 200 Ok e desativar chave pix por id")
        void testSuccess() throws Exception {
            when (pixMapper.toResponseDTO (PixFactory.responseSuccess ())).thenReturn (PixResponseDTOFactory.success ());

            final var response = mvc.perform (patch ("/v1/pix/" + ID)
                            .contentType (MediaType.APPLICATION_JSON)
                            .content (requestDTO.write (PixRequestDTOFactory.success ()).getJson ()))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.OK.value (), response.getStatus ());
        }

        @Test
        @DisplayName("Teste de Retornar status 422 UNPROCESSABLE_ENTITY pois está com id já foi desabilitado")
        void testException() throws Exception {
            when (pixService.disableById (ID_ERROR.toString ())).thenThrow (new PixExistsByIdException (ID_ERROR.toString ()));

            final var response = mvc.perform (patch ("/v1/pix/" + ID_ERROR)
                            .contentType (MediaType.APPLICATION_JSON))
                    .andReturn ().getResponse ();

            assertEquals (HttpStatus.UNPROCESSABLE_ENTITY.value (), response.getStatus ());
        }

    }


}