package kr.co.goms.web.oss;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class GomsInit {

    /**
     * 서버 기동시 동작
     */
    @PostConstruct
    public void init(){
    	
    	System.out.println("*********************************************************************");
    	System.out.println("[AMS 서버] 서버 기동 시작 및 기본 세팅 시작...");
    	
    	/* 필요하면 config/ams.properties 생성하여 처리할 수 있습니다.
        URL examURL = getClass().getClassLoader().getResource("config/ams.properties");
        String examFilePath = examURL.getFile().toString();

        // 인스턴스 생성
        System.out.println("[EXAM 서버] exam.properties 위치 : " + examFilePath);
        
        Properties prop = new Properties();
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(examFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			prop.load(new java.io.BufferedInputStream(fis));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        System.out.println("[EXAM 서버] exam.properties 내용 : " + prop.toString());
        //System.out.println("exam.properties 내용 : " + prop.get("exam.iv"));
		*/
    	
    	//EnumResponseType
    	
    	System.out.println("[AMS 서버] 서버 기동 초기화 완료...");
    	System.out.println("*********************************************************************");
		
    }
}
