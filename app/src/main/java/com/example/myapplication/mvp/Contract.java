package com.example.myapplication.mvp;

public interface Contract {
    interface Model{
        void Show(ModelCallBack callBack);
//        void Xiangq(ModelCallBack callBack);
        interface ModelCallBack{
            void success(Object o);
            void failure(Throwable throwable);
        }
    }
    interface View{
        void success(Object o);
        void failure(Throwable throwable);
    }
    interface Presenter{
        void Show();
//        void Xiangq();
    }
}
