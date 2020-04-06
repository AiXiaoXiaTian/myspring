package myioc04;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PropertyValues {
    private Map<String, PropertyValue> propertyValueList = new ConcurrentHashMap<String, PropertyValue>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.put(pv.getName(), pv);
    }

    public Map<String, PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
