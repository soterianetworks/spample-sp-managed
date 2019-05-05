package com.soterianetworks.spample.rest.context.view;

import com.soterianetworks.spample.rest.common.AbstractBaseView;
import com.soterianetworks.spase.domain.model.UserProfile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel(value = "UI view for user profile.")
public class UserProfileView extends AbstractBaseView {

    public static UserProfileView from(UserProfile user) {
        if (user == null) {
            return null;
        }
        UserProfileView result = new UserProfileView();
        BeanUtils.copyProperties(user, result, "departments", "benities", "currentBenity");
        result.setCurrentBenity(BenitySimpleView.from(user.getCurrentBenity()));
        if (user.getBenities() != null && user.getBenities().size() > 0) {
            result.setBenities(user.getBenities().stream().map(BenitySimpleView::from).collect(Collectors.toList()));
        }
        return result;
    }

    @ApiModelProperty
    private String userName;
    @ApiModelProperty
    private String email;
    @ApiModelProperty
    private String firstName;
    @ApiModelProperty
    private String lastName;
    @ApiModelProperty
    private String middleName;
    @ApiModelProperty
    private String country;
    @ApiModelProperty
    private String language;
    @ApiModelProperty
    private String expirationDate;
    @ApiModelProperty
    private Boolean enabled;

    @ApiModelProperty
    private BenitySimpleView currentBenity;
    @ApiModelProperty
    private Set<String> groups = new HashSet<>();
    @ApiModelProperty
    private Set<String> scopes = new HashSet<>();
    @ApiModelProperty
    private Set<String> permissions = new HashSet<>();
    @ApiModelProperty
    private List<BenitySimpleView> benities = new ArrayList<>();
    @ApiModelProperty
    private List<DepartmentSimpleView> departments = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BenitySimpleView getCurrentBenity() {
        return currentBenity;
    }

    public void setCurrentBenity(BenitySimpleView currentBenity) {
        this.currentBenity = currentBenity;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public List<BenitySimpleView> getBenities() {
        return benities;
    }

    public void setBenities(List<BenitySimpleView> benities) {
        this.benities = benities;
    }

    public List<DepartmentSimpleView> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentSimpleView> departments) {
        this.departments = departments;
    }
}
