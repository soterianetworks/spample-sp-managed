package com.soterianetworks.spample.rest.mock;

import com.soterianetworks.spample.rest.context.support.ContextRestSupport;
import com.soterianetworks.spample.rest.context.view.UserProfileView;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ContextRestSupportImpl implements ContextRestSupport {

    @Override
    public UserProfileView exchangeCurrentBenity(String username, String benityId) {
        return null;
    }

    @Override
    public UserProfileView getUserProfile(String username) {
        return null;
    }

}
