package work.caliburn;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public class Test extends SimpleListenerHost {
    private TabbyCatPie FriendPie = new TabbyCatPie();

    @EventHandler
    public ListeningStatus onEvent(FriendMessageEvent event){
        FriendPie.pieHandleEvent(event,TabbyCatPie.MsgEvent.FRIEND_EVENT);
        return ListeningStatus.LISTENING;
    }
}
