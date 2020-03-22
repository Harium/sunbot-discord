package examples;

import com.harium.suneidesis.chat.box.EchoBox;
import com.harium.suneidesis.chat.box.LanguageBox;
import com.harium.suneidesis.chat.discord.Discord;
import com.harium.suneidesis.chat.instance.Instance;
import javax.security.auth.login.LoginException;

public class BasicExample {

  public static void main(String[] args) throws LoginException {
    // Use your own box
    LanguageBox box = new EchoBox();
    Instance bot = new Instance("Precious Bot");
    bot.setLanguageBox(box);

    String token = "MY_TOKEN";

    Discord discord = new Discord(token);
    discord.addInstance(bot);
  }

}
