package com.github.maikoncarlos.pix.service;

import com.github.maikoncarlos.pix.exception.PixByAgencyAndAccountNotFoundException;
import com.github.maikoncarlos.pix.exception.PixByIdNotFoundException;
import com.github.maikoncarlos.pix.exception.PixExistsByIdException;
import com.github.maikoncarlos.pix.exception.PixKeyTypeNotFoundException;
import com.github.maikoncarlos.pix.fatory.PixFactory;
import com.github.maikoncarlos.pix.fatory.PixRequestDTOFactory;
import com.github.maikoncarlos.pix.fatory.PixUpdateRequestDTOFactory;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.repository.IPixRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Optional;

import static com.github.maikoncarlos.pix.util.MocksUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings
class PixServiceTest {

    @InjectMocks
    PixService pixService;

    @Mock
    IPixRepository pixRepository;

    @Mock
    IPixMapper pixMapper;

    final String pixNotFound = "Pix not found";

    @Nested
    class SavePix {

        @Test
        @DisplayName("Teste que cria chave pix chave com sucesso!")
        void testKeyPixWithSuccess() {
            when (pixRepository.save (PixFactory.requestSuccess ())).thenReturn (PixFactory.responseSuccess ());
            when (pixMapper.requestToEntity (PixRequestDTOFactory.success ())).thenReturn (PixFactory.requestSuccess ());

            final var actual = pixService.save (PixRequestDTOFactory.success ());

            assertNotNull (actual);
            assertEquals ("19d60968-d469-4df6-90e9-35a8aaf00905", actual.getId ().toString ());
        }

        @Test
        @DisplayName("Teste retorna IllegalArgumentException devido keyType inválido!")
        void testKeyPixWithIllegalArgumentException() {

            final var exception = assertThrows (IllegalArgumentException.class,
                    () -> pixService.save (PixRequestDTOFactory.success ("error")));

            assertEquals (IllegalArgumentException.class, exception.getClass ());
        }
    }

    @Nested
    class FindByIdPix {

        @Test
        @DisplayName("Teste que retorna a Chave Pix buscada pelo id")
        void testKeyPixWithSuccess() {
            when (pixRepository.findByIdAndActive (ID, true)).thenReturn (Optional.ofNullable (PixFactory.responseSuccess ()));

            final var actual = pixService.findById (ID.toString ());

            assertNotNull (actual);
            assertEquals (ID, actual.getId ());
            assertEquals (KEY_TYPE, actual.getKeyType ());
        }

        @Test
        @DisplayName("Teste retorna PixByIdNotFoundException devido UUID inválido!")
        void testPixByIdNotFoundException() {
            when (pixRepository.findByIdAndActive (ID_ERROR, true))
                    .thenThrow (new PixByIdNotFoundException (ID_ERROR.toString ()));

            final var exception = assertThrows (PixByIdNotFoundException.class,
                    () -> pixService.findById (ID_ERROR.toString ()));

            assertEquals (PixByIdNotFoundException.class, exception.getClass ());

            assertEquals (pixNotFound, exception.toProblemDetail ().getTitle ());
        }
    }

    @Nested
    class FindListByAgencyAndAccountPix {

        @Test
        @DisplayName("Teste retorna Lista de Chave Pix com Sucesso")
        void testKeyPixWithSuccess() {
            when (pixRepository.findByAgencyNumberAndAccountNumberAndActive (AGENT_NUMBER, ACCOUNT_NUMBER, true))
                    .thenReturn (List.of (PixFactory.responseSuccess ()));

            final var actual = pixService.findListByAgencyAndAccount (AGENT_NUMBER, ACCOUNT_NUMBER);

            assertNotNull (actual);
            assertEquals (1, actual.toArray ().length);
        }

        @Test
        @DisplayName("Teste retorna PixByAgencyAndAccountNotFoundException devido a não ter dados no banco com os valores informados")
        void testKeyPixByAgencyAndAccountNotFoundException() {
            when (pixRepository.findByAgencyNumberAndAccountNumberAndActive (1, 1, true))
                    .thenReturn (List.of ());

            final var exception = assertThrows (PixByAgencyAndAccountNotFoundException.class,
                    () -> pixService.findListByAgencyAndAccount (1, 1));

            assertEquals (PixByAgencyAndAccountNotFoundException.class, exception.getClass ());
        }
    }

    @Nested
    class UpdatePix {

        @Test
        @DisplayName("Teste de retornar Chave Pix atualizada com sucesso!")
        void testKeyPixWithSuccess() {
            when (pixRepository.findByIdAndActive (ID, true)).thenReturn (Optional.ofNullable (PixFactory.responseSuccess ()));
            when (pixRepository.save (PixFactory.responseSuccess ())).thenReturn (PixFactory.responseSuccess ());

            final var actual = pixService.updatePix (PixUpdateRequestDTOFactory.success ());

            assertNotNull (actual);
            assertEquals (PixFactory.responseSuccess (), actual);
        }

        @Test
        @DisplayName("Teste de retornar PixByIdNotFoundException devido a UUID inválido!")
        void testKeyPixByIdNotFoundException() {
            when (pixRepository.findByIdAndActive (ID_ERROR, true)).thenThrow (new PixByIdNotFoundException (ID_ERROR.toString ()));

            final var exception = assertThrows (PixByIdNotFoundException.class,
                    () -> pixService.updatePix (PixUpdateRequestDTOFactory.error (ID_ERROR)));

            assertEquals (PixByIdNotFoundException.class, exception.getClass ());
            assertEquals (pixNotFound, exception.toProblemDetail ().getTitle ());
        }
    }

    @Nested
    class DisableById {

        @Test
        @DisplayName("Teste de retornar PixExistsByIdException por Chave Pix já estar desativada!")
        void disableById() {
            when (pixRepository.existsByIdAndActive (ID_ERROR, false)).thenReturn (true);

            final var exception = assertThrows (PixExistsByIdException.class,
                    () -> pixService.disableById (ID_ERROR.toString ()));

            assertEquals (PixExistsByIdException.class, exception.getClass ());
        }
    }

    @Nested
    class FindByKeyTypeAndOrClientName {

        @Test
        @DisplayName("Teste deve retornar Lista de Chave Pix com sucesso na busca por Tipo de Chave somente")
        void testKeyPixWithSuccess() {
            when (pixRepository.findByKeyType (KEY_TYPE)).thenReturn (List.of (PixFactory.responseSuccess ()));

            final var actual = pixService.findByKeyTypeAndOrClientName (KEY_TYPE, null);

            assertEquals (List.of (PixFactory.responseSuccess ()), actual);
            verify (pixRepository).findByKeyType (KEY_TYPE);

        }

        @Test
        @DisplayName("Teste deve retornar Lista de Chave Pix com sucesso na busca por Tipo de Chave e Nome do Cliente")
        void testKeyPixWithSuccess_2() {
            when (pixRepository.findByKeyTypeAndClientName (KEY_TYPE, CLIENT_NAME)).thenReturn (List.of (PixFactory.responseSuccess ()));

            final var actual = pixService.findByKeyTypeAndOrClientName (KEY_TYPE, CLIENT_NAME);

            assertEquals (List.of (PixFactory.responseSuccess ()), actual);
            verify (pixRepository).findByKeyTypeAndClientName (KEY_TYPE, CLIENT_NAME);
            verify (pixRepository, times (0)).findByKeyType (KEY_TYPE);

        }

        @Test
        @DisplayName("Teste deve retornar IllegalArgumentException pelo tipo de chave do PARAMs estar inválido!")
        void testKeyPixKeyTypeNotFoundException() {
            final var exception = assertThrows (IllegalArgumentException.class,
                    () -> pixService.findByKeyTypeAndOrClientName (KEY_TYPE_ERROR, null));

            assertEquals (IllegalArgumentException.class, exception.getClass ());
        }

        @Test
        @DisplayName("Teste deve retornar PixKeyTypeNotFoundException por não existir chaves pix cadastradas com esse type de chave e nome do cliente")
        void testKeyPixKeyTypeNotFoundException_2() {
            when(pixRepository.findByKeyTypeAndClientName(KEY_TYPE, CLIENT_NAME)).thenReturn (List.of ());

            final var exception = assertThrows (PixKeyTypeNotFoundException.class,
                    () -> pixService.findByKeyTypeAndOrClientName (KEY_TYPE, CLIENT_NAME));

            assertEquals (PixKeyTypeNotFoundException.class, exception.getClass ());
        }

        @Test
        @DisplayName("Teste deve retornar PixKeyTypeNotFoundException por não existir chaves pix cadastradas com esse type de chave")
        void testKeyPixKeyTypeNotFoundException_3() {
            when(pixRepository.findByKeyType (KEY_TYPE)).thenReturn (List.of ());

            final var exception = assertThrows (PixKeyTypeNotFoundException.class,
                    () -> pixService.findByKeyTypeAndOrClientName (KEY_TYPE, null));

            assertEquals (PixKeyTypeNotFoundException.class, exception.getClass ());
        }
    }
}