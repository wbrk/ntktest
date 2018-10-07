package ntktest.example.com.ntktest.presentation;

import android.support.annotation.Nullable;

public class RouterHolder {
    @Nullable
    private static Router router;

    public static void setRouter(@Nullable Router router) {
        RouterHolder.router = router;
    }

    @Nullable
    public static Router getRouter() {
        return router;
    }
}
