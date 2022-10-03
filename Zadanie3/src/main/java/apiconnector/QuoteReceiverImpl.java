package apiconnector;

import json.JsonReader;
import model.Quote;

import java.io.IOException;
import java.util.Optional;

public class QuoteReceiverImpl implements QuoteReceiver {
    private final JsonReader jsonReader;
    private final String quoteSourceUrl;

    public QuoteReceiverImpl(JsonReader jsonReader, String quoteSourceUrl){
        this.jsonReader = jsonReader;
        this.quoteSourceUrl = quoteSourceUrl;
    }


    @Override
    public Optional<Quote> getQuote() {
        try {
            return jsonReader.read(Quote.class, quoteSourceUrl);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}
