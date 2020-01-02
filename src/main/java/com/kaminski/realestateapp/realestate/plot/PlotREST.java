package com.kaminski.realestateapp.realestate.plot;

import com.kaminski.realestateapp.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/plot", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlotREST {

    @Autowired
    private PlotService plotService;

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/{id}")
    public ResponseEntity<PlotDTO> getPlotById(@PathVariable Long id) {
        Optional<Plot> plot = plotService.findPlotById(id);
        if (plot.isPresent()) {
            return ResponseEntity.ok().body(PlotDTO.adaptFrom(plot.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<Plot> updatePlot(@RequestBody Plot plot) {
        if (plot.getId() == null) {
            return ResponseEntity.notFound().build();
        } else if (plotService.findPlotById(plot.getId()).isPresent()) {
            plot = plotService.updatePlot(plot);
            return ResponseEntity.ok().body(plot);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/type")
    public ResponseEntity<List<PlotType>> getFinishingCondition() {
        return ResponseEntity.ok().body(Arrays.asList(PlotType.values()));
    }

}
