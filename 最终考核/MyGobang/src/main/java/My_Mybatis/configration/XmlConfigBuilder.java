package My_Mybatis.configration;

import My_Mybatis.io.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 解析MyBatis的核心配置文件mybatis-config.xml
 */
public class XmlConfigBuilder {

    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public XmlConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析MyBatis-config配置文件
     * @return
     */
    public Configuration parseMyBatisXConfig(InputStream inputStream) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        //根节点 configuration 节点
        Element rootElement = document.getRootElement();
        List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element property : propertyList) {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            properties.setProperty(name,value);
        }
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");

            InputStream resourceAsStream = Resource.getResourceAsStream(resource);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }
}
















