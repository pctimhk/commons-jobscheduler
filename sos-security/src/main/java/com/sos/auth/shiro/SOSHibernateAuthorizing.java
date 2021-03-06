package com.sos.auth.shiro;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import com.sos.auth.shiro.db.SOSUser2RoleDBItem;
import com.sos.auth.shiro.db.SOSUserDBItem;
import com.sos.auth.shiro.db.SOSUserDBLayer;
import com.sos.auth.shiro.db.SOSUserPermissionDBItem;

public class SOSHibernateAuthorizing implements ISOSAuthorizing {

    private static final Logger LOGGER = Logger.getLogger(SOSHibernateAuthorizing.class);
    private SimpleAuthorizationInfo authorizationInfo = null;
    private String configurationFileName = null;

    public void setConfigurationFileName(String filename) {
        this.configurationFileName = filename;
    }

    @Override
    public SimpleAuthorizationInfo setRoles(SimpleAuthorizationInfo authorizationInfo_, PrincipalCollection principalCollection)  {
        if (authorizationInfo_ == null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo = simpleAuthorizationInfo;
        } else {
            authorizationInfo = authorizationInfo_;
        }
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SOSUserDBLayer sosUserDBLayer;
        try {
            sosUserDBLayer = new SOSUserDBLayer(configurationFileName);
        } catch (Exception e1) {
             e1.printStackTrace();
             return null;
        }
        sosUserDBLayer.getFilter().setUserName(userName);
        List<SOSUserDBItem> sosUserList = null;
        try {
            sosUserList = sosUserDBLayer.getSOSUserList(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        for (SOSUserDBItem sosUserDBItem : sosUserList) {
            for (SOSUser2RoleDBItem sosUser2RoleDBItem : sosUserDBItem.getSOSUserRoleDBItems()) {
                if (sosUser2RoleDBItem.getSosUserRoleDBItem() != null) {
                    System.out.println(sosUser2RoleDBItem.getSosUserRoleDBItem().getSosUserRole());
                    authorizationInfo.addRole(sosUser2RoleDBItem.getSosUserRoleDBItem().getSosUserRole());
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    public SimpleAuthorizationInfo setPermissions(SimpleAuthorizationInfo authorizationInfo_, PrincipalCollection principalCollection) {
        if (authorizationInfo == null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo = simpleAuthorizationInfo;
        } else {
            authorizationInfo = authorizationInfo_;
        }
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SOSUserDBLayer sosUserDBLayer;
        try {
            sosUserDBLayer = new SOSUserDBLayer(configurationFileName);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
        sosUserDBLayer.getFilter().setUserName(userName);
        List<SOSUserDBItem> sosUserList = null;
        try {
            sosUserList = sosUserDBLayer.getSOSUserList(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        for (SOSUserDBItem sosUserDBItem : sosUserList) {
            // Die direkt zugewiesenen Rechte.
            for (SOSUserPermissionDBItem sosUserPermissionDBItem : sosUserDBItem.getSOSUserPermissionDBItems()) {
                authorizationInfo.addStringPermission(sosUserPermissionDBItem.getSosUserPermission());
            }
            // Die �ber die Rollen zugewiesenen Rechte
            for (SOSUser2RoleDBItem sosUser2RoleDBItem : sosUserDBItem.getSOSUserRoleDBItems()) {
                for (SOSUserPermissionDBItem sosUserPermissionDBItemFromRole : sosUser2RoleDBItem.getSosUserRoleDBItem().getSOSUserPermissionDBItems()) {
                    authorizationInfo.addStringPermission(sosUserPermissionDBItemFromRole.getSosUserPermission());
                }
            }
        }
        return authorizationInfo;
    }

}
