/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hélder
 */
@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    public List<Transferencia> findByContaId(Long numeroConta);

    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :startDate AND :endDate")
    List<Transferencia> findByDataTransferenciaBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :startDate AND :endDate AND t.conta.id = :contaId")
    List<Transferencia> findByDataTransferenciaBetweenAndContaId(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("contaId") Long contaId);

    @Query("SELECT t FROM Transferencia t WHERE t.nomeOperadorTransacao = :operadorTransacao")
    List<Transferencia> findByNomeOperadorTransacao(@Param("operadorTransacao") String operadorTransacao);

    public List<Transferencia> findByNomeOperadorTransacaoAndContaId(String operadorTransacao,Long id);
}
