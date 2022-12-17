package work.caliburn.RasaApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

public class SimpleRasaClient {
    private String clientName = "";
    private String serverURL = "http://localhost:5005/webhooks/rest/webhook";


    public SimpleRasaClient(String clientName) {
        this.clientName = clientName;
    }
    public String getClientName() {
        return clientName;
    }
    public String simpleChat(String msg,String sender){
        String answer = "";
        ObjectMapper objectMapper = new ObjectMapper();
        String json_to_send = "";
        try {
            json_to_send = objectMapper.writeValueAsString(new SimpleRasaDialog.SimpleMsgSend(sender, msg));
        }catch (Exception e){
            return  "出错了，要发送给服务器的Json生成失败了" + e.toString();
        }
        //调用服务器喵
        String response_json = doPost(json_to_send);
        //试着解析服务器返回的对话喵
        try{
            SimpleRasaDialog.SimpleMsgReceive responses[] = objectMapper.readValue(response_json,SimpleRasaDialog.SimpleMsgReceive[].class);
            for(SimpleRasaDialog.SimpleMsgReceive response : responses) {
                if ("HttpError".equals(response.getRecipient_id())) {
                    return "出错了,服务器出问题了 " + response.getText();
                } else {
                    //有回复
                    answer = response.getText();
                }
            }
        }catch (Exception e){
            return  "出错了，从服务器发过来的Json解析额失败了" + e.toString();
        }
        return answer;
    }
    private String doPost(String body){
        OkHttpClient client = new OkHttpClient();
        RequestBody req_body = RequestBody.create(body,null);
        Request req = new Request.Builder().url(serverURL).post(req_body).build();
        Response rep;
        String json_rep = "";
        try {
            rep = client.newCall(req).execute();
            json_rep = rep.body().string();
        }catch (Exception e){
            //出错了喵，返回错误的json
            return "[{\"recipient_id\": \"HttpError\", \"text\": \""+ e.toString()+"\"}]";
        }
        if(rep.code() != 200){
            return "[{\"recipient_id\": \"HttpError\", \"text\": \"服务器错误代码:" +String.valueOf(rep.code())+"\"}]";
        }
        return json_rep;
    }
}
