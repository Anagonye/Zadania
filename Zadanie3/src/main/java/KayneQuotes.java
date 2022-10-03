import apiconnector.QuoteReceiverImpl;
import command.CommandListener;
import dao.ConnectionProvider;
import dao.QuoteDaoImpl;
import json.JsonReaderImpl;

public class KayneQuotes {

    public static void main(String[] args) {

        QuoteReceiverImpl quoteReceiver = new QuoteReceiverImpl(new JsonReaderImpl(), "https://api.kanye.rest/");
        QuoteDaoImpl quoteDao = new QuoteDaoImpl(new ConnectionProvider("jdbc:postgresql://localhost:5432/quotes","postgres", "toor"));
        CommandListener commandListener = new CommandListener(quoteReceiver, quoteDao);
        commandListener.start();



    }


}
