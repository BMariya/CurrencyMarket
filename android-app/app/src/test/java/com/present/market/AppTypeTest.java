package com.present.market;

import com.present.market.core.base.AppType;

import org.junit.Test;

public class AppTypeTest {

    @Test
    public void appAmount_isCreated() {
        AppType.AppAmount amount = new AppType.AppAmount("1.2");
    }
}
