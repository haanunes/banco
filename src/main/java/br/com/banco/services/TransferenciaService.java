package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Transferencia save(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public Transferencia findById(Long id) {
        return transferenciaRepository.findById(id).orElse(null);
    }

    public List<Transferencia> findAll() {
        return transferenciaRepository.findAll();
    }

    //regras de consultas
    public List<Transferencia> listarTransferenciasPorPeriodoEOperadorDeUmaConta(String numeroConta, LocalDateTime startDate, LocalDateTime endDate, String operadorTransacao) {
        return transferenciaRepository.findByNomeOperadorTransacaoContainingAndContaIdAndDataTransferenciaBetween(operadorTransacao, Long.parseLong(numeroConta), startDate, endDate);
    }

    public List<Transferencia> listarTransferenciasPorPeriodoDeUmaConta(String numeroConta, LocalDateTime startDate, LocalDateTime endDate) {
        return transferenciaRepository.findByDataTransferenciaBetweenAndContaId(startDate, endDate, Long.parseLong(numeroConta));
    }

    public List<Transferencia> listarTransferenciasPorOperadorTransacaoDeUmaConta(String numeroConta, String operadorTransacao) {
        return transferenciaRepository.findByNomeOperadorTransacaoContainingAndContaId(operadorTransacao, Long.parseLong(numeroConta));
    }

    public List<Transferencia> listarTransferenciasPorConta(String numeroConta) {
        return transferenciaRepository.findByContaId(Long.parseLong(numeroConta));
    }

}
