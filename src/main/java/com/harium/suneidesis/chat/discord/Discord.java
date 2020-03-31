package com.harium.suneidesis.chat.discord;


import com.harium.suneidesis.chat.box.BoxHandler;
import com.harium.suneidesis.chat.box.ChatBox;
import com.harium.suneidesis.chat.input.InputContext;
import com.harium.suneidesis.chat.output.Output;
import com.harium.suneidesis.chat.output.OutputContext;
import java.io.File;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Discord implements BoxHandler {

    private JDA jda;

    public Discord(String token) throws LoginException {
        jda = new JDABuilder(token).build();
    }

    public void addBox(ChatBox instance) {
        jda.addEventListener(new MessageListener(instance));
    }

    @Override
    public void sendMessage(String channel, String message) {
        MessageChannel messageChannel = new DiscordChannel(jda, channel);
        messageChannel.sendMessage(message).queue();
    }

    private class MessageListener extends ListenerAdapter {
        private ChatBox instance;

        public MessageListener(ChatBox instance) {
            this.instance = instance;
        }

        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            if (event.getAuthor().getId().equals(jda.getSelfUser().getId())) {
                return;
            }

            InputContext context = buildContext(event);

            if (event.isFromType(ChannelType.TEXT)) {
                instance.input(context, new DiscordOutput(event.getChannel()));
            } else if (event.isFromType(ChannelType.PRIVATE)) {
                instance.input(context, new DiscordOutput(event.getPrivateChannel()));
            } else {
                System.out.printf("%s: %s\n", event.getAuthor().getName(),
                        event.getMessage().getContentDisplay());
            }
        }

        private InputContext buildContext(MessageReceivedEvent event) {
            String message = event.getMessage().getContentDisplay();

            InputContext context = new InputContext();
            context.setSentence(message);
            context.getProperties().put(InputContext.USER_ID, event.getAuthor().getId());
            context.getProperties().put(InputContext.USER_USERNAME, event.getAuthor().getId());
            context.getProperties().put(InputContext.USER_NAME, event.getAuthor().getName());
            context.getProperties().put(InputContext.CHANNEL_ID, event.getChannel().getId());
            context.getProperties().put(InputContext.CHANNEL_NAME, event.getChannel().getName());

            return context;
        }
    }

    private class DiscordOutput implements Output {
        private MessageChannel channel;

        public DiscordOutput(MessageChannel channel) {
            this.channel = channel;
        }

        @Override
        public void print(String sentence, OutputContext context) {
            channel.sendMessage(sentence).queue();
        }

        @Override
        public void produceFile(String path, String description) {
            File file = new File(path);
            if (file.exists()) {
                channel.sendFile(file, description);
            }
        }
    }
}
