package com.feeltech.projeto.service;

import com.feeltech.projeto.model.Endereco;
import com.feeltech.projeto.model.Restaurante;
import com.feeltech.projeto.repository.EnderecoRepository;
import com.feeltech.projeto.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> listarPorId(Long id) {
        return restauranteRepository.findById(id);
    }

    public List<Restaurante> listarPorNome(String nome) {
        return restauranteRepository.findByName(nome);
    }

    public List <Restaurante> listarPorTipoCulinaria(String tipoCulinaria) {
        return restauranteRepository.findByFood(tipoCulinaria);
    }

    public List<Restaurante> listarPorCep(String cep) {
        return restauranteRepository.findAllByCep(cep);

    }

    public void salvarRestaurante(Restaurante restaurante) {

        String cep = restaurante.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.findCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        restaurante.setEndereco(endereco);

        restauranteRepository.save(restaurante);
    }

    public void atualizarRestaurante(Long id, Restaurante restaurante) {

        Optional <Restaurante> novoRestaurante = restauranteRepository.findById(id);
        if (novoRestaurante.isPresent()) {
            novoRestaurante.get().setNome(restaurante.getNome());
            novoRestaurante.get().setTipoCulinaria(restaurante.getTipoCulinaria());
            String cep = restaurante.getEndereco().getCep();
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                Endereco novoEndereco = viaCepService.findCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
            novoRestaurante.get().setEndereco(endereco);
            restauranteRepository.save(novoRestaurante.get());
        }
    }


    public void deletarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }


}
