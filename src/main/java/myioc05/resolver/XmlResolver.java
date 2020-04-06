package myioc05.resolver;

import myioc05.PropertyValue;
import myioc05.PropertyValues;
import myioc05.BeanDefinition;
import myioc05.io.XmlResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
 * xml文档解析器，将xml文档解析成deanDefinition
 * */
public class XmlResolver implements Resolver {
    private XmlResource xmlResource;
    private String scope = "singleton";
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public XmlResolver(String location) {
        this.xmlResource = new XmlResource(location);
    }

    public XmlResolver(String location, String scope) {
        this.xmlResource = new XmlResource(location);
        this.scope = scope;
    }

    @Override
    public Map<String, BeanDefinition> getRegistry() {
        return beanDefinitionMap;
    }

    @Override
    public void loadBeanDefinitions() {
        InputStream inputStream = xmlResource.getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            registerBeanDefinitions(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void registerBeanDefinitions(Document document) throws Exception {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) throws Exception {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i <= childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                processBeanDefinition((Element) node);
            }
        }
    }

    protected void processBeanDefinition(Element element) throws Exception {
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition(scope);
        beanDefinition.setBeanClass(className);
        beanDefinition.setPropertyValues(new PropertyValues());

        if (element.hasChildNodes()) {
            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i <= childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node instanceof Element) {
                    PropertyValue propertyValue = obtainBeanProperty((Element) node);
                    beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                }
            }
        }
        beanDefinitionMap.put(name, beanDefinition);
    }

    protected PropertyValue obtainBeanProperty(Element element) {
        String name = element.getAttribute("name");
        String value = element.getAttribute("value");
        PropertyValue property = new PropertyValue(name, value);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(property);
        return property;
    }
}
