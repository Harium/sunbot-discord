package examples;

import com.harium.suneidesis.chat.box.ChatBox;
import com.harium.suneidesis.chat.box.EchoBox;
import com.harium.suneidesis.chat.discord.Discord;
import javax.security.auth.login.LoginException;

public class BasicExample {

  public static void main(String[] args) throws LoginException {
    // Use your own box
    ChatBox bot = new EchoBox();

    String token = "MY_TOKEN";

    Discord discord = new Discord(token);
    discord.addBox(bot);
  }

}
