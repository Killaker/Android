package android.support.design.widget;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultBehavior {
    Class<? extends Behavior> value();
}
