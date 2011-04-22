package com.thoughtworks.videorental.domain.repository;

import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.specification.Specification;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public interface MovieRepository {
    void add(Movie entity) throws NullObjectAddedException;

    void add(Collection<Movie> entities) throws NullObjectAddedException;

    Set<Movie> selectAll();

    Set<Movie> selectAll(Comparator<Movie> comparator);

    Set<Movie> selectSatisfying(Specification<Movie> specification);

    Set<Movie> selectSatisfying(Specification<Movie> specification, Comparator<Movie> comparator);

    Movie selectUnique(Specification<Movie> specification) throws NonUniqueObjectSelectedException;

    Set<Movie> withTitles(String... titles);
}