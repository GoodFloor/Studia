package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import java.util.ArrayList;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

public class WordCensor extends SocialChannelDecorator {

    private ArrayList<String> restrictedWords = new ArrayList<String>();
    private String censorship = "%!$";

    public WordCensor(SocialChannel decoratee) {
        setDecoratedSocialChannel(decoratee);

        restrictedWords.add("MICROSOFT");
        restrictedWords.add("SONY");
    }
    @Override
    public void deliverMessage(String message) {
        for (String word : restrictedWords) {
            message = censor(word, message);
        }
        delegate.deliverMessage(message);
    }
    private String censor(String word, String from) {
        StringBuilder result = new StringBuilder();
        result.append(from);
        word = word.toUpperCase();
        from = from.toUpperCase();
        StringBuilder builder = new StringBuilder();
        builder.append(from);
        int wordLength = word.length();
        int firstOccurance = builder.indexOf(word);
        while (firstOccurance != -1) {
            result.replace(firstOccurance, firstOccurance + wordLength, censorship);
            builder.replace(firstOccurance, firstOccurance + wordLength, censorship);
            firstOccurance = builder.indexOf(word);
        }
        return result.toString();
    }

    
}
