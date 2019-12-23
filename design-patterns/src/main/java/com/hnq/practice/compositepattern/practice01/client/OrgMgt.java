package com.hnq.practice.compositepattern.practice01.client;

import com.hnq.practice.compositepattern.practice01.component.IOrgComponent;
import com.hnq.practice.compositepattern.practice01.composite.Org;
import com.hnq.practice.compositepattern.practice01.leaf.User;

/**
 * OrgMgt 是组织机构管理类，实现了组织机构树输出功能。对应于组合模式的参与者，OrgMgt 是 Client。
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class OrgMgt {

    public String rootOrgName = "总公司";

    private String orgName = "财务部";

    private String userId1 = "001";
    private String user1 = "张三";

    private String userId2 = "002";
    private String user2 = "李四";

    public void outputOrgTree(String rootName) {
        // 根组织
        IOrgComponent rootOrg = getOrg(rootName);
        if (rootOrg != null) {
            System.out.println(rootOrg.getOrgComponentInfo());
        }
    }

    private IOrgComponent getOrg(String name) {
        // 组织机构组件
        IOrgComponent orgComponent = null;
        if (rootOrgName.equals(name)) {
            orgComponent = new Org(rootOrgName);
            User userA = new User(userId1, user1);
            orgComponent.add(userA);

            Org financeDep = new Org(orgName);
            orgComponent.add(financeDep);
            User userB = new User(userId2, user2);
            financeDep.add(userB);
        }
        return orgComponent;
    }
}
