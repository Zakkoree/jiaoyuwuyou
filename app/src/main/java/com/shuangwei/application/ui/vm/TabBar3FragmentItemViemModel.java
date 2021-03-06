package com.shuangwei.application.ui.vm;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.shuangwei.application.R;
import com.shuangwei.application.entity.DemoEntity;
import com.shuangwei.application.ui.fragment.DetailFragment;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class TabBar3FragmentItemViemModel extends BaseViewModel{
    public DemoEntity.ItemsEntity entity;
    public Drawable drawableImg;
    public TabBar3FragmentItemViemModel(Context context, DemoEntity.ItemsEntity entity) {
        super(context);
        this.entity = entity;
        //ImageView的占位图片，可以解决RecyclerView中图片错误问题
        drawableImg = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //这里可以通过一个标识,做出判断，已达到跳入不能界面的逻辑
            if (entity.getId() == -1) {
                ToastUtils.showShort(entity.getName());
            } else {
                //跳转到详情界面,传入条目的实体对象
                Bundle mBundle = new Bundle();
                mBundle.putParcelable("entity", entity);
                startContainerActivity(DetailFragment.class.getCanonicalName(), mBundle);
            }
        }
    });
    //条目的长按事件
    public BindingCommand itemLongClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //使用Messenger发送事件，在NetWorkViewModel中完成删除逻辑
            Messenger.getDefault().send(TabBar3FragmentItemViemModel.this, TabBar3FragmentModel.TOKEN_NETWORKVIEWMODEL_DELTE_ITEM);
        }
    });
//    /**
//     * 可以在xml中使用binding:currentView="@{viewModel.titleTextView}" 拿到这个控件的引用, 但是不推荐这样做
//     **/
//    private TextView tv;
//    //将标题TextView控件回调到ViewModel中
//    public BindingCommand<TextView> titleTextView = new BindingCommand(new BindingConsumer<TextView>() {
//        @Override
//        public void call(TextView tv) {
//            NetWorkItemViewModel.this.tv = tv;
//        }
//    });
}
