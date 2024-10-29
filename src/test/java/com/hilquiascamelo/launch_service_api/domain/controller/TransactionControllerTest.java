package com.hilquiascamelo.launch_service_api.domain.controller;

import com.hilquiascamelo.launch_service_api.domain.dto.TransactionDto;
import com.hilquiascamelo.launch_service_api.application.service.TransactionService;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

@Transactional
public class TransactionControllerTest {
    private static final String ENDPOINT_URL = "/api/transaction";

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(transactionController)
                .build();
    }


    @Test
    public void getById() throws Exception {
        Mockito.when(transactionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(TransactionBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(transactionService, Mockito.times(1)).findById(Long.valueOf("1"));
        Mockito.verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(transactionService.save(ArgumentMatchers.any(TransactionDto.class)))
                .thenReturn(TransactionBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(TransactionBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(transactionService, Mockito.times(1)).save(ArgumentMatchers.any(TransactionDto.class));
        Mockito.verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(transactionService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(TransactionBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(TransactionBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(transactionService, Mockito.times(1))
                .update(ArgumentMatchers.any(TransactionDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(transactionService);
    }

    //@Test
    public void delete() throws Exception {
        Mockito.doNothing().when(transactionService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(TransactionBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(transactionService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(transactionService);
    }
}
