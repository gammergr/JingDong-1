package com.bwie.testten.mine;

/**
 * Created by 姜天赐 on 2017/12/1.
 */

public interface SignUpConstract {
    interface ISignUpView {
        void ShowSign();
        void ShowError(String e);
    }

    interface ISignUpModel {
        void RequestData(String url,String username,String password,String password_confirm,OnRequestListener onRequestListener);
    }

    interface OnRequestListener{
        void OnSuccess();
        void OnError(String e);
    }

    interface ISignUpPresenter {
        void onSignUp(String url,String username, String password, String password_confirm);
    }
}
