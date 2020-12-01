package zhong;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            List<Plugin> pluginList = XMLParser.getPluginList();

            PluginManager pluginManager = new PluginManager(pluginList);

            for (Plugin plugin : pluginList) {
                PluginService pluginService = pluginManager.getInstance(plugin.getClassName());
                System.out.println("开始执行[" + plugin.getName() + "]插件...");
                // 调用插件
                pluginService.service();
                System.out.println("[" + plugin.getName() + "]插件执行完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
