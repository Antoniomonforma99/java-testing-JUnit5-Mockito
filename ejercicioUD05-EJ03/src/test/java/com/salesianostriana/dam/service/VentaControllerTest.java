package com.salesianostriana.dam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.controller.VentaController;
import com.salesianostriana.dam.dto.VentaConverter;
import com.salesianostriana.dam.repos.ClienteRepositorio;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    VentaServicio ventaServicio;

    @MockBean
    ClienteRepositorio clienteRepositorio;

    @MockBean
    VentaConverter ventaConverter;



}
