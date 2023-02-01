#version 460 core

in vec3 position;
in vec2 textureCoord;

out vec2 fragTextureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(){
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
	fragTextureCoords = textureCoord / 2.5;
}
