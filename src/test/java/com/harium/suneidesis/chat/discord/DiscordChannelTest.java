package com.harium.suneidesis.chat.discord;

import net.dv8tion.jda.core.JDA;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class DiscordChannelTest {

    @Test
    public void testInit() {
        JDA jda = mock(JDA.class);
        DiscordChannel channel = new DiscordChannel(jda, "123");
        Assert.assertEquals(123L, channel.getIdLong());
    }

}
