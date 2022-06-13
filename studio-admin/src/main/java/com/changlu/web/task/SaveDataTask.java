package com.changlu.web.task;

import com.changlu.common.utils.ZipUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
@Data
public class SaveDataTask {

	//备份sql
    @Value("${backup.sql.dataBackupPath}")
	private String dataBackupPath;//备份路径
    @Value("${backup.sql.host}")
	private String host;//数据库地址
    @Value("${backup.sql.port}")
	private Integer port = 3306;//数据库端口
    @Value("${backup.sql.username}")
	private String username = "root";//数据库用户名
    @Value("${backup.sql.password}")
	private String password = "123456";//数据库密码
    @Value("${backup.sql.dataBaseName}")
	private String dataBaseName = "zhifeng";//数据库名称
    @Value("${backup.sql.filename}")
	private String sqlFileName = "studio";//数据库名称
    //备份的数据
    @Value("${backup.static.folder}")
    private String folder;
    @Value("${backup.static.zipFile}")
    private String zipFile;

//    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromUser;
    @Value("${spring.mail.tomaillist}")
    private String[] toEmailList;

    //1、备份SQL(暂时使用java执行不了docker命令)
    public String dataBaseBackupWork(){
        try {
            return dataBaseBackup(host, port, username, password, dataBaseName, sqlFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public synchronized String dataBaseBackup(String host, Integer port, String username, String password, String dataBaseName, String sqlname) throws Exception {
    	File file = new File(dataBackupPath);
        if (!file.exists()) {
            file.mkdir();
        }
        File dataFile = new File(file + File.separator + sqlname + ".sql");
//        if (dataFile.exists()) {
//            dataFile.delete();
//        }
        String os = System.getProperty("os.name");
        String s = null;
        //===暂时只让windows去执行备份,linux有问题===
        //拼接cmd命令
        //windows：cmd /c mysqldump -hlocalhost -P3306 -uroot -p123456 db > E:/back.sql
        //linux(容器内)：docker exec -it mysql mysqldump -hlocalhost -P3306 -uroot -p123456 db > E:/back.sql
        //Windows操作系统
        if (os != null && os.toLowerCase().startsWith("windows")) {
            s = String.format("cmd /c mysqldump -h%s -P%s -u%s -p%s %s > %s",host, port, username, password, dataBaseName, dataFile.getAbsolutePath());
        }else if (os != null && os.toLowerCase().startsWith("linux")) {  //linux命令
            s = String.format("docker exec -i mysql mysqldump -u%s -p%s %s > %s", username, password, dataBaseName, dataFile.getAbsolutePath());
        }
        log.info("执行的备份命令为：" + s.toString());
        Process exec = Runtime.getRuntime().exec(s.toString());
        if (exec.waitFor() == 0){
            log.info("数据库备份成功,备份路径为：{}",dataFile);
        }
        return dataFile.getAbsolutePath();//备份路径
    }

    //2、打包数据备份任务
    public String dataZipBackupWork(){
        try {
            return ZipUtils.packet(Paths.get(folder), Paths.get(zipFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //发送邮件任务
    private void sendMail(String backUpSqlPath, String backupZipPath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送者
            helper.setFrom(fromUser);
            //收件人
            helper.setTo(toEmailList);
            //抄送人
            helper.setCc(fromUser);
            //邮件主题
            helper.setSubject("长路工作室数据备份，时间：" + new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date()));
            //邮件内容
            helper.setText("长路工作室数据备份：sql、网站数据，请注意及时备份噢。");
            helper.addAttachment("zhifeng.sql", new File(backUpSqlPath));
            //由于qq最大只能有55MB，所以暂时不通过发送邮件
//            helper.addAttachment("static.zip", new File(backupZipPath));
            //文件内容
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
