package com.thoughtworks.videorental.domain.specification;

import com.thoughtworks.videorental.domain.Movie;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class MovieWithTitleSpecification implements Specification<Movie> {

    private Collection<String> movieTitles;

    public MovieWithTitleSpecification(final String... movieTitles) {
        this.movieTitles = new HashSet<String>(Arrays.asList(movieTitles));
    }

    @Override
    public boolean isSatisfiedBy(final Movie movie) {
        return movieTitles.contains(movie.getTitle());
    }

}
