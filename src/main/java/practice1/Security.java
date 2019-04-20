package practice1;

import com.google.common.collect.ImmutableList;

public class Security {

    private SecurityChecker securityChecker;

    public Security(SecurityChecker checker) {
        this.securityChecker = checker;
    }

    public boolean hasAccess(User user, Permission permission, ImmutableList<Permission> permissions) {

        if (user == null || permission == null ||permissions.size() == 0)
            return false;

        if (securityChecker.isAdmin() || this.securityChecker.checkPermission(user, permission) || permissions.contains(permission)) {
          return  true;
        }else {
            return false;
        }
    }
}
