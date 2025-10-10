/*
 * Copyright (c) "Neo4j"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.nodes.sdn.demo.controller;

import java.util.List;

import org.neo4j.nodes.sdn.demo.entity.MovieEntity;
import org.neo4j.nodes.sdn.demo.repository.MovieRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PutMapping
    public MovieEntity createOrUpdateMovie(@RequestBody MovieEntity newMovie) {
        return movieRepository.save(newMovie);
    }

    @GetMapping
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/by-title")
    public MovieEntity byTitle(@RequestParam String title) {
        return movieRepository.findOneByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        movieRepository.deleteById(id);
    }
}
