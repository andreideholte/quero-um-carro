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
        LOGGER.info("[VEICULO-SERVICE-IMPL][Criar] Iniciando Processo listar todos os veiculos.");

        List<Veiculo> veiculos = veiculoRepository.findAll();

        LOGGER.info("[VEICULO-SERVICE-IMPL][Criar] Finalizado Processo de listar todos os veiculos.");

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
        LOGGER.info("[VEICULO-SERVICE-IMPL][Criar] Iniciando Processo de criar veiculo. Veiculo [{}]", veiculo);

        veiculo = veiculoRepository.save(veiculo);

        LOGGER.info("[VEICULO-SERVICE-IMPL][Criar] Finalizado Processo de criar veiculo. Veiculo Criado [{}]", veiculo);

        return veiculo;
    }

    /** 
     * Faz a consulta de um veiculo atraves de um identificador.
     * 
     * @param id do veiculo a ser consultado na base de dados.
     * @return Veiculo encontrado na consulta que corresponde ao id informado.
     */
    @Override
    public Veiculo buscarPorId(String id) {
        LOGGER.info("[VEICULO-SERVICE-IMPL][BuscarPorId] Iniciando Processo de buscar veiculo por id. Id [{}]", id);

        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);

        LOGGER.info("[VEICULO-SERVICE-IMPL][BuscarPorId] Finalizado Processo de buscar veiculo por id. Veiculo [{}]", veiculo);

        return veiculo;
    }
    
    /** 
     * Atualiza os metadados de um veiculo.
     * 
     * @param veiculo contendo novas informacoes a serem persistidas.
     * @return Veiculo atualizado com as novas informacoes.
     */
    @Override
    public Veiculo atualizar(Veiculo veiculo) {
        LOGGER.info("[VEICULO-SERVICE-IMPL][Atualizar] Iniciando Processo de atualizar veiculo. Veiculo [{}]", veiculo);

        veiculo = veiculoRepository.save(veiculo);

        LOGGER.info("[VEICULO-SERVICE-IMPL][Atualizar] Finalizado Processo de atualizar veiculo. Veiculo Atualizado [{}]", veiculo);

        return veiculo;
    }
    
    /** 
     * Deleta um veiculo da base de dados.
     * 
     * @param veiculo a ser deletado contendo ao menos o identificador.
     */
    @Override
    public void deletar(Veiculo veiculo) {
        LOGGER.info("[VEICULO-SERVICE-IMPL][Deletar] Iniciando Processo de deletar veiculo. Veiculo [{}]", veiculo);

        veiculoRepository.delete(veiculo);

        LOGGER.info("[VEICULO-SERVICE-IMPL][Deletar] Finalizado Processo de deletar veiculo.");
    }
}