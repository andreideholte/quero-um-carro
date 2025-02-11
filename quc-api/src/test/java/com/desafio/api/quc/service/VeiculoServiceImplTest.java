package com.desafio.api.quc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.repository.VeiculoRepository;
import com.desafio.api.quc.service.impl.VeiculoServiceImpl;

@ExtendWith(SpringExtension.class)
public class VeiculoServiceImplTest {

    @InjectMocks
    private VeiculoServiceImpl veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Test
    void testCriar() {
        when(veiculoRepository.save(Mockito.any(Veiculo.class)))
                .thenReturn(new Veiculo(
                        "90as9=sd299zz3dfg",
                        "Renault",
                        "Sandero",
                        "2011",
                        "Prata",
                        "322393",
                        "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg"));

        Veiculo veiculoCriado = veiculoService.criar(new Veiculo(
                null,
                "Renault",
                "Sandero",
                "2011",
                "Prata",
                "322393",
                "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg"));

        assertEquals("90as9=sd299zz3dfg", veiculoCriado.getId());
    }

    @Test
    void testListarTodos() {
        when(veiculoRepository.findAll())
                .thenReturn(Arrays.asList(new Veiculo(
                        "90as9=sd299zz3dfg",
                        "Renault",
                        "Sandero",
                        "2011",
                        "Prata",
                        "322393",
                        "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg")));

        List<Veiculo> veiculos = veiculoService.listarTodos();
        assertEquals(1, veiculos.size());
    }

    @Test
    void testBuscarPorId() {
        when(veiculoRepository.findById(Mockito.any(String.class)))
                .thenReturn(Optional.of(new Veiculo(
                        "90as9=sd299zz3dfg",
                        "Renault",
                        "Sandero",
                        "2011",
                        "Prata",
                        "322393",
                        "https://img1.icarros.com/dbimg/galeriaimgmodelo/2/3057_1.jpg")));

        Veiculo veiculoEncontrado = veiculoService.buscarPorId("90as9=sd299zz3dfg");
        assertEquals("Sandero", veiculoEncontrado.getModelo());
    }
}
