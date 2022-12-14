package work.caliburn;

import net.dreamlu.mica.core.result.R;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TabbyCatPie {
    private ArrayList<String> my_valid_names;
    private final String cmd_split[] = {" ",",","，"}; //命令分隔符呢，比如 $my_valid_name,打开帮助菜单
    private final String meow[] = {"喵","喵！","喵～","喵喵!","",",喵～～"};
    private final String cmd_and_exp[][] = {
            {"帮助菜单" ,"打印这个菜单"},
            {"当前时间" ,"查看当前时间"}
    };

    public enum MsgEvent{
        FRIEND_EVENT,
        GROUP_EVENT
    }

    public TabbyCatPie() {
        my_valid_names = new ArrayList<>();
        my_valid_names.add("派派");
        my_valid_names.add("PiePie");
        my_valid_names.add("派总");
    }

    //随机返回我的名字喵
    private String getMyName(){
        Random r = new Random();
        return my_valid_names.get(r.nextInt(my_valid_names.size()-1));
    }

    //返回不同的 喵 好加载橘子后面，使得活灵活现喵
    private String getMeow(){
        Random r = new Random();
        return meow[r.nextInt(meow.length-1)];
    }
    private String CallMe(String msg,MsgEvent type){
        String call_me_response[] = {"在的","干嘛","有什么事","有何贵干","嗯呢","嗯嗯","我在",
                                        "说吧","我在呢","你好","叫"+getMyName()+"干什么","我在听","hi","hello","嗨害嗨喵，我在呢",
                                            "在呢","干什么","你说吧,"};
        String ret ="";
        boolean call_me = false;
        if(type == MsgEvent.GROUP_EVENT){
            //群里面叫我的话，使用我的名字喵
            for(String name : my_valid_names){
                if(name.equals(msg)){
                    call_me = true;
                    break;
                }
            }
        }
        else if(type == MsgEvent.FRIEND_EVENT){
            //私聊的话满足条件就可以了
            //叫名字可以回应喵
            for(String name : my_valid_names){
                if(name.equals(msg)){
                    call_me = true;
                    break;
                }
            }
            //直接问在么也可以
            if("hello".equals(msg) || "hi".equals(msg)||"你好".equals(msg)|| (msg.length()<=4 && msg.contains("在")) ){
                call_me = true;
            }
            if(msg.length() ==1)
                call_me = true;
        }else{
            ret = "没活了喵，无语了喵";
        }
        if(call_me){
            Random r = new Random();
            ret = call_me_response[r.nextInt(call_me_response.length-1)] + getMeow();
        }
        return ret;
    }
    private String Command(String msg,MsgEvent type){
        String ret = "";
        boolean is_command = false;
        //如果在群里的话需要叫我的名字喵
        //判断有没有叫我捏,如果名字出现在第一个字 就是命令模式喵
        boolean call_flag = false;
        for (String name : my_valid_names) {
            if (msg.indexOf(name) == 0) {
                //发现在叫我的名字喵，跳出喵
                call_flag = true;
                is_command = true;
                break;
            }
        }
        //有名字的话就吧名字替换成""，相当于是去掉了喵
        if (call_flag) {
            for (String name : my_valid_names) {
                //删掉之后就是命令了喵
                msg = msg.replace(name, "");
            }
        }
        //判断第一位有没有分隔符，有的话赶紧去掉喵
        for (int x = 0; x < cmd_split.length; x++) {
            if (msg.indexOf(cmd_split[x]) == 0) {
                msg = msg.substring(1);
            }
        }
        if(is_command) {
            //命令处理喵
            if (msg.contains("帮助菜单") || msg.toLowerCase().contains("help menu") || msg.contains("帮助")) {
                ret += getMyName() + "现在能明白的咒语有：\n";
                for (int x = 0; x < cmd_and_exp.length; x++) {
                    String cmd_exp = "\"" + cmd_and_exp[x][0] + "\"" + ",这条咒语能够" + cmd_and_exp[x][1] + getMeow() + ((x == cmd_and_exp.length - 1) ? "" : "\n");
                    ret += cmd_exp;
                }
            }
            if (msg.contains("现在几点") || msg.contains("当前时间")) {
                if (ret.length() > 0) {
                    ret += "然后,";
                }
                Date now = new Date();
                SimpleDateFormat f = new SimpleDateFormat("现在的时间是 " + "yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒," + getMeow());
                ret += (getMyName() + "看了下⌚️，发现" + f.format(now));
            }
        }
        return ret;
    }

    public void pieHandleEvent(MessageEvent event,MsgEvent type){
        String msg = event.getMessage().contentToString();
        String ret = ""; //这里是回复消息喵
        User sender  = event.getSender();//谁发的消息喵
        //处理命令和消息喵
        String cm_rsp = CallMe(msg,type);
        String cmd_rsp = Command(msg,type);
        ret = cmd_rsp + cm_rsp;
        //判断发不发消息喵
        boolean send_msg = false;
        if((!"".equals(cmd_rsp) || (!"".equals(cm_rsp))))
            send_msg = true;
        if(type == MsgEvent.FRIEND_EVENT){
            FriendMessageEvent fme = (FriendMessageEvent) event;
            if(send_msg)
                fme.getSender().sendMessage(ret);
        }else if(type == MsgEvent.GROUP_EVENT){
            //群的话就@在发消息喵
            GroupMessageEvent gme = (GroupMessageEvent) event;
            if(send_msg){
               ret =  "@" + gme.getSenderName() + " " + ret;
               gme.getGroup().sendMessage(ret);
            }
        }else{
            //这种消息我还不会处理喵
        }


    }
}
