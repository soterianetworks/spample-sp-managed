package com.soterianetworks.spample.rest.workshop.controller;

import com.soterianetworks.spample.rest.workshop.param.WorkshopSaveParam;
import com.soterianetworks.spample.rest.workshop.param.WorkshopSearchParam;
import com.soterianetworks.spample.rest.workshop.support.WorkshopRestSupport;
import com.soterianetworks.spample.rest.workshop.view.WorkshopView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "/workshops", description = "REST end-points to manage workshops.")
@RestController
public class WorkshopRestController {

    @Autowired
    private WorkshopRestSupport workshopRestSupport;

    @ApiOperation(value = "Retrieve workshops.",
            httpMethod = "GET",
            responseContainer = "List",
            response = WorkshopView.class)
    @GetMapping({"/workshops"})
    @ResponseStatus(HttpStatus.OK)
    public Page<WorkshopView> listWorkshops(@ModelAttribute WorkshopSearchParam searchParam) {
        return workshopRestSupport.listWorkshops(searchParam);
    }

    @ApiOperation(value = "Get workshop detail.",
            httpMethod = "GET",
            response = WorkshopView.class)
    @GetMapping(value = {"/workshops/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkshopView getWorkshopView(@PathVariable String id) {
        return workshopRestSupport.getWorkshop(id);
    }

    @ApiOperation(value = "Create a workshop", httpMethod = "POST", response = WorkshopView.class)
    @PostMapping("/workshops")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkshopView createWorkshop(@RequestBody @Valid WorkshopSaveParam workshopSaveParam) {
        return workshopRestSupport.createWorkshop(workshopSaveParam);
    }

    @ApiOperation(value = "Update a workshop by the specified id.", httpMethod = "PUT", response = WorkshopView.class)
    @PutMapping(value = {"/workshops/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkshopView updateWorkshop(@PathVariable String id,
                                       @RequestBody @Valid WorkshopSaveParam workshopSaveParam) {
        return workshopRestSupport.updateWorkshop(id, workshopSaveParam);
    }

    @ApiOperation(value = "Delete the workshop with the specified id.", httpMethod = "DELETE")
    @DeleteMapping(value = {"/workshops/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkshop(@PathVariable String id) {
        workshopRestSupport.deleteWorkshop(id);
    }

}
