/*
 * Javalin - https://javalin.io
 * Copyright 2017 David Åse
 * Licensed under Apache 2.0: https://github.com/tipsy/javalin/blob/master/LICENSE
 *
 */

package io.javalin;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLifecycleEvents {

    private static String startingMsg = "";
    private static String startedMsg = "";
    private static String stoppingMsg = "";
    private static String stoppedMsg = "";

    @Test
    public void testLifecycleEvents() {
        Javalin.create()
            .port(0)
            .event(JavalinEvent.SERVER_STARTING, () -> startingMsg = "Starting")
            .event(JavalinEvent.SERVER_STARTED, () -> startedMsg = "Started")
            .event(JavalinEvent.SERVER_STOPPING, () -> stoppingMsg = "Stopping")
            .event(JavalinEvent.SERVER_STOPPING, () -> stoppingMsg += "Stopping")
            .event(JavalinEvent.SERVER_STOPPING, () -> stoppingMsg += "Stopping")
            .event(JavalinEvent.SERVER_STOPPED, () -> stoppedMsg = "Stopped")
            .start()
            .stop();
        assertThat(startingMsg, is("Starting"));
        assertThat(startedMsg, is("Started"));
        assertThat(stoppingMsg, is("StoppingStoppingStopping"));
        assertThat(stoppedMsg, is("Stopped"));
    }

}
