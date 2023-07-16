package br.com.banco.controllers;


import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public List<Transferencia> findAll() {
        return transferenciaService.findAll();
    }

    @GetMapping("/{id}")
    public Transferencia findById(@PathVariable Long id) {
        return transferenciaService.findById(id);
    }

    @PostMapping
    public Transferencia save(@RequestBody Transferencia transferencia) {
        return transferenciaService.save(transferencia);
    }

    @GetMapping("/conta/{numeroConta}")
    public List<Transferencia> listarTransferenciasPorConta(@PathVariable String numeroConta) {
        return transferenciaService.listarTransferenciasPorConta(numeroConta);
    }

}
