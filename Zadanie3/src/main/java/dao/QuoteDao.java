package dao;

import model.Quote;

import java.util.Optional;

public interface QuoteDao {



    void save(Quote quote);

    Optional<Quote> findByQuote(String quote);

    int countQuotes();

    void deleteAllQuotes();
}
