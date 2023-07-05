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
			String str = "2023�w�g�L�@�b�F�I SHITTTTTT�I�I�I�I�I";
			fos.write(str.getBytes());
			fos.close();
			System.out.println("�ɮ׼g�J����");
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
			String str = "2023�w�g�L�@�b�F�I SHITTTTTT�I�I�I�I�I";
			fos.write(str.getBytes());
			System.out.println("�ɮ׼g�J����");
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
		try (FileOutputStream fos = new FileOutputStream("D:\\Java\\0705.txt")) { // \����r�� �ݭn���
			String str = "2023�w�g�L�@�b�F�I SHITTTTTT�I�I�I�I�I";
			fos.write(str.getBytes());
			System.out.println("�ɮ׼g�J����");
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
			String str = "�֭n�h�饻�F~\n";
			fos.write(str.getBytes());
			System.out.println("�ɮ׼g�J����");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �B�z����ýX�����D
	@Test
	public void fileOutputStreamTest5() {
		try (FileOutputStream fos = new FileOutputStream("0705.txt", true);
				// ��X �j���X���w�]�s�X
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
			String str = "�֭n�h�饻�F~\n";
			osw.append(str);
			System.out.println("�ɮ׼g�J����");
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
				fis.read(buffer); // �qfisŪ��512bytes�A�æs�J��buffer�줸�}�C��
				fos.write(buffer); // �Nbuffer�}�C�����줸�g�J��fos
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
			String str = "����7/5��";
			bos.write(str.getBytes());
			bos.flush();
			System.out.println("�ɮ׼g�J����");
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
				bis.read(buffer); // �qbis�w�İ�Ū��512bytes�A�æs�J��buffer�줸�}�C��
				bos.write(buffer); // �Nbuffer�}�C�����줸�g�J��bos
				bos.flush(); // �e�X�ܥ~��
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
