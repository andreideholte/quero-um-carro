package com.desafio.api.quc.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio.api.quc.document.Reserva;
import com.desafio.api.quc.exception.BusinessException;
import com.desafio.api.quc.repository.ReservaRepository;
import com.desafio.api.quc.service.ReservaService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ReservaRepository reservaRepository;
    
    /** 
     * Cria uma nova reserva de um veiculo para um usuario durante um periodo
     * maximo de trinta dias.
     * 
     * @param reserva a ser persistida na base de dados. 
     * @return Reserva persistida com todas as informacoes, incluindo o identificador.
     * @throws BusinessException
     */
    @Override
    public Reserva criar(Reserva reserva) throws BusinessException {
        LOGGER.info("[RESERVA-SERVICE-IMPL][Criar] Iniciando Processo de criar uma reserva. Reserva [{}]", reserva);

        this.validarReserva(reserva);

        reserva = reservaRepository.save(reserva);

        LOGGER.info("[RESERVA-SERVICE-IMPL][Criar] Finalizado Processo de criar uma reserva. Reserva Criada [{}]", reserva);
        
        return reserva;
    }

    /** 
     * Valida todas as precondicoes da reserva antes de persistir na base de dados.
     * Caso haja falha em alguma precondicao a validacao vai lancar uma excecao com a mensagem de erro apropriada.  
     * 
     * @param reserva contendo os dados a serem validados.
     * @throws BusinessException
     */
    private void validarReserva(Reserva reserva) throws BusinessException {
        LOGGER.debug("[RESERVA-SERVICE-IMPL][ReservaValida] Iniciando Processo de validacao da reserva.");

        if (!reserva.periodoReservaValido()) {
            throw new BusinessException("Periodo de reserva invalido, verifique se a data de inicio e inferior ou igual a data fim.");
        }
        
        if (!reserva.quantidadeDiasReservaValido()) {
            throw new BusinessException("Periodo de reserva invalido, o periodo maximo de reserva de um veiculo deve ser de 30 dias corridos.");
        }

        if (!this.validarPorUsuario(reserva.getUsuario().getId(), reserva.getDataInicio(), reserva.getDataFim())) {
            throw new BusinessException("Este usuario ja possui uma reserva de algum veiculo no periodo informado.");
        }

        if (!this.validarPorVeiculo(reserva.getVeiculo().getId(), reserva.getDataInicio(), reserva.getDataFim())) {
            throw new BusinessException("O veiculo escolhido ja esta reservado para um usuario no periodo informado.");
        }

        LOGGER.debug("[RESERVA-SERVICE-IMPL][ReservaValida] Finalizado Processo de validacao da reserva.");
    }

    /** 
     * Faz a validacao se o usuario so possui aquela reserva para a data informada.
     * 
     * @param idUsuario que esta efetuando a reserva.
     * @param dataInicio do periodo de reserva do veiculo.
     * @param dataFim do periodo de reserva do veiculo
     * @return boolean informando que se o periodo de reserva for valido para aquele usuario 
     * o retorno e TRUE, caso contrario, FALSE.
     */
    private boolean validarPorUsuario(String idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim) {
        LOGGER.debug("[RESERVA-SERVICE-IMPL][ValidarPorUsuario] Iniciando Processo de validacao da reserva por usuario. Id do Usuario [{}], Data Inicio [{}], Data Fim [{}]", idUsuario, dataInicio, dataFim);

        List<Reserva> reservas = reservaRepository.findCustomByUsuarioIdAndPeriodo(idUsuario, dataInicio, dataFim);

        LOGGER.debug("[RESERVA-SERVICE-IMPL][ValidarPorUsuario] Finalizado Processo de validacao da reserva por usuario. Id do Usuario [{}], Data Inicio [{}], Data Fim [{}]", idUsuario, dataInicio, dataFim);

        return (reservas == null || reservas.isEmpty());
    }

    /** 
     * Faz a validacao para saber se o veiculo ja esta reservado para outro usuario na data informada.
     * 
     * @param idUsuario que esta efetuando a reserva.
     * @param dataInicio do periodo de reserva do veiculo.
     * @param dataFim do periodo de reserva do veiculo.
     * @return boolean informando que se o veiculo ja estiver reservado para outro usuario na data informada 
     * o retorno e FALSE, caso contrario, TRUE.
     */
    private boolean validarPorVeiculo(String idVeiculo, LocalDateTime dataInicio, LocalDateTime dataFim) {
        LOGGER.debug("[RESERVA-SERVICE-IMPL][ValidarPorVeiculo] Iniciando Processo de buscar reservas por veiculo. Id do Veiculo [{}], Data Inicio [{}], Data Fim [{}]", idVeiculo, dataInicio, dataFim);

        List<Reserva> reservas = reservaRepository.findCustomByVeiculoIdAndPeriodo(idVeiculo, dataInicio, dataFim);

        LOGGER.debug("[RESERVA-SERVICE-IMPL][ValidarPorVeiculo] Finalizado Processo de buscar reservas por veiculo. Id do Veiculo [{}], Data Inicio [{}], Data Fim [{}]", idVeiculo, dataInicio, dataFim);

        return (reservas == null || reservas.isEmpty());
    }

}