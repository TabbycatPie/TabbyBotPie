package work.caliburn;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public class Test extends SimpleListenerHost {
    @EventHandler
    public ListeningStatus onEvent(FriendMessageEvent event){
        String msg = event.getMessage().contentToString();
        if(msg.contains("派派")){
            event.getSender().sendMessage("叫我吗？喵～");
        }
        return ListeningStatus.LISTENING;
    }
}
