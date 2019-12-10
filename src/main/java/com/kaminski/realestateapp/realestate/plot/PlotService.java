package com.kaminski.realestateapp.realestate.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotService {

    @Autowired
    private PlotRepository plotRepository;

    public Optional<Plot> findPlotById(Long id){
        return plotRepository.findById(id);
    }

    public List<Plot> getPlots(){
        return plotRepository.findAll();
    }

    public Plot updatePlot(Plot plot){
        return plotRepository.save(plot);
    }

    public Plot savePlot(Plot plot) {
        return plotRepository.save(plot);
    }
}
