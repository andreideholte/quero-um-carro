package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.VeiculoController;
import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.document.VeiculosCollection;
import com.desafio.api.quc.service.VeiculoService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(value = "Veiculo")
@RestController
public class VeiculoControllerImpl implements VeiculoController {
    
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
	private VeiculoService veiculoService;
    
    @Override
    @ApiOperation(value = "Consulta de veiculo pelo Identificador")
	@GetMapping(value= "/veiculo/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable("id") String idVeiculo) {
        LOGGER.debug("[VEICULO-CONTROLLER-IMPL][BuscarPorId] Iniciando Processo de buscar veiculo por id. Id [{}]", idVeiculo);

        Veiculo veiculo = veiculoService.buscarPorId(idVeiculo);

        if (veiculo != null) {
            LOGGER.debug("[VEICULO-CONTROLLER-IMPL][BuscarPorId] Finalizado Processo de buscar veiculo por id. Veiculo [{}]", veiculo);
            return new ResponseEntity<>(veiculo, HttpStatus.OK);
        }

        LOGGER.debug("[VEICULO-CONTROLLER-IMPL][BuscarPorId] Nenhum veiculo foi encontrado para o Id informado. Id [{}]", idVeiculo);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @ApiOperation(value = "Buscar todos os veiculos")
	@GetMapping(value= "/veiculo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculosCollection> listarTodos() {
        LOGGER.info("[VEICULO-CONTROLLER-IMPL][ListarTodos] Iniciando Processo listar todos os veiculos.");

        VeiculosCollection veiculosCollection = new VeiculosCollection();
        veiculosCollection.setVeiculos(veiculoService.listarTodos()); 

        LOGGER.info("[VEICULO-CONTROLLER-IMPL][ListarTodos] Finalizado Processo de listar todos os veiculos.");

        return new ResponseEntity<>(veiculosCollection, HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Criar um novo veiculo")
	@PostMapping(value= "/veiculo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Veiculo> criar(@RequestBody Veiculo veiculo) {
        LOGGER.info("[VEICULO-CONTROLLER-IMPL][Criar] Iniciando Processo de criar veiculo. Veiculo [{}]", veiculo);

        Veiculo veiculoCriado = veiculoService.criar(veiculo);

        LOGGER.info("[VEICULO-CONTROLLER-IMPL][Criar] Finalizado Processo de criar veiculo. Veiculo Criado [{}]", veiculoCriado);

        return new ResponseEntity<>(veiculoCriado, HttpStatus.CREATED);
    }

}
