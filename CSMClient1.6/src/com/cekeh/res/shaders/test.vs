// Last edit: 07/15/2018 - TvB
// Created:   06/11/2018 - TvB
#version 150

in vec3 position;
in vec3 normal;
in vec2 texcoord;

out vec2 pass_texcoord;
out float height;
out vec3 pass_normal;
out vec3 light_dir;

uniform mat4 transformation_matrix;
uniform mat4 view_matrix;
uniform mat4 projection_matrix;

uniform vec3 light_position;

void main(void){
	gl_Position = vec4(position, 1);
	gl_Position *= transformation_matrix;
	gl_Position *= view_matrix;
	gl_Position *= projection_matrix;
	
	pass_texcoord = texcoord;
	height = position.y;
	
	pass_normal = normalize(vec3(transformation_matrix * vec4(normal, 1.0)));
	pass_normal = normal;
	vec3 frag_pos = vec3(transpose(transformation_matrix) * vec4(position, 1.0));
	
	//vec3 pos = transformation_matrix
	
	light_dir = normalize(light_position - frag_pos);
}