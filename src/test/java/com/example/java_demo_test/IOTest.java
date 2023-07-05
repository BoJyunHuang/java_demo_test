package com.example.java_demo_test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class IOTest {

	@Test
	public void fileOutputStreamTest1() {
		try {
			FileOutputStream fos = new FileOutputStream("0705.txt");
			String str = "2023已經過一半了！ SHITTTTTT！！！！！";
			fos.write(str.getBytes());
			fos.close();
			System.out.println("檔案寫入完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// try-with-resource
	@Test
	public void fileOutputStreamTest2() {
		try (FileOutputStream fos = new FileOutputStream("0705.txt")) {
			String str = "2023已經過一半了！ SHITTTTTT！！！！！";
			fos.write(str.getBytes());
			System.out.println("檔案寫入完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void fileOutputStreamTest3() {
		try (FileOutputStream fos = new FileOutputStream("D:\\Java\\0705.txt")) { // \跳脫字符 需要兩條
			String str = "2023已經過一半了！ SHITTTTTT！！！！！";
			fos.write(str.getBytes());
			System.out.println("檔案寫入完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void fileOutputStreamTest4() {
		try (FileOutputStream fos = new FileOutputStream("0705.txt", true)) {
			String str = "快要去日本了~\n";
			fos.write(str.getBytes());
			System.out.println("檔案寫入完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 處理中文亂碼的問題
	@Test
	public void fileOutputStreamTest5() {
		try (FileOutputStream fos = new FileOutputStream("0705.txt", true);
				// 轉碼 強制輸出成預設編碼
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
			String str = "快要去日本了~\n";
			osw.append(str);
			System.out.println("檔案寫入完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void fileInputStreamTest() {
		try (FileInputStream fis = new FileInputStream("D:\\gura.png");
				FileOutputStream fos = new FileOutputStream("new_gura1.png")) {
			byte[] buffer = new byte[512];
			while (fis.available() > 0) {
				fis.read(buffer); // 從fis讀取512bytes，並存入到buffer位元陣列中
				fos.write(buffer); // 將buffer陣列中的位元寫入到fos
			}
			System.out.println(fis.available());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void bufferOutputStreamTest() {
		try (FileOutputStream fos = new FileOutputStream("D:\\Java\\0705.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			String str = "今日7/5號";
			bos.write(str.getBytes());
			bos.flush();
			System.out.println("檔案寫入完成");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void BufferInputStreamTest() {
		try (FileInputStream fis = new FileInputStream("D:\\gura.png");
				BufferedInputStream bis = new BufferedInputStream(fis);
				FileOutputStream fos = new FileOutputStream("new_gura.png");
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			byte[] buffer = new byte[512];
			while (bis.available() > 0) {
				bis.read(buffer); // 從bis緩衝區讀取512bytes，並存入到buffer位元陣列中
				bos.write(buffer); // 將buffer陣列中的位元寫入到bos
				bos.flush(); // 送出至外部
			}
			System.out.println(bis.read());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
