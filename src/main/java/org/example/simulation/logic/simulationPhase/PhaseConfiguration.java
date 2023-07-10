package org.example.simulation.logic.simulationPhase;

import org.example.simulation.logic.behaviour.Behaviour;
import org.example.simulation.logic.io.ChartLines;
import org.example.simulation.logic.io.MapDisplayer;
import org.example.simulation.logic.io.PieChart;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class PhaseConfiguration {
    private final List<Behaviour> behaviours;
    private final PhaseUtils phaseUtils;
    private final ChartLines chartLines;
    private final PieChart pieChart;
    private final MapDisplayer mapDisplayer;

    public PhaseConfiguration(List<Behaviour> behaviours, PhaseUtils phaseUtils, ChartLines chartLines, PieChart pieChart, MapDisplayer mapDisplayer) {
        this.behaviours = behaviours;
        this.phaseUtils = phaseUtils;
        this.chartLines = chartLines;
        this.pieChart = pieChart;
        this.mapDisplayer = mapDisplayer;
    }

    @Bean
    @Order(1)
    Phase getAnimalBehaviour() {
        return new AnimalBehaviour(behaviours);
    }

    @Bean
    @Order(2)
    Phase getRecovering() {
        return new Recovering(phaseUtils);
    }

    @Bean
    @Order(3)
    Phase getSevereIllness() {
        return new SevereIllness(phaseUtils);
    }

    @Bean
    @Order(4)
    Phase getDying() {
        return new Dying(phaseUtils);
    }

    @Bean
    @Order(5)
    Phase getInfectionSpread() {
        return new InfectionSpread(phaseUtils);
    }

    @Bean
    @Order(6)
    Phase getAnalysis() {
        return new Analysis();
    }

    @Bean
    @Order(7)
    Phase getDiagrams() {
        return new Diagrams(chartLines, pieChart);
    }

    @Bean
    @Order(8)
    Phase getGraphic() {
        return new Graphic(mapDisplayer);
    }

    @Bean
    @Order(7)
    Phase getStepIncrement() {
        return new StepIncrement();
    }

}
