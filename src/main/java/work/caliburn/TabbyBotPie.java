package work.caliburn;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.utils.MiraiLogger;

public final class TabbyBotPie extends JavaPlugin {
    public static final TabbyBotPie INSTANCE = new TabbyBotPie();


    private boolean isEnabled = false;


    private TabbyBotPie() {
        super(new JvmPluginDescriptionBuilder("work.caliburn.tabbybotpie", "0.1.0")
                .name("TabbyBotPie")
                .author("oscar")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        GlobalEventChannel.INSTANCE.registerListenerHost(new Test());
        getLogger().info("Test() Listener registered.");
        GlobalEventChannel.INSTANCE.registerListenerHost(new GroupMsgEventListener());
        getLogger().info("GroupMsgEventListener() Listener registered.");
    }
}