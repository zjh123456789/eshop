package com.twinking.eshop.modules.user.service;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.common.utils.excel.ImportExcelUtils;
import com.twinking.eshop.modules.sys.entity.Role;
import com.twinking.eshop.modules.user.dao.UserDao;
import com.twinking.eshop.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * @Description:  用户管理业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/10 09 26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends AbstractService<UserDao, User> {

    @Autowired
    private UserDao userDao;

    /**
     * 批量导入默认密码
     */
    private static final String DEFAULT_PASSWORD = "123456";

    /**
     * 分页查询管理员
     * @param user
     * @param request
     * @param response
     * @return
     */
    public Page<User> findAdminPage(User user, HttpServletRequest request, HttpServletResponse response){
        Page<User> page = this.findPage(new Page<User>(request, response), user);
        for (int i = 0; i < page.getList().size(); i++) {
            page.getList().get(i).setSex(getSex(page.getList().get(i).getSex()));
        }
        return page;
    }

    /**
     * 分页查询会员
     * @param page
     * @param user
     * @return
     */
    public Page<User> findCustomerPage(Page<User> page, User user) {
        user.setPage(page);
        page.setList(userDao.findAllCustomer(user));
        for (int i = 0; i < page.getList().size(); i++) {
            page.getList().get(i).setSex(getSex(page.getList().get(i).getSex()));
        }
        return page;
    }

    /**
     * 保存或新增用户
     * @param user 用户参数
     * @return
     * @throws Exception 类型转换异常
     */
    public String save0rUpdateUser(User user, String roleId) throws Exception{
        if(!user.getIsNewRecord()){
            if(Constants.USER_SUPER_ADMIN_ID.equals(user.getId())){
                return "不允许操作超级管理员！";
            }
            //从数据库取出记录的值
            User t = this.get(user.getId());
            //将编辑表单中的非NULL值覆盖数据库记录中的值
            MyBeanUtils.copyBeanNotNull2Bean(user, t);
            //编辑表单保存
            this.save(t);
        }else{
            User u = this.findUserByUsername(user);
            if(u != null){
                return "操作失败，用户名已存在";
            }
            if(roleId != null){
                user.setRole(new Role(Constants.ROLE_CUSTOMER_ROLE_ID));
            }
            //新增表单保存
            this.save(user);
        }
        return "保存成功";
    }

    /**
     * 删除管理员
     * @param user 用户id
     * @return
     */
    public String deleteAdmin(User user){
        if(Constants.USER_SUPER_ADMIN_ID.equals(user.getId())){
            return "不允许删除超级管理员！";
        }
        this.delete(user);
        return "删除管理员成功";
    }

    /**
     * 批量删除管理员
     * @param ids 删除数据的id
     * @return  成功条数
     */
    public int deleteAll(String ids){
        String idArray[] =ids.split(",");
        int effect = 0;
        for(String id : idArray){
            if(Constants.USER_SUPER_ADMIN_ID.equals(id)) {
                continue;
            }
            effect++;
            this.delete(new User(id));
        }
        return effect;
    }

    /**
     * 批量导入管理员数据
     * @param file
     * @return
     */
    public String importAdmin(MultipartFile file){
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcelUtils ei = new ImportExcelUtils(file, 1, 0);
            List<User> list = ei.getDataList(User.class);
            for (User user : list){
                try{
                    user.setRole(new Role("0"));
                    user.setPassword(DEFAULT_PASSWORD);
                    this.save(user);
                    successNum++;
                }catch(ConstraintViolationException ex){
                    failureNum++;
                }catch (Exception ex) {
                    failureNum++;
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条管理员记录。");
            }
            return "已成功导入 " + successNum + " 条管理员记录" + failureMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return "批量导入管理员失败！失败信息："+e.getMessage();
        }
    }

    /**
     * 根据用户名查询
     * @param user
     * @return
     */
    public User findUserByUsername(User user) {
        return userDao.getByUserName(user);
    }

    /**
     * 根据sn查询
     * @param user
     * @return
     */
    public User findUserBySN(User user) {
        return userDao.getUserBySN(user);
    }


    /**
     * 获取性别
     * @param key
     * @return
     */
    private String getSex(String key){
        return Constants.USER_MAN_VALUE.equals(key) ? "男" : "女";
    }

}
