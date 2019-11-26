// Edit:    08/18/2018 - TvB
// Created: 08/18/2018 - TvB

#version 150

in vec2 Texcoord;

out vec4 color;

uniform sampler2D texture0;

void main(void){
	color = texture(texture0, Texcoord);
	
	//color.rg = Texcoord;
	color.a = 1;
}