package com.springai;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springai.util.SSLUtils;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		   try {
	            SSLUtils.disableSslVerification(); // Disable SSL validation
	        } catch (NoSuchAlgorithmException | KeyManagementException e) {
	            e.printStackTrace();
	        }
		
		SpringApplication.run(Application.class, args);
	}

}
