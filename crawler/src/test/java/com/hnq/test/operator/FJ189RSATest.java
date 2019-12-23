package com.hnq.test.operator;

import com.hnq.test.common.CrawlUtils;
import org.junit.jupiter.api.Test;

import javax.script.Invocable;


/**
 * @author henengqiang
 * @date 2019/08/14
 */
public class FJ189RSATest {

    private static String phone = "18965159530";

    private static String emailModule = "863581c5892cdfe8a67b95c7abb47ead8b102e9620994ae95637f637fa22acac173b91015574507362816b30a884632d8562bf20de621d31d745291aaec7ca6f";

    private static String emailEmpoent = "10001";

    private static String userType = "50";

    private static String datem = "201908";

    private Invocable getInvocable() throws Exception {
        return CrawlUtils.createInvocable( "operator/fj189.js", "GBK");
    }

    @Test
    public void encryptTest() throws Exception {
        String encryptStr = getInvocable().invokeFunction("encryptedString", emailModule, phone).toString();
        System.out.println(encryptStr);
    }

}
