package zhong;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            List<Plugin> pluginList = XMLParser.getPluginList();

            PluginManager pluginManager = new PluginManager(pluginList);

            for (Plugin plugin : pluginList) {
                PluginService pluginService = pluginManager.getInstance(plugin.getClassName());
                System.out.println("��ʼִ��[" + plugin.getName() + "]���...");
                // ���ò��
                pluginService.service();
                System.out.println("[" + plugin.getName() + "]���ִ�����");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
