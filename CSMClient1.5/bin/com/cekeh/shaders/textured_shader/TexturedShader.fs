// Last edit: 05/26/2018 - TvB
// Created:   05/20/2018 - TvB
#version 150

in vec2 pass_texcoord;
in vec4 pass_color;

uniform sampler2D texture0;

out vec4 out_color;

void main(void){
	// Set the color
	out_color = vec4(pass_texcoord, 1, 1);
	
	out_color = texture(texture0, pass_texcoord);
	
	//out_color.rgb *= pass_color.rgb;
}