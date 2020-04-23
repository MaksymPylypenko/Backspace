package com.group8.backspace;

import com.group8.backspace.IntegrationTests.AccessFlightsIT;
import com.group8.backspace.IntegrationTests.AccessItemsIT;
import com.group8.backspace.IntegrationTests.AccessPlanetIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessFlightsIT.class,
        AccessItemsIT.class,
        AccessPlanetIT.class,
})
public class AllIntegrationTests {

}
