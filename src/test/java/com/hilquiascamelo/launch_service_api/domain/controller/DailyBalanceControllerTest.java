package com.hilquiascamelo.launch_service_api.domain.controller;

import com.hilquiascamelo.launch_service_api.domain.dto.DailyBalanceDto;
import com.hilquiascamelo.launch_service_api.application.service.DailyBalanceService;
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
public class DailyBalanceControllerTest {
    private static final String ENDPOINT_URL = "/api/daily-balance";
    private static final String ENDPOINT_URL_FIND_ALL = "/api/daily-balance/page-query";

    @InjectMocks
    private DailyBalanceController dailybalanceController;

    @Mock
    private DailyBalanceService dailybalanceService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dailybalanceController)
                .build();
    }


    @Test
    public void getById() throws Exception {
        Mockito.when(dailybalanceService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(DailyBalanceBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(dailybalanceService, Mockito.times(1)).findById(Long.valueOf("1"));
        Mockito.verifyNoMoreInteractions(dailybalanceService);
    }

    @Test
    public void save() throws Exception {
        DailyBalanceDto dailyBalanceDto = DailyBalanceBuilder.getDto();
        Mockito.when(dailybalanceService.save(ArgumentMatchers.any(DailyBalanceDto.class)))
                .thenReturn(dailyBalanceDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(dailyBalanceDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(dailybalanceService, Mockito.times(1)).save(ArgumentMatchers.any(DailyBalanceDto.class));
        Mockito.verifyNoMoreInteractions(dailybalanceService);
    }


    @Test
    public void update() throws Exception {
        Mockito.when(dailybalanceService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(DailyBalanceBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DailyBalanceBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(dailybalanceService, Mockito.times(1))
                .update(ArgumentMatchers.any(DailyBalanceDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(dailybalanceService);
    }

   // @Test
    public void delete() throws Exception {
               Mockito.doNothing().when(dailybalanceService).deleteById(Mockito.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Espera-se um status 200 OK

        // Verifica se o método foi chamado uma vez
        Mockito.verify(dailybalanceService, Mockito.times(1)).deleteById(1L);
        Mockito.verifyNoMoreInteractions(dailybalanceService);
    }
}
