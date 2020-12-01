package zhong;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
/**
 * 使用URLClassLoader动态加载jar文件，实例化插件中的对象
 * @author Junjie
 *
 */
public class PluginManager {

    private URLClassLoader urlClassLoader;
    //构造方法,作用是让别的类方便使用它
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
            //创建一个URL对象,作用是把插件在文件系统中的路径存储起来
            urls[i] = new URL("file:" + filePath);
        }
        // 将jar文件组成数组, 来创建一个URLClassLoader
        urlClassLoader = new URLClassLoader(urls);
    }

    /**
     * 获得插件
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public PluginService getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // 插件实例化对象, 插件都是实现PluginService接口
        Class<?> clazz = urlClassLoader.loadClass(className);
        Object instance = clazz.newInstance();
        //获取PluginService的具体对象
        return (PluginService) instance;
    }
}