package com.sos.auth.shiro;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.ldap.JndiLdapRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class SOSLdapAuthorizingRealm extends JndiLdapRealm {

    private static final String DEFAULT_USER_NAME_ATTRIBUTE = "CN";
    private static final String DEFAULT_GROUP_NAME_ATTRIBUTE = "memberOf";
    private SOSLdapAuthorizing authorizing;
    private String searchBase;
    private Map<String, String> groupRolesMap;
    private Map<String, String> permissions;
    private String groupNameAttribute;
    private String userNameAttribute;

    private String userSearchFilter;
    private AuthenticationToken authcToken;

    public boolean supports(AuthenticationToken token) {
        setAuthorizing(new SOSLdapAuthorizing());
        return true;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authzInfo = null;
        if (authorizing != null) {

            authorizing.setAuthcToken(authcToken);
            authorizing.setSosLdapAuthorizingRealm(this);

            authzInfo = authorizing.setRoles(authzInfo, principalCollection);
        }
        return authzInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        this.authcToken = authcToken;

        return super.doGetAuthenticationInfo(authcToken);
    }

    public void setAuthorizing(SOSLdapAuthorizing authorizing) {
        this.authorizing = authorizing;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    public void setGroupRolesMap(Map<String, String> groupRolesMap) {
        this.groupRolesMap = groupRolesMap;
    }

    public String getSearchBase() {
        return searchBase;
    }

    public Map<String, String> getGroupRolesMap() {
        return groupRolesMap;
    }

    public String getGroupNameAttribute() {
        if (groupNameAttribute == null) {
            return DEFAULT_GROUP_NAME_ATTRIBUTE;
        } else {
            return groupNameAttribute;
        }
    }

    public void setGroupNameAttribute(String groupNameAttribute) {
        this.groupNameAttribute = groupNameAttribute;
    }

    public String getUserNameAttribute() {
        if (userNameAttribute == null) {
            return DEFAULT_USER_NAME_ATTRIBUTE;
        } else {
            return userNameAttribute;
        }
    }

    public void setUserNameAttribute(String userNameAttribute) {
        this.userNameAttribute = userNameAttribute;
    }

    public Map<String, String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, String> permissions) {
        this.permissions = permissions;
    }

    public Object getLdapPrincipal(AuthenticationToken token) {
        return super.getLdapPrincipal(token);
    }

    public String getUserSearchFilter() {
        return userSearchFilter;
    }

    public void setUserSearchFilter(String userSearchFilter) {
        this.userSearchFilter = userSearchFilter;
    }

}