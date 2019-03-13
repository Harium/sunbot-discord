package com.harium.suneidesis.chat.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;

public class DiscordChannel implements MessageChannel {

    ChannelType type = ChannelType.UNKNOWN;
    private JDA jda;
    private String channel;

    public DiscordChannel(JDA jda, String channel) {
        this.jda = jda;
        this.channel = channel;
    }

    public DiscordChannel(JDA jda, String channel, ChannelType type) {
        this.jda = jda;
        this.channel = channel;
        this.type = type;
    }

    @Override
    public long getLatestMessageIdLong() {
        return 0;
    }

    @Override
    public boolean hasLatestMessage() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ChannelType getType() {
        return type;
    }

    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public long getIdLong() {
        return Long.parseLong(channel);
    }
}
