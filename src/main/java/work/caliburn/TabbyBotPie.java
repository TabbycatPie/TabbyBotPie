package work.caliburn;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;

public final class TabbyBotPie extends JavaPlugin {
    public static final TabbyBotPie INSTANCE = new TabbyBotPie();

    private TabbyBotPie() {
        super(new JvmPluginDescriptionBuilder("work.caliburn.tabbybotpie", "0.1.0")
                .name("TabbyBotPie")
                .author("oscar")
                .build());
    }

    @Override
    public void onEnable() {
        GlobalEventChannel.INSTANCE.registerListenerHost(new Test());
        GlobalEventChannel.INSTANCE.registerListenerHost(new GroupMsgEventListener());
        getLogger().info("Plugin loaded!");
    }
}