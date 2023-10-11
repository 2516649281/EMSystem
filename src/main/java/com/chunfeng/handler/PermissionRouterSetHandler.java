package com.chunfeng.handler;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.dao.entity.Router;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.service.IPermissionRouterService;
import com.chunfeng.service.IPermissionService;
import com.chunfeng.service.IRouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 动态权限配置(路由设置)
 * <p>
 * 此类是SpringSecurity动态授权的核心配置类，用户每发一次都会经过此拦截器，
 * 拦截器根据路由与数据库中进行比较，从而生成用户的权限对象
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
@Slf4j
public class PermissionRouterSetHandler implements FilterInvocationSecurityMetadataSource {
    /**
     * 导入权限业务
     */
    @Autowired
    private IPermissionService permissionService;

    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 导入路由业务
     */
    @Autowired
    private IRouterService routerService;

    /**
     * 设置动态权限
     *
     * @param object 请求对象
     * @return 安全对象的属性, 没有则返回空
     * @throws IllegalArgumentException 验证失败则抛出此异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //创建配置对象
        Collection<ConfigAttribute> collection = new HashSet<>();
        //获取当前访问的URL
        FilterInvocation invocation = (FilterInvocation) object;
        //获取请求方式
        String method = invocation.getHttpRequest().getMethod();
        String url = invocation.getRequestUrl();
        //加工url，去除参数
        if (url.contains("?")) {
            url = url.substring(0, url.lastIndexOf("?"));
        }
        log.info("当前请求路由:{}", url);
        //查找后端的路由
        Router router = new Router();
        router.setValue(url);
        router.setType(0);
        router.setMethod(method);
        //通过业务层查询路由对应的ID
        JsonRequest<List<Router>> request = routerService.lookRouter(router);
        //判断权限是否查询完成
        if (!request.getSuccess()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.UNAUTHORIZED);
        }
        // 拿到路由菜单
        List<Router> routers = request.getData();
        //获得ID值
        String[] routerIds = routers
                .stream()
                .map(Router::getId)//提取ID值
                .toArray(String[]::new);//转换为数组
        //获得权限ID
        JsonRequest<List<PermissionRouter>> request1 = permissionRouterService.lookPermissionRouterByRouter(routerIds);
        if (!request1.getSuccess()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.UNAUTHORIZED);
        }
        // 获取权限ID
        String[] ids = request1.getData()
                .stream()
                .map(PermissionRouter::getPermissionId)//从中获取权限ID
                .toArray(String[]::new);//转换为数组
        //最终获得权限菜单
        JsonRequest<List<Permission>> jsonRequest = permissionService
                .lookPermissionById(ids);
        if (!jsonRequest.getSuccess()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.UNAUTHORIZED);
        }
        //加入配置菜单
        jsonRequest.getData()
                .forEach(v -> collection
                        .add(new SecurityConfig(v.getSign())));
        log.info("动态权限配置完成!");
        return collection;
    }

    /**
     * 返回实现类定义的所有 ConfigAttributes
     *
     * @return ConfigAttribute
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 是否能够提供 ConfigAttributes
     *
     * @param clazz 正在查询的类
     * @return 如果可以提供，则返回 true
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 失效回溯法
     * <p>
     * 思想1：对于通配符匹配方案，我们主要的难点问题是在于通配符*的匹配，
     * 所以首要问题我们要定位到*所在的位置，定位到*之后我们再在此处做文章
     * 思想2：单值通配符？姑且忽略，我们只要把他当作任意字符处理即可，让他等价于任意字符。
     * 思想3：假设目标串和模板串都是普通字符串，不含有任何通配符，那么此时我们的比较方式应该是逐个字符的比较方式
     * 思想4：假设另一种特殊情况，我们的模板串是目标串中间穿插的通配符，此时我们在处理的过程中可以跳过通配符
     * 思想5：在思想4的基础上我们引申一下，我们思考一下我们应该如何跳过通配符，如果只是单纯的忽略，对我们解决问题没有任何意义，
     * 假设我们模板串中只有一个通配符，当前前面的若干字符匹配成功，此时遇见通配符，此时
     * 比较目标串当前索引开始的子串与模板串当前通配符之后的子串。如果子串能够匹配，那么自然而然的忽略了通配符
     * 思想6：在思想5的基础上我们继续引申，如果子串匹配失效，能够证明此时的匹配完全失效吗？
     * 不能，因为我们通配符之后的子串可以只匹配目标串的最后几位，我们的通配符可以代替中间的所有字符。
     * 那么问题来了，我们接下来该怎么办？
     * 思想7：在思想6的基础上我们继续引申，假设我们模板串的通配符两端能够与目标串的首尾匹配，那么我们该如何解决中间的内容匹配呢？
     * 我们再回想一下思想5中忽略通配符的过程，因为我们通配符就是用来忽略字符的，我们为什么要忽略他呢，
     * 问题出在了我们思想4中的假设，因为我们假设的是通配符是多余的，但是仔细想一下，
     * 其实通配符多余只是一种通配符匹配到0个字符的情况，那么通配符如果匹配多个字符该如何操作呢，
     * 此时，我们是不是应该忽略目标串中的字符，那么怎么忽略呢？我们再想一想思想5中的子串比较，
     * 我们是不是可以通过缩短目标串的子串来达到忽略的效果呢？可以达到，那么问题又来了，怎么缩短呢？
     * 思想8：在思想7的基础上我们再思考，假设两个子串匹配失效之后，我们怎么能够回到起点进行忽略操作？
     * 我们是不是就应该对目标子串的起始位置进行记录呢，这样匹配失败后我能够回到最初的起点。
     * 于是目标子串回到起点后加1后便达到了忽略一个字符的效果。但是目标子串忽略一个字符表示这个字符是被通配符抵消掉的，
     * 那接下来的目标子串应该是与模板串的哪部分比较呢，是不是也应该是通配符后边的子串。
     * 那么我们同样需要对通配符的位置进行记录。我们可以将这两个标记称为回溯点。
     * 思想9：在思想8的回溯思路上，我们凡是遇到失效，就回溯到回溯点的位置，直到目标串处理完毕
     * 思想10：目标串匹配结束后，已经确定目标串满足我们模板串，此时需要检测模板串是否满足目标串，
     * 也就是说模板串是否还有残余串，如果残余串是*放行，否则阻断。
     * 当残余串检测完毕之后，此时如果模板串检测到的索引刚好等于模板串的索引，表示模板串满足目标串。
     * 到此，我们整个问题的解决思路已经明确。
     * 思想11：前面的思想我们都是基于单个通配符的基础上进行引申的，那么多个通配符又能否满足呢？仔细思考一下。是完全可以，
     * 因为如果能够匹配到下一个通配符的位置，那么下一个通配符将会接替前一个通配符的监听，前一个通配符的职责已经完成。
     * 思想12：在思想2中我们忽略的单值通配符应该如何处理呢，我们前文说过，让他等价于任何字符，也就是说，
     * 在我们判断目标串中某个字符和模板串中的某个字符是否相等时，如果模板串中的字符是单值通配符，直接按照匹配成功，放行即可。
     * 至此，所有的疑点都已经一一击破，真相已经水落石出。
     *
     * @param s 待匹配字符串
     * @param p 通配符模板字符串
     * @return 匹配结果
     */
    //出自: https://www.jianshu.com/p/fc1eb5bce62d
    public boolean isMatch(String s, String p) {
        // i 用来记录s串检测的索引的位置
        int i = 0;
        // j 用来记录p串检测的索引的位置
        int j = 0;
        // 记录 待测串i的回溯点
        int ii = -1;
        // 记录 通配符*的回溯点
        int jj = -1;

        // 以s字符串的长度为循环基数，用i来记录s串当前的位置
        while (i < s.length()) {
            // 用j来记录p串的当前位置，检测p串中j位置的值是不是通配符*
            if (j < p.length() && p.charAt(j) == '*') {
                // 如果在p串中碰到通配符*，复制两串的当前索引，记录当前的位置，并对p串+1，定位到非通配符位置
                ii = i;
                jj = j;
                j++;
            } else if (j < p.length() // 检测p串是否结束
                    && (s.charAt(i) == p.charAt(j)   // 检测两串当前位置的值是否相等
                    || p.charAt(j) == '?')) { // 检测p串中j位置是否是单值通配符？
                // 如果此时p串还在有效位置上，那么两串当前位置相等或者p串中是单值通配符，表明此时匹配通过，两串均向前移动一步
                i++;
                j++;
            } else {
                // 如果在以上两种情况下均放行，表明此次匹配是失败的，那么此时就要明确一点，s串是否在被p串中的通配符*监听着，
                // 因为在首次判断中如果碰到通配符*，我们会将他当前索引的位置记录在jj的位置上，
                // 如果jj = -1 表明匹配失败，当前s串不在监听位置上
                if (jj == -1) return false;
                // 如果此时在s串在通配符*的监听下， 让p串回到通配符*的位置上继续监听下一个字符
                j = jj;
                // 让i回到s串中与通配符对应的当前字符的下一个字符上，也就是此轮匹配只放行一个字符
                i = ii + 1;
            }
        }

        // 当s串中的每一个字符都与p串中的字符进行匹配之后，对p串的残余串进行检查，如果残余串是一个*那么继续检测，否则跳出
        while (j < p.length() && p.charAt(j) == '*') j++;

        // 此时查看p是否已经检测到最后，如果检测到最后表示匹配成功，否则匹配失败
        return j == p.length();
    }
}
