// Last edit: 07/31/2018 - TvB
// Created:   07/29/2018 - TvB
#version 150

in vec3 frag_pos;
in vec3 normal_dir;

out vec4 out_color;

uniform vec4 main_color;
uniform vec3 light_position;
uniform vec3 camera_position;

void main(void){
	vec4 light_color = vec4(1, 1, 1, 1);

	vec3 ambient = light_color.rgb * 0.1;
	
	vec3 norm = normalize(normal_dir);
	vec3 light_dir = normalize(light_position - frag_pos);
	float diff = max(dot(norm, light_dir), 0.0);
	vec3 diffuse = diff * light_color.rgb;
	
	vec3 view_dir = normalize(-camera_position - frag_pos);
	vec3 reflect_dir = reflect(-light_dir, norm);
	float spec = pow(max(dot(view_dir, reflect_dir), 0.0), 32);
	vec3 specular = 0.5 * spec * light_color.rgb;

	out_color = main_color;
	out_color.rgb *= ambient + diffuse + specular;
	
}