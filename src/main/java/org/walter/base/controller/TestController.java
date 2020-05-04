package org.walter.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class PingController {

    public final String APP_VERSION = System.getenv("appVersion");

    @GetMapping("/ping")
    public String ping() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return String.format("[Success] hostname=%s, address=%s, appVersion=%s\n", addr.getHostName(), addr.getHostAddress(), APP_VERSION);
    }

    @GetMapping("/delay/{timeout}")
    public String delay(@PathVariable("timeout") long timeout) throws InterruptedException {
        Thread.sleep(timeout);
        return String.format("[Delay] %s ms", timeout);
    }

    @GetMapping("/fail")
    @ResponseStatus
    public Boolean fail() {
        return true;
    }
}
