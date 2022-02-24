package com.gabrieldev.webclientrickandmortyapi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListOfEpisodesResponse {
    private List<EpisodeResponse> results; // mesmo nome que está na API
}
