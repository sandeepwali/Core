package com.solumesl.aims.saas.adapter.processor.factory;

import java.util.HashMap;
import java.util.Map;

import com.solumesl.aims.saas.adapter.processor.Processor;
import com.solumesl.aims.saas.adapter.util.SolumSaasConfigUtil;

public class ProcessorFactory {
    private static final Map<String, Class<? extends Processor>> instances = new HashMap<>();
    

    public static void register(String clientName, Class<? extends Processor> instance) {
        if (clientName != null && instance != null) {
            instances.put(clientName, instance);
        }
    }
    public static void register(Enum clientName, Class<? extends Processor> instance) {
        if (clientName != null && instance != null) {
            instances.put(clientName.toString(), instance);
        }
    }
    public static Processor getInstance(String clientName) {
        if (instances.containsKey(clientName)) {
            return SolumSaasConfigUtil.getBean(instances.get(clientName));
        }
        return null;
    }

}
