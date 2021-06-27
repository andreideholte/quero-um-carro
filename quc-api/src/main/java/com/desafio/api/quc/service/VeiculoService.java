package com.desafio.api.quc.service;

import java.util.List;

import com.desafio.api.quc.document.Veiculo;

public interface VeiculoService {

    List<Veiculo> listarTodos();
    Veiculo criar(final Veiculo veiculo);
    Veiculo buscarPorId(final String id);
    Veiculo atualizar(Veiculo veiculo);
    void deletar(final Veiculo veiculo);

}