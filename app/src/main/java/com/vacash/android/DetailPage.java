package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vacash.android.models.Item;
import com.vacash.android.models.PurchaseHistory;
import com.vacash.android.models.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DetailPage extends AppCompatActivity {

    private User user;
    private Item item;
    private Integer itemQty, gameLogo;
    private String gameName, gameDeveloper, gameCategory;
    private TextView userBalance, itemNameView, gameNameView, itemPriceView, itemQuantityView, errorTitleView, errorMessageView, confirmMessageView, gameDescriptionView;
    private EditText userNameField, emailField;
    private RelativeLayout action_bar, dropdownMenu, ppHighlight, dark_overlay;
    private ConstraintLayout errorPopUp, confirmPopUp;
    private LinearLayout dropdownList, checkProfileButton, logoutButton;
    private ImageView backIconActionbar, itemImageView, stepperDown, stepperUp;
    private Button buyButton, errorOkayButton, confirmButton, cancelButton;
    private Animation slideDownAnimation, slideUpAnimation, fadeInAnimation, fadeOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        action_bar = findViewById(R.id.actionBar);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        dark_overlay = findViewById(R.id.dark_overlay);
        checkProfileButton = findViewById(R.id.checkProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
        backIconActionbar = findViewById(R.id.backIconActionBar);
        userBalance = findViewById(R.id.balance);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        slideDownAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        slideUpAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        action_bar.bringToFront();

        Intent previousPage = getIntent();
        user = previousPage.getParcelableExtra("userData");

        userBalance.setText(toCurrencyString(user.getBalance()));

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
                Intent profileActivity = new Intent(DetailPage.this, ProfilePage.class);
                profileActivity.putExtra("userData", user);
                startActivity(profileActivity);
            }
        });

        checkProfileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    checkProfileButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown1_touchdown));
                }
                else{
                    checkProfileButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown1));
                }

                return false;
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(DetailPage.this, LoginPage.class);
                startActivity(loginActivity);
            }
        });

        logoutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    logoutButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown2_touchdown));
                }
                else{
                    logoutButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown2));
                }

                return false;
            }
        });

        backIconActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        itemImageView = findViewById(R.id.itemImage);
        itemNameView = findViewById(R.id.itemNamesLabel);
        gameNameView = findViewById(R.id.gameNamesLabel);
        itemPriceView = findViewById(R.id.itemPricesLabel);

        item = previousPage.getParcelableExtra("itemData");
        gameName = previousPage.getStringExtra("gameName");
        gameDeveloper = previousPage.getStringExtra("gameDeveloper");
        gameCategory = previousPage.getStringExtra("gameCategory");
        gameLogo = previousPage.getIntExtra("gameLogo", 0);

        itemImageView.setImageResource(item.getItemsImage());
        itemNameView.setText(item.getItemsName());
        gameNameView.setText(gameName);

        String itemPrice = "IDR " + toCurrencyString(item.getItemsPrice());
        itemPriceView.setText(itemPrice);

        itemQuantityView = findViewById(R.id.numberQuantityLabel);
        stepperDown = findViewById(R.id.stepperDown);
        stepperUp = findViewById(R.id.stepperUp);
        buyButton = findViewById(R.id.buyButton);

        itemQty = 1;

        updateQtyAndBuyButton();

        stepperDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQty -= 1;
                updateQtyAndBuyButton();
            }
        });

        stepperUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQty += 1;
                updateQtyAndBuyButton();
            }
        });

        errorPopUp = findViewById(R.id.errorPopUp);
        errorTitleView = findViewById(R.id.errorTitle);
        errorMessageView = findViewById(R.id.errorMessage);
        errorOkayButton = findViewById(R.id.okayButton);

        confirmPopUp = findViewById(R.id.confirmPopUp);
        confirmMessageView = findViewById(R.id.confirmMessage);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        userNameField = findViewById(R.id.usernameTextField);
        emailField = findViewById(R.id.emailTextField);
        
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameField.getText().toString();
                String email = emailField.getText().toString();

                if(username.isEmpty()){
                    errorTitleView.setText("Username Error!");
                    errorMessageView.setText("Username section must be filled to continue the purchase process!");
                    errorPopUp.setVisibility(View.VISIBLE);
                    errorPopUp.startAnimation(fadeInAnimation);
                } else if (email.isEmpty()) {
                    errorTitleView.setText("Email Error!");
                    errorMessageView.setText("Email section must be filled to continue the purchase process!");
                    errorPopUp.setVisibility(View.VISIBLE);
                    errorPopUp.startAnimation(fadeInAnimation);
                } else if (itemQty <= 0) {
                    errorTitleView.setText("Quantity Error!");
                    errorMessageView.setText("Quantity must be more than 0 to continue the purchase process!");
                    errorPopUp.setVisibility(View.VISIBLE);
                    errorPopUp.startAnimation(fadeInAnimation);
                } else if (user.getBalance() < itemQty * item.getItemsPrice()) {
                    errorTitleView.setText("Balance Error!");
                    errorMessageView.setText("Your balance must be more than or at least equal to the total payment!");
                    errorPopUp.setVisibility(View.VISIBLE);
                    errorPopUp.startAnimation(fadeInAnimation);
                } else {
                    String confirmMessage = "Do you want to purchase "+ itemQty + " bundle"
                                                + ((itemQty > 1) ? "s" : "") + " of " + item.getItemsName()
                                                + " in " + gameName + " for IDR " + toCurrencyString(itemQty * item.getItemsPrice()) + "?";
                    confirmMessageView.setText(confirmMessage);

                    confirmPopUp.setVisibility(View.VISIBLE);
                    confirmPopUp.startAnimation(fadeInAnimation);
                }

                disableButtons();
            }
        });

        errorOkayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorPopUp.startAnimation(fadeOutAnimation);
                errorPopUp.setVisibility(View.INVISIBLE);
                enableButtons();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPopUp.startAnimation(fadeOutAnimation);
                confirmPopUp.setVisibility(View.INVISIBLE);
                enableButtons();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseHistory purchaseHistory = new PurchaseHistory(gameName, item.getItemsName(), itemQty, itemQty * item.getItemsPrice(), gameDeveloper, gameCategory, gameLogo);
                user.addPurchaseHistories(purchaseHistory);
                user.reduceBalance(itemQty * item.getItemsPrice());
                enableButtons();

                Intent profileActivity = new Intent(DetailPage.this, ProfilePage.class);
                profileActivity.putExtra("userData", user);
                startActivity(profileActivity);
            }
        });

        gameDescriptionView = findViewById(R.id.itemDescriptionLabel);
        gameDescriptionView.setText(previousPage.getStringExtra("gameDescription 1"));
    }

    private void disableButtons(){
        userNameField.setEnabled(false);
        emailField.setEnabled(false);
        buyButton.setEnabled(false);
        backIconActionbar.setEnabled(false);
        dropdownMenu.setEnabled(false);
        stepperDown.setEnabled(false);
        stepperUp.setEnabled(false);
    }

    private void enableButtons(){
        userNameField.setEnabled(true);
        emailField.setEnabled(true);
        buyButton.setEnabled(true);
        backIconActionbar.setEnabled(true);
        dropdownMenu.setEnabled(true);
        stepperDown.setEnabled(true);
        stepperUp.setEnabled(true);
    }

    private void updateQtyAndBuyButton(){
        itemQuantityView.setText(itemQty.toString());
        String buyButtonText = "Buy Item - IDR " + toCurrencyString(itemQty * item.getItemsPrice());
        buyButton.setText(buyButtonText);
    }

    private String toCurrencyString(Integer value){
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(value).replace("Rp", "");
    }
}