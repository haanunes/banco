package br.com.banco.controllers;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.findAll();
    }

    @GetMapping("/{id}")
    public Conta buscarContaPorId(@PathVariable Long id) {
        return contaService.findById(id);
    }

    @PostMapping
    public Conta salvarConta(@RequestBody Conta conta) {
        return contaService.save(conta);
    }

    @PutMapping("/{id}")
    public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
        Conta contaExistente = contaService.findById(id);
        if (contaExistente != null) {
            conta.setId(id); // Define o ID da conta a ser atualizada
            return contaService.save(conta);
        } 
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id) {
        Conta contaExistente = contaService.findById(id);
        if (contaExistente != null) {
            contaService.delete(id);
        } 
    }

}
