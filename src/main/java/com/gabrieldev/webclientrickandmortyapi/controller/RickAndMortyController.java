package com.gabrieldev.webclientrickandmortyapi.controller;

import com.gabrieldev.webclientrickandmortyapi.client.RickAndMortyClient;
import com.gabrieldev.webclientrickandmortyapi.response.CharacterResponse;
import com.gabrieldev.webclientrickandmortyapi.response.EpisodeResponse;
import com.gabrieldev.webclientrickandmortyapi.response.ListOfEpisodesResponse;
import com.gabrieldev.webclientrickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webClient")
public class RickAndMortyController {
    private final RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        return rickAndMortyClient.getAllEpisodes();
    }
}
