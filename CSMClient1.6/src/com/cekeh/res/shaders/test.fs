// Last edit: 07/20/2018 - TvB
// Created:   06/11/2018 - TvB
#version 150

in vec2 pass_texcoord;
in float height;
in vec3 pass_normal;
in vec3 light_dir;

out vec4 out_color;

uniform sampler2D texture0;
uniform sampler2D texture1;

void main(void){
	out_color = vec4(texture(texture0, pass_texcoord));
		
	vec3 normalmap0 = vec3(texture(texture1, pass_texcoord));
		
	float diff = max(dot(pass_normal * normalmap0, light_dir), 0.0);
	out_color.rgb *= diff;
	
	//out_color.rgb *= ((light_dir * diff) + 1) / 2;
}