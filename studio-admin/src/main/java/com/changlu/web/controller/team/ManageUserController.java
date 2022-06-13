package com.changlu.web.controller.team;

import com.changlu.common.domain.LoginBody;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.exception.ServiceException;
import com.changlu.common.utils.ExcelUtil;
import com.changlu.service.LoginService;
import com.changlu.service.ZfManageUserService;
import com.changlu.web.controller.BaseController;
import com.changlu.vo.manage.MUserVo;
import com.changlu.web.task.GenerateTeamUsersTask;
import com.changlu.web.task.SaveDataTask;
import com.changlu.common.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName ManagerUserController
 * @Author ChangLu
 * @Date 4/5/2022 5:53 PM
 * @Description 管理用户控制器
 */
@RestController
@RequestMapping("/api/team/member")
public class ManageUserController extends BaseController {

    @Autowired
    private ZfManageUserService manageUserService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private GenerateTeamUsersTask generateTeamUsersTask;

    /**
     * 查询用户成员列表（定制）
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('team:member:list')")
    public TableDataInfo list(MUserVo userVo)
    {
        startPage();
        List<MUserVo> list = manageUserService.selectSysUserList(userVo);
        return getDataTable(list);
    }

    /**
     * 获取User详细信息
     */
    @GetMapping(value = "/{userId}")
    @PreAuthorize("@ss.hasPerm('team:member:query')")
    public ResponseResult getInfo(@PathVariable("userId") Long userId)
    {
        return ResponseResult.success(manageUserService.selectSysUserByUserId(userId));
    }

    /**
     * 新增用户
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('team:member:add')")
    public ResponseResult addUser(@RequestBody LoginBody loginBody){
        return ResponseResult.success(loginService.registerUser(loginBody));
    }


    /**
     * 修改用户信息
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('team:member:edit')")
    public ResponseResult edit(@RequestBody MUserVo userVo)
    {
        ResponseResult responseResult = ResponseResult.toResponse(manageUserService.updateSysUser(userVo));
        //执行异步任务
//        generateTeamUsersTask.doGenerateTask();
        return responseResult;
    }

    /**
     *  注销账号
     */
    @DeleteMapping("/cancelled/{userId}")
    @PreAuthorize("@ss.hasPerm('team:member:cancelled')")
    public ResponseResult cancelled(@PathVariable("userId") Long userId) {
        return ResponseResult.toResponse(manageUserService.cancelledSysUser(userId));
    }

    /**
     *  激活账号
     */
    @PutMapping("/active/{userId}")
    @PreAuthorize("@ss.hasPerm('team:member:active')")
    public ResponseResult active(@PathVariable("userId") Long userId) {
        return ResponseResult.toResponse(manageUserService.activeSysUser(userId));
    }

    /**
     *  转让负责人
     */
    @PutMapping("/transfer/{userId}")
    @PreAuthorize("@ss.hasPerm('team:member:transfer')")
    public ResponseResult transfer(@PathVariable("userId") Long targetUserId){
        return ResponseResult.toResponse(manageUserService.transfer(targetUserId));
    }

    /**
     *  重置密码
     */
    @PutMapping("/resetpwd/{userIds}")
    @PreAuthorize("@ss.hasPerm('team:member:resetpwd')")
    public ResponseResult resetPwd(@PathVariable(name = "userIds") Long[] userIds){
        return ResponseResult.toResponse(manageUserService.resetPwd(userIds));
    }

    /**
     *  删除账号
     */
    @DeleteMapping("/{userId}")
    @PreAuthorize("@ss.hasPerm('team:member:remove')")
    public ResponseResult delete(@PathVariable("userId") Long targetUserId){
        return ResponseResult.toResponse(manageUserService.delete(targetUserId));
    }

    /**
     * 导出用户记录
     */
    @PostMapping("/export")
    @PreAuthorize("@ss.hasPerm('team:member:export')")
    public void export(MUserVo userVo, HttpServletResponse response){
        List<MUserVo> list = manageUserService.selectSysUserList(userVo);
        ExcelUtil<MUserVo> util = new ExcelUtil<>(MUserVo.class);
        util.exportExcel(response, list, "用户信息");
    }

    @Autowired
    private SaveDataTask saveDataTask;

    /**
     * 导出网站数据：①静态资源图片。②数据库sql。
     */
    @PostMapping("/backup")
    @PreAuthorize("@ss.hasPerm('team:member:backup')")
    public void backup(HttpServletResponse response){
        saveDataTask.dataBaseBackupWork();//数据库备份
        saveDataTask.dataZipBackupWork();//资源文件备份
        String zipFilePath = saveDataTask.getZipFile();
        String fileName = zipFilePath.substring(zipFilePath.lastIndexOf("/") +1);
        File file = new File(zipFilePath);
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            OutputStream outputStream = response.getOutputStream();){
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("content-type:octet-stream");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) { /** 将流中内容写出去 .*/
                outputStream.write(buffer, 0, len);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("导出异常");
        }
    }


}
