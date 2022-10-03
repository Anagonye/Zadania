package apiconnector;

import model.Quote;

import java.util.Optional;

public interface QuoteReceiver {

    Optional<Quote> getQuote();
}
