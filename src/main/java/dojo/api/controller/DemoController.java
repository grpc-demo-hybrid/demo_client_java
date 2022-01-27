package dojo.api.controller;

import dojo.config.DemoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(DemoConfig.class)
public class DemoController {
    @Autowired
    private DemoConfig demoConfig;

    @RequestMapping(path = "/demo", method = RequestMethod.GET)
    public DemoConfig getDemo() {
        return this.demoConfig;
    }
}
