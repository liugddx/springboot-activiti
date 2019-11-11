package com.liugddx.springbootactiviti.service.impl;

import com.liugddx.springbootactiviti.service.ActivitiService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>@ClassName ActivitiServiceImpl</p>
 * <p>@description activiti的demo，张三是申请人，lisi审批人</p>
 *
 * @author liugd
 * @version 1.0
 * @date 2019/11/10 15:41
 */
@Service
@Slf4j
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    private static final String APPLY = "zhangsan";
    private static final String APPROVE = "lisi";

    @Override
    public boolean startActivityDemo() {
        Map<String,Object> inMap = new HashMap<>();

        inMap.put("apply",APPLY);
        inMap.put("approve",APPROVE);

        //流程启动
        ExecutionEntity leave = (ExecutionEntity)runtimeService.startProcessInstanceByKey("leave", inMap);

        List<Task> tq = taskService.createTaskQuery().taskAssignee("zhangsan").list();

        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(APPLY)
                .list();
        if(tasks !=null && tasks.size()>0){
            for (Task task : tasks){
                log.info("任务id{}",task.getId());
                log.info("任务的办理人{}",task.getAssignee());
                log.info("任务名称{}",task.getName());
                log.info("任务的创建时间{}",task.getCreateTime());
                log.info("任务的流程id{}",task.getProcessInstanceId());
            }
        }


            return false;
    }
}
