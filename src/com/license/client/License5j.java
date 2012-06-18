package com.license.client;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Scanner;

import com.license.License;
import com.license.util.KeyUtil;

public class License5j {

	public static void main(String[] args) throws Exception {
		System.out.println("####################################################");
		System.out.println("###              License5j                       ###");
		System.out.println("####################################################");
		Scanner input = new Scanner(System.in);
		System.out.print("请输入用户名:");
		String user = input.nextLine();
		System.out.print("请输入IP地区址:");
		String ip = input.nextLine();
		System.out.print("请输入MAC地区址:");
		String mac = input.nextLine();
		System.out.print("请输入过期时间(yyyy-MM-dd):");
		String Expiration = input.nextLine();
		System.out.print("是否生成立License(y/n)?");
		String gen = input.nextLine();
		if(gen.equalsIgnoreCase("y")) {
			KeyUtil.generateKey(user + Expiration);
			String signText = KeyUtil.encrypt(user + Expiration);
			Properties props = new Properties();
			props.setProperty(License.KEY_LICENSOR, user);
			props.setProperty(License.KEY_IP, ip);
			props.setProperty(License.KEY_MAC, mac);
			props.setProperty(License.KEY_EXPIRATION, Expiration);
			props.setProperty(License.KEY_SIGNATURE, signText);
			props.store(new FileWriter(new File(License.LICENSE_PATH)), "License5j");
		} else {
			System.exit(-1);
		}
		System.out.println("生成成功...");
	}

}
