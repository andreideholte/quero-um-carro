package com.desafio.api.quc.controller;

import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.document.VeiculosCollection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface VeiculoController {
    
    ResponseEntity<VeiculosCollection> listarTodos();
    ResponseEntity<Veiculo> criar(@RequestBody final Veiculo veiculo);
    ResponseEntity<Veiculo> buscarPorId(@RequestParam("id") final String idVeiculo);
    ResponseEntity<Veiculo> atualizar(@RequestBody Veiculo veiculo);
    void deletar(@RequestBody final Veiculo veiculo);
}
