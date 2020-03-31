# sunbot-discord
Plugin to turn your Suneidesis Chatbot into a Discord Bot

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis.sunbot/discord/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis.sunbot/discord/)

## How to use it

```
    // Use your own box
    ChatBox bot = new EchoBox();
    
    String token = "MY_TOKEN";
    
    Discord discord = new Discord(token);
    discord.addInstance(bot);
```
