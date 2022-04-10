package xom.xiaoli.clientserver.member;

import com.xiaoli.basicservice.entity.StoreUser;
import com.xiaoli.basicservice.service.StoreUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author xiaoli
 * @Date 2022/4/3 22:41
 * @Version 1.0
 */
public class MemberOperation {

    @Test
    public void test() {
        String str = "D:/projo/ideaproj/pphelper/storage/images/image_picker1504804229.jpg";
        String[] split = str.split("/");
        System.out.println(split[split.length - 1]);
    }
}
