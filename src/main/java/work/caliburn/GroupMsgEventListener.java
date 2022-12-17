package work.caliburn;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import work.caliburn.RasaApi.SimpleRasaClient;

public class GroupMsgEventListener extends SimpleListenerHost {
    private TabbyCatPie groupPie = new TabbyCatPie();

    @EventHandler
    public ListeningStatus onEvent(GroupMessageEvent event){
        groupPie.pieHandleEvent(event,TabbyCatPie.MsgEvent.GROUP_EVENT);
        //event.getGroup().sendMessage(new SimpleRasaClient(event.getSenderName()).simpleChat(event.getMessage().contentToString(),event.getSenderName()));
        return ListeningStatus.LISTENING;
    }
}
