# sunbot-discord
Plugin to turn your Suneidesis Chatbot into a Discord Bot

## Maven
```xml
<dependency>
    <groupId>com.harium.suneidesis.sunbot</groupId>
    <artifactId>discord</artifactId>
    <version>1.0.3</version>
</dependency>
```

## How to use it

```
LanguageBox box = new MyLanguageBox();
Instance bot = new Instance("Precious Bot");
bot.setLanguageBox(box);

String token = "MY_TOKEN;

Discord discord = new Discord(token);
discord.addInstance(bot);
```
