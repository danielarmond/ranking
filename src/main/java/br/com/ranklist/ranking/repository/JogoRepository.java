package br.com.ranklist.ranking.repository;
import br.com.ranklist.ranking.models.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface JogoRepository  extends JpaRepository<Jogo, Long> {


    @Query(value = "SELECT u FROM Jogo u WHERE u.nome = ?1")
    List<Jogo> buscaNome(String descricao);
}
