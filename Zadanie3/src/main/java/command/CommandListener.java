package command;

import apiconnector.QuoteReceiver;
import dao.QuoteDao;
import model.Quote;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;


public class CommandListener {

    private final QuoteReceiver quoteReceiver;
    private final QuoteDao quoteDao;
    //private Consumer<model.Quote> handleQuote;

    public CommandListener(QuoteReceiver quoteReceiver, QuoteDao quoteDao ) {
        this.quoteReceiver = quoteReceiver;
        this.quoteDao = quoteDao;
    }


    public void start(){
        Scanner scanner = new Scanner(System.in);
        while(areThereStillQuotes()){
            System.out.println("Wpisz 'next' aby otrzymać cytat Kaynego Westa.");
            String command = scanner.next().toLowerCase();
            if(Objects.equals(command, "next")){
                findNewQuote().ifPresent(this::saveAndPrint);

            }
            else {
                System.out.println("Niepoprawna komenda. Spróbuj ponownie. ");
            }

        }
        quoteDao.deleteAllQuotes();
        System.out.println("Koniec na dziś");
        System.out.println("Kayne West zakończył prace.");
    }


    private boolean areThereStillQuotes(){
        final int MAX_QUOTES = 122;
        return quoteDao.countQuotes() != MAX_QUOTES;
    }


    private void saveAndPrint(Quote quote){
        quoteDao.save(quote);
        System.out.println(quote.getQuote());
    }

    private Optional<Quote> findNewQuote(){
        System.out.println("Szukanie cytatu...");
        while (true){
        Optional<Quote> optionalQuote = quoteReceiver.getQuote();
            if(optionalQuote.isPresent()){
                Quote quote = optionalQuote.get();
                if(quoteDao.findByQuote(quote.getQuote()).isEmpty()){
                    return optionalQuote;
                }
            }
        }

    }





}
