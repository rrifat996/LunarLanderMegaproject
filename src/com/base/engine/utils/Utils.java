package com.base.engine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

import javax.crypto.spec.DHPublicKeySpec;

import org.lwjgl.system.MemoryUtil;

public class Utils {
	public static FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
		buffer.put(data).flip();
		return buffer;
	}
	public static IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
		buffer.put(data).flip();
		return buffer;
	}
	public static String loadResource(String filename) throws Exception{
		String result;
		try(InputStream in = Utils.class.getResourceAsStream(filename);
			Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
				result = scanner.useDelimiter("\\A").next();
			}
		return result;    
	}
	public static List<String> readAllLines(String filename){
		File directory = new File("./");
		  System.out.println(directory.getAbsolutePath());
		List<String> list = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while(line != null) {
				list.add(line);
				line = reader.readLine();
;			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
