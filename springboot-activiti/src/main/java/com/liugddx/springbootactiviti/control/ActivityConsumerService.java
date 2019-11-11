package com.liugddx.springbootactiviti.control;

import com.liugddx.springbootactiviti.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>@ClassName ActivityConsumerService</p>
 * <p>@description TODO</p>
 *
 * @author liugd
 * @version 1.0
 * @date 2019/11/10 15:39
 */
@RestController
public class ActivityConsumerService {

    @Autowired
    private ActivitiService activitiService;

    @GetMapping("activitiDemo")
    public boolean startActivityDemo(){

        return activitiService.startActivityDemo();
    }



}
