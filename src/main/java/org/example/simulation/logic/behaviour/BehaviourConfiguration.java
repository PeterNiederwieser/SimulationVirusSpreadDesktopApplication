package org.example.simulation.logic.behaviour;

import org.example.simulation.data.Context;
import org.example.simulation.logic.utils.MapFieldUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class BehaviourConfiguration {
    private final Context context;
    private final MapFieldUtils mapFieldUtils;

    public BehaviourConfiguration(Context context, MapFieldUtils mapFieldUtils) {
        this.context = context;
        this.mapFieldUtils = mapFieldUtils;
    }

    @Bean
    @Order(1)
    Behaviour getStroll() {
        return new Stroll(context, mapFieldUtils);
    }
    @Bean
    @Order(2)
    Behaviour getRest() {
        return new Rest();
    }
}
