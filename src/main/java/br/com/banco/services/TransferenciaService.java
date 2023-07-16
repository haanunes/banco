package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
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

    public List<Transferencia> listarTransferenciasPorConta(String numeroConta) {
        return transferenciaRepository.findByContaId(Long.parseLong(numeroConta));
    }

}
