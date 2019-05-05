package com.soterianetworks.spample.rest.context.support;

import com.soterianetworks.spample.rest.context.view.UserProfileView;

public interface ContextRestSupport {

    UserProfileView exchangeCurrentBenity(String username, String benityId);

    UserProfileView getUserProfile(String username);

}
