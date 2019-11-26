// Edit:    08/11/2018 - TvB
// Created: 08/11/2018 - TvB

#version 150

in vec2 vertex;

void main(void){
	gl_Position = vec4(vertex, 0, 1);
}