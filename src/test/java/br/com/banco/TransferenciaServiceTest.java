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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
/**
 *
 * @author Hélder
 */

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class TransferenciaServiceTest {
    
    
    @Autowired
    private TransferenciaService transferenciaService;
    @Autowired
    private ContaService contaService;

     @Test
    public void testSalvarTransferencia() {
        Conta conta =new Conta();
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
}
