package br.com.ranklist.ranking.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import br.com.ranklist.ranking.models.Jogo;
import br.com.ranklist.ranking.form.JogoForm;
import br.com.ranklist.ranking.dto.JogoDto;
import br.com.ranklist.ranking.repository.JogoRepository;
import br.com.ranklist.ranking.form.AtualizacaoJogoForm;


@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

    @GetMapping()
    public List<JogoDto> lista(@RequestParam(required = false) String nome){

        if(nome == null) {
            List<Jogo> jogos = jogoRepository.findAll();
            return JogoDto.converter(jogos);
        }
        else{
            List<Jogo> jogos = jogoRepository.buscaNome(nome);
            return JogoDto.converter(jogos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDto> detalhar (@PathVariable Long id) {
        Optional<Jogo> jogo = jogoRepository.findById(id);
        if(jogo.isPresent()) {
            return ResponseEntity.ok(new JogoDto (jogo.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<JogoDto> cadastrar(@RequestBody @Valid JogoForm jogoForm, UriComponentsBuilder uriBuilder) {

        List<Jogo> validacaoJogo = jogoRepository.buscaNome(
                jogoForm.getNome());

        if(validacaoJogo.isEmpty()) {
            Jogo jogo = jogoForm.converter();
            jogoRepository.save(jogo);
            URI uri = uriBuilder.path("/jogos/{id}").buildAndExpand(jogo.getId()).toUri();
            return ResponseEntity.created(uri).body(new JogoDto(jogo));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<JogoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoJogoForm atualizacaoJogoForm){

        Optional<Jogo> optional = jogoRepository.findById(id);
        if(optional.isPresent()) {
            List<Jogo> validacaoJogo = jogoRepository.buscaNome(
                    atualizacaoJogoForm.getNome());
            if(validacaoJogo.isEmpty()) {
                Jogo jogo = atualizacaoJogoForm.atualizar(id, jogoRepository);
                return ResponseEntity.ok(new JogoDto(jogo));
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Jogo> optional = jogoRepository.findById(id);
        if(optional.isPresent()) {
            jogoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
