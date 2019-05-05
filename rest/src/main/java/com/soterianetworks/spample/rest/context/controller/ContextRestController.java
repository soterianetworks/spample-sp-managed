package com.soterianetworks.spample.rest.context.controller;

import com.soterianetworks.spample.rest.context.support.ContextRestSupport;
import com.soterianetworks.spample.rest.context.view.UserProfileView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "/current", description = "REST end-points to get current context.")
@RestController
@RequestMapping("/current")
public class ContextRestController {

    @Autowired
    private ContextRestSupport contextRestSupport;

    @ApiOperation(value = "Exchange current benity.",
            httpMethod = "PUT",
            response = UserProfileView.class)
    @PutMapping(value = {"/exchange-benity/{benityId}"})
    @ResponseStatus(HttpStatus.OK)
    public UserProfileView exchangeCurrentBenity(@RequestHeader(value = "ACTOR_USERNAME") String username,
                                                 @PathVariable String benityId) {
        return contextRestSupport.exchangeCurrentBenity(username, benityId);
    }

    @ApiOperation(value = "Get current user profile",
            httpMethod = "GET",
            response = UserProfileView.class)
    @RequestMapping(value = {"/user-profile"}, method = RequestMethod.GET)
    public UserProfileView getUserProfile(@RequestHeader(value = "ACTOR_USERNAME") String username) {
        return contextRestSupport.getUserProfile(username);
    }

}
