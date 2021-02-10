package com.controller;

import com.controller.util.OsNameUtil;
import com.controller.util.ServerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Date;

@Controller
@RequestMapping("/demo")
public class JvmTomcatController {

    @RequestMapping("/hello")
    public ModelAndView handle01(){
        Date date=new Date();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping("/testJvm")
    public ModelAndView testJvm(){
        System.out.println("#############this is test jvm and tomcat################");
        System.out.println("查看当前请求的进程");
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        System.out.println(name);
        String processId = name.substring(0, name.indexOf("@"));
        System.out.println("查看当前请求的进程的系统路径");

        String classpath = runtime.getClassPath();
        String librarypath = runtime.getLibraryPath();
        String bootClassPath = runtime.getBootClassPath();

        Date date=new Date();
        System.out.println("Current Process ID: " + processId);
        System.out.println("Current librarypath: " + librarypath);
        System.out.println("Current bootClassPath: " + bootClassPath);
        System.out.println("Current classpath: " + classpath);
        System.out.println("user.dir : "+System.getProperty("user.dir"));

        System.out.println("tomcat的主目录"+System.getProperty("catalina.base"));

        System.out.println("当前容器环境是tomcat");
        System.out.println("tomcat的主目录"+ServerUtil.getServerId());
        //linux下去通过shell处理
        String processPath = System.getProperty("user.dir");
        if(OsNameUtil.getCurrentOS() == 2){
             processPath = getProcessPath(processId);
        }
        System.out.println("tomcat的主目录catalina.home"+System.getProperty("catalina.home"));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("date",System.getProperty("catalina.home")+"/bin");
        modelAndView.setViewName("hello");
        return modelAndView;
    }


    public String getProcessPath(String pid){
        String[] cmd = new String[]{"/bin/sh", "-c", "ls -al /proc/"+pid+"/cmdline"};
        System.out.println("执行命令："+cmd.toString());
        String run = null;
        try {
            run = run(cmd);
            System.out.println("得到进程信息："+run);
        } catch (IOException e) {
            System.out.println("获取进程路径信息异常");
        }
        return run;
    }




    public static String run(String[] shell) throws IOException {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();;
        Process process = null;
        try{
            if(shell != null && shell.length != 0){
                process = Runtime.getRuntime().exec(shell);
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String tmp = null;
                while((tmp = reader.readLine())!= null){
                    sb.append(tmp).append("\n");
                }
            }
        }catch (Exception e){
            System.out.println("executeLinuxCmd =" + shell[2] +", e=" + e);
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
            if(process != null) {
                process.destroy();
            }
        }

        return sb.toString();
    }

}
