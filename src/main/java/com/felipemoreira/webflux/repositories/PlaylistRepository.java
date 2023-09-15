package com.felipemoreira.webflux.repositories;

import com.felipemoreira.webflux.documents.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {
}
