package com.harium.suneidesis.chat.discord;

import com.harium.suneidesis.instance.Instance;
import com.harium.suneidesis.knowledge.linguistic.core.box.Chatbox;
import com.harium.suneidesis.output.Output;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Discord implements Chatbox {

    private JDA jda;

    public Discord(String token) throws LoginException {
        jda = new JDABuilder(token).build();
    }

    public void addInstance(Instance instance) {
        jda.addEventListener(new MessageListener(instance));
    }

    private class MessageListener extends ListenerAdapter {
        private Instance instance;

        public MessageListener(Instance instance) {
            this.instance = instance;
        }

        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            if (event.getAuthor().getId().equals(jda.getSelfUser().getId())) {
                return;
            }

            if (event.isFromType(ChannelType.TEXT)) {
                String message = event.getMessage().getContentDisplay();
                instance.input(message, new DiscordOutput(event.getChannel()));
            } else if (event.isFromType(ChannelType.PRIVATE)) {
                String message = event.getMessage().getContentDisplay();
                instance.input(message, new DiscordOutput(event.getPrivateChannel()));
            } else {
                System.out.printf("%s: %s\n", event.getAuthor().getName(),
                        event.getMessage().getContentDisplay());
            }
        }
    }

    class DiscordOutput implements Output {
        private MessageChannel channel;

        public DiscordOutput(MessageChannel channel) {
            this.channel = channel;
        }

        @Override
        public void print(String sentence) {
            channel.sendMessage(sentence).queue();
        }
    }
}
