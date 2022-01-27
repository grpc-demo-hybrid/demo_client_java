package dojo.api.controller;

import dojo.api.service.IBusinessService;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Fanmao.Li
 * @since 06/09/2019
 */
@RestController
public class HelloController {
    @Autowired
    private List<IBusinessService> services;

    @Autowired
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name) {
        if (services != null) {
            services.forEach(service -> service.process(name));
        }

        HelloRequest helloRequest = HelloRequest.newBuilder()
                                                .setName("Andy")
                                                .setAge(36)
                                                .build();


        return greeterBlockingStub.sayHelloAgain(helloRequest).getMessage();
    }
}
