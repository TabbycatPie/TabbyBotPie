package work.caliburn;

import com.github.plexpt.chatgpt.Chatbot;

import java.util.Map;

public class ChatGptApi {

    private final String sessionToken = "" ;
    private final String cf_clearance = "";
    private final String user_agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";

    private Chatbot chatbot;

    public ChatGptApi(){
        chatbot = new Chatbot(sessionToken,cf_clearance,user_agent);
    }
    public String chat(String question){
        Map<String, Object> chatResponse = this.chatbot.getChatResponse(question);
        return String.valueOf(chatResponse.get("message"));
    }

    public void test(){
        Chatbot chatbot = new Chatbot(sessionToken,cf_clearance,user_agent);
        Map<String, Object> chatResponse = chatbot.getChatResponse("你好");
        System.out.println(chatResponse.get("message"));
    }
}
