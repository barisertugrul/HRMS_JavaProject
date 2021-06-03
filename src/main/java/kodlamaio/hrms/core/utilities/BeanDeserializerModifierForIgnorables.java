package kodlamaio.hrms.core.utilities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class BeanDeserializerModifierForIgnorables extends BeanDeserializerModifier {

    private java.lang.Class<?> type;
    private List<String> ignorables;

    public BeanDeserializerModifierForIgnorables(java.lang.Class clazz, String... properties) {
        ignorables = new ArrayList<>();
        for(String property : properties) {
            ignorables.add(property);
        }
        this.type = clazz;
    }

    @Override
    public BeanDeserializerBuilder updateBuilder(
            DeserializationConfig config, BeanDescription beanDesc,
            BeanDeserializerBuilder builder) {
        if(!type.equals(beanDesc.getBeanClass())) {
            return builder;
        }

        for(String ignorable : ignorables) {
            builder.addIgnorable(ignorable);                
        }

        return builder;
    }

    @Override
    public List<BeanPropertyDefinition> updateProperties(
            DeserializationConfig config, BeanDescription beanDesc,
            List<BeanPropertyDefinition> propDefs) {
        if(!type.equals(beanDesc.getBeanClass())) {
            return propDefs;
        }

        List<BeanPropertyDefinition> newPropDefs = new ArrayList<>();
        for(BeanPropertyDefinition propDef : propDefs) {
            if(!ignorables.contains(propDef.getName())) {
                newPropDefs.add(propDef);
            }
        }
        return newPropDefs;
    }
}
