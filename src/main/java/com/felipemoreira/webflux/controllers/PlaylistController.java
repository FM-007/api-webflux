package com.felipemoreira.webflux.controllers;

import com.felipemoreira.webflux.documents.Playlist;
import com.felipemoreira.webflux.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlist")
    public Flux<Playlist> getPlaylist() {
        return playlistService.findAll();
    }

    @GetMapping("/playlist/{id}")
    public Mono<Playlist> getPlaylistForID(@PathVariable String id) {
        return playlistService.findById(id);
    }

    @PostMapping("/playlist")
    public Mono<Playlist> save(@RequestBody Playlist playlist) {
        return playlistService.save(playlist);
    }

    @GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEnvents() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = playlistService.findAll();
        System.out.println("Passou aqui events!");

        return Flux.zip(interval, events);
    }

    @GetMapping("/playlist/mvc")
    public List<String> getPlaylistMvc() throws InterruptedException {
        System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());

        List<String> playlistList = new ArrayList<>();
        playlistList.add("Java 8");
        playlistList.add("Spring Security");
        playlistList.add("Github");
        playlistList.add("Deploy de uma aplicação java no IBM Cloud");
        playlistList.add("Bean no Spring Framework");
        TimeUnit.SECONDS.sleep(15);

        return playlistList;
    }
}
