# sunbot-discord
Plugin to turn your Suneidesis Chatbot into a Discord Bot

## How to use it

```
LanguageBox box = new MyLanguageBox();
Instance bot = new Instance("Precious Bot");
bot.setLanguageBox(box);

String token = "MY_TOKEN;

Discord discord = new Discord(token);
discord.addInstance(bot);
```