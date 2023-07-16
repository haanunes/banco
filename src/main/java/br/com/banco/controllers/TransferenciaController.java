package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

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

    @GetMapping("/periodo")
    public List<Transferencia> listarTransferenciasPorPeriodo(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {
        return transferenciaService.listarTransferenciasPorPeriodo(startDate, endDate);
    }

    @GetMapping("/conta/{numeroConta}")
    public List<Transferencia> listarTransferencias(@PathVariable String numeroConta,
            @RequestParam(required = false) String operadorTransacao,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {

        if (numeroConta == null && numeroConta.equals("")) {
            return transferenciaService.findAll();
        }
        //com filtros
        if (startDate != null && endDate != null) {
            return transferenciaService.listarTransferenciasPorPeriodoDeUmaConta(numeroConta, startDate, endDate);
        } else if (operadorTransacao != null && !operadorTransacao.equals("")) {
            return transferenciaService.listarTransferenciasPorOperadorTransacaoDeUmaConta(numeroConta, operadorTransacao);
        } else {
            return transferenciaService.listarTransferenciasPorConta(numeroConta);
        }
    }

}
