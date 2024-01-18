package br.com.ranklist.ranking.form;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import br.com.ranklist.ranking.models.Jogo;
import br.com.ranklist.ranking.models.enums.Categoria;
import br.com.ranklist.ranking.repository.JogoRepository;

public class AtualizacaoJogoForm {

    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    private String urlImagem;
    private Categoria categoria;
    private int nota;
    private char tier;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public char getTier() {
        return tier;
    }

    public void setTier(char tier) {
        this.tier = tier;
    }

    public Jogo atualizar(Long id, JogoRepository jogoRepository) {
        Jogo jogo = jogoRepository.getReferenceById(id);
        jogo.setNome(this.nome);
        jogo.setCategoria(this.categoria);
        jogo.setUrlImagem(this.urlImagem);
        jogo.setNota(this.nota);
        jogo.setTier(this.tier);
        return jogo;
    }
}
