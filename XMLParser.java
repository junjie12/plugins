package zhong;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析XML获取所有插件信息（这里用到dom4j）
 * @author Junjie
 *
 */
public class XMLParser {

    public static List<Plugin> getPluginList() throws DocumentException {

        List<Plugin> list = new ArrayList<Plugin>();

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("plugin.xml"));
        //获取配置文件根结点
        Element root = document.getRootElement();
        List<?> plugins = root.elements("plugin");
        for (Object pluginObj : plugins) {
            Element pluginEle = (Element) pluginObj;
            Plugin plugin = new Plugin();
            plugin.setName(pluginEle.elementText("name"));
            plugin.setJar(pluginEle.elementText("jar"));
            plugin.setClassName(pluginEle.elementText("class"));
            list.add(plugin);
        }
        return list;
    }
}
