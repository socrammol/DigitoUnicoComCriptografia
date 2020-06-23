package br.com.banco.inter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.inter.model.DigitoUnico;





@Repository
public interface DigitoUnicoRepository extends CrudRepository<DigitoUnico, Long> {

}
