package zhong;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
/**
 * ʹ��URLClassLoader��̬����jar�ļ���ʵ��������еĶ���
 * @author Junjie
 *
 */
public class PluginManager {

    private URLClassLoader urlClassLoader;
    //���췽��,�������ñ���෽��ʹ����
    public PluginManager(List<Plugin> plugins) throws MalformedURLException {
        init(plugins);
    }

    private void init(List<Plugin> plugins) throws MalformedURLException {

        int size = plugins.size();
        URL[] urls = new URL[size];

        for (int i = 0; i < size; i++) {

            Plugin plugin = plugins.get(i);
            String filePath = plugin.getJar();
            System.out.println(filePath);
            //����һ��URL����,�����ǰѲ�����ļ�ϵͳ�е�·���洢����
            urls[i] = new URL("file:" + filePath);
        }
        // ��jar�ļ��������, ������һ��URLClassLoader
        urlClassLoader = new URLClassLoader(urls);
    }

    /**
     * ��ò��
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public PluginService getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // ���ʵ��������, �������ʵ��PluginService�ӿ�
        Class<?> clazz = urlClassLoader.loadClass(className);
        Object instance = clazz.newInstance();
        //��ȡPluginService�ľ������
        return (PluginService) instance;
    }
}