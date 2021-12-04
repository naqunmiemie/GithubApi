package com.yjn.githubapi.activity;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hjq.toast.ToastUtils;
import com.yjn.githubapi.base.BaseActivity;
import com.yjn.githubapi.databinding.ActivityMainBinding;
import com.yjn.githubapi.glide.GlideApp;
import com.yjn.githubapi.model.User;
import com.yjn.githubapi.util.L;
import com.yjn.githubapi.viewmodel.UserAVM;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        final UserAVM userAVM = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserAVM.class);

        userAVM.getUser().observe(this, new Observer<User>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(User user) {
                if (user != null){
                    L.d("user != null login:" + user.login);
                    GlideApp.with(MainActivity.this)
                            .load(user.avatar)
                            .circleCrop()
                            .into(binding.ivProfileImage);
                    binding.tvLogin.setText(user.login);
                    binding.tvBio.setText(user.bio);
                    binding.tvCompany.setText(user.company);
                    binding.tvLocation.setText(user.location);
                    binding.tvFollowers.setText("followers:"+user.followers);
                    binding.tvFollowing.setText("following:"+user.following);
                    binding.tvHtmlUrl.setText(user.htmlUrl);
                    binding.tvBlog.setText(user.blog);
                }
            }
        });

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userAVM.refresh();
                binding.swipeRefresh.setRefreshing(false);
            }
        });

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.etId.getText().toString();
                userAVM.refresh(id);
            }
        });

        binding.btnInsertCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://qr.alipay.com/fkx10299wco86rtorfxi972?t=1638601405094"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    ToastUtils.show("打开支付宝失败，您可能还没有安装支付宝客户端");
                }
            }
        });
    }

}