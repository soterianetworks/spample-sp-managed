package com.soterianetworks.spample.rest.context.support.impl;

import com.soterianetworks.spample.rest.context.support.ContextRestSupport;
import com.soterianetworks.spample.rest.context.view.DepartmentSimpleView;
import com.soterianetworks.spample.rest.context.view.UserProfileView;
import com.soterianetworks.spase.domain.model.UserProfile;
import com.soterianetworks.spase.repository.BenityRepository;
import com.soterianetworks.spase.service.DepartmentService;
import com.soterianetworks.spase.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 *
 */
@Component
public class ContextRestSupportImpl implements ContextRestSupport {

    @Autowired
    private BenityRepository benityRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public UserProfileView exchangeCurrentBenity(String username, String benityId) {
        userProfileService.exchangeCurrentBenity(username, benityId);
        return getUserProfile(username);
    }

    @Override
    public UserProfileView getUserProfile(String username) {
        UserProfile userDetails = userProfileService.getUserProfile(username);
        UserProfileView view = UserProfileView.from(userDetails);
        view.setDepartments(userDetails.getDepartments()
                                       .stream()
                                       .map(model -> DepartmentSimpleView.from(model))
                                       .collect(Collectors.toList()));
        return view;
    }

}
