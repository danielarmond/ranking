package br.com.ranklist.ranking.dto;
import br.com.ranklist.ranking.models.Jogo;
import br.com.ranklist.ranking.models.enums.Categoria;
import java.util.List;
import java.util.stream.Collectors;

public class JogoDto {

    private String nome;
    private String urlImagem;
    private Categoria categoria;
    private int nota;
    private char tier;

    public JogoDto(Jogo jogo) {
        this.nome = jogo.getNome();
        this.urlImagem = jogo.getUrlImagem();
        this.categoria = jogo.getCategoria();
        this.nota = jogo.getNota();
        this.tier = jogo.getTier();
    }

    public String getNome() {
        return nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getNota() {
        return nota;
    }

    public char getTier() {
        return tier;
    }

    public static List<JogoDto> converter(List<Jogo> jogos) {
        return jogos.stream().map(JogoDto::new).collect(Collectors.toList());
    }
}