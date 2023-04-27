package gov.zwfw.iam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    private Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping(value = "/getNick", method = RequestMethod.POST)
    public String getNick(@RequestBody String body) {
        logger.info(body);
        return "小蓝";
    }

}
