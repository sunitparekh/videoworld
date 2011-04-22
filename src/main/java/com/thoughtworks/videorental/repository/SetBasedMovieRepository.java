package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;
import com.thoughtworks.videorental.domain.specification.MovieWithTitleSpecification;

import java.util.Collection;
import java.util.Set;

public class SetBasedMovieRepository extends SetBasedRepository<Movie> implements MovieRepository {

    public SetBasedMovieRepository() {
        super();
    }

    public SetBasedMovieRepository(final Collection<Movie> entities) {
        super(entities);
    }

    @Override
    public Set<Movie> withTitles(final String... titles) {
        return selectSatisfying(new MovieWithTitleSpecification(titles));
    }
}
