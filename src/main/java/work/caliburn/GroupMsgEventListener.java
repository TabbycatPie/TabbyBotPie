package work.caliburn;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class GroupMsgEventListener extends SimpleListenerHost {
    @EventHandler
    public ListeningStatus onEvent(GroupMessageEvent event){
        String msg = event.getMessage().contentToString();
        if(msg.contains("派派")){
            String caller = event.getSenderName();
            event.getGroup().sendMessage(caller + "你在叫我吗？喵～");
        }
        return ListeningStatus.LISTENING;
    }
}
