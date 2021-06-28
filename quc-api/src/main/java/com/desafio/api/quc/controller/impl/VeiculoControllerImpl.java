package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.VeiculoController;
import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.document.VeiculosCollection;
import com.desafio.api.quc.service.VeiculoService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "Veiculo")
@RequestMapping("/veiculo")
@RestController
public class VeiculoControllerImpl implements VeiculoController {
    
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
	private VeiculoService veiculoService;

    @Override
    public ResponseEntity<VeiculosCollection> listarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Veiculo> criar(Veiculo veiculo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Veiculo> buscarPorId(String idVeiculo) {
        // TODO Auto-generated method stub
        return null;
    }

}
