package com.shopics;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutHelper {

    public static void logout(Context context) {
        FirebaseAuth.getInstance().signOut();
        redirectToLoginActivity(context);
    }

    private static void redirectToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
