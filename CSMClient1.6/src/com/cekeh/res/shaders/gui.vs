// Last edit: 08/01/2018 - TvB
// Created:   08/01/2018 - TvB
#version 150

in vec2 position;
in vec2 texcoord;

out vec2 p_texcoord;

void main(void){
	gl_Position = vec4(position, 0, 1);
	
	p_texcoord = texcoord;
}