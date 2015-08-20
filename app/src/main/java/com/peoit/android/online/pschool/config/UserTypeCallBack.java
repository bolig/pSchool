package com.peoit.android.online.pschool.config;

import com.peoit.android.online.pschool.UserTypeBase;
import com.peoit.android.online.pschool.exception.NoLoginEcxeption;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UserTypeCallBack {

    private final int idType;
    private final UserTypeBase typeBase;

    public UserTypeCallBack(UserTypeBase typeBase) throws NoLoginEcxeption {
        this.typeBase = typeBase;
        this.idType = CommonUtil.getIdEntityType();
        if (idType == Constants.TPYE_NOT_LOGIN)
            throw new NoLoginEcxeption(" @libo cuurent no login ");
    }

    public void start(){
        if (typeBase != null){
            switch (idType){
                case 1:
                    //班主任
                    typeBase.current_is_teacher();
                    break;
                case 2:
                    //家长
                    typeBase.current_is_parent();
                    break;
                case 3:
                    //专家
                    typeBase.current_is_expert();
                    break;
                case 4:
                    //专家管理员
                    typeBase.current_is_expert();
                    break;
            }
        }
    }
}
