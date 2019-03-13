package com.harium.suneidesis.chat.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.utils.MiscUtil;

import java.util.FormattableFlags;
import java.util.Formatter;

public class DiscordChannel implements MessageChannel {

    private ChannelType type = ChannelType.UNKNOWN;
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

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        boolean leftJustified = (flags & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY;
        boolean upper = (flags & FormattableFlags.UPPERCASE) == FormattableFlags.UPPERCASE;
        //boolean alt = (flags & FormattableFlags.ALTERNATE) == FormattableFlags.ALTERNATE;
        String out = "#" + (upper ?  getName().toUpperCase(formatter.locale()) : getName());

        MiscUtil.appendTo(formatter, width, precision, leftJustified, out);
    }
}
