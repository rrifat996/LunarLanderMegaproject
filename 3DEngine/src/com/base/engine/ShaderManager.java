package com.base.engine;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

public class ShaderManager {
	private final int programId;
	private int vertexShaderID, fragmentShaderID;
	
	private final Map<String, Integer> uniforms;
	
	public ShaderManager() throws Exception{
		programId = GL20.glCreateProgram();
		if(programId == 0)
			throw new Exception("coudl not create shader");
		uniforms = new HashMap<>();
	}
	public void createUniform(String uniformName) throws Exception{
		int uniformLocation = GL20.glGetUniformLocation(programId, uniformName);
		if (uniformLocation < 0)
			throw new Exception("could not find uniform" + uniformName);
		uniforms.put(uniformName, uniformLocation);
	}
	public void setUniform(String uniformname, Matrix4f value) {
		try(MemoryStack stack = MemoryStack.stackPush()){
			GL20.glUniformMatrix4fv(uniforms.get(uniformname), 
					false, value.get(stack.mallocFloat(16)));
		}
	}
	public void setUniform(String uniformname, Vector4f value) {
		GL20.glUniform4f(uniforms.get(uniformname), value.x, value.y, value.z, value.w);
	}
	public void setUniform(String uniformname, Vector3f value) {
		GL20.glUniform3f(uniforms.get(uniformname), value.x, value.y, value.z);
	}
	public void setUniform(String uniformname, int value) {
		GL20.glUniform1i(uniforms.get(uniformname), value);
	}
	public void setUniform(String uniformname, float value) {
		GL20.glUniform1f(uniforms.get(uniformname), value);
	}
	public void setUniform(String uniformname, boolean value) {
		float res =0;
		if (value)
			res = 1;
		GL20.glUniform1f(fragmentShaderID, res);
	}
	public void createVertexShader(String shaderCode) throws Exception{
		vertexShaderID = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
	}
	public void createFragmentShader(String shaderCode) throws Exception{
		fragmentShaderID = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
	}
	public int createShader(String shaderCode, int shaderType) throws Exception{
		int shaderID = GL20.glCreateShader(shaderType);
		if(shaderID == 0)
			throw new Exception("error creating shader");
		
		GL20.glShaderSource(shaderID, shaderCode);
		GL20.glCompileShader(shaderID);
	
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0)
			throw new Exception("error compiling shader code type: " + shaderType
					+ " info" + GL20.glGetShaderInfoLog(shaderID, 1024)
					);
		GL20.glAttachShader(programId, shaderID);
		return shaderID;
	}
	public void link() throws Exception{
		GL20.glLinkProgram(programId);
		if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0)
			throw new Exception("error linking shader code"
					+ " info" + GL20.glGetProgramInfoLog(programId, 1024)
					);
		if(vertexShaderID != 0)
			GL20.glDetachShader(programId, vertexShaderID);
		if(fragmentShaderID != 0)
			GL20.glDetachShader(programId, fragmentShaderID);
		GL20.glValidateProgram(programId);
		if(GL20.glGetProgrami(programId, GL20.GL_VALIDATE_STATUS) == 0)
			throw new Exception("unable to validate prog" + GL20.glGetProgramInfoLog(programId, 1024));
	}
	public void bind() {
		GL20.glUseProgram(programId);
	}
	public void unbind() {
		GL20.glUseProgram(0);
	}
	public void cleanup() {
		unbind();
		if(programId != 0)
			GL20.glDeleteProgram(programId);
	}
}
