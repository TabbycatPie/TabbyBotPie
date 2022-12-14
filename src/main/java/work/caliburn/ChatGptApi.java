package work.caliburn;

import com.github.plexpt.chatgpt.Chatbot;

import java.util.Map;

public class ChatGptApi {

    private final String token = "？？？？？？";

    private final String cf_clearance = "NHnShND.ET9u71gdw58xsX7bWfV1n？？？？？？23842.14ccae96-160";
    private final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";

    private Chatbot chatbot = new Chatbot(token,cf_clearance,userAgent);


    public String chat(String question){
        Map<String, Object> chatResponse = this.chatbot.getChatResponse(question);
        return String.valueOf(chatResponse.get("message"));
    }
}
