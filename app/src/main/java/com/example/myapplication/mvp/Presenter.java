package com.example.myapplication.mvp;

public class Presenter implements Contract.Presenter{
    private Contract.View view;
    private Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        this.model =new Model();
    }

    @Override
    public void Show() {
        model.Show(new Contract.Model.ModelCallBack() {
            @Override
            public void success(Object o) {
                view.success(o);
            }

            @Override
            public void failure(Throwable throwable) {
                view.failure(throwable);
            }
        });
    }
}
