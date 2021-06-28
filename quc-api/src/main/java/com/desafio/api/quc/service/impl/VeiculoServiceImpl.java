package com.desafio.api.quc.service.impl;

import java.util.List;

import com.desafio.api.quc.document.Veiculo;
import com.desafio.api.quc.repository.VeiculoRepository;
import com.desafio.api.quc.service.VeiculoService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	private static final Logger LOGGER = LogManager.getLogger();
    
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    /** 
     * Retora todos os veiculos da base de dados cadastrados até o momento.
     * 
     * @return List<Veiculo> encontrados na base de dados no momento da consulta.
     */
    public List<Veiculo> listarTodos() {
        LOGGER.debug("[VEICULO-SERVICE-IMPL][ListarTodos] Iniciando Processo listar todos os veiculos.");

        List<Veiculo> veiculos = veiculoRepository.findAll();

        LOGGER.debug("[VEICULO-SERVICE-IMPL][ListarTodos] Finalizado Processo de listar todos os veiculos.");

        return veiculos;
    }
    
    /** 
     * Persiste um novo veiculo na base de dados.
     * A imagem do objeto deve ser uma url.
     * 
     * @param veiculo a ser persistido na base de dados.
     * @return Veiculo persistido com todas as informações, incluindo o identificador.
     */
    @Override
    public Veiculo criar(Veiculo veiculo) {
        LOGGER.debug("[VEICULO-SERVICE-IMPL][Criar] Iniciando Processo de criar veiculo. Veiculo [{}]", veiculo);

        veiculo = veiculoRepository.save(veiculo);

        LOGGER.debug("[VEICULO-SERVICE-IMPL][Criar] Finalizado Processo de criar veiculo. Veiculo Criado [{}]", veiculo);

        return veiculo;
    }

    /** 
     * Faz a consulta de um veiculo atraves de um identificador.
     * 
     * @param id do veiculo a ser consultado na base de dados.
     * @return Veiculo encontrado na consulta que corresponde ao id informado.
     */
    @Override
    public Veiculo buscarPorId(String idVeiculo) {
        LOGGER.debug("[VEICULO-SERVICE-IMPL][BuscarPorId] Iniciando Processo de buscar veiculo por id. Id [{}]", idVeiculo);

        Veiculo veiculo = veiculoRepository.findById(idVeiculo).orElse(null);

        LOGGER.debug("[VEICULO-SERVICE-IMPL][BuscarPorId] Finalizado Processo de buscar veiculo por id. Veiculo [{}]", veiculo);

        return veiculo;
    }

}