package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {
    @Test
    public void testWordCensor() {
        SocialChannel channel = new TestSpySocialChannel();
        SocialChannel censor = new WordCensor(channel);
        censor.deliverMessage("Microsoft");
        TestSpySocialChannel spy = (TestSpySocialChannel)channel;
        assertEquals("%!$", spy.lastMessagePublished());
    }
    @Test
    public void testDoubleWordCensor() {
        SocialChannel channel = new TestSpySocialChannel();
        SocialChannel censor = new WordCensor(channel);
        censor.deliverMessage("SoNySoNyMiCrOsOfT");
        TestSpySocialChannel spy = (TestSpySocialChannel)channel;
        assertEquals("%!$%!$%!$", spy.lastMessagePublished());
    }

    @Test
    public void testWordCensorURLApprender() {
        SocialChannel channel = new TestSpySocialChannel();
        SocialChannel decorated = new URLAppender("http://microsoft.com", new WordCensor(channel));
        decorated.deliverMessage("Microsoft website:");
        TestSpySocialChannel spy = (TestSpySocialChannel)channel;
        assertEquals("%!$ website: http://%!$.com", spy.lastMessagePublished());
    }
    
    @Test
    public void testMessageTruncatorWordCensor() {
        SocialChannel channel = new TestSpySocialChannel();
        SocialChannel decorated = new WordCensor(new MessageTruncator(5, channel));
        decorated.deliverMessage("SonySony");
        TestSpySocialChannel spy = (TestSpySocialChannel)channel;
        assertEquals("%!...", spy.lastMessagePublished());
    }
}
