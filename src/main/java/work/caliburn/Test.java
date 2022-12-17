package work.caliburn;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import work.caliburn.RasaApi.SimpleRasaClient;

public class Test extends SimpleListenerHost {
    private TabbyCatPie FriendPie = new TabbyCatPie();

    @EventHandler
    public ListeningStatus onEvent(FriendMessageEvent event){
        //FriendPie.pieHandleEvent(event, TabbyCatPie.MsgEvent.FRIEND_EVENT);
        event.getSender().sendMessage(new SimpleRasaClient(event.getSenderName()).simpleChat(event.getMessage().contentToString(),event.getSenderName()));
        return ListeningStatus.LISTENING;
    }
}
