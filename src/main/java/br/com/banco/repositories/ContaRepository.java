/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hélder
 */

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    // Métodos personalizados podem ser adicionados aqui
    // Se não forem necessários métodos personalizados, o repositório já terá os métodos básicos (save, findById, findAll, etc.)
    
}