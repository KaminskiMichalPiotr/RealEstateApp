package com.kaminski.realestateapp.realestate.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotService {

    @Autowired
    private PlotRepo plotRepo;

    public Optional<Plot> findPlotById(Long id) {
        return plotRepo.findById(id);
    }

    public List<Plot> getPlots() {
        return plotRepo.findAll();
    }

    public Plot updatePlot(Plot plot) {
        return plotRepo.save(plot);
    }

    public Plot savePlot(Plot plot) {
        return plotRepo.save(plot);
    }
}
