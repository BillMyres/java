// Last edit: 07/31/2018 - TvB
// Created:   07/29/2018 - TvB
#version 150

in vec3 position;
in vec3 normal;

out vec3 frag_pos;
out vec3 normal_dir;

uniform mat4 transformation_matrix;
uniform mat4 view_matrix;
uniform mat4 projection_matrix;

void main(void){
	gl_Position = vec4(position, 1);
	gl_Position *= transformation_matrix;
	gl_Position *= view_matrix;
	gl_Position *= projection_matrix;
	
	frag_pos = vec3(transformation_matrix * vec4(position, 1.0));
	normal_dir =  mat3(transpose(inverse(transformation_matrix))) * normal;
}