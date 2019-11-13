package com.liugddx.springbootactiviti;

import java.util.ArrayList;
import java.util.List;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.jupiter.api.Test;
import sun.applet.Main;

/**
 * <p>@ClassName curatorZookeeperClientTest</p>
 * <p>@description TODO</p>
 *
 * @author liugd
 * @version 1.0
 * @date 2019/11/12 16:47
 */
public class curatorZookeeperClientTest {

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        /*RetryPolicy retryPolicy1 = new RetryNTimes(3, 1000);//最大重试次数，和两次重试间隔时间
        RetryPolicy retryPolicy2 = new RetryUntilElapsed(5000, 1000);//会一直重试直到达到规定时间，第一个参数整个重试不能超过时间，第二个参数重试间隔
        //第一种方式
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.3:2181", 5000,5000,retryPolicy);//最后一个参数重试策略
        */

        //第二种方式
        CuratorFramework client1 = CuratorFrameworkFactory.builder().connectString("120.77.157.220:2181")
                .sessionTimeoutMs(5000)//会话超时时间
                .connectionTimeoutMs(5000)//连接超时时间
                .retryPolicy(retryPolicy)
                .build();

        client1.start();

        String path = client1.create().creatingParentsIfNeeded()//若创建节点的父节点不存在会先创建父节点再创建子节点
                .withMode(CreateMode.EPHEMERAL)//withMode节点类型，
                .forPath("/curator/3","131".getBytes());
        System.out.println(path);

        List<String> list = client1.getChildren().forPath("/");
        System.out.println(list);

        //String re = new String(client1.getData().forPath("/curator/3"));//只获取数据内容
        Stat stat = new Stat();
        String re = new String(client1.getData().storingStatIn(stat)//在获取节点内容的同时把状态信息存入Stat对象
                .forPath("/curator/3"));
        System.out.println(re);
        System.out.println(stat);


        client1.delete().guaranteed()//保障机制，若未删除成功，只要会话有效会在后台一直尝试删除
                .deletingChildrenIfNeeded()//若当前节点包含子节点
                .withVersion(-1)//指定版本号
                .forPath("/dubbo");

        Thread.sleep(Integer.MAX_VALUE);



    }

}
