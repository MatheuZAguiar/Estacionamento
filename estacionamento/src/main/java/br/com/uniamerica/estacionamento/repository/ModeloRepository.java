package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    @Query("From Modelo where ativo = :ativo")
    public List<Modelo> findByAtivo(@Param("ativo")final boolean ativo);

    boolean existsByNomeModelo(String nomeModelo);
    Modelo findByNomeModelo(String nomeModelo);
}