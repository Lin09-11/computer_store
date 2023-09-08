package com.example.mycomputerstore.controller;


import com.example.mycomputerstore.controller.ex.*;
import com.example.mycomputerstore.entity.User;
import com.example.mycomputerstore.service.IUserService;
import com.example.mycomputerstore.utitl.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@RequestMapping("users")
@RestController//RestController=@RequestMapping+@RequestBody
@RequestMapping("/users")
/**
 * 这里继承BaseController表示：可以处理异常类
 */
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * @GetMapping("/reg") //@RequestBody//表示此方法的响应结果以json格式进行数据的1响应给前端
     * public JsonResult<Void> reg(User user) {
     * //创建响应结果对象
     * JsonResult<Void> result = new JsonResult<>();
     * try {
     * userService.reg(user);
     * result.setState(200);
     * result.setMessage("用户注册成功");
     * } catch (UsernameDuplicatedException e) {
     * result.setState(4000);
     * result.setMessage("用户名被占用");
     * } catch (InsertException e) {
     * result.setState(5000);
     * result.setMessage("注册时产生未知的异常");
     * }
     * return result;
     * }
     */

    /**
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据
     * SpringBoot会将前端url地址中的参数名和pojo类的属性名进行比较，如果这两个名称相同，
     * 则将值注入到pojo类中对于的属性上
     * @param user
     * @return
     */
    @PostMapping("/reg")
    //@RequestBody//表示此方法的响应结果以json格式进行数据的1响应给前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 2.接收数据方式：请求处理的参数列表设置为非pojo类型
     * SpringBoot会直接将请求的参数名和方法的参数名直接进行比较，如果名称
     * 相同则自动完成值的依赖注入
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public JsonResult<User> login(String username,
                                  String password,
                                  HttpSession session){
        User user = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username",user.getUsername());
        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        System.out.println(password);
        return new JsonResult<>(OK,user);

    }

    /**
     * 修改用户密码
     * @param oldPassword
     * @param newPassword
     * @param session 这里使用session，是因为要根据uid查询是哪一位用户，所以可以直接根据session来获取uid和username
     * @return
     */
    @PostMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    /**
     * 根据uid查询用户信息，并展示在页面
     * @param session
     * @return
     */
    @GetMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    /**
     * 点击更新按钮，修改用户信息
     * @param user
     * @param session：这里使用session是为了获取用户的uid和username
     * @return
     */
    @PostMapping("/change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        //user对象有四部分数据：username,phone,email,gender
        //uid数据需要再次封装到user中【SpringBoot实现依赖注入时，自动提交表单的数据，所以我们手动将用户的uid加入到user中】
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

    /**
     * MultipartFile接口是SpringMVC提供的一个接口，这个接口为我们包装了
     * 获取文件类型的数据（任何类型的file都可以接收），Springboot它有整合了
     * SpringMVC，只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile
     * 的参数，任何SpringBoot就自动将传递给服务的文件数据赋值给这个参数
     *
     * @RequestParam 表示请求中的参数，将请求中的参数注入请求处理方法的某个参数上，
     * 如果名称不一致则可以使用@RequestPara注解进行标记和映射
     * @param session
     * @param file
     * @return
     */
    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //上传文件的类型
    public static final List<String> AVATAR_TYPE;
    static {
        AVATAR_TYPE = new ArrayList<>();
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @PostMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {

        /**
         * 1.参数名为什么必须用file:在upload.html页面的147行<input type=
         * "file" name="file">中的name="file",所以必须有一个方法的参数名
         * 为file用于接收前端传递的该文件.如果想要参数名和前端的name不一
         * 样:@RequestParam("file")MultipartFile ffff:把表单中name=
         * "file"的控件值传递到变量ffff上
         * 2.参数类型为什么必须是MultipartFile:这是springmvc中封装的一个
         * 包装接口,如果类型是MultipartFile并且参数名和前端上传文件的name
         * 相同,则会自动把整体的数据包传递给file
         */

        //判断文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件超出限制");
        }
        //判断文件的类型是否为我们规定的类型
        String contentType = file.getContentType();
        //如果集合包含某一个元素则返回true
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件  ../upload/文件.png
        /**
         * session.getServletContext()获取当前Web应用程序的上下文
         * 对象(每次启动tomcat都会创建一个新的上下文对象)
         * getRealPath("/upload")的/代表当前web应用程序的根目录,通过该相
         * 对路径获取绝对路径,返回一个路径字符串,如果不能进行映射返回null,单
         * 斜杠可要可不要
         */
        String parent = session.getServletContext().getRealPath("upload");

        //File对象指向这个路径,File是否存在
        File dir = new File(parent);
        if (!dir.exists()) {//检测目录是否存在
            dir.mkdir();//创建当前的目录
        }
        //获取到这个文件名称，UUID工具来生成一个新的字符串作为文件名
        //例如：avatar01.png
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename=" + originalFilename);
        //获取文件后缀
        String suffix = "";
        int index = originalFilename.lastIndexOf(".");
        suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        //表示在dir这个文件下创建一个filename的文件
        File dest = new File(dir, filename);//此时dest是一个空文件
        //将参数file中数据写入到这个空文件中
        try {
            //将file文件中的数据写入到dest文件
            //transferTo是一个封装的方法,用来将file文件中的数据写入到dest文件
            file.transferTo(dest);
            /**
             * 先捕获FileStateException再捕获IOException是
             * 因为后者包含前者,如果先捕获IOException那么
             * FileStateException就永远不可能会被捕获
             */
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            //这里不用打印e,而是用自己写的FileUploadIOException类并
            // 抛出文件读写异常
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径/upload/test.png
        String avater = "/upload/" + filename;
        userService.changeAvatar(uid, avater, username);
        //返回用户头像的路径给前端页面，将来用于头像展示
        return new JsonResult<>(OK, avater);
    }
}
