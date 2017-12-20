package com.bwie.testten.mine;

import com.bwie.testten.mine.bean.LoginBean;

import java.util.List;

/**
 * Created by 姜天赐 on 2017/12/1.
 */

public interface LoginConstract {

    interface ILoginView {
        void showLogin(LoginBean.DataBean db);
        void showerroe(String e);
    }

    interface ILoginModel {
        void RequestData(String url,String mobile,String password,LoginConstract.OnRequestListener onRequestListener);
    }

    interface OnRequestListener{
        void OnSuccess(LoginBean.DataBean db);
        void OnError(String e);
    }

    interface ILoginPresenter {
        void onSignUp(String url,String mobile, String password);
    }

}
