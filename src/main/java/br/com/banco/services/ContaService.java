package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta findById(Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    public void delete(Long id) {
        contaRepository.deleteById(id);
    }

}
