package com.kaminski.realestateapp.realestate.plot;

import com.kaminski.realestateapp.announcement.Announcement;
import com.kaminski.realestateapp.announcement.AnnouncementService;
import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.realestate.RealEstateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/plot", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PlotREST {

    @Autowired
    private PlotService plotService;

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<?> getPlotById(@PathVariable Long id) {
        Optional<Plot> plot = plotService.findPlotById(id);
        if (plot.isPresent()) {
            return ResponseEntity.ok().body(plot.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Plot> createPlot(@RequestBody Plot plot) {
        if(plot.getId() == null){
            plot = plotService.savePlot(plot);
        }
        else if (plotService.findPlotById(plot.getId()).isPresent()) {
            plot = plotService.updatePlot(plot);
        } else {
            plot.setId(null);
            plot = plotService.savePlot(plot);
        }
        return ResponseEntity.ok().body(plot);
    }

    @GetMapping("")
    public ResponseEntity<List<Plot>> getPlots() {
        List<Plot> plots = plotService.getPlots();
        return ResponseEntity.ok().body(plots);
    }

    @GetMapping("/type")
    public ResponseEntity<List<PlotType>> getFinishingCondition() {
        return ResponseEntity.ok().body(Arrays.asList(PlotType.values()));
    }

}
