package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.document.VeiculosCollection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface VeiculoController {

    /**
     * 
     * @return
     */
    ResponseEntity<VeiculosCollection> listarTodos();

    /**
     * 
     * @param veiculo
     * @return
     */
    ResponseEntity<Veiculo> criar(@RequestBody final Veiculo veiculo);

    /**
     * 
     * @param idVeiculo
     * @return
     */
    ResponseEntity<Veiculo> buscarPorId(@PathVariable("id") final String idVeiculo);

}
