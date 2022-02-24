package com.gabrieldev.webclientrickandmortyapi.client;

import com.gabrieldev.webclientrickandmortyapi.response.CharacterResponse;
import com.gabrieldev.webclientrickandmortyapi.response.EpisodeResponse;
import com.gabrieldev.webclientrickandmortyapi.response.ListOfEpisodesResponse;
import com.gabrieldev.webclientrickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RickAndMortyClient {
    // WebClient nativo do webflux (Assíncrono, não bloqueante)
    private final WebClient webClient;

    @Autowired
    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findCharacterById(String id) {
        log.info("Buscando o personagem com o id [{}]", id);

        return webClient
                .get()
                .uri("/character/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // junta todas as informações
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findLocationById(String id) {
        log.info("Buscando a localização com o id [{}]", id);

        return webClient
                .get()
                .uri("/location/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // junta todas as informações
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findEpisodeById(String id) {
        log.info("Buscando a episódio com o id [{}]", id);

        return webClient
                .get()
                .uri("/episode/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // junta todas as informações
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        log.info("Buscando todos os episódios");

        return webClient
                .get()
                .uri("/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Erro na api")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }
}
