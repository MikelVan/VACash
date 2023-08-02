package com.vacash.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vacash.android.adapters.PurchaseHistoryAdapter;
import com.vacash.android.interfaces.RecyclerViewInterface;
import com.vacash.android.models.PurchaseHistory;
import com.vacash.android.models.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ProfilePage extends AppCompatActivity implements RecyclerViewInterface {

    User user;
    ScrollView scrollView;
    EditText usernameField, emailField, amountField;
    TextView errorMsg, userBalance;
    Button topUpBtn;


    private RelativeLayout action_bar, dropdownMenu, ppHighlight, dark_overlay;
    private LinearLayout dropdownList, checkProfileButton, logoutButton;
    private ImageView homeIconActionBar;
    private Animation slideDownAnimation, slideUpAnimation, fadeInAnimation, fadeOutAnimation;

    public void showError(TextView errorView){
        errorView.animate().alpha(1.0f).setDuration(250);
    }

    public void hideError(TextView errorView){
        errorView.animate().alpha(0.0f).setDuration(250);
    }

    public boolean isNumeric(String string){
        try{
            Integer integer = Integer.parseInt(string);
        }
        catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        action_bar = findViewById(R.id.actionBar);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        dark_overlay = findViewById(R.id.dark_overlay);
        checkProfileButton = findViewById(R.id.checkProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
        homeIconActionBar = findViewById(R.id.homeIconActionBar);
        userBalance = findViewById(R.id.balance);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        slideDownAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        slideUpAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        action_bar.bringToFront();

        Intent previousActivity = getIntent();
        user = previousActivity.getParcelableExtra("userData");
//        user = previousActivity.getBundleExtra("userData").getParcelable("userData");

        setUserBalanceText(user.getBalance());

        scrollView = findViewById(R.id.scrollableView);
        usernameField = findViewById(R.id.usernameField);
        emailField = findViewById(R.id.emailField);
        amountField = findViewById(R.id.amountField);
        errorMsg = findViewById(R.id.errorMessage);
        topUpBtn = findViewById(R.id.topUpBtn);

        dark_overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dark_overlay.setEnabled(false);

                ppHighlight.startAnimation(fadeOutAnimation);
                ppHighlight.setVisibility(View.INVISIBLE);

                if (dropdownList.getVisibility() == View.VISIBLE) {
                    dropdownList.startAnimation(slideUpAnimation);
                    dropdownList.setVisibility(View.INVISIBLE);
                }

                dark_overlay.startAnimation(fadeOutAnimation);
                dark_overlay.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dark_overlay.setEnabled(true);
                    }
                },550);
            }
        });

        dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ppHighlight.getVisibility() == View.VISIBLE) {
                    ppHighlight.startAnimation(fadeOutAnimation);
                    ppHighlight.setVisibility(View.INVISIBLE);
                } else {
                    ppHighlight.setVisibility(View.VISIBLE);
                    ppHighlight.startAnimation(fadeInAnimation);
                }

                if (dropdownList.getVisibility() == View.VISIBLE) {
                    dropdownList.startAnimation(slideUpAnimation);
                    dropdownList.setVisibility(View.INVISIBLE);
                } else {
                    dropdownList.setVisibility(View.VISIBLE);
                    dropdownList.startAnimation(slideDownAnimation);
                }

                if (dark_overlay.getVisibility() == View.VISIBLE) {
                    dark_overlay.startAnimation(fadeOutAnimation);
                    dark_overlay.setVisibility(View.INVISIBLE);
                } else {
                    dark_overlay.setVisibility(View.VISIBLE);
                    dark_overlay.startAnimation(fadeInAnimation);
                }
            }
        });

        checkProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(ProfilePage.this, ProfilePage.class);
                profileActivity.putExtra("userData", user);
                startActivity(profileActivity);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(ProfilePage.this, LoginPage.class);
                startActivity(loginActivity);
            }
        });

        homeIconActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(ProfilePage.this, HomePage.class);
                homeActivity.putExtra("userData", user);
                startActivity(homeActivity);
            }
        });

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());

        hideError(errorMsg);

        topUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountField.getText().toString();

                if(amount.isEmpty()){
                    errorMsg.setText("Please input the amount.");
                    showError(errorMsg);
                } else if (!isNumeric(amount)) {
                    errorMsg.setText("Input must be number.");
                    showError(errorMsg);
                } else if (Integer.parseInt(amount) <= 0) {
                    errorMsg.setText("Amount must be more 0.");
                    showError(errorMsg);
                } else {
                    hideError(errorMsg);

                    user.addBalance(Integer.parseInt(amount));
                    amountField.setText("");

                    setUserBalanceText(user.getBalance());
                }
            }
        });

        if (user.getPurchaseHistories().size() == 0){
            findViewById(R.id.noData).setVisibility(View.VISIBLE);
        }

        RecyclerView purchaseHistoryRecycleView = findViewById(R.id.purchaseHistoryRecycleView);
        purchaseHistoryRecycleView.setLayoutManager(new LinearLayoutManager(this));
        purchaseHistoryRecycleView.setAdapter(new PurchaseHistoryAdapter(user.getPurchaseHistories(), this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Integer id = item.getItemId();

        if(id == R.id.profile){
            Intent profileActivity = new Intent(ProfilePage.this, ProfilePage.class);
            profileActivity.putExtra("userData", user);
            startActivity(profileActivity);
        } else if (id == R.id.logout) {
            Intent loginActivity = new Intent(ProfilePage.this, LoginPage.class);
            startActivity(loginActivity);
        }

        return true;
    }

    private void setUserBalanceText(Integer balance){
        userBalance.setText(NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(balance).replace("Rp", ""));
    }

    @Override
    public void onItemClick(int position) {
        Intent itemActivity = new Intent(ProfilePage.this, ItemPage.class);
        itemActivity.putExtra("userData", user);
        itemActivity.putExtra("gameLogo", user.getPurchaseHistories().get(position).getGameLogo());
        itemActivity.putExtra("gameName", user.getPurchaseHistories().get(position).getGameName());
        itemActivity.putExtra("gameDeveloper", user.getPurchaseHistories().get(position).getGameDeveloper());
        itemActivity.putExtra("gameCategory", user.getPurchaseHistories().get(position).getGameCategory());
        startActivity(itemActivity);
    }
}