// Last edit: 08/01/2018 - TvB
// Created:   08/01/2018 - TvB
#version 150

in vec2 p_texcoord;

out vec4 out_color;

uniform sampler2D texture0;

void main(void){
	out_color = texture(texture0, p_texcoord);
	
	//out_color.r = out_color.a;
}