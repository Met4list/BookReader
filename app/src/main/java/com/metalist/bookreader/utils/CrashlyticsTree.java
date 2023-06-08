package com.metalist.bookreader.utils;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class CrashlyticsTree extends Timber.Tree {
    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();

        crashlytics.setCustomKey("priority", priority);
        crashlytics.setCustomKey("tag", tag);
        crashlytics.setCustomKey("message", message);

        if (t == null) {
            crashlytics.recordException(new Exception(message));
        } else {
            crashlytics.recordException(t);
        }
    }
}