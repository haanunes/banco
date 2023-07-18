/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banco;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.ContaService;
import br.com.banco.services.TransferenciaService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author Hélder
 */

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransferenciaServiceTest {

    @Autowired
    private TransferenciaService transferenciaService;
    @Autowired
    private ContaService contaService;

    @Test
    public void testSalvarTransferencia() {
        Conta conta = new Conta();
        conta.setNomeResponsavel("João");

        Conta contaSalva = contaService.save(conta);

        // Configurar o cenário de teste
        Transferencia transferencia = new Transferencia();
        // ... configurar os dados da transferência ...
        transferencia.setDataTransferencia(LocalDateTime.now());
        transferencia.setNomeOperadorTransacao("João");
        transferencia.setTipo("TRANSFERÊNCIA");
        transferencia.setValor(BigDecimal.valueOf(200));
        transferencia.setConta(contaSalva);
        // Chamar o método que queremos testar
        Transferencia transferenciaSalva = transferenciaService.save(transferencia);

        // Verificar se a transferência foi salva com sucesso
        assertNotNull(transferenciaSalva.getId());
        // ... verificar outros campos importantes da transferência ...

        // Você também pode consultar o repositório ou serviço
        // para garantir que a transferência foi persistida corretamente
        // e que pode ser recuperada posteriormente se necessário.
    }

    @Test
    public void testFindById() {
        // Configurar o cenário de teste
        Conta conta = new Conta();
        conta.setNomeResponsavel("João");

        Conta contaSalva = contaService.save(conta);

        Transferencia transferencia = new Transferencia();
        transferencia.setDataTransferencia(LocalDateTime.now());
        transferencia.setNomeOperadorTransacao("João");
        transferencia.setTipo("TRANSFERÊNCIA");
        transferencia.setValor(BigDecimal.valueOf(200));
        transferencia.setConta(contaSalva);

        // Salvar a transferência para obter o ID
        Transferencia transferenciaSalva = transferenciaService.save(transferencia);

        // Testar o findById com um ID válido
        Transferencia transferenciaEncontrada = transferenciaService.findById(transferenciaSalva.getId());
        assertNotNull(transferenciaEncontrada);
        assertEquals(transferenciaSalva.getId(), transferenciaEncontrada.getId());

        // Testar o findById com um ID inválido
        Long idInvalido = -1L;
        Transferencia transferenciaNaoEncontrada = transferenciaService.findById(idInvalido);
        assertNull(transferenciaNaoEncontrada);
    }
    
    @Test
    public void testFindAll() {
        // Configurar o cenário de teste
        Conta conta1 = new Conta();
        conta1.setNomeResponsavel("João");
        Conta contaSalva1 = contaService.save(conta1);

        Transferencia transferencia1 = new Transferencia();
        transferencia1.setDataTransferencia(LocalDateTime.now());
        transferencia1.setNomeOperadorTransacao("João");
        transferencia1.setTipo("TRANSFERÊNCIA");
        transferencia1.setValor(BigDecimal.valueOf(200));
        transferencia1.setConta(contaSalva1);
        transferenciaService.save(transferencia1);

        Conta conta2 = new Conta();
        conta2.setNomeResponsavel("Maria");
        Conta contaSalva2 = contaService.save(conta2);

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setDataTransferencia(LocalDateTime.now());
        transferencia2.setNomeOperadorTransacao("Maria");
        transferencia2.setTipo("TRANSFERÊNCIA");
        transferencia2.setValor(BigDecimal.valueOf(300));
        transferencia2.setConta(contaSalva2);
        transferenciaService.save(transferencia2);

        // Testar o findAll quando existem transferências no banco de dados
        List<Transferencia> transferenciasEncontradas = transferenciaService.findAll();
        assertNotNull(transferenciasEncontradas);
        assertFalse(transferenciasEncontradas.isEmpty());
        assertEquals(2, transferenciasEncontradas.size());

        
    }
    
    @Test
    public void testListarTransferenciasPorConta() {
        // Configurar o cenário de teste
        Conta conta1 = new Conta();
        conta1.setNomeResponsavel("João");
        Conta contaSalva1 = contaService.save(conta1);

        Transferencia transferencia1 = new Transferencia();
        transferencia1.setDataTransferencia(LocalDateTime.now());
        transferencia1.setNomeOperadorTransacao("João");
        transferencia1.setTipo("TRANSFERÊNCIA");
        transferencia1.setValor(BigDecimal.valueOf(200));
        transferencia1.setConta(contaSalva1);
        transferenciaService.save(transferencia1);

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setDataTransferencia(LocalDateTime.now());
        transferencia2.setNomeOperadorTransacao("João");
        transferencia2.setTipo("TRANSFERÊNCIA");
        transferencia2.setValor(BigDecimal.valueOf(300));
        transferencia2.setConta(contaSalva1);
        transferenciaService.save(transferencia2);

        // Testar o listarTransferenciasPorConta para a conta1
        List<Transferencia> transferenciasPorConta = transferenciaService.listarTransferenciasPorConta(contaSalva1.getId().toString());
        assertNotNull(transferenciasPorConta);
        assertEquals(2, transferenciasPorConta.size());
        assertEquals(contaSalva1.getId(), transferenciasPorConta.get(0).getConta().getId());
        assertEquals(contaSalva1.getId(), transferenciasPorConta.get(1).getConta().getId());
    }
}
