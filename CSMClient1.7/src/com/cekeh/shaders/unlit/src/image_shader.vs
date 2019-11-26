// Edit:    08/18/2018 - TvB
// Created: 08/18/2018 - TvB

#version 150

in vec2 vertex;
in vec2 texcoord;

out vec2 Texcoord;

void main(void){
	gl_Position = vec4(vertex, 0, 1);
	
	Texcoord = texcoord;
}