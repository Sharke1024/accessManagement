package com.itheima.ssm.ssm;

import java.io.Serializable;
import java.util.List;

/**
 * 资源权限表实现类
 */
public class Permission implements Serializable {
    private Object id;
    private String permissionName;
    private String url;
    private List<Role> roles;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
